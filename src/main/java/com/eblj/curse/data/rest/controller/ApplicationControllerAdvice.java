package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.exception.PedidoNaoEncontradoException;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegrasnegocioException(RegraNegocioException ex){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidosNotFoundException( PedidoNaoEncontradoException ex ){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> erros= ex.getBindingResult().getAllErrors()
                 .stream()
                 .map(err ->err.getDefaultMessage())
                 .collect(Collectors.toList());
         return new ApiErrors(erros);
    }

}
