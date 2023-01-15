package com.example.finalProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "regester_patinet")
@Data
public class patientEnt {
    @Column(name = "patientid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer patientid;

    @Column(name = "username")
    private String  username;

    @Column(name = "password")
    private String  password;

    @Column(name = "pai_name")
    private String  paiName;
    
    @Column(name = "phone_num")
    private String phoneNumber;

    @Column(name = "age")
    private Integer age ;
    
    @Column(name = "gender")
    private String gender;
    
}
