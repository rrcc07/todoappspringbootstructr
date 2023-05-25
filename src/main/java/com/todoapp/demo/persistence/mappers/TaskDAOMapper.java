package com.todoapp.demo.persistence.mappers;

import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.persistence.dao.TaskDAO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskDAOMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "taskStatus", source = "taskStatus"),
            @Mapping(target = "createdDate", source = "createdDate")

    })
    Task toTask(TaskDAO taskDAO);

    @InheritInverseConfiguration
    TaskDAO toTask(Task task);
}
