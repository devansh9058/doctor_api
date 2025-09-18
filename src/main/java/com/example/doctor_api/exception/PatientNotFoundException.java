package com.example.doctor_api.exception;

import com.example.doctor_api.controller.PatientController;

public class PatientNotFoundException extends RuntimeException{


    public PatientNotFoundException(String msg){
        super(msg);
    }
}
