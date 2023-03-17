
public class Company {
	
	private Integer companyId;
	private String companyName;
	private String companyTaxIdentifier;
	private Double companyRate;
	private double companyBalance;

	public Company() {
		super();
	}

	public Company(Integer companyId, String companyName, String companyTaxIdentifier, Double companyRate) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyTaxIdentifier = companyTaxIdentifier;
		this.companyRate = companyRate;
		this.companyBalance = 0.0;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCnpjTaxIdentifier() {
		return companyTaxIdentifier;
	}

	public void setCnpjTaxIdentifier(String cnpjTaxIdentifier) {
		this.companyTaxIdentifier = cnpjTaxIdentifier;
	}

	public Double getCompanyRate() {
		return companyRate;
	}

	public void setCompanyRate(Double companyRate) {
		this.companyRate = companyRate;
	}

	public double getCompanyBalance() {
		return companyBalance;
	}

	public void setCompanyBalance(Double companyBalance) {
		this.companyBalance = companyBalance;
	}

}
