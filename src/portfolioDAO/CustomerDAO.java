package portfolioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import portfolioDAO.OracleConnection;
import portfolioInterfaces.CustomerDAOI;
import portfolioModels.CurrentHoldings;
import portfolioModels.Customer;
import portfolioModels.History;



public class CustomerDAO implements CustomerDAOI {
	
	
	
	@Override
	public int insertHistory(History tradeinsert) {
		
		Connection conn = null;
		String sql = "insert into history (customerid,stocksym, ordertype,numshares,price,txdate,stockname) values (?,?,?,?,?,?,?)";
		int done = 0;
		
		System.out.println("now print inserthistory DAO");
		System.out.println("order type is "+tradeinsert.getOrdertype());
		System.out.println("do I get these variables customerid "+tradeinsert.getCustomerid());
		System.out.println("do I get these variables stockname "+tradeinsert.getStockname());
		System.out.println("do I get these variables stocksym "+tradeinsert.getStocksym());
		System.out.println("do I get these variables num shares "+tradeinsert.getNumshares());
		System.out.println("do I get these variables price "+tradeinsert.getPrice());
		System.out.println("do I get these variables order type "+tradeinsert.getOrdertype());
		System.out.println("do I get these variables before conversion to sql date txdate "+tradeinsert.getTxdate());
						
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1,tradeinsert.getCustomerid());
			ps.setString(2,tradeinsert.getStocksym());
			ps.setString(3,tradeinsert.getOrdertype());
			ps.setInt(4,tradeinsert.getNumshares());
			ps.setDouble(5,tradeinsert.getPrice());
			ps.setDate(6,new java.sql.Date(tradeinsert.getTxdate().getTime()));	 // convert java util date to sql friendly date usage		
			ps.setString(7,tradeinsert.getStockname());
			done = ps.executeUpdate();
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
				
