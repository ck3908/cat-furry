package portfolioInterfaces;

import java.util.List;

import portfolioModels.CurrentHoldings;
import portfolioModels.Customer;
import portfolioModels.History;

public interface CustomerDAOI {
	

	
	public int deleteHistory(int userid, String symbol);
	public List<CurrentHoldings> getCurrentHoldings(int userid);
	public List<History> getTransactHistory(int userid);
	public double getCashBalance(String username);
	public CurrentHoldings getStockPosition(int id, String symbol);
	public int insertHistory(History tradeinsert);
	public int insertCurrent(CurrentHoldings addtrade);
	public int updateCurrent(CurrentHoldings updatetrade);
	public int updateCash(int userid, double netcash);
	public int deletePosition(int userid, String symbol);
	public int updateGainLoss(int userid, double profitloss);
	public double getGainLoss(String username);

}
