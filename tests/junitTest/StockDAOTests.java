package junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import portfolioDAO.StockDAO;
import portfolioModels.StockTable;

public class StockDAOTests {
	
	StockDAO sDAO = new StockDAO();
	String symbol = "AAPL";
	
	//for time being price is fixed so we test that... with live data, remove this test

	@Test
	public void testGetOneStockInfo() {
		StockTable thisStock = new StockTable();
		thisStock = sDAO.getOneStockInfo(symbol);
		assertThat(thisStock.getstocksym(),is("AAPL"));
		assertThat(thisStock.getstockname(),is("Apple Inc."));
		assertThat(thisStock.getlastprice(),is(220.0));  //remove test when live data present
		assertThat(thisStock.getSector(),is("Technology"));
	}
	
	@Test
	public void testGetStockPrice() {
		double price = 0.0;
		price = sDAO.getStockPrice(symbol);
		assertThat(price,is(220.0));  //remove test when live data present
	}
	
	@Test
	public void testGetStockData() {
		List<StockTable> getStockInfo = new ArrayList<StockTable>();		
		getStockInfo = sDAO.getStockData();
		assertThat(getStockInfo, notNullValue());
	}

}
