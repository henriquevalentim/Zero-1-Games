<%@page import="br.edu.fatec.les.dominio.Cupom"%>
<%@page import="br.edu.fatec.les.dominio.MetodoEntrega"%>
<%@page import="br.edu.fatec.les.dominio.FormaPagamento"%>
<%@page import="br.edu.fatec.les.dominio.CarrinhoCompra"%>
<%@page import="br.edu.fatec.les.dominio.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.les.dominio.cliente.Cartao"%>
<%@page import="br.edu.fatec.les.dominio.Pedido"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - Jogos</title>

  <!-- Bootstrap core CSS -->
  <link href="../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
  
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  
  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">
  <link href="../assets/stylesheets/categorias.css" rel="stylesheet">

</head>

<body>

<c:import url="fragmentos/navbarLogado.jsp" />

<%
	Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
	CarrinhoCompra carrinho = (CarrinhoCompra)request.getSession().getAttribute("carrinho");
	List<Item> itens = carrinho.getItens();
	pedido.setItens(itens);
	List<Cartao> cartoes = null;
	List<Cartao> cartoesCompra = new ArrayList<Cartao>();
	Cartao cartao = null;
	List<FormaPagamento> formasPagamentos = new ArrayList<FormaPagamento>();
	
	if(request.getParameter("idCupom") != null && !request.getParameter("idCupom").equals("")){
		FormaPagamento formaPagamento = new FormaPagamento();
		Cupom cupom = new Cupom();
		if(request.getParameter("idCupom")!=null){
			cupom.setId(Integer.valueOf(request.getParameter("idCupom")));
		}
		if(request.getParameter("valorCupom") != null){			
			cupom.setValor(Double.valueOf(request.getParameter("valorCupom")));
		}
		formaPagamento.setCupom(cupom);
		formaPagamento.setValor(cupom.getValor());
		formasPagamentos.add(formaPagamento);
		
	}
	
	if(request.getParameter("cartao1") != null){
		cartoes = (List<Cartao>)request.getAttribute("cartoes");
		
		for(int i = 0;i < cartoes.size();i++){
			if(cartoes.get(i).getId().equals(Integer.valueOf(request.getParameter("cartao1")))){
				cartao = cartoes.get(i);
				break;
			}
		}
	
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setCartao(cartao);
		formaPagamento.setValor(pedido.getSubtotal());
		formasPagamentos.add(formaPagamento);
		pedido.setFormaPagamento(formasPagamentos);
		pedido.setCartoes(cartoesCompra);
	}else if(request.getParameter("cartao10") != ""){
		String strIdCartao10 = request.getParameter("cartao10");
		String strIdCartao20 = request.getParameter("cartao20");
		
		String strValorCartao1 = request.getParameter("txtValorCartao"+strIdCartao10);
		String strValorCartao2 = request.getParameter("txtValorCartao"+strIdCartao20);
		
		Cartao cartao10 = new Cartao();
		Cartao cartao20 = new Cartao();
		FormaPagamento formaPagamento1 = new FormaPagamento();
		FormaPagamento formaPagamento2 = new FormaPagamento();
		if(strIdCartao10 != null && strIdCartao20 != null && !strIdCartao20.isEmpty() && !strIdCartao10.isEmpty()){			
			cartao20.setId(Integer.valueOf(strIdCartao20));
			cartao10.setId(Integer.valueOf(strIdCartao10));
		}
		
		if(request.getAttribute("cartoes") != null){
			cartoes = (List<Cartao>)request.getAttribute("cartoes");
			for(int i = 0;i < cartoes.size();i++){
				if(cartoes.get(i).getId().equals(Integer.valueOf(cartao10.getId()))){
					cartao10 = cartoes.get(i);
					break;
				}
			}
			for(int i = 0;i < cartoes.size();i++){
				if(cartoes.get(i).getId().equals(Integer.valueOf(cartao20.getId()))){
					cartao20 = cartoes.get(i);
					break;
				}
			}
			if(strValorCartao1 != null && !strValorCartao1.isEmpty()){			
				formaPagamento1.setValor(Double.valueOf(strValorCartao1));
			}else{
				formaPagamento1.setValor(0.0);
			}
			if(strValorCartao2 != null && !strValorCartao2.isEmpty()){			
				formaPagamento2.setValor(Double.valueOf(strValorCartao2));
			}else{
				formaPagamento2.setValor(0.0);
			}
			formaPagamento1.setCartao(cartao10);
			formaPagamento2.setCartao(cartao20);
			
			formasPagamentos.add(formaPagamento1);
			formasPagamentos.add(formaPagamento2);
			
			pedido.setCartoes(cartoesCompra);
		}
		
	}
	pedido.setFormaPagamento(formasPagamentos);

	request.getSession().setAttribute("pedido", pedido);
