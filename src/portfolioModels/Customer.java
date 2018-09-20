package portfolioModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import customAnnotations.EmailConstraint;



public class Customer {
	
	
	@NotNull
	@Size(min = 3, max = 30, message = "User name must be between {2} and {1}")
	private String username;
	
	@NotNull
	@Size(min = 3, max = 30, message = "Password name must be between {2} and {1}")
	private String password;
	
	@NotNull
    @Min(1000)
	private double cashdeposit;
	
	private double gainloss;
	
	@NotNull
	@Size(min = 3, max = 30, message = "User name must be between {2} and {1}")
	private String fullname;
	
	@EmailConstraint
	private String email;
	private String address;
	
	@Size(min = 3, message ="phone number entered [${validatedValue}] is invalid. It must have at least {min} digits")
	private String telephone;
	
	
	public Customer() {
		super();
		this.username = "";
		this.password = "";
		this.cashdeposit = 0.0;
		this.gainloss = 0.0;
		this.fullname = "";
		this.email = "";
		this.address = "";
		this.telephone = "";
	}


	public Customer(String username, String password, double cashdeposit, double gainloss, String fullname,
			String email, String address, String telephone) {
		super();
		this.username = username;
		this.password = password;
		this.cashdeposit = cashdeposit;
		this.gainloss = gainloss;
		this.fullname = fullname;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public double getCashdeposit() {
		return cashdeposit;
	}


	public void setCashdeposit(double cashdeposit) {
		this.cashdeposit = cashdeposit;
	}


	public double getGainloss() {
		return gainloss;
	}


	public void setGainloss(double gainloss) {
		this.gainloss = gainloss;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
