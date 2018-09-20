package springwork.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import portfolioDAO.CustomerDAO;
import portfolioDAO.StockDAO;
import portfolioModels.CurrentHoldings;
import portfolioModels.Customer;
import portfolioModels.History;
import portfolioModels.StockTable;
import portfolioModels.Symbols;

@SessionAttributes({"stockdetail","tradedata","cashposition","stockposition","usersession","useridsession"}) //make this detail available for preparetrade and execute trade during session
@Controller
public class StockController {
//	@ModelAttribute("historyform")
//	public History setuphistory() {
//		return new History();
//	}
	
	
	//this one works basic form but can't use any input buttons
	@ModelAttribute("listsymbols")
	@RequestMapping(value = "/gettradepage", method = RequestMethod.GET)   //replace getsymbols
	public ModelAndView xxgetStockSym(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> listsymbols = new ArrayList<String>();
		List<StockTable> stockinfo = new ArrayList<StockTable>();
		StockDAO stockDAO = new StockDAO();
		stockinfo = stockDAO.getStockData();
		for (int i = 0; i < stockinfo.size(); i++) {
			listsymbols.add(stockinfo.get(i).getstocksym());
		}
		ModelAndView mav = new ModelAndView("tradestocks");
		mav.addObject("message","working");
		mav.addObject("listsymbols",listsymbols); 
		return mav;
	
	}	
	
	
	@RequestMapping(value = "/viewcash", method = RequestMethod.GET)
	public ModelAndView customercash(@SessionAttribute("usersession") String user, @SessionAttribute("useridsession") int userid) {
		CustomerDAO customerDAO = new CustomerDAO();
		double cashbalance = customerDAO.getCashBalance(user);
	//	double profitloss = customerDAO.getGainLoss(user);
		ModelAndView mav = new ModelAndView("cashmgmt");
		mav.addObject("username",user);
		mav.addObject("cash",cashbalance);
//		mav.addObject("gainloss",profitloss);
		return mav;		
	}
	
	@RequestMapping(value = "/updatecashaccount", method = RequestMethod.POST)
		public ModelAndView updatecashacct(HttpServletRequest request,
				HttpServletResponse response, @SessionAttribute("cashposition") double cashpos,  @SessionAttribute("useridsession") int userid) {
			String username=request.getParameter("username");  
			String cashmove=request.getParameter("changecash");
			String message ="";
			double cashchange = 0.0;
			if (cashmove.endsWith("/")) {
				cashmove = cashmove.substring(0, cashmove.length() - 1);
				}
			cashchange = Double.valueOf(cashmove);
			if ((cashchange+cashpos) < 0.0) { //implies it is a withdrawal more than the account
				message = "Error: Withrawal is more than your account";
				ModelAndView mav = new ModelAndView("cashmgmt");
				mav.addObject("username",username);
				mav.addObject("cash",cashpos);
				mav.addObject("message",message);
				return mav;
			}
			else {  //make the withdrawal or deposit
				CustomerDAO customerDAO = new CustomerDAO();
				double netcash = cashchange+cashpos; //now cashpos has changed after this! referencing it later has different value
				int done = customerDAO.updateCash(userid, cashchange);
				double newcashpos = customerDAO.getCashBalance(username);  //get cashpos from database
				ModelAndView mav = new ModelAndView("cashmgmt");
				message = "Cash management successful";
				mav.addObject("username",username);
				mav.addObject("cash",newcashpos);
				mav.addObject("message",message);
				return mav;
			}
		
		}
	
	
	@RequestMapping(value = "/getportfolio", method = RequestMethod.GET)
	public ModelAndView customerportfolio(@SessionAttribute("usersession") String user, @SessionAttribute("useridsession") int userid) { // need session user name and id?
		CustomerDAO customerDAO = new CustomerDAO();
		double cashbalance = 0.0;
		double profitorloss = 0.0;
		double marketvalue = 0.0;
		DecimalFormat df = new DecimalFormat("#.00");
		
		List<CurrentHoldings> customerHoldings = new ArrayList<CurrentHoldings>();
		
		System.out.println(" do I get session userid here?"+userid);
		// current portfolio add to model and view and sent to jsp page done
		customerHoldings = customerDAO.getCurrentHoldings(userid);
		
		for (int i=0;i<customerHoldings.size();i++) {
			marketvalue = marketvalue + customerHoldings.get(i).getNumshares()*customerHoldings.get(i).getAvgprice();
		}
		marketvalue = Math.round(marketvalue*100)/100; // essentially round to 2 places
		cashbalance = customerDAO.getCashBalance(user);
		profitorloss = customerDAO.getGainLoss(user);
		
		ModelAndView mav = new ModelAndView("portfolio");
		mav.addObject("username",user);
		mav.addObject("cash",cashbalance);
		mav.addObject("gainloss",profitorloss);
		mav.addObject("totalmarketvalue",marketvalue);
		mav.addObject("stockholdings",customerHoldings);
		return mav;
		
	}
	
