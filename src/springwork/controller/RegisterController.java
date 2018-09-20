package springwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import portfolioDAO.RegisterDAO;
import portfolioModels.Customer;

@Controller
@RequestMapping("/")
@SessionAttributes("customerkey")
public class RegisterController {
	
	@ModelAttribute("customerkey")
	public Customer setUpUserForm() {
		return new Customer();
	}
	
	@RequestMapping(value = "/customerInfo", method=RequestMethod.POST)
	public ModelAndView user_info(@ModelAttribute("customerkey") @Valid Customer c, BindingResult errors) {

		if (errors.hasErrors()) 
		{ModelAndView mav = new ModelAndView("register");
		return mav;
		}
		ModelAndView mav = new ModelAndView("newcustomer");
		mav.addObject("username", c.getUsername()); //see if these  lines work
		mav.addObject("password", c.getPassword());
		mav.addObject("email",c.getEmail());
		mav.addObject("cashdeposit",c.getCashdeposit());
		mav.addObject("fullname",c.getFullname());
		mav.addObject("address",c.getAddress());
		mav.addObject("telephone",c.getTelephone());
		mav.addObject("gainloss",c.getGainloss());
//		mav.addObject("password", "1234567");
//		mav.addObject("email","q@f.com");
//		mav.addObject("cashdeposit",1500.0);
//		mav.addObject("fullname","mccd");
//		mav.addObject("address","12 st");
//		mav.addObject("telephone","123456");
		
		return mav;
	}

//	@RequestMapping(value = "/customer_confirm", method=RequestMethod.POST)
//	public ModelAndView user_info_confirm(@ModelAttribute("userkey") Customer c) {
//
//		ModelAndView mav = new ModelAndView("UserAccountPage");
//
//		return mav;
//	}

	@RequestMapping("/register")
	public ModelAndView register() { 	
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}



	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(HttpServletRequest request,
			HttpServletResponse response) {

		String fullname= request.getParameter("fullname");
		//	customer.setFullname(fullname);
		
		if (fullname.endsWith("/")) {
			fullname = fullname.substring(0, fullname.length() - 1);
			}	

		String username= request.getParameter("username");
		if (username.endsWith("/")) {
			username = username.substring(0, username.length() - 1);
			}	
		
		System.out.println("at registration mapping usernmae is"+username);

		String password= request.getParameter("password");
		
		if (password.endsWith("/")) {
			password = password.substring(0, password.length() - 1);
			}	
		
		//	customer.setPassword(password);
		String email=request.getParameter("email");
		if (email.endsWith("/")) {
			email = email.substring(0, email.length() - 1);
			}	
		//	customer.setEmail(email);
		String address=request.getParameter("address");
		if (address.endsWith("/")) {
			address = address.substring(0, address.length() - 1);
			}
		//	customer.setAddress(address);
		String telephone=request.getParameter("telephone");
		if (telephone.endsWith("/")) {
			telephone = telephone.substring(0, telephone.length() - 1);
			}
		System.out.println("at registration mapping telehpne is"+telephone);
		//	customer.setTelephone(telephone);
		
		String cash = request.getParameter("cashdeposit");
		if (cash.endsWith("/")) {
			cash = cash.substring(0, cash.length() - 1);
			}
		double cashdeposit= Double.valueOf(cash);
	//	double cashdeposit= Double.valueOf(request.getParameter("cashdeposit"));
		if (telephone.endsWith("/")) {
			telephone = telephone.substring(0, telephone.length() - 1);
			}
		System.out.println("at registration mapping cash deposit after conversion is"+cashdeposit);
		//	customer.setcashdeposit(cashdeposit);
		String message;
		Boolean registered = false;

		RegisterDAO registerDAO = new RegisterDAO();
		registered = registerDAO.registration( username,  password,  cashdeposit,  fullname,  email,  address,  telephone);

		if(registered){
//			message = "Welcome " +username + ".";
//			return new ModelAndView("login", 
//					"message", message);  
			return new ModelAndView("login");

		}else{
			message = "Registration Unsuccessful";
			return new ModelAndView("errorPage", 
					"message", message);

		}
		
//		if(registered){
//			message = "Welcome " +username + ".";
//			return new ModelAndView("welcome", 
//					"message", message);  
//
//		}else{
//			message = "registration unsuccessful";
//			return new ModelAndView("errorPage", 
//					"message", message);
//
//		}
		
		
		
	}
}
