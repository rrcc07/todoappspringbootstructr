package com.todoapp.demo.mapper;

import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskDto, Task>{
    @Override
    public Task map(TaskDto in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
