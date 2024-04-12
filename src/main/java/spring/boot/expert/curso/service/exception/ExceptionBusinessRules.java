package spring.boot.expert.curso.service.exception;

public class ExceptionBusinessRules extends RuntimeException{

    public ExceptionBusinessRules(String message) {
        super(message);
    }
    
}