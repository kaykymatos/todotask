package com.kaykymatos.todotask.repositories;

import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByNameContainingIgnoreCase(String name);
    Optional<UserEntity> findByEmailContainingIgnoreCase(String email);
    @Query("SELECT u FROM UserEntity u " +
            "WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<UserEntity> searchClients(String search);
}
