package com.petproject.todo.controller;

import com.petproject.todo.service.dto.request.TaskDtoRequest;
import com.petproject.todo.service.dto.response.TaskDtoResponse;
import com.petproject.todo.service.serv.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@Validated
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/task_lists/{id}/tasks")
    public List<TaskDtoResponse> getAll(@PathVariable Long id, HttpServletRequest request) {
        return service.getAllByTaskListId(request.getRemoteUser(), id);
    }

    @GetMapping("/tasks/{id}")
    public TaskDtoResponse getById(@PathVariable Long id, HttpServletRequest request) {
        return service.getById(request.getRemoteUser(), id);
    }

    @PostMapping("/task_lists/{id}/tasks")
    public TaskDtoResponse create(@PathVariable Long id, @Valid @RequestBody TaskDtoRequest dtoRequest, HttpServletRequest request) {
        return service.create(request.getRemoteUser(), id, dtoRequest);
    }

    @PutMapping("/tasks/{id}")
    public TaskDtoResponse update(@PathVariable Long id, @Valid @RequestBody TaskDtoRequest dtoRequest, HttpServletRequest request) {
        return service.update(request.getRemoteUser(), id, dtoRequest);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id, HttpServletRequest request) {
        service.deleteById(request.getRemoteUser(), id);
    }
}
