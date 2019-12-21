<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - cartões</title>

  <!-- Bootstrap core CSS -->
  <link href="../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
  
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  

  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">

</head>

<body>

  <c:import url="fragmentos/navbarLogado.jsp" />
  
  <!-- Page Content -->
  	<div class="container">
	
  	<h4 class="m-3">Minha conta</h4>
	
	<c:import url="fragmentos/navbarCliente.jsp" />
	
	<h4 class="m-3"><b>Adicionar cartão de crédito</b></h4>
	
	
		<c:if test="${ resultado != null && !resultado.msg.equals('Cadastro realizado com sucesso.') }">
		  	<div class="alert alert-danger col-md-4 offset-md-4" role="alert">
			  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
		    		<c:out value = "${erro}"/><br>
		      	</c:forTokens>
			</div>
	  </c:if>
	  
	  <c:if test="${ resultado != null && resultado.msg.equals('Cadastro realizado com sucesso.') }">
	  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
			  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
		    		<c:out value = "${erro}"/><br>
		      	</c:forTokens>
			</div>
	  </c:if>
	
	  <form action="ControlePagamentoCartao" method="POST">
	  <div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label">Nome impresso no cartão</label>
				<input id="txtNomeCartao" name="txtNomeCartao" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).nome}"/></c:if>'class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cep">Numero do cartão</label>
				<input id="txtNumeroCartao" name="txtNumeroCartao" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).numero}"/></c:if>'class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
			<label for="sabor" class="control-label">Bandeira</label>
				<select id="txtBandeiraId" name="txtBandeiraId" class="form-control">
					<option value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bandeira}"/></c:if>'><c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bandeira}"/></c:if></option>
					<option value="1">Visa</option>
					<option value="2">Mastercard</option>
					<option value="3">Elo</option>
					<option value="4">American Express</option>
					<option value="5">Hipercard</option>
				</select>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="complemento">Código de segurança (CSV)</label>
				<input id="txtCodigoCartao" name="txtCodigoCartao" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).codigoSeguranca}"/></c:if>'class="form-control"/>
			</div>
	</div>
	
	
	<br>
	<input type="hidden" name="txtIdCliente" value="${ UsuarioAutenticado.entidades.get(0).id }">
	<div class="row">
		<div class="offset-md-4 col-md-4">
			<button type="submit" class="btn btn-success w-25" name="btnOperacao" value="SALVAR">Salvar</button>
			<button type="button" class="btn btn-light w-25">Cancelar</button>
		</div>
	</div>
	
	<br>
	
  </form>
<!--fim row -->

  	</div>
  <!-- /.container -->
		<br><br>

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
