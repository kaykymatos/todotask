package com.kaykymatos.todotask.resources;

import com.kaykymatos.todotask.dto.TaskDTO;
import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import com.kaykymatos.todotask.entities.enums.TaskStatus;
import com.kaykymatos.todotask.services.TodoTasksService;
import com.kaykymatos.todotask.services.UserService;
import com.kaykymatos.todotask.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {
    @Autowired
    private TodoTasksService taskService;

    @PostMapping
    public ResponseEntity<TaskEntity> create(@RequestBody TaskDTO dto) {

        TaskEntity obj = taskService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskEntity> update(@PathVariable Long id, @RequestBody TaskDTO task) {
        TaskEntity obj = taskService.update(id, task);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> findAll() {
        List<TaskEntity> tasks = taskService.fidAll();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskEntity> findById(@PathVariable Long id) {
        TaskEntity task = taskService.findById(id);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping(value = "/searchTasks/{text}")
    public ResponseEntity<List<TaskEntity>> searchTasks(@PathVariable String text) {
        List<TaskEntity> task = taskService.searchTasks(text);
        return ResponseEntity.ok().body(task);
    }
}
