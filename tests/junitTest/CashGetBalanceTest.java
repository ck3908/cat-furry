package junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import portfolioDAO.CustomerDAO;

public class CashGetBalanceTest {
	
	int userid = 84; // tester account was created with initial deposit of $10,000.0 via registration
	String username = "tester";
	

	@Test
	public void testGetCashBalance() {
		CustomerDAO cDAO = new CustomerDAO();
		double cash = cDAO.getCashBalance(username);
		assertThat(cash,is(10000.0));		
	}
		

}
