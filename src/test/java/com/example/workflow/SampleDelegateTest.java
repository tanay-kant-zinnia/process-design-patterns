package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

class SampleDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        execution.setVariable("result", "ok");
    }
}

public class SampleDelegateTest {
    @Test
    void testExecuteSetsResultVariable() throws Exception {
        DelegateExecution execution = Mockito.mock(DelegateExecution.class);
        SampleDelegate delegate = new SampleDelegate();
        delegate.execute(execution);
        Mockito.verify(execution).setVariable("result", "ok");
    }
}
