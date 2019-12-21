<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - pesquisa de clientes</title>
	
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/bootstrap-toggle.min.css"/>
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
			Pesquisa de clientes
		</h1>
	</div>
</div>

<div class="container-fluid">

<div class="row">
	<c:if test="${ resultado != null && !resultado.msg.contains('sucesso') }">
	  	<div class="alert alert-danger col-md-6" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
</div>

<div class="row">
	<c:if test="${ resultado != null && resultado.msg.contains('sucesso') }">
  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  </div>

	<form action="ControleCliente" method="POST" class="form-vertical">
		<div class="row">
			<div class="col-md-6 form-group">
				<label for="input-produto-nome">E-mail</label>
				<input id="txtEmail" name="txtEmail" type="text"
					value='<c:if test="${ resultado.entidades != null }"><c:out value="${cliente.email}"/></c:if>'class="form-control"/>
			</div>
			
			<div class="col-md-6 form-group">
				<label for="input-produto-nome">Nome</label>
				<input id="txtNome" name="txtNome" type="text"
					value='<c:if test="${ resultado.entidades != null }"><c:out value="${cliente.nome}"/></c:if>'class="form-control"/>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 form-group">
				<label for="input-produto-nome">CPF</label>
				<input id="txtCpf" name="txtCpf" type="text"
					value='<c:if test="${ resultado.entidades != null }"><c:out value="${cliente.cpf}"/></c:if>'class="form-control"/>
			</div>
		
			<div class="col-md-4 form-group">
				<label for="input-produto-nome">Data Nascimento</label>
				<input id="txtDtNascimento" name="txtDtNascimento" type="date"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${cliente.dataNascimento}"/></c:if>'class="form-control"/>
			</div>
		
			<div class="col-md-4 form-group">
				<label for="input-produto-nome">Telefone</label>
				<input id="txtTelefone" name="txtTelefone" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${cliente.telefone.numero}"/></c:if>'class="form-control"/>
			</div>
			
		</div>
		
		
		<div class="form-group">
			<button class="btn  btn-primary" type="submit" name="btnOperacao" value="CONSULTAR">Pesquisar</button>
		</div>

	</form>
	
	<div class="table-responsive  zg-tabela-simples">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th class="table-jogos-col-codigo">E-mail</th>
						<th class="table-jogos-col-titulo">Nome</th>
						<th class="table-jogos-col-desenvolvedor">CPF</th>
						<th class="table-jogos-col-plataforma">Telefone</th>
						<th class="table-jogos-col-categoria">Data Nascimento</th>
						<th class="table-jogos-col-acoes"></th>
					</tr>
				</thead>
				
				<tbody>
				
				<c:forEach var="cliente" items="${ resultado.entidades }">
					<tr>
						<td>${ cliente.email }</td>
						<td>${ cliente.nome }</td>
						<td>${ cliente.cpf }</td>
						<td>${ cliente.telefone.numero }</td>
						<td>${ cliente.dataNascimento }</td>
						<td class="text-center">
							<a class="btn  btn-link  btn-xs" href="ControleCliente?btnOperacao=EXCLUIR&idCliente=${ cliente.id }" id="excluir" title="Excluir">
								<i class="fa fa-trash" id="excluirCliente"></i>
							</a>
						</td>
					</tr>
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
<script src="../../assets/javascripts/vendors/jquery.maskMoney.min.js"></script>
<script src="../../assets/javascripts/sweetalert2.js"></script>
<script src="../../assets/javascripts/sweetalert.init.js"></script>
<script src="../../assets/javascripts/util.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>
<script src="../../assets/javascripts/vendors/bootstrap-toggle.min.js"></script>

</body>
</html>