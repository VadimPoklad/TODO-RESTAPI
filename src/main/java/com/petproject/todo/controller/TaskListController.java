package com.petproject.todo.controller;

import com.petproject.todo.service.dto.request.TaskListDtoRequest;
import com.petproject.todo.service.dto.response.TaskListDtoResponse;
import com.petproject.todo.service.serv.TaskListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task_lists", produces = "application/json")
@Validated
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class TaskListController {
    private final TaskListService service;

    @Autowired
    public TaskListController(TaskListService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TaskListDtoResponse> getAll(HttpServletRequest request) {
        return service.getAllByUsername(request.getRemoteUser());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDtoResponse getById(@PathVariable Long id, HttpServletRequest request) {
        return service.getById(request.getRemoteUser(), id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskListDtoResponse create(@Valid @RequestBody TaskListDtoRequest dtoRequest, HttpServletRequest request) {
        return service.create(request.getRemoteUser(), dtoRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskListDtoResponse update(@PathVariable Long id, @Valid @RequestBody TaskListDtoRequest dtoRequest, HttpServletRequest request) {
        return service.update(request.getRemoteUser(), id, dtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, HttpServletRequest request) {
        service.deleteById(request.getRemoteUser(), id);
    }
}
