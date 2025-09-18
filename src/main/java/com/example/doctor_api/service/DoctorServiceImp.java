package com.example.doctor_api.service;

import com.example.doctor_api.entity.Doctor;
import com.example.doctor_api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImp implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
