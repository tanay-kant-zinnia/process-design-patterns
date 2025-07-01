package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;

import org.camunda.bpm.scenario.ProcessScenario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.variable.Variables;
import static org.mockito.Mockito.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.withVariables;



 @Deployment(resources = "process.bpmn")
public class SampleProcessTest {
    //set history level to activity`
      public ProcessEngine myProcessEngine = ProcessEngineConfiguration
      .createStandaloneInMemProcessEngineConfiguration()
      .setJdbcUrl("jdbc:h2:mem:camunda;DB_CLOSE_DELAY=1000")
      .buildProcessEngine();
      
  private ProcessScenario insuranceApplication = mock(ProcessScenario.class);

  @RegisterExtension
  ProcessEngineExtension extension = ProcessEngineExtension
      .builder()
      .useProcessEngine(myProcessEngine)
      .build(); 

      @BeforeEach
    public void defineHappyScenario() {
        when(insuranceApplication.waitsAtUserTask("say-hello")).thenReturn((task) -> task.complete(withVariables("approved", true)));

    }
    
  @Test
   
    void testHappyPath() {
        
        RuntimeService runtimeService = extension.getProcessEngine().getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process-design-patterns-process");
       assertThat(processInstance).isNotNull();
        assertThat(processInstance).isEnded();
    }
}


