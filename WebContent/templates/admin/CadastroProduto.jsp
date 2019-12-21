<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - cadastrar produto</title>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/upload.min.css"/>
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
			Cadastro de produto
		</h1>
	</div>
</div>

<div class="container-fluid">
<div class="row">
	<c:if test="${ resultadoSalvar != null && !resultadoSalvar.msg.equals('Cadastro realizado com sucesso.') && clienteSalvo == null }">
	  	<div class="alert alert-danger col-md-6" role="alert">
		  	<c:forTokens items = "${ resultadoSalvar.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
</div>

<div class="row">
	<c:if test="${ clienteSalvo != null && clienteSalvo.msg.equals('Cadastro realizado com sucesso.') }">
  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ clienteSalvo.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  </div>

	<form method="POST" action="ControleJogo" class="form-vertical">
		<div class="row">
			<div class="col-md-3 form-group">
				<label for="input-produto-codigo">Código</label>
				<input id="txtCodigo" name="txtCodigo" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).codigo}"/></c:if>'class="form-control"/>
			</div>
			
			<div class="col-md-6 form-group">
				<label for="input-produto-nome">Título</label>
				<input id="txtTitulo" name="txtTitulo" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).titulo}"/></c:if>'class="form-control"/>
			</div>
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Desenvolvedora</label>
				<input id="txtDesenvolvedor" name="txtDesenvolvedor" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).desenvolvedor.nome}"/></c:if>'class="form-control"/>
			</div>
		</div>

		<div class="row">
			<div class="col-md-3  form-group">
				<label class="control-label" for="plataforma">Plataforma</label>
					<select id="txtPlataforma" name="txtPlataforma" class="form-control">
						<option value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).plataforma}"/></c:if>'><c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).plataforma}"/></c:if></option>
						<option value="PS4">PS4</option>
					    <option value="PS3">PS3</option>
					    <option value="XBOX ONE">XBOX ONE</option>
					    <option value="XBOX 360">XBOX 360</option>
					</select>
			</div>
			
<!-- 			<div class="col-md-3 form-group"> -->
<!-- 				<label for="input-produto-nome">Categoria</label> -->
<!-- 				<input id="txtCategoria" name="txtCategoria" type="text" class="form-control"/> -->
<!-- 			</div> -->
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Gênero</label>
				<select id="txtGenero" name="txtGenero" class="form-control">
						<option value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).generoJogo}"/></c:if>'><c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).generoJogo}"/></c:if></option>
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
			
			<div class="col-md-3  form-group">
				<label class="control-label" for="classificacaoIndicativa">Classificação Indicativa</label>
					<select id="txtClassificacaoIndicativa" name="txtClassificacaoIndicativa" class="form-control">
						<option value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).classificacaoIndicativa}"/></c:if>'><c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).classificacaoIndicativa}"/></c:if></option>
						<option value="LIVRE">LIVRE</option>
					    <option value="10">10</option>
					    <option value="12">12</option>
					    <option value="14">14</option>
					    <option value="16">16</option>
					    <option value="18">18</option>
					</select>
			</div>
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Distribuidora</label>
				<input id="txtDistribuidora" name="txtDistribuidora" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).distribuidora.nome}"/></c:if>'class="form-control"/>
			</div>
			
		</div>
		
		<div class="row">
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Número de jogadores offline</label>
				<input id="txtNumeroJogadoresOffline" name="txtNumeroJogadoresOffline" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).numeroJogadoresOffline}"/></c:if>'class="form-control js-plain"/>
			</div>
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Número de jogadores online</label>
				<input id="txtNumeroJogadoresOnline" name="txtNumeroJogadoresOnline" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).numeroJogadoresOnline}"/></c:if>'class="form-control js-plain"/>
			</div>
			
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Preço de Compra</label>
				<input id="txtPrecoCompra" name="txtPrecoCompra" type="text" value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).precoCompra}"/></c:if>'class="form-control"/>
			</div>
		
			<div class="col-md-3 form-group">
				<label for="input-produto-nome">Quantidade</label>
				<input id="txtQuantidade" name="txtQuantidade" type="text"
				value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).quantidade}"/></c:if>'class="form-control js-plain"/>
			</div>
		</div>
		
	<div class="row">
		<div class="form-group  col-sm-12"> 
			<label class="control-label">Foto</label> 
			<input type="text" id="txtUrlFoto" name="txtUrlFoto" 
			value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).urlFoto}"/></c:if>'class="form-control h-50"/> 
		</div>
	</div>
		
	<div class="row">
		<div class="form-group col-md-12">
			<label for="input-produto-descricao">Descrição</label>
			<input id="txtDescricao" name="txtDescricao"
			value='<c:if test="${ resultadoSalvar.entidades != null }"><c:out value="${resultadoSalvar.entidades.get(0).descricao}"/></c:if>'class="form-control h-50"/>
		</div>
	</div>
		

		
		<div class="form-group">
			<button class="btn btn-primary" type="submit" id="btnOperacao" name="btnOperacao" value="SALVAR">Salvar</button>
			<a href="pesquisa-produtos.html" class="btn  btn-default">Cancelar</a>
		</div>
	</form>
</div>
	</section>
	
	<footer class="zg-layout-footer  js-content">
		<div class="container-fluid">
			<span class="zg-footer-disclaimer">&copy; 2019 FATEC MOGI DAS CRUZES. Todos os direitos reservados.</span>
		</div>
	</footer>

<div class="zg-search-modal  js-search-modal">
	<form action="#" class="zg-search-modal__form">
		<input class="zg-search-modal__input  js-search-modal-input" type="text" placeholder="O que voce esta procurando?"/>
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
<script src="../../assets/javascripts/util.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>
<script src="../../assets/javascripts/vendors/upload.min.js"></script>
<script src="../../assets/javascripts/vendors/uikit.min.js"></script>
<script src="../../assets/javascripts/vendors/bootstrap-toggle.min.js"></script>


<script type="text/javascript">
	function uploadFile(){
		var target = document.querySelector(".js-img");
		var file = document.querySelector("input[type=file]").files[0];
		
		var reader = new FileReader();
		
		reader.onloadend = function (){
			target.src = reader.result;
			document.getElementById("txtUrlFoto").value = reader.result;
		};
		
		if(file){
			reader.readAsDataURL(file);
		}else{
			target.src = "";
		}
	}
</script>

</body>
</html>