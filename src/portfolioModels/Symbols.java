package portfolioModels;

public class Symbols {
	
	private String stocksym;
	private String stockname;
	
	
	
	public Symbols() {
		super();
		this.stocksym = "";
		this.stockname = "";
	}



	public Symbols(String stocksym, String stockname) {
		super();
		this.stocksym = stocksym;
		this.stockname = stockname;
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
	
	

}