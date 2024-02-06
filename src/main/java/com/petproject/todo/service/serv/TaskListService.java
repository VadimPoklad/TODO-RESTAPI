package com.petproject.todo.service.serv;

import com.petproject.todo.exception.EntityIllegalArgumentsException;
import com.petproject.todo.repository.entity.TaskList;
import com.petproject.todo.repository.entity.User;
import com.petproject.todo.repository.repo.TaskListRepository;
import com.petproject.todo.service.dto.request.TaskListDtoRequest;
import com.petproject.todo.service.dto.response.TaskListDtoResponse;
import com.petproject.todo.service.mapper.TaskListMapper;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class TaskListService{
    private final TaskListRepository taskListRepository;
    private final JPAStreamer jpaStreamer;
    private final TaskListMapper mapper;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository, JPAStreamer jpaStreamer, TaskListMapper mapper) {
        this.taskListRepository = taskListRepository;
        this.jpaStreamer = jpaStreamer;
        this.mapper = mapper;
    }
    public List<TaskListDtoResponse> getAllByUsername(String username) {
        List<TaskList> list =
                jpaStreamer.stream(TaskList.class)
                        .filter(taskList -> taskList.getUser().getUsername().equals(username))
                        .toList();
        return mapper.listToDtoList(list);
    }

    public TaskListDtoResponse getById(String username, Long id) {
        TaskList list = SingleResult.getSingleResult(
                jpaStreamer.stream(TaskList.class)
                        .filter(taskList -> taskList.getUser().getUsername().equals(username) &&
                                Objects.equals(taskList.getId(), id)));

        return mapper.toDto(list);
    }


    public TaskListDtoResponse create(String username, TaskListDtoRequest dto) {
        try {
            User user = SingleResult.getSingleResult(
                    jpaStreamer.stream(User.class).filter(u -> u.getUsername().equals(username)));
            TaskList taskList = mapper.toEntity(dto);
            taskList.setUser(user);
            System.out.println(dto);
            System.out.println(taskList);
            return mapper.toDto(taskListRepository.save(taskList));
        }catch (Exception e){
            throw new EntityIllegalArgumentsException(e);
        }
    }


    public TaskListDtoResponse update(String username, Long id, TaskListDtoRequest dto) {
        try {
            TaskList list = SingleResult.getSingleResult(
                    jpaStreamer.stream(TaskList.class)
                            .filter(taskList -> taskList.getUser().getUsername().equals(username) &&
                                    Objects.equals(taskList.getId(), id)));
            list.setName(dto.getName());
            return mapper.toDto(taskListRepository.save(list));
        }catch (Exception e){
            throw new EntityIllegalArgumentsException(e);
        }
    }


    public void deleteById(String username, Long id) {
        TaskList list = SingleResult.getSingleResult(
                jpaStreamer.stream(TaskList.class)
                        .filter(taskList -> taskList.getUser().getUsername().equals(username) &&
                                Objects.equals(taskList.getId(), id)));
        taskListRepository.delete(list);
    }
}
