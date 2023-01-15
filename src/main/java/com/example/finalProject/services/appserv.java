package com.example.finalProject.services;

import com.example.finalProject.Entity.appointment;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.pojo.app;
import com.example.finalProject.repos.apprepo;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class appserv {

  @Autowired
  private apprepo repo;

  //@Autowired
  //private TokenUtility tokenUtility;


  public Result makeAnAppByDoc(appointment app) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    if (app.getDoctorid() == null) {
      if (app.getPatientid() == null) result.setStatus("0");
      Description.put("DoctorID", "You need to insert doctor id ");
      result.setResultMap(Description);
      return result;
    }
    if (
      app.getTime().isBefore(LocalTime.parse("08:00")) ||
      app.getTime().isAfter(LocalTime.parse("17:00"))
    ) {
      result.setStatus("0");
      Description.put("clinic :", "the clinic is closed . clinic working hours are between 08:00am and 17:00pm");
      result.setResultMap(Description);
      return result;
    }
    // if (app.getTime() == null ) {
    //   result.setStatus("0");
    //   Description.put("appointment avalabilaty  :", "the appointment is not available at this time");
    //   result.setResultMap(Description);
    //   return result;
    // }
    if (repo.findtalldoctord(app.getDate() , app.getTime() , app.getDoctorid().getDoctorid()).size() > 0) {
      result.setStatus("0");
      Description.put("appointment avalabilaty  :", "the appointment is not available at this time");
      result.setResultMap(Description);
      return result;
    }
    repo.save(app);
    result.setStatus("1");
    Description.put("description", " created appointment sucssefully");
    result.setResultMap(Description);
    return result;
    }
  

  public Result makeAnAppByPai(appointment app) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    if (app.getDoctorid().getDoctorid() == null) {
      if (app.getPatientid().getPatientid() == null) result.setStatus("0");
      Description.put("PatientID", "You need to insert Patient id ");
      result.setResultMap(Description);
      return result;
    }
    if (
      app.getTime().isBefore(LocalTime.parse("08:00")) ||
      app.getTime().isAfter(LocalTime.parse("17:00"))
    ) {
      result.setStatus("0");
      Description.put("clinic :", "the clinic is closed . clinic working hours are between 08:00am and 17:00pm");
      result.setResultMap(Description);
      return result;
    }
    if (app.getTime() != null || app.getDate() !=null) {
      result.setStatus("0");
      Description.put("appointment avalabilaty  :", "the appointment is not available at this time");
      result.setResultMap(Description);
      return result;
    }
    
    if (repo.findtalldoctord(app.getDate() , app.getTime() , app.getDoctorid().getDoctorid()).size() >= 0) {
      result.setStatus("0");
      Description.put("appointment avalabilaty  :", "the appointment is not available at this time");
      result.setResultMap(Description);
      return result;
    }
    
    repo.save(app);
    result.setStatus("1");
    Description.put("description", " created appointment sucssefully");
    result.setResultMap(Description);
    return result;
    }
  

  public Result cancelappointment(Integer id) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    if (id == null || id < 0) {
      result.setStatus("0");
      Description.put("Id", "Insert positve number only .");
    }
    repo.deleteById(id);
    result.setStatus("1");
    Description.put("description", "canceled appointment sucssefully");
    result.setResultMap(Description);
    return result;
  }
  
}
