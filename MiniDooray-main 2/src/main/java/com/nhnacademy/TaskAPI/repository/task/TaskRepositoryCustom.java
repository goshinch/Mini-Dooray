package com.nhnacademy.TaskAPI.repository.task;

import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskRequest;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TaskRepositoryCustom {
    TaskDto getTask(Long taskId);
    List<TaskDto> getTasks();
    TaskDto updateTask(Long taskId, TaskRequest taskRequest);
}
