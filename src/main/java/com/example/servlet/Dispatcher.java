package com.example.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controllers.LoginController;
import com.example.controllers.SignUpController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException
	{
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/EmployeeReimbursementSystem/api/login":
			LoginController.login(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/signup":
			SignUpController.signUp(req, res);
			break;
	}
		
	}

	
	
}
