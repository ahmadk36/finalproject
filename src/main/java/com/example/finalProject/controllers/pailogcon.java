package com.example.finalProject.controllers;

import com.example.finalProject.Entity.patientEnt;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.services.pailogin;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userp")
public class pailogcon {

  @Autowired
  private pailogin pailog;

  @PostMapping("/pailogin")
  public Result loginp(@RequestBody @Valid patientEnt login) {
    return pailog.login(login);
  }
}
