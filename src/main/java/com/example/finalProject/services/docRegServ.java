package com.example.finalProject.services;

import com.example.finalProject.Entity.appointment;
import com.example.finalProject.Entity.docreg;
import com.example.finalProject.pojo.Result;

import com.example.finalProject.repos.apprepo;
import com.example.finalProject.repos.avaTrepo;
import com.example.finalProject.repos.docregRepo;
import com.example.finalProject.repos.paiRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class docRegServ {
@Autowired
private paiRepo prp;
  @Autowired
  private docregRepo drp;
  @Autowired
  private apprepo arp;
  @Autowired
  private avaTrepo tarp;

  public Result regdoctor(docreg doc) {
  	Result result = new Result();
		Map<String, Object> Description = new HashMap<>();
		if (doc.getDoctorname() == null || doc.getDoctorname().isEmpty()) {
			result.setStatus("1");
      Description.put("description ", "failed");
			Description.put("Doctor Name", " Doctor Name cannot be empty!");
			result.setResultMap(Description);
			return result;
		}
		if (doc.getDoctorid() == null) {
			if (doc.getDoctorid() < 0)
				result.setStatus("1");
        Description.put("description ", "failed");
        Description.put("Id", " id not found or invalid");
			result.setResultMap(Description);
			return result;
		}
    drp.save(doc);
    result.setStatus("0");
    Description.put("description ", "success");
    Description.put("description", "doctor regestered successfully");
    result.setResultMap(Description);
    return result;
}
public Result updatedoctor(docreg doc) {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  if (doc.getDoctorname() == null || doc.getDoctorname().isEmpty()) {
    result.setStatus("1");
    Description.put("description ", "failed");
    Description.put("Doctor Name", " Doctor Name cannot be empty!");
    result.setResultMap(Description);
    return result;
  }
  if (doc.getDoctorid() == null) {
    if (doc.getDoctorid() < 0)
      result.setStatus("1");
      Description.put("description ", "failed");
      Description.put("Id", " id not found or invalid");
    result.setResultMap(Description);
    return result;
  }
  drp.save(doc);
  result.setStatus("0");
  Description.put("description ", "success");
  Description.put("description", "doctor updated successfully");
  result.setResultMap(Description);
  return result;
}

public Result findpatientbyid(Integer id) {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  if (id == null ) {
    result.setStatus("1");
    Description.put("description ", "failed");
    Description.put("Id", "patient not found Or insert a positive number.");
  }
  
  result.setStatus("0");
  Description.put("description ", "success");
  Description.put("result", prp.findById(id));
  result.setResultMap(Description);
  return result;
}
public Result findAllpatient() {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  result.setStatus("0");
  Description.put("description ", "success");
  Description.put("result :", prp.findByall());
  result.setResultMap(Description);
  return result;
  
}

public Result findAllapp() {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  result.setStatus("0");
  Description.put("description ", "success");
  Description.put("result", arp.findallappointment());
  result.setResultMap(Description);
  return result;
}
 public Result vistedorNot(appointment st) {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
    vistedorNot2(st.getId(), st.getStatus());
  arp.save(st);
  result.setStatus("0");
  Description.put("Description", "success");
  Description.put("result", " ");
  result.setResultMap(Description);
  return result;
   
 }
public Result findAllcount(Integer paatientid , Integer doctortid) {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  result.setStatus("0");
  Description.put("result : " , arp.finpaitentcount(paatientid , doctortid));
  Description.put("description ", "success");
  result.setResultMap(Description);
  return result;

}
public Result available() {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  result.setStatus("0");
  Description.put("result : " , tarp.availabletime() );
  Description.put("description ", "success");
  result.setResultMap(Description);
  return result;

}
public void vistedorNot2(Integer status , Integer id) {
  arp.updatevisit(id , status   );

}
public Result summarydoc(LocalDate fromdate , LocalDate  todate, Integer doctorid) {
  Result result = new Result();
  Map<String, Object> Description = new HashMap<>();
  result.setStatus("0");
  Description.put("description ", "success");
  Description.put("result : " , arp.summaryfordoc(fromdate , todate , doctorid));
  result.setResultMap(Description);
  return result;

}

public String createCsvFile(LocalDate fromdate , LocalDate  todate, Integer doctorid) {
  List<appointment> appointmentlist = arp.summaryfordoc(fromdate , todate , doctorid);
  File file=new File("C:\\Users\\tiger\\.ms-ad\\doctorsummary.csv");
  try (PrintWriter writer = new PrintWriter(file)) {
    writer.println("doctor_id,patient_id,time,date");
    
    appointmentlist.forEach(x -> {
      StringJoiner sb=new StringJoiner(",");
      sb.add(x.getDoctorid() != null ? x.getDoctorid().getDoctorid() + "" : "0");
      sb.add(x.getPatientid()!=null?x.getPatientid().getPatientid()+"":"0");
      sb.add(x.getTime()!=null?x.getTime()+"":"0");
      sb.add(x.getDate()!=null?x.getDate()+"":"0");
      writer.println(sb.toString());
    });
    writer.flush();
    
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  }
return file.getAbsolutePath();
}
}

  

