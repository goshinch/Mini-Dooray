package com.nhnacademy.TaskAPI.repository.task;

import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskRequest;
import com.nhnacademy.TaskAPI.entity.QProject;
import com.nhnacademy.TaskAPI.entity.QTask;
import com.nhnacademy.TaskAPI.entity.Task;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    public TaskRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public TaskDto getTask(Long taskId) {
        QTask qTask = QTask.task;
        QProject qProject = QProject.project;

        return from(qTask)
                .select(Projections.bean(TaskDto.class,
                        qTask.taskId,
                        qProject.projectId,
                        qTask.createdAt,
                        qTask.updatedAt,
                        qTask.taskName,
                        qTask.description,
                        qTask.state
                        )
                )
                .leftJoin(qTask.project, qProject)
                .where(qTask.taskId.eq(taskId))
                .fetchFirst();
    }

    @Override
    public List<TaskDto> getTasks() {
        QTask qTask = QTask.task;
        QProject qProject = QProject.project;

        return from(qTask)
                .select(Projections.bean(TaskDto.class,
                                qTask.taskId,
                                qProject.projectId,
                                qTask.createdAt,
                                qTask.updatedAt,
                                qTask.taskName,
                                qTask.description,
                                qTask.state
                        )
                )
                .leftJoin(qTask.project, qProject).fetch();
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskRequest taskRequest) {
        QTask qTask = QTask.task;
        QProject qProject = QProject.project;

        update(qTask)
                .set(qTask.taskName, taskRequest.getTaskName())
                .set(qTask.description, taskRequest.getDescription())
                .set(qTask.state, Task.State.valueOf(taskRequest.getState()))
                .set(qTask.updatedAt, LocalDateTime.now())
                .where(qTask.taskId.eq(taskId))
                .execute();

        return from(qTask)
                .select(Projections.bean(TaskDto.class,
                                qTask.taskId,
                                qProject.projectId,
                                qTask.createdAt,
                                qTask.updatedAt,
                                qTask.taskName,
                                qTask.description,
                                qTask.state
                        )
                )
                .leftJoin(qTask.project, qProject)
                .where(qTask.taskId.eq(taskId))
                .fetchFirst();
    }
}
