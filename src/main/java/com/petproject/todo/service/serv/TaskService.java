package com.petproject.todo.service.serv;

import com.petproject.todo.repository.entity.*;
import com.petproject.todo.repository.repo.TaskRepository;
import com.petproject.todo.service.dto.request.TaskDtoRequest;
import com.petproject.todo.service.dto.response.TaskDtoResponse;
import com.petproject.todo.service.mapper.TaskMapper;
import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService{
    private final JPAStreamer jpaStreamer;
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(JPAStreamer jpaStreamer, TaskRepository taskRepository, TaskMapper mapper) {
        this.jpaStreamer = jpaStreamer;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public List<TaskDtoResponse> getAllByTaskListId(String username, Long id) {
        List<Task> tasks =
                jpaStreamer.stream(StreamConfiguration.of(Task.class)
                                .joining(Task$.taskList))
                        .filter(t -> t.getTaskList().getUser().getUsername().equals(username) &&
                                Objects.equals(t.getTaskList().getId(), id)).toList();
        return mapper.listToDtoList(tasks);
    }


    public TaskDtoResponse getById(String username, Long id) {
        Task task = SingleResult.getSingleResult(
                jpaStreamer.stream(StreamConfiguration.of(Task.class)
                                .joining(Task$.taskList))
                        .filter(t -> t.getTaskList().getUser().getUsername().equals(username) &&
                                Objects.equals(t.getId(), id)));

        return mapper.toDto(task);
    }

    public TaskDtoResponse create(String username, Long taskListId, TaskDtoRequest dto) {
        TaskList list = SingleResult.getSingleResult(
                jpaStreamer.stream(StreamConfiguration.of(TaskList.class)
                                .joining(TaskList$.user))
                        .filter(taskList -> taskList.getUser().getUsername().equals(username) &&
                                Objects.equals(taskList.getId(), taskListId)));
        Task task = mapper.toEntity(dto);
        task.setTaskList(list);
        return mapper.toDto(taskRepository.save(task));
    }

    public TaskDtoResponse update(String username, Long id, TaskDtoRequest dto) {
        Task task = SingleResult.getSingleResult(
                jpaStreamer.stream(StreamConfiguration.of(Task.class)
                                .joining(Task$.taskList))
                        .filter(t -> t.getTaskList().getUser().getUsername().equals(username) &&
                                Objects.equals(t.getId(), id)));
        task.setText(dto.getText());
        task.setIsComplete(dto.isComplete());
        return mapper.toDto(taskRepository.save(task));

    }

    public void deleteById(String username, Long id) {
        Task task = SingleResult.getSingleResult(
                jpaStreamer.stream(StreamConfiguration.of(Task.class)
                                .joining(Task$.taskList))
                        .filter(t -> t.getTaskList().getUser().getUsername().equals(username) &&
                                Objects.equals(t.getId(), id)));
        taskRepository.delete(task);
    }
}
