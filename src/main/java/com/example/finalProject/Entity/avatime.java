package com.example.finalProject.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ava_time")
@Data
public class avatime {
    @Column(name = "at_id")
    @Id
    private Integer at_id;

    @Column(name = "from_time")
    private LocalTime  fromtime;

    @Column(name = "to_Time")
    private LocalTime  toTime;

    @Column(name = "label")
    private String  label;
    
    @Column(name = "status")
    private int status;

    @Column(name = "at_Date")
    private LocalDate atdate;


    
}
