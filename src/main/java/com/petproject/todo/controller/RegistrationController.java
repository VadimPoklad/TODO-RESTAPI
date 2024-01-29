package com.petproject.todo.controller;

import com.petproject.todo.service.dto.request.UserDtoRequest;
import com.petproject.todo.service.serv.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("userData", new UserDtoRequest());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid  UserDtoRequest userDtoRequest, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userDtoRequest);
            return "register";
        }
        if(!Objects.equals(userDtoRequest.getPassword(), userDtoRequest.getPasswordConfirm()))
            throw new IllegalArgumentException();
        userDtoRequest.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
        userService.create(userDtoRequest);
        return "register_success";
    }
}