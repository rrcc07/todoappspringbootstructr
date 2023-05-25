package com.todoapp.demo.mapper;

import com.todoapp.demo.dto.TaskDto;
import com.todoapp.demo.model.entity.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskDTOMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "taskStatus", source = "taskStatus"),
            @Mapping(target = "createdDate", source = "createdDate")
    })
    TaskDto toTask(Task task);

    @InheritInverseConfiguration
    Task toTask(TaskDto taskDto);
}
