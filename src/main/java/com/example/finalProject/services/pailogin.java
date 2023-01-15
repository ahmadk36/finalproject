package com.example.finalProject.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalProject.Entity.patientEnt;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.repos.paiRepo;
import com.example.finalProject.security.paiTokenUtility;

@Service
public class pailogin {
    @Autowired
	paiRepo repo;
	@Autowired
	private paiTokenUtility tokenUtility;

	public Result login(patientEnt pailogi) {
		Result result = new Result();
		Map<String, Object> mapResult = new HashMap<>();
		pailogi = repo.findByUsername(pailogi.getUsername());
		if (pailogi == null) {
			mapResult.put("user", "User Not Found :(");
			result.setStatus("Failed");
			result.setResultMap(mapResult);
			return result;
		}
		if (!pailogi.getPassword().equalsIgnoreCase(pailogi.getPassword())) {

			mapResult.put("password", "Inncorect Password :(");
			result.setStatus("Failed");
			result.setResultMap(mapResult);
			return result;

		}
		String token = tokenUtility.generateToken(pailogi.getUsername());
		mapResult.put("token", token);
		result.setStatus("Succ");
		result.setResultMap(mapResult);
		return result;
	}
    
    
  
}
