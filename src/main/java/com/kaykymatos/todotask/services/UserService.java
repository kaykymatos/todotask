package com.kaykymatos.todotask.services;

import com.kaykymatos.todotask.dto.UserDTO;
import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import com.kaykymatos.todotask.entities.enums.TaskStatus;
import com.kaykymatos.todotask.repositories.UserRepository;
import com.kaykymatos.todotask.services.exceptions.DatabaseException;
import com.kaykymatos.todotask.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public List<UserEntity> fidAll() {
        return repository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> user = repository.findById(id);

        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<UserEntity> findByName(String name) {
        List<UserEntity> user = repository.findByNameContainingIgnoreCase(name);

        return user;
    }

    public UserEntity findByEmail(String email) {
        Optional<UserEntity> user = repository.findByEmailContainingIgnoreCase(email);

        return user.orElseThrow(() -> new ResourceNotFoundException(email));
    }

    public UserEntity insert(UserDTO dto) {
        UserEntity user = new UserEntity(null,dto.getName(),dto.getEmail());
        return repository.save(user);
    }

    public void delete(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserEntity update(Long id, UserDTO dto) {
        try {

            UserEntity user = new UserEntity(0L,dto.getName(),dto.getEmail());
            UserEntity entity = repository.getReferenceById(id);
            updateData(entity, user);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    public List<UserEntity> searchClients(String search) {
        return repository.searchClients(search);
    }

    private void updateData(UserEntity entity, UserEntity user) {
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
    }
}
