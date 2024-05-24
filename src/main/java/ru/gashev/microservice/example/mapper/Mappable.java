package ru.gashev.microservice.example.mapper;

public interface Mappable<Entity, Dto> {

    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

}
