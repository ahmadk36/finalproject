package com.example.finalProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "regester_doc")
@Data
public class docreg {
    @Column(name = "doctorid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorid;

    @Column(name = "username")
    private String  username;

    @Column(name = "password")
    private String  password;

    @Column(name = "doctor_name")
    private String  doctorname;
    
    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "phone_num")
    private String phoneNumber;
    
    
}
