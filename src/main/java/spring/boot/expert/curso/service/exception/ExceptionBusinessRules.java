package spring.boot.expert.curso.service.exception;

public class ExceptionBusinessRules extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExceptionBusinessRules(String message) {
        super(message);
    }
    
}