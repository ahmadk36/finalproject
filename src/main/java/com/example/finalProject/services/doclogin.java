package com.example.finalProject.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalProject.Entity.docreg;
import com.example.finalProject.pojo.Result;
import com.example.finalProject.repos.docregRepo;
import com.example.finalProject.security.DocTokenUtility;


@Service
public class doclogin {
    @Autowired
	docregRepo repo;
	@Autowired
	private DocTokenUtility tokenUtility;

	public Result login(docreg login) {
		Result result = new Result();
		Map<String, Object> mapResult = new HashMap<>();
		login = repo.findByUsername(login.getUsername());
		if (login == null) {
			mapResult.put("user", "User Not Found :(");
			result.setStatus("1");
			result.setResultMap(mapResult);
			return result;
		}
		if (!login.getPassword().equalsIgnoreCase(login.getPassword())) {

			mapResult.put("password", "Inncorect Password :(");
			result.setStatus("1");
			result.setResultMap(mapResult);
			return result;

		}
		String token = tokenUtility.generateToken(login.getUsername());
		result.setStatus("0");
		result.setStatus("success");
		mapResult.put("token", token);
		result.setResultMap(mapResult);
		return result;
	}
}
