package portfolioInterfaces;

import java.util.List;

import portfolioModels.CurrentHoldings;
import portfolioModels.StockTable;


public interface StockDAOI {
	
	public List<StockTable> getStockData ();
	public double getStockPrice(String symbol);
	public StockTable getOneStockInfo(String symbol);
	


	

}
