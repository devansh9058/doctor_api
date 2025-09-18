package com.example.doctor_api.adviceController;

import com.example.doctor_api.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex ){
        //System.out.println("method arguments not valid exception handler called");
        BindingResult br=ex.getBindingResult();
        List<ObjectError> objectErrorList=br.getAllErrors();
        List<String> errorMessageList=new ArrayList<>();
         for(ObjectError error:objectErrorList){
        errorMessageList.add(error.getDefaultMessage());
         }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageList);
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> patientNotFoundExceptionHandler(PatientNotFoundException ex ){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(RuntimeException ex ){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }
}
