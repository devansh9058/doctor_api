package com.example.doctor_api.service;

import com.example.doctor_api.entity.Doctor;
import com.example.doctor_api.entity.Patient;
import jakarta.validation.Valid;

import java.util.List;

public interface PatientService {
    void savePatient(@Valid Patient patient);

    List<Doctor> findDoctorListByParientId(int pid);
}
