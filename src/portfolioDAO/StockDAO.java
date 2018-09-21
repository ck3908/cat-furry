package portfolioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import portfolioInterfaces.StockDAOI;
import portfolioModels.CurrentHoldings;
import portfolioModels.StockTable;


public class StockDAO implements StockDAOI {
	
	

	@Override
	public StockTable getOneStockInfo(String symbol) {
		StockTable thisStock = new StockTable();
		String sql = "select * from stocktable where stocksym = ?";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,symbol);
//			System.out.println("at stockDAO, preparing to get data");
			ResultSet rs = ps.executeQuery();
			rs.next();
			thisStock.setstocksym(rs.getString("stocksym"));
			thisStock.setstockname(rs.getString("stockname"));
			thisStock.setlastprice(rs.getDouble("lastprice"));
			thisStock.setquotepage(rs.getString("quotepage"));
			thisStock.setSector(rs.getString("sector"));
			thisStock.setIndustry(rs.getString("industry"));			
		}		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(!conn.equals(null)) { 
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return thisStock;	
		
	}
	
	
	@Override
	public double getStockPrice(String stocksym) {
		double stockprice = 0.0;
//		System.out.print("stocksym is "+stocksym);
		String sql = "select lastprice from stocktable where stocksym = ?";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,stocksym);
//			System.out.println("at stockDAO, preparing to get data");
			ResultSet rs = ps.executeQuery();
			rs.next();
			stockprice = rs.getDouble("lastprice");			

		}		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(!conn.equals(null)) { 
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return stockprice;
	}

	@Override
	
	public List<StockTable> getStockData() {
		List<StockTable> getStockInfo = new ArrayList<StockTable>();
		String sql = "select * from stocktable order by stocksym asc";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StockTable StockInfo = new StockTable();				
				
				StockInfo.setstocksym(rs.getString("stocksym"));
				StockInfo.setstockname(rs.getString("stockname"));
				StockInfo.setlastprice(rs.getDouble("lastprice"));
				StockInfo.setquotepage(rs.getString("quotepage"));
				StockInfo.setSector(rs.getString("sector"));
				StockInfo.setIndustry(rs.getString("industry"));

				getStockInfo.add(StockInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(!conn.equals(null)) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return getStockInfo;		
	}	
	
	
};
