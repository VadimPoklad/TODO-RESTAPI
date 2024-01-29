package com.petproject.todo.service.dto.response;


import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDtoResponse {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
