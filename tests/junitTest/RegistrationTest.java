package junitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import portfolioDAO.RegisterDAO;

public class RegistrationTest {
	
	String username = "batman";
	String password = "robin";
	double cashdeposit = 8888.0;
	String fullname = "batman";
	String email = "bat@cave.com";
	String address = "bat world";
	String telephone = "12345";
	double gainloss = 0.0;
	boolean recordToDelete = true;
	RegisterDAO rDAO = new RegisterDAO();

	@Test
	public void testRegistration() {
		boolean done = false;
		done = rDAO.registration(username, password, cashdeposit, fullname, email, address, telephone);
		assertThat(done, equalTo(true));
	}
	
	@After
	public void deleteTestRecord() {
		int done = 0;
		if(recordToDelete) {
			done = rDAO.deleteRegistration(username);
		}
	}
}
