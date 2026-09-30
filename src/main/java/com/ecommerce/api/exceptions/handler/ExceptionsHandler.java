package com.ecommerce.api.exceptions.handler;

import com.ecommerce.api.exceptions.DuplicateNameExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionsHandler {



    @ExceptionHandler(NotFoundExceptions.class)
    public ProblemDetail problemDetail(NotFoundExceptions ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("não foi possivel encontrar o recurso.");
        problemDetail.setProperty("Id", ex.getId());
        problemDetail.setProperty("name", ex.getName());
        problemDetail.setProperty("problem cause", ex.getCause());

        return problemDetail;
    }

    @ExceptionHandler(DuplicateNameExceptions.class)
    public ProblemDetail problemDetail (DuplicateNameExceptions ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());

        problemDetail.setTitle("esse nome ja existe cadastrado, verifique e tente novaamente");
        problemDetail.setProperty("name", ex.getName());
        problemDetail.setProperty("problem cause", ex.getCause());

        return problemDetail;
    }
}
