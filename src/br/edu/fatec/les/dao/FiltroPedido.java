package br.edu.fatec.les.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.les.dominio.Pedido;

public class FiltroPedido {

	private String querry;
	private Map<Integer, String> mapFiltroQuerry;
	private List<Integer> listQuerryPedido;

	private boolean flgPedido = false;

	public FiltroPedido() {

	}

	public String gerarQuerry(Pedido pedido) {

		querry = "SELECT * FROM tb_pedido AS P,tb_usuario AS U, tb_cliente AS C,tb_status_pedido AS SP ";

		mapFiltroQuerry = new HashMap<Integer, String>();

		String selectPedido = querryPedido(pedido);
		if (flgPedido) {
			querry += " WHERE " + selectPedido + " AND P.ped_cli_id = U.usu_id AND C.cli_usu_id = U.usu_id AND SP.stp_id = P.ped_stp_id;";
		} else if (!flgPedido) {
			querry += " WHERE P.ped_cli_id = U.usu_id AND C.cli_usu_id = U.usu_id AND SP.stp_id = P.ped_stp_id;";
		}
		System.out.println(querry);
		return querry;

	}

	private String querryPedido(Pedido pedido) {

		boolean flgWhere = false;
		String selectPedido = "";
		listQuerryPedido = new ArrayList<Integer>();

		// id
		if (pedido.getId() != null) {
			if (pedido.getId() > 0) {
				mapFiltroQuerry.put(0, "ped_id = " + pedido.getId());
				listQuerryPedido.add(0);
				flgPedido = true;
				flgWhere = true;
			}
		}
		
		if (pedido.getCliente().getId() != null) {
			if (pedido.getCliente().getId() > 0) {
				mapFiltroQuerry.put(1, "U.usu_id = " + pedido.getCliente().getId());
				listQuerryPedido.add(1);
				flgPedido = true;
				flgWhere = true;
			}
		}

		// nome
		if (pedido.getCliente().getNome() != null) {
			if (!pedido.getCliente().getNome().trim().equals("")) {
				mapFiltroQuerry.put(2, "C.cli_nome LIKE '%" + pedido.getCliente().getNome() + "%'");
				listQuerryPedido.add(2);
				flgWhere = true;
				flgPedido = true;
			}
		}

		// email
		if (pedido.getNumeroPedido() != null) {
			if (!pedido.getNumeroPedido().trim().equals("")) {
				mapFiltroQuerry.put(3, "P.ped_numero = '" + pedido.getNumeroPedido() + "'");
				listQuerryPedido.add(3);
				flgWhere = true;
				flgPedido = true;
			}
		}
		// id
		if (pedido.getStatusPedido() != null) {
			if (pedido.getStatusPedido().getId() > 0) {
				mapFiltroQuerry.put(4, "SP.stp_id = " + pedido.getStatusPedido().getId());
				listQuerryPedido.add(4);
				flgPedido = true;
				flgWhere = true;
			}
		}
		

		// teve filtro?
		if (flgWhere) {
			for (Integer i : listQuerryPedido) {
				if (i != listQuerryPedido.get(0)) {
					selectPedido += " AND ";
				}
				selectPedido += mapFiltroQuerry.get(i);
			}
		}

		return selectPedido;

	}
}