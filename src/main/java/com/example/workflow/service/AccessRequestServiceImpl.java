package com.example.workflow.service;



import com.example.workflow.domain.AccessRequest;
import com.example.workflow.repository.AccessRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessRequestServiceImpl implements AccessRequestService {

    private  final AccessRequestRepository accessRequestRepository;

    public AccessRequestServiceImpl(AccessRequestRepository accessRequestRepository) {
        this.accessRequestRepository = accessRequestRepository;
    }

    @Override
    public Long create(Long entityId, String username, String comment) {
        AccessRequest accessRequest = new AccessRequest();
        accessRequest.setEntityId(entityId);
        accessRequest.setUsername(username);
        accessRequest.setComment(comment);

       return accessRequestRepository.saveAndFlush(accessRequest).getId();
    }

    @Override
    public void update(Long entityId, String approver, Boolean isApproved) {
        Optional<AccessRequest> accessRequestOptional= accessRequestRepository.findOneByEntityId(entityId);
        if(accessRequestOptional.isPresent()){
            AccessRequest accessRequest = accessRequestOptional.get();
            accessRequest.setApprover(approver);
            accessRequest.setApproved(isApproved);
            accessRequestRepository.saveAndFlush(accessRequest);
        }
    }
}
