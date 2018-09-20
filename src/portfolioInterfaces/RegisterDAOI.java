package portfolioInterfaces;

import java.util.HashMap;

public interface RegisterDAOI {
	
//	public String getpassword (String username);
	
	public HashMap<String,String> getpwid (String username);
	
	public boolean registration(String username, String password, double cashdeposit, String fullname, String email, String address,
			String telephone);
	
}
