package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ReimbursementDao;
import com.example.dao.ReimbursementDaoDB;
import com.example.logging.Logging;
import com.example.models.Reimbursement;
import com.example.models.User;
import com.example.services.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReimbursementController {

	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) {
	
		String json;
		List<Reimbursement> allR = rServ.getAllReimbs();
		
		try {
			ObjectMapper om = new ObjectMapper();
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(allR);
			PrintWriter writer = response.getWriter();
			writer.write(json);
		}
		catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void getAllUserReimbs(HttpServletRequest req, HttpServletResponse res) {
		
		String json;
		
		try {
		
		//To read stringified JSON data
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		int userId = Integer.parseInt(parsedObj.get("userId").asText());
		
		List<Reimbursement> allUR = rServ.findUsersReimbursement(userId);
		ObjectMapper om = new ObjectMapper();
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(allUR);
		PrintWriter writer = res.getWriter();
		writer.write(json);
		
		}catch(Exception e)
		{
			System.out.println("User ID doesn't exist");
			e.printStackTrace();
		}
	}
	

	public static void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		StringBuilder buffer = new StringBuilder();

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
		
		double amount = parsedObj.get("amount").asDouble();
		String description = parsedObj.get("description").asText();
		
		//String typeR = parsedObj.get("type").asText();
		String type = parsedObj.get("type").textValue();
				
		int author = (int) req.getSession().getAttribute("id");
		
		int typeId = 0;
		
		if(type.equals("lodging"))
		{
			typeId = 1;
		}
		else if(type.equals("travel"))
		{
			typeId=2;
		}
		else if(type.equals("food"))
		{
			typeId=3;
		}
		else if(type.equals("other"))
		{
			typeId=4;
		}
		try {
			System.out.println("In the create reimbursement handler :)");
			Reimbursement r = rServ.createReimbursement(amount, description, typeId, author);
			Logging.logger.info("User successfully created reimbursement");
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(r));			
		}catch(Exception e) {
			res.setStatus(403);
			Logging.logger.warn("User tried creating reimbursement unsuccessfully");
		}
	}
	
	public static void updateReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("IN");
		
		StringBuilder buffer = new StringBuilder();
		
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
		
		int id = parsedObj.get("id").asInt();
		String stat = parsedObj.get("stat").textValue();
		
		int status = 0;
		if(stat.equals("accept"))
		{
			//accepted status
			status = 2;
		}
		else {
			//denied status
			status = 3;
		}
		try {
		System.out.println("In the status update handler");
		Reimbursement r = rServ.findReimbursement(id);
		r.setStatus_id(status);
		rServ.updateStatus(r);
		System.out.println(r);
		res.setStatus(200);
		res.getWriter().write(new ObjectMapper().writeValueAsString(r));
		    }
		catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Reimbursement with that id doesn't exist");
			Logging.logger.warn("Manager tried updating reimbursement with non existent reimbursement id");
		}
	}
	
	
public static void getAllUserReimbsPending(HttpServletRequest req, HttpServletResponse res) {
		
		
		try {
		
		int userId =  (int) req.getSession().getAttribute("id");
		List<Reimbursement> allUR = rDao.getAllReimbursementsUserStat(userId, "pending");
		res.getWriter().write(new ObjectMapper().writeValueAsString(allUR));
		
		}catch(Exception e)
		{
			System.out.println("User ID doesn't exist");
			e.printStackTrace();
		}
	}
	
public static void getAllPending(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	List<Reimbursement> allUR = rDao.getAllReimbursementsStatus(1);
	res.getWriter().write(new ObjectMapper().writeValueAsString(allUR));
	
}

public static void getAllResolved(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	List<Reimbursement> allUR = rDao.getAllReimbursementResolved();
	res.getWriter().write(new ObjectMapper().writeValueAsString(allUR));
}


public static void getAllUserReimbsResolved(HttpServletRequest req, HttpServletResponse res) {
	
	String json;
	
	try {
	
	int userId = (int) req.getSession().getAttribute("id");
	List<Reimbursement> allUR = rDao.getAllReimbursementsUserStat(userId, "other");
	res.getWriter().write(new ObjectMapper().writeValueAsString(allUR));
	
	}catch(Exception e)
	{
		System.out.println("User ID doesn't exist");
		e.printStackTrace();
	}
}
}
