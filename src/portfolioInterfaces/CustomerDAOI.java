package portfolioInterfaces;

import java.util.List;

import portfolioModels.CurrentHoldings;
import portfolioModels.Customer;
import portfolioModels.History;

public interface CustomerDAOI {
	

	
	
	public List<CurrentHoldings> getCurrentHoldings(int userid);
	public List<History> getTransactHistory(int userid);
	public CurrentHoldings getStockPosition(int id, String symbol);
	
	public int deletePosition(int userid, String symbol);
	public int deleteHistory(int userid, String symbol);
	
	public double getCashBalance(String username);	
	public int updateCash(int userid, double netcash);	
	public int updateGainLoss(int userid, double profitloss);
	public double getGainLoss(String username);
	
	
	
	
	public int insertHistory(History tradeinsert);
	public int insertCurrent(CurrentHoldings addtrade);
	public int updateCurrent(CurrentHoldings updatetrade);
	

}
