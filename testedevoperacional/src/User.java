
public class User {

	private String userName;
	private String userPassword;
	private Client userClient;
	private Company userCompany;

	public User() {
		super();
	}

	public User(
			String userName,
			String userPassword,
			Client userClient,
			Company userCompany) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userClient = userClient;
		this.userCompany = userCompany;
	}

	public boolean IsAdmin() {
		return this.userCompany == null && this.userClient == null;
	}

	public boolean IsEmpresa() {
		return this.userCompany != null;
	}

	public boolean IsCliente() {
		return this.userClient != null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Client getUserClient() {
		return userClient;
	}

	public void setUserClient(Client userClient) {
		this.userClient = userClient;
	}

	public Company getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(Company userCompany) {
		this.userCompany = userCompany;
	}
}
