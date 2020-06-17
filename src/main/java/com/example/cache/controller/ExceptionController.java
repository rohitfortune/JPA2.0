package com.example.cache.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cache.exception.RecordNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<String> recordNotFoundHandler(){
		return new ResponseEntity<>("No Record Found",HttpStatus.NOT_FOUND);
	}
}
