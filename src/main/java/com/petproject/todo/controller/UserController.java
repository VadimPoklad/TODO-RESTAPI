package com.petproject.todo.controller;

import com.petproject.todo.service.dto.request.UserDtoRequest;
import com.petproject.todo.service.dto.response.UserDtoResponse;
import com.petproject.todo.service.serv.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = "application/json")
@Validated
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping()
    public List<UserDtoResponse> getAll(){
        return service.getALl();
    }
    @GetMapping("/{id}")
    public UserDtoResponse getById(@PathVariable Long id){
        return service.getById(id);
    }
    @PostMapping
    public UserDtoResponse create(@Valid @RequestBody UserDtoRequest request){
        return service.create(request);
    }
    @PutMapping("/{id}")
    public UserDtoResponse update(@PathVariable Long id, @Valid @RequestBody UserDtoRequest request){
        return service.update(id, request);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
