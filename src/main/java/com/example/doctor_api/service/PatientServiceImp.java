package com.example.doctor_api.service;

import com.example.doctor_api.entity.Doctor;
import com.example.doctor_api.entity.Patient;
import com.example.doctor_api.exception.PatientNotFoundException;
import com.example.doctor_api.repository.DoctorRepository;
import com.example.doctor_api.repository.PatientRepository;
import com.example.doctor_api.utility.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);

    }

    @Override
    public List<Doctor> findDoctorListByParientId(int pid) {
        Patient patient=patientRepository.findById(pid).orElse(null);
        if(patient==null){
           throw new PatientNotFoundException("patient id does not exit");
        }
        String city=patient.getCity();
        if(!(city.equals("noida")||city.equals("delhi")||city.equals("bareilly"))){
            throw new RuntimeException("We are still wating to expand your location");

        }
        String symptom=patient.getSymptom();
        String speciality= Symptom.map.get(symptom);
       List<Doctor> docList= doctorRepository.getDoctorListBySpeciality(speciality,city);
    if(docList.isEmpty()){
        throw new RuntimeException("There is not any doctor present in symtom");

    }
        return docList;

    }
}
