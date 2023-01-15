package com.example.finalProject.repos;

import com.example.finalProject.Entity.appointment;
import com.example.finalProject.pojo.app;
import com.example.finalProject.pojo.appcopy;
import com.example.finalProject.pojo.avadoc;
import com.example.finalProject.pojo.summarydoc;
import com.example.finalProject.pojo.summarypai;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface apprepo extends JpaRepository<appointment, Integer> {
  appointment findByTime(LocalTime time);

  @Query(
    nativeQuery = true,
    value = "SELECT  doctor_id ,patient_id,regester_patinet.pai_name , time FROM appointment  inner join regester_patinet on appointment.patient_id = regester_patinet.patientid "
  )
  List<app> findallappointment();

  @Query(
    nativeQuery = true,
    value = "select doctor_id from appointment  where date !=:date and time !=:time "
  )
  List<avadoc> findallavadocs(LocalDate date , LocalTime time);

  @Query(nativeQuery = true, value = "SELECT * FROM appointment ")
  List<appointment> findtimeava();

  @Query(nativeQuery = true, value = "SELECT id , doctor_id , patient_id, time , date ,pai_name , status FROM appointment where date >= :fromdate and date <= :todate and doctor_id = :doctorid ")
  List<appointment> summaryfordoc(LocalDate fromdate , LocalDate todate , Integer doctorid);

  @Query(nativeQuery = true, value = "SELECT * FROM appointment where date >= :fromdate and date <= :todate and doctor_id = :patientid")
  List<summarypai> summaryforpai(LocalDate fromdate , LocalDate todate , Integer patientid);

  @Query(nativeQuery = true, value = "SELECT * FROM appointment where date = :date and time = :time  and doctor_id = :doctorid")
  List<appointment> findtalldoctord(LocalDate date , LocalTime time, Integer doctorid);

  
  @Query(
    nativeQuery = true,
    value = "SELECT COUNT(*) as visited FROM appointment where patient_id=:patientid and doctor_id =:doctorid "
  )
  List<appcopy> finpaitentcount(Integer patientid, Integer doctorid);

  @Transactional
  @Modifying
  @Query(
    nativeQuery = true,
    value = "UPDATE appointment SET status =:status WHERE id =:id"
  )
  void updatevisit(Integer status, Integer id);
}
