package com.kaykymatos.todotask.repositories;

import com.kaykymatos.todotask.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoTasksRepository extends JpaRepository<TaskEntity,Long> {
    @Query("SELECT t FROM TaskEntity t " +
            "JOIN t.client c " +
            "WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<TaskEntity> searchTasks(String search);


}
