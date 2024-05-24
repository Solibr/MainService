package ru.gashev.microservice.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import ru.gashev.microservice.example.dto.TaskDto;
import ru.gashev.microservice.example.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {

    @ValueMapping(source = "status", target = "status")
    @ValueMapping(source = "type", target = "type")
    Task toEntity(TaskDto taskDto);

    @ValueMapping(source = "status", target = "status")
    @ValueMapping(source = "type", target = "type")
    TaskDto toDto(Task task);
}
