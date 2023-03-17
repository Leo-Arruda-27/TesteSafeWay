
public class Client {
	
	private String taxIdentifier;
	private String clientName;
	private String clientUser;
	private Integer clientAge;

	public Client(String taxIdentifier, String clientName, String clientUser, Integer clientAge) {
		super();
		this.taxIdentifier = taxIdentifier;
		this.clientName = clientName;
		this.clientUser = clientUser;
		this.clientAge = clientAge;
	}

	public String getTaxIdentifer() {
		return taxIdentifier;
	}

	public void setTaxIdentifier(String cpf) {
		this.taxIdentifier = cpf;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getClientUser() {
		return clientUser;
	}

	public void setClientUser(String clientUser) {
		this.clientUser = clientUser;
	}

	public Integer getClientAge() {
		return clientAge;
	}

	public void setClientAge(Integer clientAge) {
		this.clientAge = clientAge;
	}

}
