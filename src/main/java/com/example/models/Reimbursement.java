package com.example.models;

public class Reimbursement {

	private int id;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private int status_id;
	private int type_id;
	private int author_id;
	private int resolved_id;
	
	public Reimbursement(double amount, String description, int type_id, int author_id) {
		this.amount = amount;
		this.description = description;
		this.type_id = type_id;
		this.author_id = author_id;
		//set defualt status_id to 0, which is pending. 1 will be accepted and 2 will be denied.
		this.status_id = 0;
		
	}
	
	public Reimbursement(int id, double amount, String submitted, String resolved, String description, int status_id,
			int type_id, int author_id, int resolved_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.status_id = status_id;
		this.type_id = type_id;
		this.author_id = author_id;
		this.resolved_id = resolved_id;
	}

	public Reimbursement() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolved_id() {
		return resolved_id;
	}

	public void setResolved_id(int resolved_id) {
		this.resolved_id = resolved_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", status_id=" + status_id + ", type_id=" + type_id + ", author_id="
				+ author_id + ", resolved_id=" + resolved_id + "]";
	}

	

	
	
}
