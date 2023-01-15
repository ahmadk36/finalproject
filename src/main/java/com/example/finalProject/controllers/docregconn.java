package com.example.finalProject.controllers;

import com.example.finalProject.Entity.appointment;
import com.example.finalProject.Entity.docreg;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.security.DocTokenUtility;
import com.example.finalProject.services.appserv;
import com.example.finalProject.services.docRegServ;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc")
public class docregconn {

  @Autowired
  private docRegServ svd;

  @Autowired
  private appserv apps;

  @Autowired
  private DocTokenUtility tokenUtility;

  @PostMapping("/Docregester")
  public Result regdoc(@RequestBody docreg regd) {
    return svd.regdoctor(regd);
  }

  @PutMapping("/DocUpdate")
  public Result regdocu( HttpServletRequest request,
  HttpServletResponse response,
  @Valid @RequestBody docreg updateDoc) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.updatedoctor(updateDoc);
    } else {
      return result;
    }
    
  }

  @PostMapping("/setappointment")
  public Result appo(
    HttpServletRequest request,
    HttpServletResponse response,
    @Valid @RequestBody appointment app
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return apps.makeAnAppByDoc(app);
    } else {
      return result;
    }
  }

  @DeleteMapping("/Cancelappointmentdoc")
  public Result doccancelapp(
    HttpServletRequest request,
    HttpServletResponse response,
    @Valid @RequestParam Integer id
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return apps.cancelappointment(id);
    } else {
      return result;
    }
  }

  @GetMapping("/findPaitentById")
  public Result findtById(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestParam Integer Id
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.findpatientbyid(Id);
    } else {
      return result;
    }
  }

  @GetMapping("/findAllpatient")
  public Result findAllpatient(
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.findAllpatient();
    } else {
      return result;
    }
  }

  @GetMapping("/findallAppointment")
  public Result findAllappointment(
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.findAllapp();
    } else {
      return result;
    }
  }

  @PutMapping("/updatevisitstatus")
  public Result updatevistied(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestBody appointment apps
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.vistedorNot(apps);
    } else {
      return result;
    }
  }
  @GetMapping("/visitCountById")
  public Result FindAllcount(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestParam Integer patientid,
    @RequestParam Integer doctorid
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.findAllcount(patientid, doctorid);
    } else {
      return result;
    }
  }
  @GetMapping("/ava")
  public Result ava(
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    Result result = tokenUtility.checkToken(request.getHeader("token"));
    if (result.getStatus().equalsIgnoreCase("Succ")) {
      return svd.available();
    } else {
      return result;
    }
  }
  @GetMapping("/summarydoc")
public Result summarydoc(
  HttpServletRequest request,
  HttpServletResponse response ,@RequestParam String fromdate , @RequestParam String todate , @RequestParam Integer doctorid
) {
  Result result = tokenUtility.checkToken(request.getHeader("token"));
  if (result.getStatus().equalsIgnoreCase("Succ")) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  
    //convert String to LocalDate
    LocalDate fromlDateConverted = LocalDate.parse(fromdate, formatter);
    LocalDate tolDateConverted = LocalDate.parse(todate, formatter);
    return svd.summarydoc( fromlDateConverted , tolDateConverted , doctorid);
  } else {
    return result;
  }
}
@GetMapping(value = "/doctorcvs")
	public Object downloadCsvWithNameFile(HttpServletRequest request, HttpServletResponse response , @RequestParam String fromdate , @RequestParam String todate , @RequestParam Integer doctorid) {
		HttpHeaders header = new HttpHeaders();
    Result result = tokenUtility.checkToken(request.getHeader("token"));
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "test.csv");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  
    //convert String to LocalDate
    LocalDate fromlDateConverted = LocalDate.parse(fromdate, formatter);
    LocalDate tolDateConverted = LocalDate.parse(todate, formatter);
		String pathFile = svd.createCsvFile(fromlDateConverted , tolDateConverted , doctorid);
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(new File(pathFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStreamResource resource = new InputStreamResource(input);
		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		// return resource;
	}
  }

