import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		configData();
	}

	public static void executar(
			List<User> listUsers,
			List<Client> listClients,
			List<Company> listCompanies,
			List<Product> listProducts,
			List<Product> listShoppingCart,
			List<Sale> listSales) {

		Scanner scanner = new Scanner(System.in);
		Boolean restart = true;

		while (restart) {

			System.out.println("Entre com seu usuário e senha:");
			System.out.print("Usuário: ");
			String username = scanner.next();
			System.out.print("Senha: ");
			String senha = scanner.next();

			List<User> usuariosSearch = listUsers.stream().filter(users -> users.getUserName().equals(username))
					.collect(Collectors.toList());
			restart = true;

			if (usuariosSearch.size() > 0) {
				User usuarioLogado = usuariosSearch.get(0);
				if (usuarioLogado.getUserPassword().equals(senha)) {
					System.out.println("Escolha uma opção para iniciar");

					if (usuarioLogado.IsEmpresa()) {

						Integer escolha;
						do {
							System.out.println("1 - Listar vendas");
							System.out.println("2 - Ver produtos");
							System.out.println("0 - Deslogar");
							escolha = scanner.nextInt();
							switch (escolha) {
								case 1: {
									System.out.println("\n**************************************************");
									System.out.println("VENDAS EFETUADAS");
									listSales.stream().forEach(venda -> {
										if (venda.getEmpresa().getCompanyId()
												.equals(usuarioLogado.getUserCompany().getCompanyId())) {
											System.out.println("**************************************************");
											System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
													+ venda.getCliente().getTaxIdentifer() + ": ");

											venda.getItens().stream().forEach(x -> {
												System.out
														.println(x.getProductId() + " - " + x.getProductName() + "    R$"
																+ x.getProductPrice());
											});

											System.out.println("Total Venda: R$" + venda.getValor());
											System.out
													.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
											System.out.println("Total Líquido  para empresa: R$"
													+ (venda.getValor() - venda.getComissaoSistema()));
											System.out.println("\n**************************************************");
										}
									});
									System.out.println(
											"Saldo Empresa: " + usuarioLogado.getUserCompany().getCompanyBalance());
									System.out.println("\n**************************************************");
									break;
								}
								case 2: {
									System.out.println("\n**************************************************");
									System.out.println("MEUS PRODUTOS");
									listProducts.stream().forEach(produto -> {
										if (produto.getProductCompany().getCompanyId()
												.equals(usuarioLogado.getUserCompany().getCompanyId())) {
											System.out.println("**************************************************");
											System.out.println("Código: " + produto.getProductId());
											System.out.println("Produto: " + produto.getProductName());
											System.out.println("Quantidade em estoque: " + produto.getProductQuantity());
											System.out.println("Valor: R$" + produto.getProductPrice());
											System.out.println("\n**************************************************");
										}
									});

									System.out.println(
											"Saldo Empresa: " + usuarioLogado.getUserCompany().getCompanyBalance());
									System.out.println("\n**************************************************");

									break;
								}
								case 0: {
									restart = true;
									break;
								}
								default: {
									System.out.println("opcao invalida");
									break;
								}
							}
						} while (escolha != 0);

					} else if (usuarioLogado.IsCliente()) {

						Integer escolha;
						do {
							System.out.println("1 - Relizar Compras");
							System.out.println("2 - Ver Compras");
							System.out.println("0 - Deslogar");
							escolha = scanner.nextInt();
							switch (escolha) {
								case 1: {
									System.out.println(
											"Para realizar uma compra, escolha a empresa onde deseja comprar: ");
									listCompanies.stream().forEach(x -> {
										System.out.println(x.getCompanyId() + " - " + x.getCompanyName());
									});
									Integer escolhaEmpresa = scanner.nextInt();
									Integer escolhaProduto = -1;

									do {
										System.out.println("Escolha os seus produtos: ");
										listProducts.stream().forEach(x -> {
											if (x.getProductCompany().getCompanyId().equals(escolhaEmpresa)) {
												System.out.println(x.getProductId() + " - " + x.getProductName());
											}
										});
										System.out.println("0 - Finalizar compra");
										escolhaProduto = scanner.nextInt();
										for (Product produtoSearch : listProducts) {
											if (produtoSearch.getProductId().equals(escolhaProduto)) {
												listShoppingCart.add(produtoSearch);
											}
										}
									} while (escolhaProduto != 0);

									System.out.println("\n**************************************************");
									System.out.println("Resumo da compra: ");

									listShoppingCart.stream().forEach(x -> {
										if (x.getProductCompany().getCompanyId().equals(escolhaEmpresa)) {
											System.out
													.println(x.getProductId() + " - " + x.getProductName() + "    R$" + x.getProductPrice());
										}
									});

									Company empresaEscolhida = listCompanies.stream()
											.filter(company -> company.getCompanyId().equals(escolhaEmpresa))
											.collect(Collectors.toList())
											.get(0);
									Client clienteLogado = listClients.stream()
											.filter(client -> client.getClientName()
													.equals(usuarioLogado.getUserClient().getClientName()))
											.collect(Collectors.toList())
											.get(0);

									Sale venda = criarVenda(listShoppingCart, empresaEscolhida, clienteLogado,
											listSales);

									System.out.println("Total: R$" + venda.getValor());
									System.out.println("\n**************************************************");
									listShoppingCart.clear();
									break;
								}
								case 2: {
									System.out.println("\n**************************************************");
									System.out.println("COMPRAS EFETUADAS");
									listSales.stream().forEach(venda -> {
										if (venda.getCliente().getClientName().equals(usuarioLogado.getUserName())) {
											System.out.println("\n**************************************************");
											System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
													+ venda.getEmpresa().getCompanyName() + ": ");
											venda.getItens().stream().forEach(x -> {
												System.out
														.println(x.getProductId() + " - " + x.getProductName() + "    R$"
																+ x.getProductPrice());
											});
											System.out.println("Total: R$" + venda.getValor());
											System.out.println("\n**************************************************");
										}
									});
									break;
								}
								case 0: {
									restart = true;
									break;
								}
								default: {
									System.out.println("opcao invalida");
									break;
								}
							}
						} while (escolha != 0);

					} else {
						Integer escolha;
						do {
							System.out.println("1 - Ver Empresas");
							System.out.println("2 - Ver Clientes");
							System.out.println("3 - Ver Usuarios");
							System.out.println("0 - Deslogar");
							escolha = scanner.nextInt();
							switch (escolha) {
								case 1: {
									System.out.println("EMPRESAS");

									listCompanies.stream().forEach(companie -> {

										System.out.println("\n**************************************************");
										System.out.println("Código da empresa: " + companie.getCompanyId());
										System.out.println("Nome da Empresa: " + companie.getCompanyName());
										System.out.println("CNPJ: " + companie.getCnpjTaxIdentifier());
										System.out.println("Taxa: " + companie.getCompanyRate());
										System.out.println("Saldo: " + companie.getCompanyBalance());

									});
									break;
								}
								case 2: {
									System.out.println("CLIENTES");

									listClients.stream().forEach(client -> {

										System.out.println("\n**************************************************");
										System.out.println("CPF do Cliente: " + client.getTaxIdentifer());
										System.out.println("Nome do Cliente: " + client.getClientName());
										System.out.println("Usuario do Cliente: " + client.getClientName());
										System.out.println("Idade do Cliente: " + client.getClientAge());

									});
									break;
								}
								case 3: {
									System.out.println("USUARIOS");

									listUsers.stream().forEach(user -> {

										System.out.println("\n**************************************************");
										System.out.println("Nome do Usuario: " + user.getUserName());
										System.out.println("Senha do Usuario: " + user.getUserPassword());

									});
									break;
								}
								case 0: {
									restart = true;
									break;
								}
								default: {
									System.out.println("opcao invalida");
									break;
								}
							}
						} while (escolha != 0);
					}
				} else {
					print("Senha incorreta");
				}
			} else {
				print("Usuário não encontrado");
			}
		}
	}

	public static Sale criarVenda(List<Product> carrinho, Company empresa, Client cliente, List<Sale> vendas) {
		Double total = carrinho.stream().mapToDouble(Product::getProductPrice).sum();
		Double comissaoSistema = total * empresa.getCompanyRate();
		int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCódigo() + 1;

		Sale venda = new Sale(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
		empresa.setCompanyBalance(empresa.getCompanyBalance() + total);
		vendas.add(venda);
		return venda;
	}

	public static void configData() {

		List<Product> listShoppingCart = new ArrayList<Product>();
		List<Sale> listSales = new ArrayList<Sale>();

		Company company1 = new Company(1, "Level Varejo", "53239160000154", 0.05);
		Company company2 = new Company(2, "SafeWay Padaria", "30021423000159", 0.15);
		Company company3 = new Company(3, "SafeWay Restaurante", "41361511000116", 0.20);

		Product prod1 = new Product(1, "Pão Frances", 5, 3.50, company2);
		Product prod2 = new Product(2, "Coturno", 10, 400.0, company1);
		Product prod3 = new Product(3, "Jaqueta Jeans", 15, 150.0, company1);
		Product prod4 = new Product(4, "Calça Sarja", 15, 150.0, company1);
		Product prod5 = new Product(5, "Prato feito - Frango", 10, 25.0, company3);
		Product prod6 = new Product(6, "Prato feito - Carne", 10, 25.0, company3);
		Product prod7 = new Product(7, "Suco Natural", 30, 10.0, company3);
		Product prod8 = new Product(8, "Sonho", 5, 8.50, company2);
		Product prod9 = new Product(9, "Croissant", 7, 6.50, company2);
		Product prod10 = new Product(10, "Ché Gelado", 4, 5.50, company2);

		Client client1 = new Client("07221134049", "Allan da Silva", "client1", 20);
		Client client2 = new Client("72840700050", "Samuel da Silva", "client2", 24);

		User user1 = new User("admin", "1234", null, null);
		User user3 = new User("client1", "1234", client1, null);
		User user4 = new User("client2", "1234", client2, null);
		User user5 = new User("company2", "1234", null, company1);
		User user2 = new User("company1", "1234", null, company2);
		User user6 = new User("company3", "1234", null, company3);

		List<User> listUsers = Arrays.asList(user1, user2, user3, user4, user5, user6);
		List<Client> listClients = Arrays.asList(client1, client2);
		List<Company> listCompanies = Arrays.asList(company2, company1, company3);
		List<Product> listProducts = Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9,
				prod10);

		executar(listUsers, listClients, listCompanies, listProducts, listShoppingCart, listSales);
	}

	public static void print(String message) {
		System.out.println(message);
	}
}