<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>Zero1Games - pesquisa de produto</title>

<link rel="stylesheet" type="text/css"
	href="../../assets/stylesheets/vendors/bootstrap-toggle.min.css" />
<link rel="stylesheet" type="text/css"
	href="../../assets/stylesheets/vendors/extra-dashboard.css" />
<link rel="stylesheet" type="text/css"
	href="../../assets/stylesheets/zero1games.css" />
<link rel="stylesheet" type="text/css"
	href="../../assets/stylesheets/style.css" />

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

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
				<h1>Pesquisa de produto</h1>
			</div>
		</div>

		<div class="container-fluid">

			<div class="row">
				<c:if
					test="${ resultado != null && !resultado.msg.contains('sucesso') }">
					<div class="alert alert-danger col-md-6" role="alert">
						<c:forTokens items="${ resultado.msg }" delims="!" var="erro">
							<c:out value="${erro}" />
							<br>
						</c:forTokens>
					</div>
				</c:if>
			</div>

			<div class="row">
				<c:if
					test="${ resultado != null && resultado.msg.contains('sucesso') }">
					<div class="alert alert-success col-md-4 offset-md-4" role="alert">
						<c:forTokens items="${ resultado.msg }" delims="!" var="erro">
							<c:out value="${erro}" />
							<br>
						</c:forTokens>
					</div>
				</c:if>
			</div>

			<form method="POST" action="ControleJogo"
				class="form-vertical  js-form-loading">
				<div class="row">
					<div class="col-md-3 form-group">
						<label for="codigo">Código</label> <input type="text"
							id="txtCodigo" name="txtCodigo"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.codigo}"/></c:if>'
							class="form-control" />
					</div>

					<div class="col-md-6 form-group">
						<label for="titulo">Título</label> <input type="text"
							id="txtTitulo" name="txtTitulo"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.titulo}"/></c:if>'
							class="form-control" />
					</div>

					<div class="col-md-3 form-group">
						<label for="desenvolvedora">Desenvolvedora</label> <input
							type="text" id="txtDesenvolvedor" name="txtDesenvolvedor"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.desenvolvedor.nome}"/></c:if>'
							class="form-control" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 form-group">
						<label for="plataforma">Plataforma</label> <select
							id="txtPlataforma" name="txtPlataforma" class="form-control">
							<option
								value='<c:if test="${ jogo != null }"><c:out value="${jogo.plataforma}"/></c:if>'><c:if
									test="${ jogo != null }">
									<c:out value="${jogo.plataforma}" />
								</c:if></option>
							<option value="PS4">PS4</option>
							<option value="PS3">PS3</option>
							<option value="XBOX ONE">XBOX ONE</option>
							<option value="XBOX 360">XBOX 360</option>
						</select>
					</div>

					<div class="col-md-3 form-group">
						<label for="genero">Gênero</label> <select id="txtGenero"
							name="txtGenero" class="form-control">
							<option
								value='<c:if test="${ jogo != null }"><c:out value="${jogo.generoJogo}"/></c:if>'><c:if
									test="${ jogo != null }">
									<c:out value="${jogo.generoJogo}" />
								</c:if></option>
							<option value="ACAO">AÇÃO</option>
							<option value="AVENTURA">AVENTURA</option>
							<option value="TIRO">TIRO</option>
							<option value="RPG">RPG</option>
							<option value="ESPORTE">ESPORTE</option>
							<option value="LUTA">LUTA</option>
							<option value="MUSICA">MUSICA</option>
							<option value="CORRIDA">CORRIDA</option>
						</select>
					</div>

					<div class="col-md-3 form-group">
						<label for="distribuidora">Distribuidora</label> <input
							type="text" id="txtDistribuidora" name="txtDistribuidora"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.distribuidora.nome}"/></c:if>'
							class="form-control" />
					</div>

					<div class="col-md-3 form-group">
						<label for="numero de jogadores online">Número de
							jogadores online</label> <input id="txtNumeroJogadoresOnline"
							name="txtNumeroJogadoresOnline"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.numeroJogadoresOnline}"/></c:if>'
							type="text" class="form-control js-plain" />
					</div>

				</div>

				<div class="row">

					<div class="col-md-3  form-group">
						<label class="control-label" for="classificacaoIndicativa">Classificação
							Indicativa</label> <select id="txtClassificacaoIndicativa"
							name="txtClassificacaoIndicativa" class="form-control">
							<option
								value='<c:if test="${ jogo != null }"><c:out value="${jogo.classificacaoIndicativa}"/></c:if>'><c:if
									test="${ jogo != null }">
									<c:out
										value="${jogo.classificacaoIndicativa}" />
								</c:if></option>
							<option value="LIVRE">LIVRE</option>
							<option value="DEZ">DEZ</option>
							<option value="DOZE">DOZE</option>
							<option value="QUATORZE">QUATORZE</option>
							<option value="DEZESSEIS">DEZESSEIS</option>
							<option value="DEZOITO">DEZOITO</option>
						</select>
					</div>

					<div class="col-md-3 form-group">
						<label for="input-produto-nome">Número de jogadores
							offline</label> <input id="txtNumeroJogadoresOffline"
							name="txtNumeroJogadoresOffline"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.numeroJogadoresOffline}"/></c:if>'
							type="text" class="form-control js-plain" />
					</div>

					<div class="col-md-2  form-group">
						<label for="precoDe">Preço de compra</label> <input type="text"
							id="txtPrecoCompra" name="txtPrecoCompra"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.precoCompra}"/></c:if>'
							class="form-control js-decimal" />
						<!-- 					<label for="valorAte" class="zg-form-label-between">até</label> -->
						<!-- 					<input type="text" class="form-control  zg-form-control-inline-sm js-decimal" id="valorAte"/> -->
					</div>

					<div class="col-md-2  form-group">
						<label for="precoDe">Quantidade</label> <input type="text"
							class="form-control js-plain" id="txtQuantidade"
							name="txtQuantidade"
							value='<c:if test="${ jogo != null }"><c:out value="${jogo.quantidade}"/></c:if>' />
						<!-- 					<label for="valorAte" class="zg-form-label-between">até</label> -->
						<!-- 					<input type="text" class="form-control  zg-form-control-inline-sm js-plain" id="valorAte"/> -->
					</div>
					
					<div class="col-md-2 form-group">
						<label for="txtStatus">Status</label> <select id="txtStatus"
							name="txtStatus" class="form-control">
							<option
								value='<c:if test="${ jogo != null }"><c:out value="${jogo.status}"/></c:if>'><c:if
									test="${ jogo != null }">
									<c:out value="${jogo.status}" />
								</c:if></option>
							<option value="true">Ativo</option>
							<option value="false">Desativado</option>

						</select>
					</div>

				</div>

				<div class="form-group">
					<button class="btn  btn-primary" type="submit" name="btnOperacao"
						value="CONSULTAR">Pesquisar</button>
				</div>

			</form>

			<div class="table-responsive  zg-tabela-simples">
				<table class="table  table-bordered table-hover">
					<thead>
						<tr>
							<th class="table-jogos-col-codigo">Código</th>
							<th class="table-jogos-col-titulo">Título</th>
							<th class="table-jogos-col-desenvolvedor">Desenvolvedora</th>
							<th class="table-jogos-col-plataforma">Plataforma</th>
							<th class="table-jogos-col-categoria">Gênero</th>
							<th class="table-jogos-col-categoria">Distribuidora</th>
							<th class="table-jogos-col-valor">Quantidade</th>
							<th class="table-jogos-col-valor">Preço compra</th>
							<th class="table-jogos-col-valor">Preço venda</th>
							<th class="table-jogos-col-acoes"></th>
						</tr>
					</thead>
		
					<tbody>
					
						<c:forEach var="produto" items="${ resultadoConsultaJogo.entidades }">
							<tr>
								<td class="text-center">${ produto.codigo }</td>
								<td>${ produto.titulo }</td>
								<td>${ produto.desenvolvedor.nome }</td>
								<td>${ produto.plataforma }</td>
								<td>${ produto.generoJogo }</td>
								<td>${ produto.distribuidora.nome }</td>
								<td>${ produto.quantidade }</td>
								<td>R$ ${ produto.precoCompra }</td>
								<td>R$ ${ produto.precoVenda }</td>
								<td class="text-center">
									<a href="ControleJogo?btnOperacao=PERFIL&id=${ produto.id }" id="editar" title="Editar"> 
										<i class="fa fa-pencil"></i>
									</a> 
									<a href="ControleJogo?btnOperacao=EXCLUIR&id=${ produto.id }" class="btn  btn-link  btn-xs" id="excluir" title="Excluir"> <i
										class="fa fa-trash"></i>
									</a>
									<c:if test="${ !produto.status }">
										<a href="ControleAtivacao?btnOperacao=ALTERAR&id=${ produto.id }&op=ativar" class="btn  btn-link  btn-xs" id="Ativar" title="Ativar jogo"> <i
											class="fa fa-wrench"></i>
										</a>
									</c:if>
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
			<span class="zg-footer-disclaimer">&copy; 2019 FATEC MOGI DAS
				CRUZES. Todos os direitos reservados.</span>
		</div>
	</footer>

	</div>

	<div class="zg-search-modal  js-search-modal">
		<form action="#" class="zg-search-modal__form">
			<input class="zg-search-modal__input  js-search-modal-input"
				type="text" placeholder="O que vocÃª estÃ¡ procurando?" />
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
	<script src="../../assets/javascripts/sweetalert.init.js"></script>
	<script src="../../assets/javascripts/util.js"></script>
	<script src="../../assets/javascripts/zero1games.js"></script>
	<script src="../../assets/javascripts/vendors/bootstrap-toggle.min.js"></script>

</body>
</html>