%>


  <!-- Page Content -->
  	<div class="container">
  	
  		<form action="ControlePedido" method="POST">
			<h3 class="card-title my-3">Revise e confirme sua compra</h3>
			
			<div class="row">
			  	<div class="col-md-12 mt-3">
				  	<c:if test="${ resultado != null && !resultado.msg.equals('Cadastro realizado com sucesso.') }">
						<div class="alert alert-danger col-md-4" role="alert">
							<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
						    	<c:out value = "${erro}"/><br>
						    </c:forTokens>
						</div>
				  	</c:if>
			  	</div>
	  		</div>
			
			<h5 class="card-title mt-4"><b>Detalhes do Envio</b></h5>
			<div class="row">
		  		<div class="col-md-12 mt-1">
					<div class="card col-md-8 mr-2">
					  	<div class="card-body">
				  			<div class="form-inline">
				  				<i class="fa fa-map-marker m-3" style="font-size:40px;"></i>
					  			<div class="form-group">
						    		<p class="card-text card_botton">${ pedido.endereco.logradouro }, ${ pedido.endereco.numero } </p><br>
					  			</div>
						    		<p class="card-text card_botton">${ pedido.endereco.bairro } - ${ pedido.endereco.cidade.nome } - ${ pedido.endereco.cidade.estado.nome }<br></p>
				  			</div>
						</div>
					</div>
				 </div>
			</div>
			
			<div class="row">
		  		<div class="col-md-12 mt-1">
					<div class="card col-md-8 mr-2">
					  	<div class="card-body">
				  			<div class="form-inline">
				  				<i class="fa fa-truck m-3" style="font-size:40px;"></i>
					  			<div class="form-group">
						    		<p class="card-text card_botton">Metodo de Transporte: ${ pedido.metodoEntrega.tipo } Valor: <fmt:formatNumber value='${ pedido.metodoEntrega.valor }' type="currency"></fmt:formatNumber></p><br>
					  			</div>
				  			</div>
						</div>
					</div>
				 </div>
			</div>
			
			<h5 class="card-title mt-4"><b>Detalhes do Pagamento</b></h5>
			<c:forEach var="formaPagamento" items="${ pedido.formaPagamento }">
				<div class="row">
			  		<div class="col-md-12 mt-1">
					  	<div class="card col-md-8">
						  	<div class="card-body">
					  			<div class="form-inline">
					  				<i class="fa fa-credit-card m-2" style="font-size:40px;"></i>
						  			<div class="form-group">
							    		<p class="card-text card_botton">Terminado em ${ formaPagamento.cartao.numero.substring(formaPagamento.cartao.numero.length() - 4) }</p>
						  			</div>
					  			</div>
							</div>
						</div>
					 </div>
				</div>
			</c:forEach>
			
		
			<div class="row">
		<div class="card col-md-8 m-3">
		  	<div class="card-body">
			    <h5 class="card-title">Produtos</h5>
			    <fmt:setLocale value="${user.locale}" />
			    <c:forEach var="item" items="${ carrinho.itens }">
				    <div class="row">
					    <h6 class="card-subtitle mb-2 col-md-5 text-muted">${ item.jogo.titulo }</h6>
					    <h6 class="card-subtitle mb-2 col-md-2 text-muted">${ item.quantidade }</h6>
					    
					    <h6 class="card-subtitle mb-2 col-md-3 text-muted"><fmt:formatNumber value='${ item.jogo.precoVenda * item.quantidade }' type="currency"></fmt:formatNumber></h6>
				    </div>
				</c:forEach>
				<div class="row">
					<h6 class="card-subtitle mb-2 col-md-7 text-muted">Envio:</h6>
					<h6 class="card-subtitle mb-2 col-md-3 text-muted"><fmt:formatNumber value='${ pedido.metodoEntrega.valor }' type="currency"></fmt:formatNumber></h6>
				</div>
				<div class="row">
				    	<h6 class="card-subtitle mb-2 col-md-7 text-muted">Desconto:</h6>
				    	<h6 class="card-subtitle mb-2 col-md-3 text-muted">-<fmt:formatNumber value='${ resultadoCupom.valor }' type="currency"></fmt:formatNumber></h6>
				</div>
				<div class="row">
				    	<h6 class="card-subtitle mb-2 col-md-7 text-muted">Total:</h6>
				    	<h6 class="card-subtitle mb-2 col-md-3 text-muted"><fmt:formatNumber value='${ pedido.subtotal - resultadoCupom.valor }' type="currency"></fmt:formatNumber></h6>
				    	<input type="hidden" name="txtDescontoCupom" value='${ resultadoCupom.valor }' />
				</div>
				
				<div class="card-footer" style="background-color: white;">
				<div class="row">
			    	<div class="form-check offset-md-9 col-md-3">
					  	<button type="submit" id="btnOperacao" name="btnOperacao" value="SALVAR" class="btn btn-success">FINALIZAR COMPRA</button>
					</div>
				</div>
				</div>
			</div>
		</div>
		</form>
		
  	</div>
  	
  <br><br><br><br><br>
  	</div>
  <!-- /.container -->

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
