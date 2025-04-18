package com.kaykymatos.todotask.repositories;

import com.kaykymatos.todotask.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoTasksRepository extends JpaRepository<TaskEntity,Long> {
    List<TaskEntity> findByNameContainingIgnoreCase(String name);
    List<TaskEntity> findByDescriptionContainingIgnoreCase(String description);
    List<TaskEntity> findByClientId(Long clientId);


}
