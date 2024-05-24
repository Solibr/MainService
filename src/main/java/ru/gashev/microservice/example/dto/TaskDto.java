package ru.gashev.microservice.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TaskDto {

    private long id;

    private String name;

    private Long projectId;

    private String type;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private ZonedDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private ZonedDateTime statusUpdateTime;

    private String text;

}
