package com.example.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ReimbursementDao;
import com.example.dao.ReimbursementDaoDB;
import com.example.models.Reimbursement;
import com.example.services.ReimbursementService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
	
	public static void getAllReimbStat(HttpServletRequest req, HttpServletResponse res) {
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
		
		int statId = Integer.parseInt(parsedObj.get("statId").asText());
		
		List<Reimbursement> allSR = rServ.getAllStat(statId);
		ObjectMapper om = new ObjectMapper();
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(allSR);
		PrintWriter writer = res.getWriter();
		writer.write(json);
		
		}catch(Exception e)
		{
			System.out.println("Stat ID doesn't exist");
			e.printStackTrace();
		}
	}

	public static void addReimbursement(HttpServletRequest req, HttpServletResponse res) {
		
	}
	
	public static void updateReimbursement(HttpServletRequest req, HttpServletResponse res) {
		
	}
	
}
