package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Reimbursement;
import com.example.models.User;
import com.example.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao{
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	public ReimbursementDaoDB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {

		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursementsStatus(int statusId) {

		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.status_id = '" + statusId + "'";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	
	@Override
	public List<Reimbursement> getAllReimbursementResolved() {

		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.status_id = 2 OR reimbursements.status_id = 3";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	@Override
	public void createReimbursement(Reimbursement r) throws SQLException {
		Connection con = conUtil.getConnection();
		
		
		String sql = "INSERT INTO reimbursements(amount, submitted, resolved,  description, status_id, type_id, author_id, resolver_id) values"
				+ "(?,NOW(),NULL,?,1,?,?, NULL)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, r.getAmount());
		ps.setString(2, r.getDescription());
		ps.setInt(3, r.getType_id());
		ps.setInt(4, r.getAuthor_id());
		
		ps.execute();
	}
	

	@Override
	public void updateReimbursement(Reimbursement r) {
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE reimbursements SET amount = ?, submitted = ?, resolved = ?, description = ?, status_id = ?, type_id = ?, author_id = ?, resolver_id = ?"
					+ "WHERE reimbursements.id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getSubmitted());
			ps.setString(3, r.getResolved());
			ps.setString(4, r.getDescription());
			ps.setInt(5, r.getStatus_id());
			ps.setInt(6, r.getType_id());
			ps.setInt(7, r.getAuthor_id());
			ps.setInt(8, r.getResolved_id());
			ps.setInt(9, r.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteReimbursement(Reimbursement r) {

		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM reimbursements WHERE reimbursements.id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, r.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void statusReimbursement(Reimbursement r)
	{
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE reimbursements SET resolved = NOW(), status_id ='" + r.getStatus_id()
					+ "'WHERE reimbursements.id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, r.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursementsType(int typeId) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.type_id = '" + typeId + "'";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsUser(int authorId) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.author_id = '" + authorId + "'";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public Reimbursement findReimbursement(int reimbId) {
		
		Reimbursement r = new Reimbursement();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.id = '" + reimbId + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				r.setId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getString(3));
				r.setResolved(rs.getString(4));
				r.setDescription(rs.getString(5));
				r.setStatus_id(rs.getInt(6));
				r.setType_id(rs.getInt(7));
				r.setAuthor_id(rs.getInt(8));
				r.setResolved_id(rs.getInt(9));
			}
			
			return r;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsUserStat(int authorId, String stat) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		if(stat == "pending") {
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//Create a simple statement
			String sql = "SELECT * FROM reimbursements WHERE reimbursements.author_id = '" + authorId + "' AND reimbursements.status_id = 1";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the ResultSet and create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
			
			return reimbList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		}
		else {
			try {
				//Make the actual connection to the db
				Connection con = conUtil.getConnection();
				
				//Create a simple statement
				String sql = "SELECT * FROM reimbursements WHERE reimbursements.author_id = '" + authorId + "' AND reimbursements.status_id != 1";
				
				//We need to create a statement with the sql string
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);
				
				//We have to loop through the ResultSet and create objects based off the return
				while(rs.next()) {
					reimbList.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
				}
				
				return reimbList;
				
			} catch(SQLException e) {
				e.printStackTrace();
			}		
		}
		return null;
	}
	
	
}
