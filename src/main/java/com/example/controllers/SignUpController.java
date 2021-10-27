package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;

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

public class SignUpController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void signUp(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException
	{
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
				String first = parsedObj.get("first").asText();
				String last = parsedObj.get("last").asText();
				String email = parsedObj.get("email").asText();
				int role = 1;
				
				try {
					System.out.println("In the sign up handler");
					User u = uServ.signUp(first,last,email,username,password,role);
					System.out.println(u);
					Logging.logger.info("new user signed up");
					//We will keep track of if a user is signed in by storing their id in the session
					//req.getSession().setAttribute("id", u.getId());
					res.setStatus(200);
					res.getWriter().write(new ObjectMapper().writeValueAsString(u));
				} catch(Exception e) {
					res.setStatus(403);
					res.getWriter().println("Username or email already exists");
					Logging.logger.warn("new user tried signing up with email or user that already exists.");
				}
				
	}
}
