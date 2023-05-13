package com.turkcell.commonpackage.config.exceptions;

import com.turkcell.commonpackage.utils.constants.Messages;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import com.turkcell.commonpackage.utils.exceptions.EntityAlreadyExistsException;
import com.turkcell.commonpackage.utils.exceptions.EntityNotFoundException;
import com.turkcell.commonpackage.utils.results.ExceptionResult;
import com.turkcell.commonpackage.utils.results.ValidExceptionResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult entityNotFoundExceptionHandler(EntityNotFoundException e, HttpServletRequest request){
        return getExceptionResponse(e, request,HttpStatus.NOT_FOUND.toString());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult entityAlreadyExistsExceptionHandler(EntityAlreadyExistsException e, HttpServletRequest request){
        return getExceptionResponse(e, request,HttpStatus.ALREADY_REPORTED.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResult validExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidExceptionResult result = new ValidExceptionResult(HttpStatus.BAD_REQUEST.toString(),
                Messages.VALIDATION_EXCEPTION_MESSAGE,
                request.getServletPath(),
                LocalDateTime.now());

        result.setErrors(e.getBindingResult());

        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult entityNotFoundExceptionHandler(BusinessException e, HttpServletRequest request){
        return getExceptionResponse(e, request,HttpStatus.NOT_FOUND.toString());
    }

    private ExceptionResult getExceptionResponse(RuntimeException e,
                                                 HttpServletRequest request,
                                                 String status){
        return new ExceptionResult(status,e.getMessage(),request.getServletPath(),LocalDateTime.now());
    }
}
