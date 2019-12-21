<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.les.dominio.MetodoEntrega"%>
<%@page import="br.edu.fatec.les.dominio.FormaPagamento"%>
<%@page import="br.edu.fatec.les.dominio.cliente.Endereco"%>
<%@page import="br.edu.fatec.les.dominio.EntidadeDominio"%>
<%@page import="br.edu.fatec.les.dominio.Pedido"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Zero1Games - Jogos</title>

<!-- Bootstrap core CSS -->
<link href="../assets/stylesheets/vendors/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">


<!-- Custom styles for this template -->
<link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
<link href="../assets/stylesheets/style.css" rel="stylesheet">
<link href="../assets/stylesheets/categorias.css" rel="stylesheet">

<style type="text/css">
	.fixo {
	  height:900px;
	}
	.fixed {
	  position: fixed;
	  padding-left: 55%;
	  /* background-color: red; */
	}
</style>
<script type="text/javascript">
	function somaValor( id ) {
		let strValor = document.getElementById('txtValorCartao'+id).value;
		let strValorAtual = document.getElementById("totalPago").innerHTML
		let valor = Number(strValor);
		let valorAtual = Number(strValorAtual);
		let total = valor + valorAtual;
		document.getElementById("totalPago").innerText = total.toFixed(2);	
	}
	
	function colocaId( id ) {
		if(document.getElementById("cartao10").value == ""){
			document.getElementById("cartao10").value = id;				
		}else if(document.getElementById("cartao20").value == ""){
			document.getElementById("cartao20").value = id;	
		}
	}
	
</script>

</head>

