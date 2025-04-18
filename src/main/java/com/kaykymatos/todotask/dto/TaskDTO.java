package com.kaykymatos.todotask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaykymatos.todotask.entities.UserEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TaskDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Long clientId;
    private Integer taskStatus;

    public TaskDTO() {

    }

    public TaskDTO(Long id, String name, String description, Long clientId, Integer taskStatus) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.clientId = clientId;
        this.taskStatus = taskStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
