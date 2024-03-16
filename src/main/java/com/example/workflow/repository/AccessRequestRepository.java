package com.example.workflow.repository;


import com.example.workflow.domain.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    Optional<AccessRequest> findOneByEntityId(Long entityId);
}
