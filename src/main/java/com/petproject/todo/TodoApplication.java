package com.petproject.todo;


import com.petproject.todo.service.serv.SingleResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.petproject.todo","com.speedment.jpastreamer"})
public class TodoApplication implements SingleResult {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
