package springwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import portfolioDAO.StockDAO;


@Controller
public class HomeController {
	
	
	// not used - just for testing
	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}




//	@RequestMapping("/*")
	@RequestMapping(value = { "/*", "/home" }, 
			  method = RequestMethod.GET)	
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}


// not used just for testing
	@RequestMapping("/validate")
	public ModelAndView validate() {
		ModelAndView mav = new ModelAndView("validate");
		return mav;
	}


	
	//not used - just for testing
	@RequestMapping("/displayroute")
	public ModelAndView userPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("UserPage");
		HttpSession se = request.getSession();
		se.setAttribute("u", request.getParameter("name"));
		mav.addObject("user_name",request.getParameter("name"));
		return mav;

	}



}