package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.logging.Logging;
import com.example.models.User;
import com.example.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {


	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		//To read in stringified JSON data from a POST request is a little more complicated than reading form data
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
		
		try {
			System.out.println("In the login handler");
			User u = uServ.signIn(username, password);
			System.out.println(u);
			Logging.logger.info("User logged in");
			//We will keep track of if a user is signed in by storing their id in the session
			req.getSession().setAttribute("id", u.getId());
			//req.getSession().setAttribute("role", u.getUserRoleId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
			
			
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Username or password incorrect");
			Logging.logger.warn("User tried logging in with incorrect credentials");
		}
		
	}
	
	public static void logout(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		req.getSession().setAttribute("id", null);
	}

}
