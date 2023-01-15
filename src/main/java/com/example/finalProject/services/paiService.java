package com.example.finalProject.services;

import com.example.finalProject.Entity.appointment;
import com.example.finalProject.Entity.docreg;
import com.example.finalProject.Entity.patientEnt;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.repos.apprepo;
import com.example.finalProject.repos.docregRepo;
import com.example.finalProject.repos.paiRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class paiService {

  @Autowired
  private paiRepo prp;

  @Autowired
  private docregRepo rp;
  @Autowired
  private apprepo arp ;

  public Result regester_patinet(patientEnt pai) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    prp.save(pai);
    result.setStatus("0");
    Description.put("description", "patient regestered successfully");
    result.setResultMap(Description);
    return result;
  }

  public List<docreg> findAllDoc() {
    return rp.findAll();
  }

  public Result updatepatient(patientEnt paiup) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    if (paiup.getPaiName() == null || paiup.getPaiName().isEmpty()) {
      result.setStatus("1");
      Description.put("description ", "failed");
      Description.put("result :", " patient Name cannot be empty!");
      result.setResultMap(Description);
      return result;
    }
    if (paiup.getPatientid() == null) {
      if (paiup.getPatientid() < 1) result.setStatus("1");
      Description.put("description ", "failed");
      Description.put("Id", " id not found or invalid");
      result.setResultMap(Description);
      return result;
    }
    prp.save(paiup);
    result.setStatus("0");
    Description.put("description ", "success");
    Description.put("description", "patient updated successfully");
    result.setResultMap(Description);
    return result;
  }

  public Result findAlldoc() {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    result.setStatus("0");
    Description.put("description ", "success");
    Description.put("Doctor", rp.findByalldoc());
    result.setResultMap(Description);
    return result;
  }
  public Result findAvadoc(appointment appss) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    findavadocs(appss.getDate() , appss.getTime());
    result.setStatus("0");
    Description.put("description ", "success");
    Description.put("result : " , arp.findallavadocs(appss.getDate(), appss.getTime()));
    result.setResultMap(Description);
    return result;
  
  }
  public void findavadocs(LocalDate date , LocalTime time) {
    arp.findallavadocs(date, time);
  
  }
  public Result reportforpai(LocalDate fromdate , LocalDate  todate, Integer patientid) {
    Result result = new Result();
    Map<String, Object> Description = new HashMap<>();
    result.setStatus("0");
    Description.put("description ", "success");
    Description.put("result : " , arp.summaryforpai(fromdate , todate , patientid));
    result.setResultMap(Description);
    return result;

  }

}
