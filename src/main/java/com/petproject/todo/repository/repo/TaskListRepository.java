package com.petproject.todo.repository.repo;

import com.petproject.todo.repository.entity.TaskList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends CrudRepository<TaskList, Long> {
}