	@RequestMapping(value = "/gethistory", method = RequestMethod.GET)
	public ModelAndView portfoliohistory(@SessionAttribute("usersession") String user, @SessionAttribute("useridsession") int userid) { // need session user name and id?
		CustomerDAO customerDAO = new CustomerDAO();
		List<History> customerHistory = new ArrayList<History>();
		
		System.out.println(" do I get session userid here?"+userid);
		// current portfolio add to model and view and sent to jsp page done
		customerHistory = customerDAO.getTransactHistory(userid);
		
		ModelAndView mav = new ModelAndView("tradehistory");
		mav.addObject("username",user);
		mav.addObject("historytrades",customerHistory);
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/executetrade", method = RequestMethod.POST)
	public ModelAndView updatetradeinfo(@SessionAttribute("tradedata") History tradeinfo, @SessionAttribute("stockposition") CurrentHoldings stockpos, @SessionAttribute("cashposition") double cashpos) {	//grab the object from session attributes
		
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd"); //may need this, keep for time being

		
		//insert trade to customer's history database
		CustomerDAO customerDAO = new CustomerDAO();
		int done = 0;
		double cashtrade = 0;
		done = customerDAO.insertHistory(tradeinfo); //update history table holding all trades by customer
		
		// figure out total value of trade
		double tradevalue = tradeinfo.getPrice()*tradeinfo.getNumshares();
		
		if (tradeinfo.getOrdertype().equals("Buy")) { // new position or add to existing position												
			//check if new position
			System.out.println("buy order triggered");
			if (stockpos.getNumshares() == 0) { // since no shares existing, then new position - just update current portfolio
				//build new stock position from tradeinfo
				stockpos.setCustomerid(tradeinfo.getCustomerid());
				stockpos.setStocksym(tradeinfo.getStocksym());
				stockpos.setNumshares(tradeinfo.getNumshares());
				stockpos.setAvgprice(tradeinfo.getPrice());
				stockpos.setTxdate(tradeinfo.getTxdate());
				stockpos.setStockname(tradeinfo.getStockname());				
				done = customerDAO.insertCurrent(stockpos);
			}
			else { // there is an existing position so figure out average price of holdings
				double currentvalue = stockpos.getAvgprice()*stockpos.getNumshares();				
				double newtotalvalue = currentvalue+tradevalue;
				int totalshares = stockpos.getNumshares() + tradeinfo.getNumshares();
				double avgprice = newtotalvalue / totalshares;
				// modify certain stockpos information to update current portfolio holding of that stock
				stockpos.setNumshares(totalshares);
				stockpos.setAvgprice(avgprice);
				stockpos.setTxdate(tradeinfo.getTxdate());  //set new date to current just for simplicity
				done = customerDAO.updateCurrent(stockpos);		
			}
			// after buy, update cash position - deduct purchase price from cash account
			cashtrade = -tradevalue;
		// move update cashtrade to bottom					
		}
		else {  // sell order

			if (stockpos.getNumshares() == tradeinfo.getNumshares()) { // sell entire position, delete from currentholdings
				done = customerDAO.deletePosition(tradeinfo.getCustomerid(), tradeinfo.getStocksym());
			}
			else { // sell partial position
				int netstockposition = stockpos.getNumshares()-tradeinfo.getNumshares();  
				stockpos.setNumshares(netstockposition); //update net shares in position
				done = customerDAO.updateCurrent(stockpos);				
			}			
			cashtrade = tradevalue; //proceeds from trade added to cash position 		
			double profitloss = tradeinfo.getNumshares()*(tradeinfo.getPrice() - stockpos.getAvgprice());
			
			//update customer profit and loss position
			done = customerDAO.updateGainLoss(tradeinfo.getCustomerid(), profitloss);
			
		}
		done = customerDAO.updateCash(tradeinfo.getCustomerid(), cashtrade);		
	
		System.out.println("now print trade data");
		System.out.println("do I get these variables customerid "+tradeinfo.getCustomerid());
		System.out.println("do I get these variables stockname "+tradeinfo.getStockname());
		System.out.println("do I get these variables stocksym "+tradeinfo.getStocksym());
		System.out.println("do I get these variables num shares "+tradeinfo.getNumshares());
		System.out.println("do I get these variables price "+tradeinfo.getPrice());
		System.out.println("do I get these variables order type "+tradeinfo.getOrdertype());
		System.out.println("do I get these variables txdate "+tradeinfo.getTxdate());
//		System.out.println("do I get these variables txdate "+newFormat.format(tradeinfo.getTxdate()));
		
		System.out.println("now print current holdings");
		
		System.out.println("cash postion is "+cashpos);
		
		System.out.println("do I get these variables customerid "+stockpos.getCustomerid());
		System.out.println("do I get these variables stockname "+stockpos.getStockname());
		System.out.println("do I get these variables stocksym "+stockpos.getStocksym());
		System.out.println("do I get these variables num shares "+stockpos.getNumshares());
		System.out.println("do I get these variables price "+stockpos.getAvgprice());
		System.out.println("do I get these variables txdate "+stockpos.getTxdate());
	
	ModelAndView mav = new ModelAndView("home");
	return mav;
	}
	
	
	
	@RequestMapping(value = "/validatetrade", method = RequestMethod.POST)
	public ModelAndView checkiftradeable(@ModelAttribute("historyform") History history, BindingResult result, @SessionAttribute("usersession") String current_user, @SessionAttribute("useridsession") int current_userid,
			 ModelMap model) {
		
		System.out.println("at validate trade username is "+current_user);
		System.out.println("at validate trade customerid is "+current_userid);
	
	//  String current_user = "emmi";
	//	int current_id = 24;
		boolean tradeable = false;
		int shares = history.getNumshares();  // number of shares to trade
		double tradesize = shares*history.getPrice(); //total size of the trade
		
		//prepare to validate by getting customer current holding on the stock, if any
		CurrentHoldings customerHolding = new CurrentHoldings();
		CustomerDAO customerPosition = new CustomerDAO();
		customerHolding = customerPosition.getStockPosition(current_userid,history.getStocksym());
		double current_cash = customerPosition.getCashBalance(current_user);
		
		// validate if valid trade making sure shares are positive number
		if (shares <= 0 ) {
			model.addAttribute("errormsg","shares has to be more than 0");
			ModelAndView mav = new ModelAndView("tradethestock");
			return mav;
		}
		
		if (history.getOrdertype().equals("Buy")) {  //buy order						
			if (tradesize > current_cash) {
				model.addAttribute("errormsg","you do not have enough funds to do this transaction");
				ModelAndView mav = new ModelAndView("tradethestock");
				return mav;
			}
			else {
				tradeable = true;	//for this buy order			
			}
		}
		else { // sell order - by default since it is only 2 selection for user to pick			
			if (shares > customerHolding.getNumshares()) { //error: customer wants to sell more shares than they own - not allowed
				model.addAttribute("errormsg","your do not have that many shares to sell");
				ModelAndView mav = new ModelAndView("tradethestock");
				return mav;
			}
			else { 
				tradeable = true;	//for this sell order			
			}
		}
		
		if (tradeable) { //prepare to let the user confirm the final details
			history.setCustomerid(current_userid);  //now pass the customerid as well to complete the tradedata
			model.addAttribute("txvalue", tradesize);
			model.addAttribute("cashposition", current_cash);
			model.addAttribute("stockposition", customerHolding); //this could be empty first time around
			model.addAttribute("tradedata",history);  //this should persist to other controllers?
			ModelAndView mav = new ModelAndView("confirmtrade");
			return mav;
		}
		else {  //untradeable - may or may not need this condition since checks done above - might not be reachable
			ModelAndView mav = new ModelAndView("home");
			return mav;
		}
	}

	
	
	//same as above in method format
//	@RequestMapping(value = "/validatetrade2222", method = RequestMethod.POST)
//	public String updatehistory22222(@ModelAttribute("historyform") History history, BindingResult result,
//			 ModelMap model) {
//		System.out.println("at validatetrade");
//		System.out.println("do I get these variables stockname "+history.getStockname());
//		System.out.println("do I get these variables stocksym "+history.getStocksym());
//		System.out.println("do I get these variables num shares "+history.getNumshares());
//		System.out.println("do I get these variables price "+history.getPrice());
//		System.out.println("do I get these variables order type "+history.getOrdertype());
//		System.out.println("do I get these variables txdate "+history.getTxdate());
//		return "home";
//	}
	
	@RequestMapping("/preparetrade")
	public ModelAndView preparetrade(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("tradethestock");
		//do I need to set up get parameters?
		//String symbol = request.getParameter(name)
		return mav;
	}
	
	
	//need to get this fix, not use for time being
		@ModelAttribute("listysymbols")
		@RequestMapping(value = "/settradepage", method = RequestMethod.GET)   //replace getsymbols
		public ModelAndView getStockSym(HttpServletRequest request,
				HttpServletResponse response) {
//		public ModelAndView getStockSym(HttpServletRequest request,
//				HttpServletResponse response) {
			List<Symbols> listsymbols = new ArrayList<Symbols>();
			List<StockTable> stockinfo = new ArrayList<StockTable>();
			StockDAO stockDAO = new StockDAO();
			stockinfo = stockDAO.getStockData();
			System.out.println("size is "+stockinfo.size());
			for (int i = 0; i < stockinfo.size(); i++) {				
				Symbols symbol = new Symbols();
				symbol.setStocksym(stockinfo.get(i).getstocksym());
				symbol.setStockname(stockinfo.get(i).getstockname());
				listsymbols.add(symbol);
			}
			request.getSession().setAttribute("listsymbols",listsymbols); //make it available to next call
			ModelAndView mav = new ModelAndView("tradestocks");
			mav.addObject("message","");
			mav.addObject("listsymbols",listsymbols);
			return mav;
		
		}

	
	@ModelAttribute("listysymbols")
	@RequestMapping(value = "/getprice", method = RequestMethod.POST)  //changed to get from post 91018
	public ModelAndView getStockPrice(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("inside getprice");

		String stocksym= request.getParameter("symbol");
		
		double stockprice = 0.0;

//		StockDAO stockDAO = new StockDAO();
//		stockprice = stockDAO.getStockPrice(stocksym);
//		Random r=new Random();
//		stockprice = stockprice*(r.nextInt(500)/100)*(r.nextInt(2)-1);  //assume price changes from 1% to 5%
			

//		request.setAttribute("stockprice", stockprice);
//		request.getRequestDispatcher("tradepage.jsp").forward(request, response);
	

		ModelAndView mav = new ModelAndView("tradestocks");  //use tradepage2 for time being
		mav.addObject("stock_price",stockprice); 
		mav.addObject("stock_sym",stocksym);  //keep selected symbol
		return mav;
//		
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
	
	
	
	
//	@ModelAttribute("listysymbols")
	@RequestMapping(value = "/getdetail", method = RequestMethod.POST)  //changed to get from post 91018
	public ModelAndView getStockDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		System.out.println("inside getdetail");

		String stocksym= request.getParameter("symbol");
		
		StockTable stockdetail = new StockTable();

		StockDAO stockDAO = new StockDAO();
		stockdetail = stockDAO.getOneStockInfo(stocksym);
		
		// 092018 added change here to generate random prices to micmic price movements
		double stockprice = stockdetail.getlastprice();
		Random r=new Random();
		double dnum = r.nextInt(10)/100.0;
		System.out.println("dnum price after random is "+dnum);
		stockprice = stockprice*(1+dnum); //assume price changes from 1% to 10%
		stockprice = (double) Math.round(stockprice*100)/100; // round to 2 decimal places
		System.out.println("stock price after random is "+stockprice);
		stockdetail.setlastprice(stockprice);	// modify last price

//		request.setAttribute("stockprice", stockprice);
//		request.getRequestDispatcher("tradepage.jsp").forward(request, response);
	
		request.setAttribute("active","on");  //turn on input field submit button
		model.addAttribute("stockdetail",stockdetail);   //to pass parameters between controllers later on by referencing on session attribute on top
		ModelAndView mav = new ModelAndView("tradestocks");  //use tradepage2 for time being
//		mav.addObject("stock_price",stockdetail.getlastprice());  use random instead
		mav.addObject("stock_price",stockprice);  // stockprice is now random off base price
		mav.addObject("stock_sym",stockdetail.getstocksym());//keep selected symbol
		mav.addObject("stock_name",stockdetail.getstockname());//to pass to view
		return mav;
//		
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

