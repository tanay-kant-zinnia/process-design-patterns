package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.camunda.bpm.engine.TaskService;




 @Deployment(resources = "process.bpmn")
public class SampleProcessTest {


  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();
  

  @Test
  public void ruleUsageExample() {
    
    RuntimeService runtimeService = processEngineRule.getRuntimeService();
    runtimeService.startProcessInstanceByKey("process-design-patterns-process");

    TaskService taskService = processEngineRule.getTaskService();
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("Say hello to\nadmin", task.getName());

    taskService.complete(task.getId());
    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
  }

}


