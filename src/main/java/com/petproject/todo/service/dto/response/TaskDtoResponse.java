package com.petproject.todo.service.dto.response;


import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoResponse {
    private Long id;
    private boolean isComplete;
    private String text;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
