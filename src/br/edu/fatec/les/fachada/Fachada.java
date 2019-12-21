package br.edu.fatec.les.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.les.dao.AnaliseGraficoDAO;
import br.edu.fatec.les.dao.CartaoDAO;
import br.edu.fatec.les.dao.ClienteDAO;
import br.edu.fatec.les.dao.CupomDAO;
import br.edu.fatec.les.dao.EnderecoDAO;
import br.edu.fatec.les.dao.EstoqueDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dao.JogoDAO;
import br.edu.fatec.les.dao.PedidoDAO;
import br.edu.fatec.les.dao.UsuarioDAO;
import br.edu.fatec.les.dominio.AnaliseGrafico;
import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Estoque;
import br.edu.fatec.les.dominio.MetodoEntrega;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.dominio.cliente.Endereco;
import br.edu.fatec.les.dominio.cliente.Telefone;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.strategy.cliente.AutenticacaoSenha;
import br.edu.fatec.les.strategy.cliente.CriptografarSenha;
import br.edu.fatec.les.strategy.cliente.ValidarAutenticacao;
import br.edu.fatec.les.strategy.cliente.ValidarCartao;
import br.edu.fatec.les.strategy.cliente.ValidarCpf;
import br.edu.fatec.les.strategy.cliente.ValidarDadosCartao;
import br.edu.fatec.les.strategy.cliente.ValidarDadosCliente;
import br.edu.fatec.les.strategy.cliente.ValidarDadosUsuario;
import br.edu.fatec.les.strategy.cliente.ValidarEndereco;
import br.edu.fatec.les.strategy.cliente.ValidarExistenciaCliente;
import br.edu.fatec.les.strategy.cliente.ValidarSenha;
import br.edu.fatec.les.strategy.cliente.ValidarTelefone;
import br.edu.fatec.les.strategy.jogo.CalcularPrecoVenda;
import br.edu.fatec.les.strategy.jogo.ValidarDadosJogo;
import br.edu.fatec.les.strategy.jogo.ValidarExistenciaJogo;
import br.edu.fatec.les.strategy.jogo.ValidarQuantidadeEstoque;
import br.edu.fatec.les.strategy.jogo.ValidarQuantidadeJogo;
import br.edu.fatec.les.strategy.pedido.AplicarValorCupomCartaoCredito;
import br.edu.fatec.les.strategy.pedido.EntregaProduto;
import br.edu.fatec.les.strategy.pedido.SolicitacaoDevolucao;
import br.edu.fatec.les.strategy.pedido.ValidarExistenciaCupom;
import br.edu.fatec.les.strategy.pedido.ValidarPagamento;
import br.edu.fatec.les.strategy.pedido.ValidarValorPagamento;

public class Fachada implements IFachada {

	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rns;
	private StringBuilder sb = new StringBuilder();

	Resultado resultado = null;

	IDAO dao = null;

	String nomeClasse = null;

	List<IStrategy> rng = null;

	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, List<IStrategy>>();

