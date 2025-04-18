package com.kaykymatos.todotask.services;

import com.kaykymatos.todotask.dto.TaskDTO;
import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import com.kaykymatos.todotask.entities.enums.TaskStatus;
import com.kaykymatos.todotask.repositories.TodoTasksRepository;
import com.kaykymatos.todotask.repositories.UserRepository;
import com.kaykymatos.todotask.services.exceptions.DatabaseException;
import com.kaykymatos.todotask.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoTasksService {

    @Autowired
    private TodoTasksRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<TaskEntity> fidAll() {
        return repository.findAll();
    }

    public TaskEntity findById(Long id) {
        Optional<TaskEntity> task = repository.findById(id);

        return task.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<TaskEntity> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<TaskEntity> findByDescriptionContainingIgnoreCase(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<TaskEntity> findByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    public TaskEntity insert(TaskDTO dto) {
        UserEntity client = userRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getClientId()));
        TaskEntity task = new TaskEntity(
                null,
                dto.getName(),
                dto.getDescription(),
                new Date().toInstant(),
                new Date().toInstant(),
                client, TaskStatus.valueOf(dto.getTaskStatus()));

        return repository.save(task);
    }

    public void delete(Long id) {
        Optional<TaskEntity> task = repository.findById(id);
        if (task.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TaskEntity update(Long id, TaskDTO dto) {

        UserEntity client = userRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getClientId()));
        TaskEntity task = new TaskEntity(
                null,
                dto.getName(),
                dto.getDescription(),
                new Date().toInstant(),
                new Date().toInstant(),
                client, TaskStatus.valueOf(dto.getTaskStatus()));
        try {
            TaskEntity entity = repository.getReferenceById(id);
            updateData(entity, task);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(TaskEntity entity, TaskEntity task) {
        entity.setTaskStatus(task.getTaskStatus());
        entity.setName(task.getName());
        entity.setDescription(task.getDescription());
        entity.setClient(task.getClient());
        entity.setUpdatedDate(new Date().toInstant());
    }
}
