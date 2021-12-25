package com.springboot.blog.exception;

import com.springboot.blog.paylaod.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*----------handle specific exceptions-----------*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),resourceNotFoundException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException blogAPIException,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),blogAPIException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



    /*---this method belongs to ResponseEntityExceptionHandler class and this method throws the default
    error message, and this has been overridden now to get customized error message of validation---*/
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        HashMap<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errMessage = error.getDefaultMessage();
            errors.put(fieldName,errMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    /*---this is an another way to customize the validation error message
    while using this approach, make sure to remove extended ResponseEntityExceptionHandler class---*/

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException,
//                                                               WebRequest webRequest){
//
//        HashMap<String,String> errors = new HashMap<>();
//        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error)->{
//            String fieldName = ((FieldError)error).getField();
//            String errMessage = error.getDefaultMessage();
//            errors.put(fieldName,errMessage);
//        });
//
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//
//    }

    /*-----------------------Handle global exception----------------------------*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
