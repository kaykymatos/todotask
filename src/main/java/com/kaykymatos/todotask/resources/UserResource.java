package com.kaykymatos.todotask.resources;

import com.kaykymatos.todotask.dto.UserDTO;
import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import com.kaykymatos.todotask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserDTO user) {
        UserEntity obj = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody UserDTO user) {
        UserEntity obj = userService.update(id, user);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.fidAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<List<UserEntity>> findByName(@PathVariable String name) {
        var task = userService.findByName(name);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) {
        var task = userService.findByEmail(email);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping(value = "/searchClients/{text}")
    public ResponseEntity<List<UserEntity>> searchClients(@PathVariable String text) {
        List<UserEntity> task = userService.searchClients(text);
        return ResponseEntity.ok().body(task);
    }
}