<body>

	<c:import url="fragmentos/navbarLogado.jsp" />

	<%
		Pedido pedido = null;
		Endereco endereco = null;
		if(request.getSession().getAttribute("pedido") == null){
			pedido = new Pedido();
			endereco = null;			
		}else{
			pedido = (Pedido)request.getSession().getAttribute("pedido");
			endereco = pedido.getEndereco();
		}
		if (request.getParameter("idEndereco") != null) {
			List<Endereco> enderecos = (List<Endereco>) request.getAttribute("enderecos");
			for (int i = 0; i < enderecos.size(); i++) {
				if (enderecos.get(i).getId().equals(Integer.valueOf(request.getParameter("idEndereco")))) {
					endereco = enderecos.get(i);
					break;
				}
			}
			pedido.setEndereco(endereco);
		}

		if (request.getParameter("valorEntregaSedex") != null) {
			MetodoEntrega metodoEntrega = new MetodoEntrega();
			metodoEntrega.setValor(Double.valueOf(request.getParameter("valorEntregaSedex")));
			Double total = (Double) request.getSession().getAttribute("total");
			metodoEntrega.setId(Integer.valueOf(request.getParameter("idMetodoEntrega")));
			pedido.setMetodoEntrega(metodoEntrega);
			pedido.setSubtotal(total + metodoEntrega.getValor());
			
		}
		request.getSession().setAttribute("pedido", pedido);
	%>

	<!-- Page Content -->
	<div class="container">
	<div class="float-right fixed fixo">
		<h3>
			SubTotal: <fmt:formatNumber value='${ pedido.subtotal - resultadoCupom.valor }' type='currency'></fmt:formatNumber>
		</h3>
		<div class="form-inline">
			<h3>
				Total Pago: <h3 id="totalPago">0</h3>
			</h3>
		</div>
	</div>
	
		<h4 class="card-title mt-4">Como voce prefere pagar?</h4>
		<form action="resumo.jsp" method="POST" name="formPagamento">
			<div id="umCartao">
				<div class="row">
					<div class="col-md-12 mt-3">
						<c:forEach var="cartoes" items="${ cartoes }">
							<div class="card col-md-8">
								<div class="card-body">
									<div class="form-inline">
										<input type="radio" name="cartao1" id="idPagamento"
											value="${ cartoes.id }"> <i
											class="fa fa-credit-card m-2" style="font-size: 40px;"></i>
										<div class="form-group">
											<p class="card-text card_botton">Terminado em ${ cartoes.numero.substring(cartoes.numero.length() - 4) }</p>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<div id="doisCartoes" style="display: none;">
				<div class="row">
					<div class="col-md-12 mt-3">
						<c:forEach var="cartoes" items="${ cartoes }">
							<div class="card col-md-8">
								<div class="card-body">
									<div class="form-inline">
										<input type="checkbox" name="cartao2" id="idPagamento"
											onclick="verificar(${ cartoes.id });" value="${ cartoes.id }">
										<i class="fa fa-credit-card m-2" style="font-size: 40px;"></i>
										<div class="form-group">
											<div class="row">
												<p class="card-text card_botton col-md-8">Terminado em
													${ cartoes.numero.substring(cartoes.numero.length() - 4) }</p>
												<input class="form-control col-md-4" pattern= "[0-9]{0,15}[.]{0,1}[0-9]{0,4}"
													name='txtValorCartao${ cartoes.id }'
													id='txtValorCartao${ cartoes.id }' type="text"  onclick="colocaId(${ cartoes.id });"
													onblur='somaValor(${ cartoes.id });' value=""
													/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 mt-3">
					<h4 class="card-title mt-2">Outros meios de pagamento</h4>

					<div class="card col-md-8">
						<div class="card-body">
							<div class="form-inline">
								<input type="radio" name="metodoPagamento" id="cartaoUm"
									onclick="exibirUmCartao()" value="umCartao" checked> <i
									class="fa fa-credit-card m-3" style="font-size: 40px;"></i>
								<div class="form-group">
									<p class="card-text card_botton">1 Cartão de Credito</p>
								</div>
							</div>
						</div>
					</div>

					<div class="card col-md-8">
						<div class="card-body">
							<div class="form-inline">
								<input type="radio" name="metodoPagamento" id="cartaoDois"
									onclick="exibirDoisCartao()" value="doisCartoes"> <i
									class="fa fa-credit-card m-3" style="font-size: 40px;"></i>
								<div class="form-group">
									<p class="card-text card_botton">2 Cartões de Credito</p>
								</div>
							</div>
						</div>
					</div>

					<div class="card col-md-8">
						<div class="card-body">
							<div class="form-inline">
								<input type="radio" name="metodoPagamento" id="boleto"
									onclick="exibirBoleto()" value="cupom"> <i
									class="fa fa-barcode m-3" style="font-size: 40px;"></i>
								<div class="form-group">
									<p class="card-text card_botton">Cupom</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
			<div class="card col-md-7 m-3">
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
				
				<div class="row">
					<div class="col-md-4 mt-4">
					    <h6 class="card-subtitle mb-2 col-md-8 text-muted"><b>Cupom de desconto</b></h6>
			    	</div>
				</div>
				
				  <c:if test="${ resultadoErroCupom != null }">
					  	<div class="alert alert-danger col-md-6" role="alert">
						  	<c:forTokens items = "${ resultadoErroCupom.msg }" delims = "!" var = "erro">
					    		<c:out value = "${erro}"/><br>
					      	</c:forTokens>
						</div>
				  </c:if>
				
					<div class="row">
					    <div class="form-inline m-2">
							<input name="txtCupom" id="txtCupom" type="text" value="${ param['txtCupom'] }" class="form-control">
							<button type="submit" formaction="ControleCupom" name="btnOperacao" value="ALTERAR" class="btn btn-primary">APLICAR</button>
					    </div>
					</div>
					</div>
			</div>
		</div>	
		
		<input type="hidden" name="idCupom" id="idCupom" value="${ resultadoCupom.id }" />
		<input type="hidden" name="valorCupom" id="valorCupom" value="${ resultadoCupom.valor }" />
			
		
		<input type="hidden" name="cartao10" id="cartao10" value="" />
		<input type="hidden" name="cartao20" id="cartao20" value="" />	

			<div class="row">
				<div class="col-md-8 ">
					<button type="submit" id="btnContinuar" class="btn btn-primary w-25 h-10 mt-3">Continuar</button>
					<a href="CadastrarCartaoPagamento.jsp" type="button" id="btnCartao"
						class="btn btn-success mt-3">adicionar cartão</a>
				</div>
			</div>
		</form>

		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
	<!-- /.container -->

	<c:import url="fragmentos/footer.jsp" />
	
}
	
	<!-- Bootstrap core JavaScript -->
	<script src="../assets/javascripts/vendors/jquery.min.js"></script>
	<script src="../assets/javascripts/EscolherPagamento.js"></script>
	<script src="../assets/javascripts/vendors/bootstrap.min.js"></script>


</body>

</html>
