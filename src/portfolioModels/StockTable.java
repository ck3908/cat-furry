package portfolioModels;

public class StockTable {
	
	private String stocksym;
	private String stockname;
	private double lastprice;
	private String quotepage;
	private String sector;
	private String industry;
	
	public StockTable() {
		super();
		this.stocksym = "";
		this.stockname = "";
		this.lastprice = 0.0;
		this.quotepage = "";
		this.sector = "";
		this.industry = "";
	}

	public StockTable(String stocksym, String stockname, double lastprice, String quotepage, String sector,
			String industry) {
		super();
		this.stocksym = stocksym;
		this.stockname = stockname;
		this.lastprice = lastprice;
		this.quotepage = quotepage;
		this.sector = sector;
		this.industry = industry;
	}

	
	public String getstocksym() {
		return stocksym;
	}

	public void setstocksym(String stocksym) {
		this.stocksym = stocksym;
	}

	public String getstockname() {
		return stockname;
	}

	public void setstockname(String stockname) {
		this.stockname = stockname;
	}

	public double getlastprice() {
		return lastprice;
	}

	public void setlastprice(double lastprice) {
		this.lastprice = lastprice;
	}

	public String getquotepage() {
		return quotepage;
	}

	public void setquotepage(String quotepage) {
		this.quotepage = quotepage;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
	
	}
	
	


