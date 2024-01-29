package com.petproject.todo.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {
    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
    @Email
    private String email;
}
