package com.example.models;

public class Status {
	
	private String status;
	
	public Status() {
		this.status = "pending";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [status = " + status + "]";
	}
	
	

}
