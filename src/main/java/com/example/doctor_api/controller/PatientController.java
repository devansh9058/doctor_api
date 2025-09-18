package com.example.doctor_api.controller;

import com.example.doctor_api.entity.Doctor;
import com.example.doctor_api.entity.Patient;
import com.example.doctor_api.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping("add")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody  Patient patient){
        patientService.savePatient(patient);

        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }
    @GetMapping("doctorList/bypid")
    public ResponseEntity<List<Doctor>> getDoctorByPatientId(@RequestParam  int pid){
       List<Doctor> doctorList=patientService.findDoctorListByParientId(pid);
        return ResponseEntity.ok(doctorList);
    }
}
