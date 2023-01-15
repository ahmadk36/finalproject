package com.example.finalProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class appointment implements Serializable {

  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @ManyToOne
  @JoinColumn(name = "pai_name", referencedColumnName = "pai_name")
  private patientEnt paiName;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private docreg doctorid;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private patientEnt patientid;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @Column(name = "date")
  LocalDate date;

  @Column(name = "status")
  private Integer status;

  @JsonFormat(pattern = "HH:mm")
  @Column(name = "TIME")
  public LocalTime time;
}
