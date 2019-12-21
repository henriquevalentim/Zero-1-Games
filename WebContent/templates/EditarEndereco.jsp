<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - endereços</title>

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
	
	<h4 class="m-3"><b>Editar endereço</b></h4>
	
	<c:if test="${ resultado != null && !resultado.msg.contains('sucesso') }">
	  	<div class="alert alert-danger col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  
  <c:if test="${ resultado != null && resultado.msg.contains('sucesso') }">
  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
	
	<form method="POST" action="ControleEndereco">
		<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="txtNomeEndereco">Nome Endereco</label>
				<input id="txtNomeEndereco" name="txtNomeEndereco" type="text"
				value="${EnderecoPerfil != null ? EnderecoPerfil.nome : ''}" class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
			<label for="sabor" class="control-label">Tipo de residência</label>
					<select id="txtTpResidencia" name="txtTpResidencia" class="form-control">
						<option value="${EnderecoPerfil != null ? EnderecoPerfil.tipoResidencia : ''}">${EnderecoPerfil != null ? EnderecoPerfil.tipoResidencia : 'CASA'}</option>
						<option value="APARTAMENTO">Apartamento</option>
						<option value="CASA" >Casa</option>
						<option value="COMERCIAL">Comercial</option>
						<option value="OUTRO">Outro</option>
					</select>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="txtCep">CEP</label>
				<input id="txtCep" name="txtCep" type="text" 
				value="${EnderecoPerfil != null ? EnderecoPerfil.cep : ''}" class="form-control"/>
			</div>
			
	</div>
	
	<div class="row">
	
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="numero">Numero</label>
				<input id="txtNumero" name="txtNumero" type="text" 
				value="${EnderecoPerfil != null ? EnderecoPerfil.numero : ''}" class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="logradouro">Logradouro</label>
				<input id="txtLogradouro" name="txtLogradouro" type="text" 
				value= "${EnderecoPerfil != null ? EnderecoPerfil.logradouro : ''}" class="form-control"/>
			</div>
	
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="bairro">Bairro</label>
				<input id="txtBairro" name="txtBairro" type="text" 
				value="${EnderecoPerfil != null ? EnderecoPerfil.bairro : ''}" class="form-control"/>
			</div>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="complemento">Complemento</label>
				<input id="txtComplemento" name="txtComplemento" type="text" 
				value="${EnderecoPerfil != null ? EnderecoPerfil.complemento : ''}" class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
					<label class="control-label" for="tipologradouro">Tipo de logradouro</label>
						<select id="txtTpLogradouro" name="txtTpLogradouro" name="txtTpLogradouro" class="form-control">
							<option value="${EnderecoPerfil != null ? EnderecoPerfil.tipoLogradouro : ''}">${EnderecoPerfil != null ? EnderecoPerfil.tipoLogradouro : ''}</option>
							<option value="AVENIDA">Avenida</option>
							<option value="RUA">Rua</option>
							<option value="TRAVESSA">Travessa</option>
							<option value="OUTRO">Outro</option>
						</select>
			</div>
			
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cidade">Cidade</label>
				<input id="txtCidade" name="txtCidade" type="text"
				value="${EnderecoPerfil != null ? EnderecoPerfil.cidade.nome : ''}" class="form-control"/>
			</div>
	
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="estado">Estado</label>
				<input id="txtEstado" name="txtEstado" type="text" 
				value="${EnderecoPerfil != null ? EnderecoPerfil.cidade.estado.nome : ''}" class="form-control"/>
			</div>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="pais">País</label>
				<input id="txtPais" name="txtPais" type="text"
				value="${EnderecoPerfil != null ? EnderecoPerfil.cidade.estado.pais.nome : ''}" class="form-control"/>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<div class="form-check">
	  				<input class="form-check-input" type="checkbox" value="COBRANCA" name="txtEnderecoCobranca" id="defaultCheck1" ${EnderecoPerfil.cobranca == true ? 'checked' : ''}>
	  					<label class="form-check-label" for="defaultCheck1">
	    					Endereço de Cobrança
	  					</label>
				</div>
				
				<div class="form-check">
	  				<input class="form-check-input" type="checkbox" value="ENTREGA" name="txtEnderecoEntrega" id="defaultCheck1"  ${EnderecoPerfil.entrega == true ? 'checked' : ''}>
	  					<label class="form-check-label" for="defaultCheck1">
	    					Endereço de Entrega
	  					</label>
				</div>
			</div>
			
	</div>
	
	<br>
	<br>
	<input type="hidden" name="id" value="${EnderecoPerfil != null ? EnderecoPerfil.id : ''}">
	
	<div class="row">
		<div class="offset-md-4 col-md-4">
			<button type="submit" class="btn btn-success w-25" name="btnOperacao" value="ALTERAR">Editar</button>
			<a type="button" class="btn btn-light w-25" href="ControleEndereco?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).id }">Cancelar</a>
		</div>
	</div>
	
	<br>
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
