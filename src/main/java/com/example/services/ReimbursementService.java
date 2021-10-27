package com.example.services;

import java.sql.SQLException;
import java.util.List;

import com.example.dao.ReimbursementDao;
import com.example.exceptions.ReimbursementAlreadyExistsException;
import com.example.exceptions.ReimbursementDoesNotExistException;
import com.example.logging.Logging;
import com.example.models.Reimbursement;

public class ReimbursementService {

	private ReimbursementDao rDao;
	
	public ReimbursementService(ReimbursementDao rDao) {
		this.rDao = rDao;
	}
	
	public Reimbursement createReimbursement(double amount, String desc, int type_id, int author_id) {
		
		Reimbursement r = new Reimbursement(amount,desc,type_id,author_id);
		
		try {
			rDao.createReimbursement(r);
			Logging.logger.info("New Reimbursement was created");
		} catch (SQLException e) {
			e.printStackTrace();
			Logging.logger.warn("Reimbursement attempted to be created that already exists in the database");
			throw new ReimbursementAlreadyExistsException();
		}		
		return null;
	}
	
	public List<Reimbursement> getAllReimbs(){
		
		Logging.logger.info("Reimbursements were requested");
		return rDao.getAllReimbursements();
	}
	
	public List<Reimbursement> getAllStat(int stat){
		Logging.logger.info("Reimbursements of status were requested");
		return rDao.getAllReimbursementsStatus(stat);
	}
	
	public List<Reimbursement> findUsersReimbursement(int authorId) {
		
		Logging.logger.info("Reimbursements attatched to user were found");
		return rDao.getAllReimbursementsUser(authorId);
	}
	
	public List<Reimbursement> getAllReimbsType(int type_id){
		
		Logging.logger.info("Reimbursements of specific type were requested");
		return rDao.getAllReimbursementsType(type_id);
	}
	
	public Reimbursement findReimbursement(int reimbursementId) {
		Reimbursement r = null;
		
		r = rDao.findReimbursement(reimbursementId);
		if(r.getId()==0) {
			Logging.logger.warn("tried opening reimbursement that doesn't exist");
			throw new ReimbursementDoesNotExistException();
		}	
		else {
			Logging.logger.info("Reimbursement attatched to id was found");
			return r;
		}
	}

	
	public void updateReimbursement(Reimbursement r) {
		
		try {
			rDao.updateReimbursement(r);
			Logging.logger.info("Reimbursement updated");
			} catch(ReimbursementDoesNotExistException e) {
				e.printStackTrace();
				Logging.logger.warn("Attemp to update reimbursement that doesn't exist");

			}
		
	}
	
	public void updateStatus(Reimbursement r) {
		
		try {
			rDao.statusReimbursement(r);
			Logging.logger.info("Reimbursement status updated");
			} catch(ReimbursementDoesNotExistException e) {
				e.printStackTrace();
				Logging.logger.warn("Attemp to update status of a reimbursement that doesn't exist");

			}
		
	}
	
	public String checkStatus(Reimbursement r) {
		
		if(r.getStatus_id()==1)
		{
			return "pending";
		}
		if(r.getStatus_id()==2)
		{
			return "approved";
		}
		else {
			return "denied";
		}
		
	}
	
	public void deleteReimbursement(Reimbursement r) {
		
		try {
			rDao.deleteReimbursement(r);
			Logging.logger.info("Reimbursement deleted");
			} catch(ReimbursementDoesNotExistException e) {
				e.printStackTrace();
				Logging.logger.warn("Reimbursement deleted that didn't exist");

			}	
	}
	

	
}
