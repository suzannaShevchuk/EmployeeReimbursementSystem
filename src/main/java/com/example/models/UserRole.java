package com.example.models;

public class UserRole {

	//employee or manager
	private String role;
	
	public UserRole(String role) {
		this.role = role;
	}

	public UserRole() {
		
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [role = " + role + "]";
	}

	
}
