package com.example.finalProject.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalProject.Entity.docreg;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.services.doclogin;

@RestController
@RequestMapping("/user")
public class docloginconn {
    @Autowired
    private doclogin doclogi;
       
    @PostMapping("/doclogin")
    public Result login (@RequestBody @Valid docreg login ){
        return doclogi.login(login);
    }
}
