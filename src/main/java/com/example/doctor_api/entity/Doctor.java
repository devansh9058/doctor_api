package com.example.doctor_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int docid;
    @NotNull(message="name is required")
    @Size(min=3,message="name has been minimum three character")
    private String name;
    @NotNull(message="city is required")
    @Pattern(regexp="noida|bareilly|delhi",message="noida or bareilly or delhi")
    private String city;
    @NotNull(message="email is required")
    @Pattern(regexp= "^(.+)@(\\S+)$",message="email not valid")
    private String email;
    @NotNull(message="phone is required")
    @Pattern(regexp="^[0-9]{10,10}$",message="phone number atlest have 10 digits")
    private String phone;
    @NotNull(message="speciality is required")
    @Pattern(regexp="Ent|Ortho|Gyne|Dermatology",message="Ent or Ortho or Gyne or Dermatology")
    private String speciality;
    public int getDocid() {
        return docid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }
}
