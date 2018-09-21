package junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portfolioDAO.CustomerDAO;

public class CashUpdateTest {
	
	int userid = 84; // tester account was created with initial deposit of $10,000.0
	String username = "tester";
	double changeInCash = 999; //deposit $999
	double changeBack = -999; // remove $999
	
	@Before
	public void updateCash() {
		CustomerDAO cDAO = new CustomerDAO();
		int done = 0;
		done = cDAO.updateCash(userid, changeInCash);
	}
	

	@Test
	public void testUpdateCash() {   //after updateCash now cash balance should be $10,999.0
		CustomerDAO cDAO = new CustomerDAO();
		double cash = cDAO.getCashBalance(username);
		assertThat(cash,is(10999.0));		
	}
	
	@After
	public void removeCash() {  //this is done to get back to original state for testing later
		CustomerDAO cDAO = new CustomerDAO();
		int done = 0;
		done = cDAO.updateCash(userid, changeBack);
	}

}
