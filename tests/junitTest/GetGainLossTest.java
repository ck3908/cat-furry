package junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portfolioDAO.CustomerDAO;

public class GetGainLossTest {
	
	int userid = 84; // tester account was created with initial deposit of $10,000.0 via registration
	String username = "tester";
	double profitloss = 888.0;
	
	@Before
	public void updateGL() {
		CustomerDAO cDAO = new CustomerDAO();
		int done = 0;
		done = cDAO.updateGainLoss(userid, profitloss);
	}

	@Test
	public void testGetGainLoss() {
		CustomerDAO cDAO = new CustomerDAO();
		double pl = 0;
		pl = cDAO.getGainLoss(username);
		assertThat(pl,is(888.0));	
	}
	
	@After
	public void deductGL() {  // bring back to initial state
		CustomerDAO cDAO = new CustomerDAO();
		int done = 0;
		done = cDAO.updateGainLoss(userid, -profitloss);
	}
		

}