		return done;							
	}	
			

	
	@Override
	public int insertCurrent(CurrentHoldings addtrade) {
		
		Connection conn = null;
		String sql = "insert into currentholdings (customerid,stocksym,numshares,avgprice,txdate,stockname) values (?,?,?,?,?,?)";
		int done = 0;
		
		System.out.println("now print insertCurrent DAO");
		System.out.println("do I get these variables customerid "+addtrade.getCustomerid());
		System.out.println("do I get these variables stockname "+addtrade.getStockname());
		System.out.println("do I get these variables stocksym "+addtrade.getStocksym());
		System.out.println("do I get these variables num shares "+addtrade.getNumshares());
		System.out.println("do I get these variables price "+addtrade.getAvgprice());
		System.out.println("do I get these variables order type "+addtrade.getTxdate());
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1,addtrade.getCustomerid());
			ps.setString(2,addtrade.getStocksym());
			ps.setInt(3,addtrade.getNumshares());
			ps.setDouble(4,addtrade.getAvgprice());
			ps.setDate(5,new java.sql.Date(addtrade.getTxdate().getTime()));	 // convert java util date to sql friendly date usage		
			ps.setString(6,addtrade.getStockname());
			
			done = ps.executeUpdate();
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
				
		return done;							
	}	
		
	@Override
	public int updateCurrent(CurrentHoldings updatetrade) {
		
		System.out.println("now print updateCurrent DAO");
		System.out.println("do I get these variables customerid "+updatetrade.getCustomerid());
		System.out.println("do I get these variables stockname "+updatetrade.getStockname());
		System.out.println("do I get these variables stocksym "+updatetrade.getStocksym());
		System.out.println("do I get these variables num shares "+updatetrade.getNumshares());
		System.out.println("do I get these variables price "+updatetrade.getAvgprice());
		System.out.println("do I get these variables order type "+updatetrade.getTxdate());
		
		Connection conn = null;
		String sql = "update currentholdings set numshares = ?, avgprice = ?, txdate = ? where customerid = ? and stocksym = ?";
		int done = 0;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1,updatetrade.getNumshares());
			ps.setDouble(2,updatetrade.getAvgprice());
			ps.setDate(3,new java.sql.Date(updatetrade.getTxdate().getTime()));	 // convert java util date to sql friendly date usage		
			ps.setInt(4,updatetrade.getCustomerid());
			ps.setString(5, updatetrade.getStocksym());
			done = ps.executeUpdate();
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
				
		return done;							
	}	
	
	
	@Override
	public int updateCash(int userid, double netcash) {
		int done = 0;
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			String sql = "update customer set cashdeposit = cashdeposit + ? where customerid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setDouble(1,netcash);
			ps.setInt(2,userid);			
			done = ps.executeUpdate();
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
				
		return done;							
	}
	
		
	@Override
	public int deletePosition(int userid, String symbol) {
		int done = 0;
		String sql = "delete from currentholdings where customerid = ? and stocksym = ?";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			ps.setString(2,symbol);
			System.out.println("at deletePostion, preparing to delete stock position");
			done = ps.executeUpdate();	//delete the record from currentholdings	
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
		return done;	
		
	}
	
	@Override
	public int updateGainLoss(int userid, double profitloss) {
		int done = 0;
		String sql = "update customer set gainloss = gainloss + ? where customerid = ?";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1,profitloss);
			ps.setInt(2,userid);
			done = ps.executeUpdate();
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
		return done;							
	}
	

	@Override
	public CurrentHoldings getStockPosition(int cust_id, String symbol) {
		CurrentHoldings getPosition = new CurrentHoldings();
		String sql = "select * from currentholdings where customerid = ? and stocksym = ?";
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,cust_id);
			ps.setString(2,symbol);
			System.out.println("at stockDAO, preparing to get stock position");
			ResultSet rs = ps.executeQuery();
			// there is only going to be one position of the symbol in any given portfolio
			// for example, buy or sell the shares again will simply add or deduct from the current position which will
			// only still show one record after updated
			if (rs.next()) { // check if there is a record of the holding
				getPosition.setCustomerid(rs.getInt("customerid"));
				getPosition.setStocksym(rs.getString("stocksym"));
				getPosition.setStockname(rs.getString("stockname"));
				getPosition.setNumshares(rs.getInt("numshares"));
				getPosition.setAvgprice(rs.getDouble("avgprice"));
				getPosition.setTxdate(rs.getDate("txdate"));			
			}
			else { getPosition.setNumshares(0);
			} // no current position in stock		}
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
		return getPosition;	
		
	}
	
	
	
		
	@Override
	
	public double getCashBalance(String username) {
	
		String sql = "select cashdeposit from customer where username = ?";
		double cashbalance = 0.0;
				
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cashbalance = rs.getDouble("cashdeposit");					

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
		return cashbalance;
	}
	
	@Override
	public double getGainLoss(String username) {
		String sql = "select gainloss from customer where username = ?";
		double profitloss = 0.0;
				
		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			profitloss = rs.getDouble("gainloss");					

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
		return profitloss;
	}
	
	
	@Override

	public int deleteHistory(int userid, String symbol) {
		int done = 0;
		Connection conn = null;
		String sql = "delete from history where customerid = ? and stocksym = ?";
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			ps.setString(2,symbol);
			System.out.println("at deleteHistory, preparing to delete stock position");
			done = ps.executeUpdate();	//delete the record from currentholdings	
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
		return done;
	}
	
	
	@Override

	public List<CurrentHoldings> getCurrentHoldings(int userid) {
		ArrayList<CurrentHoldings> currentHoldings = new ArrayList<CurrentHoldings>();
		String sql = "select * from currentholdings where customerid = ?";

		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CurrentHoldings stock = new CurrentHoldings();
				stock.setCustomerid(rs.getInt("customerid"));
				stock.setStocksym(rs.getString("stocksym"));
				stock.setStockname(rs.getString("stockname"));
				stock.setNumshares(rs.getInt("numshares"));
				stock.setAvgprice(rs.getDouble("avgprice"));
				stock.setTxdate(rs.getDate("txdate"));
				currentHoldings.add(stock);
			}		

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
		return currentHoldings;
	}

	@Override

	public List<History> getTransactHistory(int userid) {
		List<History> stockHoldings = new ArrayList<History>();

		String sql = "select * from history where customerid = ? order by txdate desc, stocksym asc";

		Connection conn = null;
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				History stock = new History();
				stock.setStocksym(rs.getString("stocksym"));
				stock.setStockname(rs.getString("stockname"));
				stock.setOrdertype(rs.getString("ordertype"));
				stock.setNumshares(rs.getInt("numshares"));
				stock.setPrice(rs.getDouble("price"));
				stock.setTxdate(rs.getDate("txdate"));
				stockHoldings.add(stock);
			}		

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
		return stockHoldings;
	}

}



