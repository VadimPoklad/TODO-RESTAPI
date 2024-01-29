package com.petproject.todo.service.mapper;


import com.petproject.todo.repository.entity.TaskList;
import com.petproject.todo.service.dto.request.TaskListDtoRequest;
import com.petproject.todo.service.dto.response.TaskListDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper{
    List<TaskListDtoResponse> listToDtoList(List<TaskList> list);
    TaskList toEntity(TaskListDtoRequest dto);
    TaskListDtoResponse toDto(TaskList entity);
}