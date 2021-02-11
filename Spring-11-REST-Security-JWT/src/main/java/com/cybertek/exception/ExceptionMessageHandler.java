package com.cybertek.exception;

import com.cybertek.entity.ResponseWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice //global
@Order(Ordered.HIGHEST_PRECEDENCE) // give priority to spring framework prior to Spring's exception
public class ExceptionMessageHandler {
    //building custom exception

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseWrapper> serviceException(ServiceException se){
        String message = se.getMessage();

        return new ResponseEntity<>(ResponseWrapper.builder()
                .success(false)
                .code(HttpStatus.CONFLICT.value())
                .message(message)
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseWrapper> accessDeniedException(AccessDeniedException ae){
        String message = ae.getMessage();

        return new ResponseEntity<>(ResponseWrapper.builder()
                .success(false)
                .code(HttpStatus.FORBIDDEN.value())
                .message(message)
                .build(), HttpStatus.FORBIDDEN);
    }


}
