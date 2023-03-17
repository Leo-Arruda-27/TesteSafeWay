import java.util.List;

public class Sale {
	
	private Integer idSale;
	private List<Product> listItems;
	private Double saleAmount;
	private Double saleRate;
	private Company company;
	private Client client;

	public Sale(Integer código, List<Product> itens, Double valor, Double comissaoSistema, Company empresa, Client cliente) {
		super();
		this.idSale = código;
		this.listItems = itens;
		this.saleAmount = valor;
		this.saleRate = comissaoSistema;
		this.company = empresa;
		this.client = cliente;
	}

	public Sale() {
		super();
	}

	public Integer getCódigo() {
		return idSale;
	}
	
	public Company getEmpresa() {
		return company;
	}

	public void setEmpresa(Company empresa) {
		this.company = empresa;
	}

	public Client getCliente() {
		return client;
	}

	public void setCliente(Client cliente) {
		this.client = cliente;
	}

	public void setCódigo(Integer código) {
		this.idSale = código;
	}

	public List<Product> getItens() {
		return listItems;
	}

	public void setItens(List<Product> itens) {
		this.listItems = itens;
	}

	public Double getValor() {
		return saleAmount;
	}

	public void setValor(Double valor) {
		this.saleAmount = valor;
	}

	public Double getComissaoSistema() {
		return saleRate;
	}

	public void setComissaoSistema(Double comissaoSistema) {
		this.saleRate = comissaoSistema;
	}

}
