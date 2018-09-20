package portfolioModels;

import java.util.Date;

public class CurrentHoldings {
	
	private int customerid;
	private String stocksym;
	private String stockname;
	private int numshares;
	private double avgprice;
	private Date txdate;
		

	public CurrentHoldings() {
		super();
		this.customerid = 0;
		this.stocksym = "";
		this.stockname = "";
		this.numshares = 0;
		this.avgprice = 0.0;
		this.txdate = new Date();
	}


	public CurrentHoldings(int customerid, String stocksym, String stockname, int numshares, double avgprice,
			Date txdate) {
		super();
		this.customerid = customerid;
		this.stocksym = stocksym;
		this.stockname = stockname;
		this.numshares = numshares;
		this.avgprice = avgprice;
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


	public int getNumshares() {
		return numshares;
	}


	public void setNumshares(int numshares) {
		this.numshares = numshares;
	}


	public double getAvgprice() {
		return avgprice;
	}


	public void setAvgprice(double avgprice) {
		this.avgprice = avgprice;
	}


	public Date getTxdate() {
		return txdate;
	}


	public void setTxdate(Date txdate) {
		this.txdate = txdate;
	}
	
	
	
}