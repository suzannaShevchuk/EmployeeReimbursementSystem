package com.example.models;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private int userRoleId;
	
	//Used to send user info to the database because the db autogenerates the id
		public User(String firstName, String lastName, String email, String username, String password, int userRoleId) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.username = username;
			this.email = email;
			this.password = password;
			this.userRoleId = userRoleId;
		}
		

		//Used to get info from the database and create a user from it
		public User(int id, String firstName, String lastName, String email, String username, String password, int userRoleId) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
			this.userRoleId = userRoleId;
		}

		public User() {
			
		}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userId) {
		this.userRoleId = userId;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", userRoleId=" + userRoleId + "]";
	}

	
	
}
