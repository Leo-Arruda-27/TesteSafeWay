
public class Product {
	
	private Integer productId;
	private String productName;
	private Integer productQuantity;
	private Double productPrice;
	private Company productCompany;

	public Product(Integer productId,String productName, Integer productQuantity, Double productPrice, Company productCompany) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productCompany = productCompany;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Company getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(Company productCompany) {
		this.productCompany = productCompany;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}
