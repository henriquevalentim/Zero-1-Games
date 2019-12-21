<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>Zero1Games - Controle de Estoque</title>

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
				<h1>Pesquisa de produtos em estoque</h1>
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

			<form method="POST" action="ControleEstoque"
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

				</div>

				<div class="row">

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
							<th class="table-jogos-col-plataforma">Plataforma</th>
							<th class="table-jogos-col-valor">Quantidade</th>
							<th class="table-jogos-col-acoes"></th>
						</tr>
					</thead>
		
					<tbody>
					
						<c:forEach var="produto" items="${ resultadoConsultaJogo.entidades }">
							<tr>
								<td class="text-center">${ produto.codigo }</td>
								<td>${ produto.titulo }</td>
								<td>${ produto.plataforma }</td>
								<td>${ produto.quantidade }</td>
								<td class="text-center">
<%-- 									<a href="ControleEstoque?btnOperacao=PERFIL&id=${ produto.id }" id="adicionar" title="Adicionar">  --%>
<!-- 										<i class="fa fa-plus"></i> -->
<!-- 									</a>  -->
									<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalExemploAdd${ produto.id }">
							  			Adicionar 
									</button>
<%-- 									<a href="ControleEstoque?btnOperacao=EXCLUIR&id=${ produto.id }" class="btn  btn-link  btn-xs" id="retirar" title="Retirar"> <i --%>
<!-- 										class="fa fa-minus"></i> -->
<!-- 									</a> -->
									<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalExemploRem${ produto.id }">
							  			Remover 
									</button>
							
									<!-- Modal Adicionar-->
									<div class="modal fade" id="modalExemploAdd${ produto.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">Quantos produtos deseja adicionar ao estoque?</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <form action="ControleEstoque" method="POST">
										      <div class="modal-body">
										        	<div class="col-md-8  form-group">
														<label class="control-label">Quantidade:</label>
														<input name="txtQuantidade" type="text" class="form-control"/>
														<input type="hidden" name="idJogo" value="${ produto.id }" class="form-control"/>
														<input type="hidden" name="txtAcao" value="Adicionar" class="form-control"/>
													</div>
										      </div>
										      <div class="modal-footer">
										        <button type="submit" class="btn btn-danger" name="btnOperacao" value="ALTERAR">Confirmar</button>
										        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
										      </div>
									      </form>
									    </div>
									  </div>
									</div>
									
									<!-- Modal Remover-->
									<div class="modal fade" id="modalExemploRem${ produto.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">Quantos produtos deseja remover do estoque?</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <form action="ControleEstoque" method="POST">
										      <div class="modal-body">
										        	<div class="col-md-8  form-group">
														<label class="control-label">Quantidade:</label>
														<input name="txtQuantidade" type="text" class="form-control"/>
														<input type="hidden" name="idJogo" value="${ produto.id }" class="form-control"/>
														<input type="hidden" name="txtAcao" value="Remover" class="form-control"/>
													</div>
										      </div>
										      <div class="modal-footer">
										        <button type="submit" class="btn btn-danger" name="btnOperacao" value="ALTERAR">Confirmar</button>
										        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
										      </div>
									      </form>
									    </div>
									  </div>
									</div>
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