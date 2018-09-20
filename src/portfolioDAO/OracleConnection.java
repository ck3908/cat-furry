package portfolioDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection {

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "stockuser";
		String pass = "mystock123";
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		return conn;

	}
}