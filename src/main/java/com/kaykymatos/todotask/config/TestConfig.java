package com.kaykymatos.todotask.config;

import com.kaykymatos.todotask.entities.TaskEntity;
import com.kaykymatos.todotask.entities.UserEntity;
import com.kaykymatos.todotask.entities.enums.TaskStatus;
import com.kaykymatos.todotask.repositories.TodoTasksRepository;
import com.kaykymatos.todotask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner, WebMvcConfigurer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoTasksRepository todoTasksRepository;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type", "Accept")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers")
                .maxAge(3600);
    }
    @Override
    public void run(String... args) throws Exception {
        UserEntity u1 = new UserEntity(null, "Maria Brown", "maria@gmail.com");
        UserEntity u2 = new UserEntity(null, "Alex Green", "alex@gmail.com");
        userRepository.saveAll(Arrays.asList(u1, u2));

        TaskEntity t1 = new TaskEntity(null,"Comprar produtos de limpeza","", Instant.parse("2019-06-20T19:53:07Z"), Instant.parse("2019-06-20T19:53:07Z"), u1, TaskStatus.CANCELED);
        TaskEntity t2 = new TaskEntity(null, "Comprar frutas","",Instant.parse("2019-06-21T19:53:07Z"), Instant.parse("2019-06-21T19:53:07Z"), u2, TaskStatus.DONE);
        TaskEntity t3 = new TaskEntity(null, "Regar as flores","",Instant.parse("2019-06-22T19:53:07Z"), Instant.parse("2019-06-22T19:53:07Z"), u1, TaskStatus.IN_PROCESS);
        todoTasksRepository.saveAll(Arrays.asList(t1, t2, t3));

    }
}
