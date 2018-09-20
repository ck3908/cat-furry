package portfolioMain;

import java.util.ArrayList;
import java.util.List;

import portfolioDAO.StockDAO;
import portfolioModels.StockTable;
import portfolioModels.History;

public class TestStock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		List <Stocks> myHistList = new ArrayList<Stocks>();
//		int userid = 1;
//		
//		UserDAO holder = new UserDAO();
//		myHistList = holder.getTransactHistory(userid);
//		
//
//		System.out.println("symbol       shares        price       transact date");
//		System.out.println("====================================================");
//		for (int k = 0; k < myHistList.size(); k++) {
//			System.out.println(myHistList.get(k).getStock_symbol()+"    "+myHistList.get(k).getShares()
//					+"      "+myHistList.get(k).getTransact_price()+"          "+myHistList.get(k).getTransact_date());
//		}
//		
		
		List <StockTable> getAllStock = new ArrayList<StockTable>();
		StockDAO stockinfo = new StockDAO();
		getAllStock = stockinfo.getStockData();
		
		System.out.println("there are "+getAllStock.size() + "number of records fetched");
		
		System.out.println("symbol       name        price       quotepage                         sector           industry");
		System.out.println("=====================================================================================================");
//		for (int i = 0; i < getAllStock.size(); i++) {
//			System.out.println(getAllStock.get(i).getStock_symbol()+"    "+getAllStock.get(i).getStock_name()
//					+"      "+getAllStock.get(i).getLast_price()+"          "+getAllStock.get(i).getQuote_page()
//					+"         "+getAllStock.get(i).getSector()+"             "+getAllStock.get(i).getIndustry());
//		}

	}

}
