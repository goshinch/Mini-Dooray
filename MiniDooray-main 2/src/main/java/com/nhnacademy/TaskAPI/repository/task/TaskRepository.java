package com.nhnacademy.TaskAPI.repository.task;

import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskResponse;
import com.nhnacademy.TaskAPI.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {
}
