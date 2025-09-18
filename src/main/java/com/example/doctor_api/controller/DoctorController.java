package com.example.doctor_api.controller;

import com.example.doctor_api.entity.Doctor;
import com.example.doctor_api.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
   @PostMapping("add")
   public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor){

       doctorService.saveDoctor(doctor);

       return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
   }
}
