package com.petproject.todo.service.mapper;

import com.petproject.todo.repository.entity.User;
import com.petproject.todo.service.dto.request.UserDtoRequest;
import com.petproject.todo.service.dto.response.UserDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper{
    List<UserDtoResponse> listToDtoList(List<User> list);

    @Mappings({
            @Mapping(target = "accountNonExpired", expression = "java(true)"),
            @Mapping(target = "accountNonLocked", expression = "java(true)"),
            @Mapping(target = "credentialsNonExpired", expression = "java(true)"),
            @Mapping(target = "enabled", expression = "java(true)"),
    })
    User toEntity(UserDtoRequest dto);

    UserDtoResponse toDto(User entity);
}