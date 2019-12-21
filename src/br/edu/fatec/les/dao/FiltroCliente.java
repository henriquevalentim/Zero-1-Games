package br.edu.fatec.les.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.les.dominio.cliente.Cliente;

public class FiltroCliente {

	private String querry;
	private Map<Integer, String> mapFiltroQuerry;
	private List<Integer> listQuerryCliente;

	private boolean flgCliente = false;

	public FiltroCliente() {

	}

	public String gerarQuerry(Cliente cliente) {

		querry = "SELECT * FROM tb_cliente as C,tb_telefone as T";

		mapFiltroQuerry = new HashMap<Integer, String>();

		String selectCliente = querryCliente(cliente);
		if (flgCliente) {
			querry += " WHERE " + selectCliente + " AND cli_id = tel_cli_id AND cli_status = true;";
		} else if (!flgCliente) {
			querry += " WHERE cli_id = tel_cli_id AND cli_status = true;";
		}
		System.out.println(querry);
		return querry;

	}

	private String querryCliente(Cliente cliente) {

		boolean flgWhere = false;
		String selectCliente = "";
		listQuerryCliente = new ArrayList<Integer>();

		// id
		if (cliente.getId() != null) {
			if (cliente.getId() > 0) {
				mapFiltroQuerry.put(0, "cli_id = " + cliente.getId());
				listQuerryCliente.add(0);
				flgCliente = true;
				flgWhere = true;
			}
		}

		// nome
		if (cliente.getNome() != null) {
			if (!cliente.getNome().trim().equals("")) {
				mapFiltroQuerry.put(1, "cli_nome LIKE '%" + cliente.getNome() + "%'");
				listQuerryCliente.add(1);
				flgWhere = true;
				flgCliente = true;
			}
		}

		// email
		if (cliente.getEmail() != null) {
			if (!cliente.getEmail().trim().equals("")) {
				mapFiltroQuerry.put(2, "cli_email = '" + cliente.getEmail() + "'");
				listQuerryCliente.add(2);
				flgWhere = true;
				flgCliente = true;
			}
		}
		// cpf
		if (cliente.getCpf() != null) {
			if (!cliente.getCpf().trim().equals("")) {
				mapFiltroQuerry.put(3, "cli_cpf = '" + cliente.getCpf() + "'");
				listQuerryCliente.add(3);
				flgWhere = true;
				flgCliente = true;
			}
		}

		// data nascimento
		if (cliente.getDataNascimento() != null) {
				mapFiltroQuerry.put(4, "cli_datanascimento = '" + cliente.getDataNascimento() + "'");
				listQuerryCliente.add(4);
				flgWhere = true;
				flgCliente = true;
		}

		// numero telefone
		if (cliente.getTelefone() != null) {
			if (!cliente.getTelefone().getNumero().trim().equals("")) {
				mapFiltroQuerry.put(5, "tel_numero LIKE '%" + cliente.getTelefone().getNumero() + "%'");
				listQuerryCliente.add(5);
				flgWhere = true;
				flgCliente = true;
			}
		}

		
		// teve filtro?
		if (flgWhere) {
			for (Integer i : listQuerryCliente) {
				if (i != listQuerryCliente.get(0)) {
					selectCliente += " AND ";
				}
				selectCliente += mapFiltroQuerry.get(i);
			}
		}

		return selectCliente;

	}
}