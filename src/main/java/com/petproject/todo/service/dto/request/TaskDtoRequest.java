package com.petproject.todo.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoRequest {
    @NotNull
    private boolean isComplete;
    @NotBlank
    @Size(max = 50)
    private String text;
}
