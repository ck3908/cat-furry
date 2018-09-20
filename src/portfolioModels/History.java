package portfolioModels;

import java.util.Date;


public class History {
	
	private int customerid;
	private String stocksym;
	private String stockname;
	private String ordertype;
	
	private int numshares;
	private double price;
	private Date txdate;
	
	
	public History() {
		super();
		this.customerid = 0;
		this.stocksym = "";
		this.stockname = "";
		this.ordertype = "";
		this.numshares = 0;
		this.price = 0.0;
		this.txdate = new Date();
	}


	public History(int customerid, String stocksym, String stockname, String ordertype, int numshares, double price,
			Date txdate) {
		super();
		this.customerid = customerid;
		this.stocksym = stocksym;
		this.stockname = stockname;
		this.ordertype = ordertype;
		this.numshares = numshares;
		this.price = price;
		this.txdate = txdate;
	}




	public int getCustomerid() {
		return customerid;
	}




	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}




	public String getStocksym() {
		return stocksym;
	}




	public void setStocksym(String stocksym) {
		this.stocksym = stocksym;
	}




	public String getStockname() {
		return stockname;
	}




	public void setStockname(String stockname) {
		this.stockname = stockname;
	}




	public String getOrdertype() {
		return ordertype;
	}




	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}




	public int getNumshares() {
		return numshares;
	}




	public void setNumshares(int numshares) {
		this.numshares = numshares;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public Date getTxdate() {
		return txdate;
	}




	public void setTxdate(Date txdate) {
		this.txdate = txdate;
	}
	

	
}
