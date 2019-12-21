<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - Pendencias</title>
	
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/extra-dashboard.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/zero1games.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/style.css"/>
	
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

<div class="zg-layout-loading  js-loading-overlay">
	<div class="zg-layout-loading__container">
		<span class="zg-balls-spinner">Carregando...</span>
	</div>
</div>

    <c:import url="../fragmentos/navbaradm.jsp" />

	<section class="zg-layout-content  js-content">


<div class="page-header">
	<div class="container-fluid">
		<h1>
			Solicitações - Aguardando aprovação
		</h1>
	</div>
</div>

<div class="container-fluid">


	<form  action="ControleSolicitacaoPedido" class="form-vertical" method="POST">
		<div class="row">
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">N° pedido</label>
				<input type="text" name="txtNumeroPedido" class="form-control"/>
			</div>
			
			<div class="col-md-6 form-group">
				<label for="input-produto-nome">Nome cliente</label>
				<input type="text" name="txtNomeCliente" class="form-control"/>
			</div>
			
		</div>
			<div class="row">
				<div class="col-md-4 form-group">
					<label for="input-produto-nome">Status</label> <select id="end" name="txtStatus"
						class="form-control">
						<option value=""></option>
						<option value="1">AGUARDANDO APROVAÇÃO DE PAGAMENTO</option>
						<option value="2">PAGAMENTO REPROVADO</option>
						<option value="3">EM TRASPORTE</option>
						<option value="4">ENTREGUE</option>
						<option value="5">AGUARDANDO APROVAÇÃO DE TROCA</option>
						<option value="6">TROCA REPROVADA</option>
						<option value="7">EM TROCA</option>
						<option value="8">TROCADO</option>
						<option value="9">COMPRA CANCELADA</option>
					</select>
				</div>

				<div class="col-md-4 form-group">
					<label for="input-produto-nome">Tipo de solicitação</label> <select
						id="end" class="form-control">
						<option value=""></option>
<!-- 					<option value="">APROVAÇÃO DE PAGAMENTO</option> -->
<!-- 					<option value="">APROVAÇÃO DE ENVIO</option> -->
						<option value="APROVACAO+DE+TROCA">APROVAÇÃO DE TROCA</option>
						<option value="APROVACAO+DE+CANCELAMENTO">APROVAÇÃO DE CANCELAMENTO</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<button class="btn btn-primary" type="submit" name="btnOperacao" value="CONSULTAR">Pesquisar</button>
			</div>

	</form>
	
	<div class="table-responsive  zg-tabela-simples">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th class="table-jogos-col-codigo text-center">N° pedido</th>
						<th class="table-jogos-col-titulo text-center">Nome cliente</th>
						<th class="table-jogos-col-desenvolvedor text-center">Status</th>
						<th class="table-jogos-col-plataforma text-center">Solicitação</th>
						<th class="table-jogos-col-acoes text-center">Ações</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="pedido" items="${ resultado.entidades }">
						<c:if test="${ pedido.solicitacao != null }">
							<tr>
								<td class="text-center" >${ pedido.numeroPedido }</td>
								<td class="text-center">${ pedido.cliente.nome }</td>
								<td class="text-center">${ pedido.statusPedido.nome }</td>
								<td class="text-center">${ pedido.solicitacao }</td>
								<td class="text-center">
								
										<a href="ControleSolicitacaoPedido?btnOperacao=ALTERAR&idPedido=${ pedido.id }&txtSolicitacao=${ pedido.solicitacao }&txtStatus=${ pedido.statusPedido.id }&op=true" class="badge badge-primary btn-xs">
										<i class="fa fa-check-circle" style="font-size: 20px;"></i>
									</a>
		
									<a href="ControleSolicitacaoPedido?btnOperacao=ALTERAR&idPedido=${ pedido.id }&txtSolicitacao=${ pedido.solicitacao }&txtStatus=${ pedido.statusPedido.id }&op=false" class="badge badge-warning btn-xs">
										<i class="fa fa-window-close" style="font-size: 20px;" ></i>
									</a>
									
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
</div>

	</section>
	
	<footer class="zg-layout-footer  js-content">
		<div class="container-fluid">
			<span class="zg-footer-disclaimer">&copy; 2019 FATEC MOGI DAS CRUZES. Todos os direitos reservados.</span>
		</div>
	</footer>

</div>

<div class="zg-search-modal  js-search-modal">
	<form action="#" class="zg-search-modal__form">
		<input class="zg-search-modal__input  js-search-modal-input" type="text" placeholder="O que vocÃª estÃ¡ procurando?"/>
		<div class="zg-search-modal__input-icon">
			<i class="glyphicon  glyphicon-search  js-search-modal-go"></i>
		</div>
	</form>
	
	<div class="zg-search-modal__controls">
		<i class="glyphicon glyphicon-remove  js-search-modal-close"></i>
	</div>
</div>

<script src="../../assets/javascripts/vendors.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>

</body>
</html>