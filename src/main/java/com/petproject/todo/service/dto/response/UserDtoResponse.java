package com.petproject.todo.service.dto.response;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
}
