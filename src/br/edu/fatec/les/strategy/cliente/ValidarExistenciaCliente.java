package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dao.ClienteDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarExistenciaCliente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente= (Cliente) entidade;
		IDAO dao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setEmail(cliente.getEmail());
		
		if(dao.consultar(cli).size() != 0) {
			return "Cliente ja cadastrado!";
		}
		
		return null;
	}

}
