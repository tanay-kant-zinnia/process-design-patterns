package com.example.workflow;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.RequiredHistoryLevel;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;



@RunWith(SpringRunner.class)
@SpringBootTest
@Deployment(resources = "process.bpmn")
@RequiredHistoryLevel(ProcessEngineConfiguration.HISTORY_ACTIVITY)
public class SampleProcessTest {


@Autowired
private ProcessEngine processEngine;


   @MockBean
    private TestDelegate testDelegate;
    @MockBean DelegateExecution delegateExecution;

@Autowired
private DataSource dataSource;

@Before
public void setup(){
    
}
   

  @Test
  public void ruleUsageExample() throws Exception {

   // doNothing().when(testDelegate).execute(delegateExecution);
//this is how you skip a delegate
   doAnswer(invocation -> {
    System.out.println(" ************************test11111");
    return null;
}).when(testDelegate).execute(any(DelegateExecution.class));
    RuntimeService runtimeService = processEngine.getRuntimeService();
    runtimeService.startProcessInstanceByKey("process-design-patterns-process");

    TaskService taskService = processEngine.getTaskService();
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("Hello", task.getName());
    taskService.complete(task.getId());
    
    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
  }

   
}


