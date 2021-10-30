package SystemDriver;

import com.example.dao.ReimbursementDao;
import com.example.dao.ReimbursementDaoDB;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.Reimbursement;
import com.example.models.User;
import com.example.services.ReimbursementService;
import com.example.services.UserService;

public class Driver {
	
	private static UserDao uDao = new UserDaoDB();
	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	
	private static UserService uServ = new UserService(uDao);
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UserDoesNotExistException, InvalidCredentialsException {
		

	}

}
