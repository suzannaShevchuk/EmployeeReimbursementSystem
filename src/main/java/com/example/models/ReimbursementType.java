package com.example.models;

public class ReimbursementType {

	//LODGING, TRAVEL, FOOD, or OTHER.
	
	private String type;
	
	public ReimbursementType(String type) {
		this.type = type;
	}
	
	public ReimbursementType() {
		this.type = "OTHER";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementType [type = " + type + "]";
	}
	
	
}
