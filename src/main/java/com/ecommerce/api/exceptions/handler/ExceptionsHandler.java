package com.ecommerce.api.exceptions.handler;

import com.ecommerce.api.entity.Product;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.exceptions.ProductNotAvailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

    @ExceptionHandler(DuplicateExceptions.class)
    public ProblemDetail problemDetail (DuplicateExceptions ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());

        problemDetail.setTitle("esse nome ja existe cadastrado, verifique e tente novaamente");
        problemDetail.setProperty("problem field", ex.getName());
        problemDetail.setProperty("problem cause", ex.getCause());

        return problemDetail;
    }

    @ExceptionHandler(ProductNotAvailable.class)
    public ProblemDetail problemDetail(ProductNotAvailable ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());

        problemDetail.setTitle("product not available " + ex.getProduct().getName());
        problemDetail.setProperty("product status ", ex.getProduct().getStatus());
        problemDetail.setProperty("product stock ", ex.getProduct().getStock());

        return problemDetail;
    }

}
