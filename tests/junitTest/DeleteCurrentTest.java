package junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portfolioDAO.CustomerDAO;
import portfolioModels.CurrentHoldings;
import portfolioModels.History;

public class DeleteCurrentTest {

CustomerDAO cDAO = new CustomerDAO();
	
	
	int userid = 61;
	String stocksym = "IBM";
	String stockname = "testing";
	String ordertype = "Buy";
	int numshares = 100;
	Double price = 99.9;
	boolean recordToDelete;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyy");
	
	@Before
	public  void setUp() throws SQLException, ParseException {
		
		int done = 0;
		Date txdate = dateFormat.parse("09-12-2018");
			
		CurrentHoldings choldings = new CurrentHoldings();
		choldings.setCustomerid(61);
		choldings.setStocksym(stocksym);
		choldings.setStockname(stockname);
		choldings.setNumshares(numshares);
		choldings.setAvgprice(price);
		choldings.setTxdate(txdate);
		done = cDAO.insertCurrent(choldings);  //insert into Current to delete later and test delete done
		recordToDelete = true;
	}
	
	@Test
	public void testDeletePosition() throws SQLException {
		int done = 0;
		if(recordToDelete) {
			done = cDAO.deletePosition(userid, stocksym);
		}
		assertThat(done,is(1));  
	}
	

		

}
