package com.kaykymatos.todotask.repositories;

import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByNameContainingIgnoreCase(String name);
    Optional<UserEntity> findByEmailContainingIgnoreCase(String email);

}
