package com.todoapp.demo.mapper;

import com.todoapp.demo.dto.NewTaskDto;
import com.todoapp.demo.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NewTaskDTOMapper {

    @Mappings({
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "eta", source = "eta"),
            @Mapping(target = "taskStatus", source = "taskStatus"),
            @Mapping(target = "createdDate", source = "createdDate"),
            @Mapping(target = "id", ignore = true)
    })
    Task toTask(NewTaskDto newTaskDto);
}
