package com.example.doctor_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int patid;
    @NotNull(message="name is required")
    @Size(min=3,message="name has been minimum three character")
    private String name;
    @NotNull(message="city is required")

    private String city;
    @NotNull(message="email is required")
    @Pattern(regexp= "^(.+)@(\\S+)$",message="email not valid")
    private String email;
    @NotNull(message="phone is required")
    @Pattern(regexp="^[0-9]{10,10}$",message="phone number atlest have 10 digits")
    private String phone;
    @NotNull(message="symptom is required")
    @Pattern(regexp="Arthritis|Backpain|Tisssue injuries|Skin infection|Dysmenorrhea|Skin burn|Ear pain",message= "invalid symptom")
    private String symptom;

    public int getPatid() {
        return patid;
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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPatid(int patid) {
        this.patid = patid;
    }
}
