package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dao.CupomDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.web.util.GeradorCodigo;
import br.edu.fatec.les.web.util.JavaMail;

public class GerarCupomValorPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		Pedido pedido = (Pedido)entidade;
		String codigoCupom = GeradorCodigo.gerarCodigoCupom();
		Cupom cupom = new Cupom();
		cupom.setCodigo(codigoCupom);
		cupom.setStatus(true);
		cupom.setValor(pedido.getSubtotal());
		
		IStrategy validarCupom = new ValidarCupom();
		sb.append(validarCupom.processar(cupom));
		
		if(sb.length() == 0) {
			IDAO dao = new CupomDAO();
			dao.salvar(cupom);
			JavaMail.mandarEmailCupom(pedido.getCliente().getEmail(), cupom);
		}
		
		return sb.toString();
	}

}
