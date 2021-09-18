package com.assignment.review.exceptions;

import com.assignment.review.controller.pojo.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> constraintViolations = ex.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = constraintViolations.iterator();

        while(iterator.hasNext()) {
            FieldError constraintViolation = (FieldError)iterator.next();
            if (constraintViolation.getField().equals("reviewScore")) {
                stringBuilder.append("reviewScore is either missing or values is not between 1 and 5").append(", ");
            } else if (constraintViolation.getField().equals("productId")) {
                stringBuilder.append("Product Id is required");
                stringBuilder.append(constraintViolation.getField()).append(", ");
            } else {
                stringBuilder.append("Invalid or missing required parameter: ");
                stringBuilder.append(constraintViolation.getField()).append(", ");
            }
        }

        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
        ErrorResponse errorResponse = (new ErrorResponse.ErrorResponseBuilder()).description(stringBuilder.toString()).errorCode("400").build();
        return errorResponse;
    }

    @ExceptionHandler({ Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return (new ErrorResponse.ErrorResponseBuilder()).description(ex.getLocalizedMessage()).errorCode("500").build();
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(EmptyResultDataAccessException ex) {
        return (new ErrorResponse.ErrorResponseBuilder()).description(ex.getLocalizedMessage()).errorCode("500").build();
    }

    @ExceptionHandler({ AuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleGenericException(AuthorizationException ex) {
        return (new ErrorResponse.ErrorResponseBuilder()).description(ex.getLocalizedMessage()).errorCode("403").build();
    }

}
