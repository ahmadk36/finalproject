package com.example.finalProject.controllers;

import com.example.finalProject.Entity.appointment;

import com.example.finalProject.Entity.patientEnt;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.security.paiTokenUtility;
import com.example.finalProject.services.appserv;
import com.example.finalProject.services.paiService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pai")
public class paiconn {

  @Autowired
  private paiService svP;

  @Autowired
  private paiTokenUtility tokenUtility;

  @Autowired
  private appserv apps;

  @PostMapping("/regP")
  public Result regesterpatinet(@RequestBody patientEnt regPA) {
    
    return svP.regester_patinet(regPA);
  }

  @PostMapping("/setapp")
  public Result appo(
    HttpServletRequest request,
    HttpServletResponse response,
    @Valid @RequestBody appointment app
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("appointment set successfully")) {
      return apps.makeAnAppByPai(app);
    } else {
      return result;
    }
  }

  @PutMapping("/patientUpdate")
  public Result regdocu(HttpServletRequest request,
  HttpServletResponse response,
  @Valid @RequestBody patientEnt updatepatient) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (
      result.getStatus().equalsIgnoreCase("information updated successfully")
    ) {
      return svP.updatepatient(updatepatient);
    } else {
      return result;
    }
    
  }

  @DeleteMapping("/Cancelappointmentpai")
  public Result paicancelapp(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestParam Integer id
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (
      result.getStatus().equalsIgnoreCase("appointment canceled successfully")
    ) {
      return apps.cancelappointment(id);
    } else {
      return result;
    }
  }
  @GetMapping("/findAlldoc")
	public Result findAllpatient(HttpServletRequest request, HttpServletResponse response) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (
      result.getStatus().equalsIgnoreCase("doctor are that we fetched")
    ) {
      return svP.findAlldoc();
    } else {
      return result;
    }
		
	}
  @GetMapping("/findavadoc")
  public Result findavadoc(
    HttpServletRequest request,
    HttpServletResponse response ,@RequestBody appointment apps
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svP.findAvadoc(apps);
    } else {
      return result;
    }
}
@GetMapping("/summaryforpai")
public Result findAva(
  HttpServletRequest request,
  HttpServletResponse response ,@RequestParam String fromdate , @RequestParam String todate , @RequestParam Integer patientid
) {
  Result result = tokenUtility.checkToken(request.getHeader("token"));
  if (result.getStatus().equalsIgnoreCase("Succ")) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  
    //convert String to LocalDate
    LocalDate fromlDateConverted = LocalDate.parse(fromdate, formatter);
    LocalDate tolDateConverted = LocalDate.parse(todate, formatter);
    return svP.reportforpai( fromlDateConverted , tolDateConverted , patientid);
  } else {
    return result;
  }
}
}
