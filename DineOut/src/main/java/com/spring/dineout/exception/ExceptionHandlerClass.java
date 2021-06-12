package com.spring.dineout.exception;

import java.net.http.HttpHeaders;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.dineout.error.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {
	
	   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   
	   @ExceptionHandler(DineOutException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(
	           DineOutException ex) {
	       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }

}
