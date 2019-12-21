<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - Alterar cadastro</title>

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

	<h4 class="m-3"><b>Cadastro</b></h4>
	<div class="offset-md-6 col-md-4">
		<!-- BotÃ£o para acionar modal -->
		<button type="button" class="btn btn-primary ml-4" data-toggle="modal" data-target="#modalExemplo">
 		Alterar Senha
		</button>

		<!-- Modal -->
		<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  		<div class="modal-dialog" role="document">
		    	<div class="modal-content">
		      	<div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Alterar Senha</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
			        <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		      	<div class="modal-body">
		      		<form action="ControleSenha" method="POST">
			        	<div class="col-md-8  form-group">
							<label class="control-label">Senha atual</label>
							<input type="password" name="txtSenha" class="form-control"/>
						</div>
						
						<div class="col-md-8  form-group">
							<label class="control-label">Nova senha</label>
							<input type="password" name="senhaNova" class="form-control"/>
						</div>
						
						<div class="col-md-8  form-group">
							<label class="control-label">Redigite a nova senha</label>
							<input type="password" name="confirmaSenha" class="form-control"/>
						</div>
						<div class="modal-footer">
						<input type="hidden" name="txtLogin" value="${ UsuarioAutenticado.entidades.get(0).email }"/>
						<input type="hidden" name="txtUsuarioId" value="${ UsuarioAutenticado.entidades.get(0).usuario.id }"/>
			        <button type="submit" name="btnOperacao" value="ALTERAR" class="btn btn-primary">Alterar</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
		      	</div>
					</form>
		      	</div>
		      	
		    	</div>
	  		</div>
		</div>
	</div>
		
	<c:if test="${ resultadoErroAlterar != null && !resultadoErroAlterar.msg.contains('sucesso') }">
	  	<div class="alert alert-danger col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  
  <c:if test="${ resultado != null && resultado.msg.equals('Alteração realizada com sucesso.') }">
  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
		
	<form method="POST" action="ControleCliente">
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="email">E-mail</label>
				<input id="txtEmail" name="txtEmail" type="text"
					value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).email : ''}" class="form-control"/>
			</div>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="nome">Nome</label>
				<input id="txtNome" name="txtNome" type="text"
					value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).nome : ''}" class="form-control"/>
			</div>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="datanascimento">Data de nascimento</label>
				<input id="txtDtNascimento" name="txtDtNascimento" type="text"
					value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).dataNascimento : ''}" class="form-control"/>
			</div>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cpf">CPF</label>
				<input id="txtCpf" name="txtCpf" type="text"
					value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).cpf : ''}" class="form-control"/>
			</div>
			
				<input id="txtSenha" name="txtSenha" type="hidden"
					value="AAaa@@11" class="form-control"/>
					
				<input id="txtId" name="txtId" type="hidden"
					value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).id : ''}" class="form-control"/>

			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="genero">Gênero</label><br>
				<input type="radio" name="txtGenero" id="txtGenero" value="M" checked>
				  	<label class="form-check-label" for="exampleRadios1">
				    	Masculino
				  	</label>
				<input type="radio" name="txtGenero" id="txtGenero" value="F">
				  	<label class="form-check-label" for="exampleRadios1">
				    	Feminino
				  	</label>
			</div>
			
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="telefone">Telefone</label>
				<input id="txtTelefone" name="txtTelefone" type="text"
				value="${UsuarioAutenticado.entidades != null ? UsuarioAutenticado.entidades.get(0).telefone.numero : ''}" class="form-control"/>
			</div>
	</div>
	<br>
	
	<div class="row">
		<div class="offset-md-4 col-md-4">
			<button type="submit" class="btn btn-success w-50" name="btnOperacao" value="ALTERAR">Salvar alterações</button>
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
