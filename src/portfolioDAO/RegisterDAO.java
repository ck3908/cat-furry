package portfolioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import portfolioInterfaces.RegisterDAOI;

public class RegisterDAO implements RegisterDAOI{
	
	
	@Override
	public HashMap<String,String> getpwid (String username) {
		boolean validuser = false;
		String sql = "select password, customerid from customer where username = ?";
		Connection conn = null;
		String customerpw = "";
		int customerid = 0; //will need to convert to string later to map
		String stringcustid = ""; //string version of customerid
		HashMap<String,String> map = new HashMap<String,String>();
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			System.out.println("at retrieving password, preparing to get pw");
			ResultSet rs = ps.executeQuery();

			// must check if any results
			if (rs.next() == false) { //user entered wrong username
				customerpw = "invalid";  //send back to the controller a flag wrong info 
				stringcustid = "99";
			}
			else { // valid username - prepare to get data
				customerpw = rs.getString(1);
				customerid = rs.getInt(2);
				stringcustid = String.valueOf(customerid);  //convert to string in order to put in map		
				System.out.println("at retrieved password is "+customerpw);
				System.out.println("at retrieved customerid is "+customerid);
			}
			map.put("pwd", customerpw);
			map.put("cid", stringcustid);	
		}		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("at registerDAO, line 39 before crash");
			if(!conn.equals(null)) { System.out.println("at registerDAO, line 40 after crash");
			try {
				conn.close();
			} catch (SQLException e) {System.out.println("at registerDAO, sql exception occurred");
			e.printStackTrace();
			}
			}
		}
		return map;
	}

	
//	@Override
//	public String getpassword (String username) {
//		boolean validuser = false;
//		String sql = "select password from customer where username = ?";
//		Connection conn = null;
//		String customerpw = "";
//		try {
//			conn = OracleConnection.getConn();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1,username);
//			System.out.println("at retrieving password, preparing to get pw");
//			ResultSet rs = ps.executeQuery();
//			rs.next();
//			customerpw = rs.getString(1);
//			System.out.println("at retrieved password is "+customerpw);
//			
//		}		 catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("at registerDAO, line 39 before crash");
//			if(!conn.equals(null)) { System.out.println("at registerDAO, line 40 after crash");
//				try {
//					conn.close();
//				} catch (SQLException e) {System.out.println("at registerDAO, sql exception occurred");
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return customerpw;
//	}
	
	
	
	@Override
	public boolean registration (String username, String password, double cashdeposit, String fullname, String email, String address,
			String telephone)  {

		
		int inserted = 0;
		boolean done = false;
		double gainloss = 0.0; //initialize initial position for new registrant
		String sql = "insert into customer (username,  password,  cashdeposit,  fullname,  email,  address,  telephone, gainloss) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			System.out.println("at registerDAO, in try catch before conn connection");
			conn = OracleConnection.getConn();
			
			System.out.println("at registerDAO, after conn connection");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,BCrypt.hashpw(password, BCrypt.gensalt()));
			ps.setDouble(3,cashdeposit);
			ps.setString(4,fullname);
			ps.setString(5,email);
			ps.setString(6,address);
			ps.setString(7,telephone);
			ps.setDouble(8,gainloss);
			System.out.println("at registerDAO, preparing to insert data");
			inserted = ps.executeUpdate();
			if (inserted > 0) {
				done = true;
				System.out.println("inserted data!");
			}

		}		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("at registerDAO, line 39 before crash");
			if(!conn.equals(null)) { System.out.println("at registerDAO, line 40 after crash");
				try {
					conn.close();
				} catch (SQLException e) {System.out.println("at registerDAO, sql exception occurred");
					e.printStackTrace();
				}
			}
		}

		return done;
	}
	
	
	@Override
	public int deleteRegistration(String username) {
		int done = 0;
		Connection conn = null;
		String sql = "delete from customer where username = ?";
		try {
			conn = OracleConnection.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			done = ps.executeUpdate();	//delete the record from register - to support testing rollback	
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

}
