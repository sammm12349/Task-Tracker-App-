package com.devitiro.task.domain.entities.dto;

public record ErrorResponse( int status,
                            String message,
                            String details) {

}
