package com.caspercodes.pokemonapi.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ApiErrorResponse {
    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
