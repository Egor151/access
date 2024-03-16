package com.example.workflow.delegate;


import com.example.workflow.config.ProcessVariableConstants;
import com.example.workflow.service.AccessRequestService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CreateAccessRequest implements JavaDelegate {

    private final AccessRequestService accessRequestService;

    public CreateAccessRequest(AccessRequestService accessRequestService) {
        this.accessRequestService = accessRequestService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception{
        String username = (String) delegateExecution.getVariable(ProcessVariableConstants.USERNAME);
        String comment = (String) delegateExecution.getVariable(ProcessVariableConstants.COMMENT);
        Long entiyId = (Long) delegateExecution.getVariable(ProcessVariableConstants.ENTITY_ID);

        Long id = accessRequestService.create(entiyId, username, comment);

        delegateExecution.setVariable(ProcessVariableConstants.ID, id);
    }
}
