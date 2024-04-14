package spring.boot.expert.curso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spring.boot.expert.curso.ApiErrors;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;


@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ExceptionBusinessRules.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(ExceptionBusinessRules ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
    
}
