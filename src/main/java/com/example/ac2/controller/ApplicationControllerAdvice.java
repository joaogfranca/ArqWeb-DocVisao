package com.example.ac2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ac2.dto.dtoException.ApiErrorDTO;
import com.example.ac2.exceptions.RegraException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraException.class)
    public ApiErrorDTO handlerRegraException(RegraException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handlerMethodValidException(jakarta.validation.ConstraintViolationException ex) {
        List<String> erros = ex.getConstraintViolations()
                .stream()
                .map(erro -> erro.getMessage())
                .collect(
                        Collectors.toList());
        return new ApiErrorDTO(erros);
    }

}
