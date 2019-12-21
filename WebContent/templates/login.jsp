<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Zero1Games - Login</title>

  <!-- Bootstrap core CSS -->
  <link href="../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
  
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  
  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">

</head>

<body class="bg-dark">

  <c:import url="fragmentos/navbar.jsp" />
  
  <c:if test="${ usuarioErro != null }">
		  	<div class="alert alert-danger col-md-4 offset-md-4 mt-2" role="alert">
			  	<c:forTokens items = "${ usuarioErro.msg }" delims = "!" var = "erro">
		    		<c:out value = "${erro}"/><br>
		      	</c:forTokens>
			</div>
	  </c:if>

  <div class="container">
	  <div class="row">
		  <div class="col-md-6 offset-md-3">
		    <div class="card card-login mt-5">
		      <div class="card-header">Login</div>
		      <div class="card-body">
		        <form action="ControleUsuario" method="POST">
		          <div class="form-group">
		            <div class="form-label-group">
		              <label for="inputEmail">E-mail</label>
		              <input type="email" id="txtLogin" name="txtLogin" class="form-control" placeholder="Ex. gabrielbittencourt@gmail.com" required="required" autofocus="autofocus">
		            </div>
		          </div>
		          <div class="form-group">
		            <div class="form-label-group">
		              <label for="inputPassword">Senha</label>
		              <input type="password" id="txtSenha" name="txtSenha" class="form-control" placeholder="*******" required="required">
		            </div>
		          </div>
		          <div class="form-group">
		            <div class="checkbox">
		              <label>
		                <input type="checkbox" value="remember-me">
		                Lembrar senha
		              </label>
		            </div>
		          </div>
		          <button type="submit" class="btn btn-primary btn-block" name="btnOperacao" value="CONSULTAR" >Entrar</button>
		        </form>
		        <div class="text-center">
		          <a class="d-block small mt-3" href="CadastroCliente.jsp">É novo na Zero1Games ? Cadastre-se!</a>
		          <a class="d-block small" href="#">Esqueceu sua senha?</a>
		        </div>
		      </div>
		    </div>
		  </div>
	  </div>
  </div>

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">2019 FATEC MOGI DAS CRUZES. Todos os direitos reservados.</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript-->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>


</body>

</html>
