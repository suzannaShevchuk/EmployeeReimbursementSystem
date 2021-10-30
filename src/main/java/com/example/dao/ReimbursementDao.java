package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Reimbursement;


public interface ReimbursementDao {

	List<Reimbursement> getAllReimbursements();
	
	void createReimbursement(Reimbursement r) throws SQLException;
	
	void updateReimbursement(Reimbursement r);
	
	void deleteReimbursement(Reimbursement r);
	
	void statusReimbursement(Reimbursement r);

	List<Reimbursement> getAllReimbursementsType(int typeId);

	List<Reimbursement> getAllReimbursementsStatus(int statusId);
	
	List<Reimbursement> getAllReimbursementsUser(int authorId);
	
	Reimbursement findReimbursement(int reimbId);

	List<Reimbursement> getAllReimbursementsUserStat(int authorId, String stat);
}
