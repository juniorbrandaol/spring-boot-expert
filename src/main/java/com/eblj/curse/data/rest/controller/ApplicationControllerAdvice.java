package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegrasnegocioException(RegraNegocioException ex){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }

}
