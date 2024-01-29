package com.petproject.todo.repository.repo;

import com.petproject.todo.repository.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
