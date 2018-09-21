package springwork.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import portfolioDAO.CustomerDAO;
import portfolioDAO.RegisterDAO;

@SessionAttributes({"useridsession","usersession","cashposition"}) 
@Controller
public class LoginController {
	
	@RequestMapping("/login") 
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/logout") 
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("usersession");
		session.removeAttribute("useridsession");
		session.invalidate();
		ModelAndView mav = new ModelAndView("home");
		mav.getModel().clear();
		return mav;
	}
	
	
	
	
	@RequestMapping("/loginprocess")  
	public ModelAndView loginprocess(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String username=request.getParameter("username");  
		String password=request.getParameter("password");
		
		String message;
		HashMap<String,String> pwidmap = new HashMap<String,String>();
		RegisterDAO userDAO = new RegisterDAO();
		pwidmap = userDAO.getpwid(username);
		//print out all the keys
		for(String value: pwidmap.values()) {
			System.out.println(value);
		}
		String getpw = pwidmap.get("pwd");
		String getcustid = pwidmap.get("cid");
		
		if (getpw.equals("invalid")) { // if username not on database then...
			message = "Wrong username or password, please try again or register";
			return new ModelAndView("login", 
					"message", message);
		}
		
		// otherwise continue
		int customerid = Integer.parseInt(getcustid);  //convert to integer
		System.out.println("in loginprocess converted customerid to integer "+customerid);
		System.out.println("encrypted pw is "+getpw);
		
	
//		if( password != null && !password.equals("") && 
//				password.equals(getpw))
		
		if (BCrypt.checkpw(password,getpw))
		{	
			HttpSession session = request.getSession();
			session.setAttribute("usersession", request.getParameter("username"));	
			model.addAttribute("usersession",request.getParameter("username"));
			model.addAttribute("useridsession",customerid);  //now put in session.attributes to access later
			
			//setup cashmgmt account for user to view to start trading
			CustomerDAO customerDAO = new CustomerDAO();
			double cashbalance = customerDAO.getCashBalance(username);
			model.addAttribute("cashposition",cashbalance);
			double profitloss = customerDAO.getGainLoss(username);
			ModelAndView mav = new ModelAndView("cashmgmt");
			mav.addObject("username",username);
			mav.addObject("cash",cashbalance);
			mav.addObject("gainloss",profitloss);
			message = "Welcome " +username + ".";
			return mav;  

		}else{
			message = "Wrong username or password.";
			return new ModelAndView("login", 
					"message", message);
		}
	}

}
