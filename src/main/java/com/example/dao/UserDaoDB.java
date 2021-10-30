package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.User;
import com.example.utils.ConnectionUtil;


public class UserDaoDB implements UserDao {

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	public UserDaoDB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM users";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			return userList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	@Override
	public User getUserByUsername(String username) {

		User user = new User();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE users.username = '" + username + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleId(rs.getInt(7));
			}
			
			
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void createUser(User u) throws SQLException {
       Connection con = conUtil.getConnection();
		
		//We will still create the sql string, but with some small changes
		String sql = "INSERT INTO users(username, user_password, first_name, last_name,  email, role_id) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getFirstName());
		ps.setString(4, u.getLastName());
		ps.setString(5, u.getEmail());
		ps.setInt(6, u.getUserRoleId());
		
		ps.execute();
		
	}

	@Override
	public void updateUser(User u) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE users SET username = ?, user_password = ?, first_name = ?, last_name = ?, email = ?, role_id = ?"
					+ "WHERE users.id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getUserRoleId());
			ps.setInt(7, u.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User u) {

		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM reimbursements WHERE reimbursements.author_id = ?;  DELETE FROM users WHERE users.id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserByID(int id) {

		User user = new User();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE users.id = '" + id + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleId(rs.getInt(7));
			}
			
			
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
