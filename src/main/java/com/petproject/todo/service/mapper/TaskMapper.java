package com.petproject.todo.service.mapper;

import com.petproject.todo.repository.entity.Task;
import com.petproject.todo.service.dto.request.TaskDtoRequest;
import com.petproject.todo.service.dto.response.TaskDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    List<TaskDtoResponse> listToDtoList(List<Task> list);
    @Mapping(target = "isComplete", source = "complete")
    Task toEntity(TaskDtoRequest dto);
    @Mapping(target = "complete", source = "isComplete")
    TaskDtoResponse toDto(Task entity);
}
