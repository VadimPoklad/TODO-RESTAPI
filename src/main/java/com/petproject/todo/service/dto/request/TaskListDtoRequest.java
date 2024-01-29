package com.petproject.todo.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDtoRequest {
    @NotBlank
    @Size(max = 50)
    private String name;
}
