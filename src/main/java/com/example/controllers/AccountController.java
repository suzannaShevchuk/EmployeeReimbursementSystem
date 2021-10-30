package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.logging.Logging;
import com.example.models.Reimbursement;
import com.example.models.User;
import com.example.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountController {
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void getAccount(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		int id =  (int) req.getSession().getAttribute("id");
		System.out.println(id);
		
		User acc = uDao.getUserByID(id);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(acc));
	}

	public static void getAllUsers(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<User> all = uDao.getAllUsers();
		res.getWriter().write(new ObjectMapper().writeValueAsString(all));
	}
	
	public static void updateAccount(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		StringBuilder buffer = new StringBuilder();
		
		//The buffered reader will read the json data line by line
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		System.out.println(data);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		String first = parsedObj.get("first").asText();
		String last = parsedObj.get("last").asText();
		String email = parsedObj.get("email").asText();
		
		int id =  (int) req.getSession().getAttribute("id");
		
		User u = uDao.getUserByID(id);
		
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail(email);
		u.setFirstName(first);
		u.setLastName(last);
		
		try {
			System.out.println("in the account update handler");
		uDao.updateUser(u);
		Logging.logger.info("User successfully updated their account");
		res.setStatus(200);
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));			
		
		}catch(Exception e) {
			res.setStatus(403);
			Logging.logger.warn("Something went wrong with user update");
		}
		
		
	}
}
