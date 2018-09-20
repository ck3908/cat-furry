package junitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portfolioDAO.CustomerDAO;
import portfolioModels.CurrentHoldings;
import portfolioModels.History;

public class DAOtest {
	
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
		
		History history = new History();
		history.setCustomerid(userid);
		history.setStockname(stockname);
		history.setStocksym(stocksym);
		history.setOrdertype(ordertype);
		history.setNumshares(numshares);
		history.setTxdate(txdate);
		history.setPrice(price);
		done = cDAO.insertHistory(history);
			
		CurrentHoldings choldings = new CurrentHoldings();
		choldings.setCustomerid(61);
		choldings.setStocksym(stocksym);
		choldings.setStockname(stockname);
		choldings.setNumshares(numshares);
		choldings.setAvgprice(price);
		choldings.setTxdate(txdate);
		done = cDAO.insertCurrent(choldings);
		recordToDelete = true;
	}
	
	@Test
	public void testGetStockPosition() throws SQLException, ParseException {
		CurrentHoldings stocks = cDAO.getStockPosition(userid,stocksym);
		assertThat(stocks.getStockname(),is("testing"));
		assertThat(stocks.getStocksym(),is("IBM"));
		assertThat(stocks.getNumshares(),is(100));
		assertThat(stocks.getAvgprice(),is(99.9));
		assertThat(stocks.getTxdate(),is(dateFormat.parse("09-12-2018")));		
	}
		
	
	@Test
	public void testGetTransactionHistory() throws SQLException {
		List<History> stocks = cDAO.getTransactHistory(userid);
		assertThat(stocks,  notNullValue());
	}
	
	@Test
	public void testGetCurrentHoldings() throws SQLException {
		List<CurrentHoldings> stocks = cDAO.getCurrentHoldings(userid);
		assertThat(stocks,  notNullValue());
	}

	@After
	public void testDeletePosition() {
		if(recordToDelete) {
			cDAO.deletePosition(userid, stocksym);
		}
		
	}
	
	@After
	public void testDeleteHistory() {
		if(recordToDelete) {
			cDAO.deleteHistory(userid, stocksym);
		}
		
	}
	
	
	
}
