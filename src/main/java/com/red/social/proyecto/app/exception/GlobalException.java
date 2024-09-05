package com.red.social.proyecto.app.exception;

import com.red.social.proyecto.app.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> manejarResourcesNotFoundException(ResourceNotFoundException exception,
                                                                          WebRequest webRequest){

        ErrorDetails errorDetalles=new ErrorDetails(exception.getMessage(),webRequest.getDescription(false), new Date());

        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> manejarException(Exception exception,
                                                          WebRequest webRequest){

        ErrorDetails errorDetalles=new ErrorDetails(exception.getMessage(),webRequest.getDescription(false),new Date());

        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

