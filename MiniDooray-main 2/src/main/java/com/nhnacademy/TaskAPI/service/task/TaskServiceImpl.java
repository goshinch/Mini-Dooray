package com.nhnacademy.TaskAPI.service.task;

import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskRequest;
import com.nhnacademy.TaskAPI.entity.Task;
import com.nhnacademy.TaskAPI.exception.NotFoundException;
import com.nhnacademy.TaskAPI.repository.project.ProjectRepository;
import com.nhnacademy.TaskAPI.repository.task.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found ID: ";
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.getTasks();
    }

    @Override
    public TaskDto getTask(Long id) {
        return taskRepository.getTask(id);
    }

    @Override
    public TaskDto createTask(Long projectId, TaskRequest taskRequest) {
        Task task = new Task();
        task.setProject(projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND_MESSAGE + projectId)));
        task.setTaskName(taskRequest.getTaskName());
        task.setDescription(taskRequest.getDescription());
        task.setUpdatedAt(LocalDateTime.now());
        task.setState(Task.State.valueOf(taskRequest.getState()));
        return taskRepository.getTask(taskRepository.save(task).getTaskId());
    }

    @Override
    public TaskDto updateTask(Long id, TaskRequest taskRequest) {
        return taskRepository.updateTask(id, taskRequest);
    }

    @Override
    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "{\"result : ok\"}";
    }
}