		// Colocar os Daos no map
		daos.put(Cartao.class.getName(), new CartaoDAO());
		daos.put(Endereco.class.getName(), new EnderecoDAO());
		daos.put(Jogo.class.getName(), new JogoDAO());
		daos.put(Estoque.class.getName(), new EstoqueDAO());
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Usuario.class.getName(), new UsuarioDAO());
		daos.put(Pedido.class.getName(), new PedidoDAO());
		daos.put(Cupom.class.getName(), new CupomDAO());
		daos.put(MetodoEntrega.class.getName(), new EnderecoDAO());
		daos.put(AnaliseGrafico.class.getName(), new AnaliseGraficoDAO());
		
		
		/* ##########CLIENTE############ */
		IStrategy vCliente = new ValidarDadosCliente();
		IStrategy vCpf = new ValidarCpf();
		IStrategy vSenha = new ValidarSenha();
		IStrategy vExistenciaCliente = new ValidarExistenciaCliente();
		IStrategy criptografarSenha = new CriptografarSenha();

		List<IStrategy> rnsCliente = new ArrayList<IStrategy>();
		rnsCliente.add(vCliente);
		rnsCliente.add(vCpf);
		rnsCliente.add(vSenha);
		rnsCliente.add(vExistenciaCliente);
		rnsCliente.add(criptografarSenha);
		
		
		/* ##########Cupom############ */
		IStrategy vExistenciaCupom = new ValidarExistenciaCupom();
		
		List<IStrategy> rnsCupom = new ArrayList<IStrategy>();
		rnsCupom.add(vExistenciaCupom);
		
		
		/* ##########Usuario############ */
		IStrategy vUsuario = new ValidarDadosUsuario();
		IStrategy vAutenticar = new ValidarAutenticacao();
		IStrategy autenticacaoSenha = new AutenticacaoSenha();

		List<IStrategy> rnsUsuario = new ArrayList<IStrategy>();
		rnsUsuario.add(vUsuario);
		rnsUsuario.add(vAutenticar);
		rnsUsuario.add(autenticacaoSenha);

		
		/* ##########JOGO############ */
		IStrategy vJogo = new ValidarDadosJogo();
		IStrategy validarQuantidadeJogo = new ValidarQuantidadeJogo();
		IStrategy vExistenciaJogo = new ValidarExistenciaJogo();
		IStrategy cPrecoVenda = new CalcularPrecoVenda();
		
		List<IStrategy> rnsJogo = new ArrayList<IStrategy>();
		rnsJogo.add(vJogo);
		rnsJogo.add(validarQuantidadeJogo);
		rnsJogo.add(cPrecoVenda);
		rnsJogo.add(vExistenciaJogo);
		
		/* ##########ESTOQUE############ */
		IStrategy vQuantidade = new ValidarQuantidadeEstoque();
		
		List<IStrategy> rnsEstoque = new ArrayList<IStrategy>();
		rnsEstoque.add(vQuantidade);
		
		/* ##########ENDEREÇO############ */
		List<IStrategy> rnsEndereco = new ArrayList<IStrategy>();
		IStrategy vEndereco = new ValidarEndereco();

		rnsEndereco.add(vEndereco);

		
		/* ##########CARTAO############ */
		List<IStrategy> rnsCartao = new ArrayList<IStrategy>();
		IStrategy vCartao = new ValidarCartao();
		IStrategy vDadosCartao = new ValidarDadosCartao();

		rnsCartao.add(vCartao);
		rnsCartao.add(vDadosCartao);

		
		/* ##########TELEFONE############ */
		List<IStrategy> rnsTelefone = new ArrayList<IStrategy>();
		IStrategy vTelefone = new ValidarTelefone();

		rnsTelefone.add(vTelefone);
		
		/* ##########PEDIDO############ */
		List<IStrategy> rnsPedido = new ArrayList<IStrategy>();
		IStrategy aplicarValorCupomCartao = new AplicarValorCupomCartaoCredito();
		IStrategy entregaProduto = new EntregaProduto();
		IStrategy vOperadora = new ValidarPagamento();
		IStrategy sDevolucao = new SolicitacaoDevolucao();
		IStrategy vFormaPagamento = new ValidarValorPagamento();
		
		rnsPedido.add(aplicarValorCupomCartao);
		rnsPedido.add(sDevolucao);
		rnsPedido.add(entregaProduto);
		rnsPedido.add(vOperadora);
		rnsPedido.add(vFormaPagamento);
		
		
		rns.put(Cliente.class.getName(), rnsCliente);
		rns.put(Jogo.class.getName(), rnsJogo);
		rns.put(Estoque.class.getName(), rnsEstoque);
		rns.put(Endereco.class.getName(), rnsEndereco);
		rns.put(Cartao.class.getName(), rnsCartao);
		rns.put(Telefone.class.getName(), rnsTelefone);
		rns.put(Usuario.class.getName(), rnsUsuario);
		rns.put(Pedido.class.getName(), rnsPedido);
		rns.put(Cupom.class.getName(), rnsCupom);
	}

	private void executarRegras(List<IStrategy> rngEntidade, EntidadeDominio entidade) {
		
		String msg = "";
		for (IStrategy strategy : rngEntidade) {
			msg = strategy.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		rng = rns.get(nomeClasse);
		sb.setLength(0);

		executarRegras(rng, entidade);

		// verificar se é um cliente pq cliente tem q verificar alem dos dados dele
		// tem q validar os dados de end
		
		if (nomeClasse == Cliente.class.getName()) {
			Cliente cliente = (Cliente) entidade;

			// executar regras dos endereços
			List<IStrategy> rngEnd = rns.get(Endereco.class.getName());
			executarRegras(rngEnd, cliente.getEndereco());
		}

		System.out.println("Erro:" + sb.toString());
		// se tem msg de erro ele não salva
		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			try {
				dao = daos.get(nomeClasse);
				dao.salvar(entidade);
				System.out.println("salvando no banco....");
				resultado.addEntidades(entidade);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Salvar...");
			}
		} else {
			System.out.println("erro encontrado....");
			resultado.addEntidades(entidade);
			resultado.setMsg(sb.toString());
		}
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		
		rng = rns.get(nomeClasse);
		sb.setLength(0);

		executarRegras(rng, entidade);
		System.out.println("Erro:" + sb.toString());

		// se tem msg de erro ele não salva
		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			try {
				dao = daos.get(nomeClasse);
				dao.alterar(entidade);
				System.out.println("alterando no banco....");
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Alterar...");
			}
		} else {
			System.out.println("erro encontrado....");
			resultado.setMsg(sb.toString());
		}
		
		resultado.addEntidades(entidade);
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Resultado();

		nomeClasse = entidade.getClass().getName();

		dao = daos.get(nomeClasse);

		try {
			dao.excluir(entidade);
			System.out.println("excluindo do banco");
		} catch (Exception e) {
			e.printStackTrace();
			resultado.setMsg("NÃ£o foi possível realizar a consulta...");
		}

		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Resultado();
		nomeClasse = entidade.getClass().getName();
		rng = rns.get(nomeClasse);

		
		if(nomeClasse.equals(Usuario.class.getName())) {
			executarRegras(rng, entidade);
		}
		if(!nomeClasse.equals(MetodoEntrega.class.getName())) {
			if (sb.length() == 0 || sb.toString().trim().equals("")) {
				dao = daos.get(nomeClasse);
				try {
					resultado.setEntidades(dao.consultar(entidade));
					System.out.println("consultando no banco");
				} catch (Exception e) {
					e.printStackTrace();
					resultado.setMsg("NÃ£o foi possível realizar a consulta...");
				}
			}else {
				System.out.println("Fachada Consultar: erro encontrado....");
				resultado.setMsg(sb.toString());
			}
		}
			resultado.addEntidades(entidade);

		return resultado;
	}
}
