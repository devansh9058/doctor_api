package com.example.doctor_api.repository;

import com.example.doctor_api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
     @Query("from Doctor where speciality=:arg1 and city=:arg2")
    List<Doctor> getDoctorListBySpeciality(@Param("arg1") String speciality,@Param("arg2") String city);
}
