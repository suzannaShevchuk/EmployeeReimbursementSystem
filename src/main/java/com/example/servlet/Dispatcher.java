package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controllers.AccountController;
import com.example.controllers.LoginController;
import com.example.controllers.ReimbursementController;
import com.example.controllers.SessionController;
import com.example.controllers.SignUpController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException
	{
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/EmployeeReimbursementSystem/api/login":
			LoginController.login(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/signup":
			SignUpController.signUp(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/pending":
			ReimbursementController.getAllUserReimbsPending(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/resolved":
			ReimbursementController.getAllUserReimbsResolved(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/create":
			ReimbursementController.addReimbursement(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/session":
			SessionController.getSession(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/user":	
			AccountController.getAccount(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/update":
			AccountController.updateAccount(req, res);
			break;
		case "/EmployeeReimbursementSystem/api/logout":
			LoginController.logout(req, res);
			break;
	}
		
	}

	
	
}
