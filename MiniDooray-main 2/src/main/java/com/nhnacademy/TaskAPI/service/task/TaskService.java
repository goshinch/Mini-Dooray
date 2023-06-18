package com.nhnacademy.TaskAPI.service.task;

import com.nhnacademy.TaskAPI.dto.project.ProjectRequest;
import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskRequest;
import com.nhnacademy.TaskAPI.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();
    TaskDto getTask(Long id);
    TaskDto createTask(Long projectId, TaskRequest taskRequest);
    TaskDto updateTask(Long id, TaskRequest taskRequest);
    String deleteTask(Long id);
}
