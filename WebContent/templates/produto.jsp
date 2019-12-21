<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - Jogos</title>

  <!-- Bootstrap core CSS -->
  <link href="../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
  
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  

  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">
  <link href="../assets/stylesheets/categorias.css" rel="stylesheet">

</head>

<body>

  <c:import url="${ UsuarioAutenticado != null ? 'fragmentos/navbarLogado.jsp' : 'fragmentos/navbar.jsp'}" />

  <!-- Page Content -->
  <div class="container">

    <div class="row">
	    <div class="col-md-4 m-5">
	    	<img class="card-img-top img-fluid h-70" src="<c:out value="${ jogoPerfil.urlFoto }" />" alt="">
		</div>
		<div class="card col-md-5 m-3" style="width: 18rem;">
		  	<div class="card-body">
			    <div class="separador-card m-2">
				    <h4 class="card-title card-preco-red"><b><c:out value="${ jogoPerfil.titulo }" /></b></h4>
				    <div class="form-inline">
			    		<h6 class="card-subtitle m-2 text-muted little-word">Código: <c:out value="${ jogoPerfil.codigo }" /></h6>
				    </div>
			    </div>
			    <fmt:setLocale value="${user.locale}" />
			    <h3 class="card-text card-preco-red mt-5"><b><fmt:formatNumber value="${ jogoPerfil.precoVenda }" type="currency"></fmt:formatNumber></b></h3>
			    <h5 class="card-text">até <b>10x</b> de <b><fmt:formatNumber value="${ jogoPerfil.precoVenda / 10}"	type="currency"></fmt:formatNumber> </b></h5>
			    <h5 class="card-text mb-3">ou <b><fmt:formatNumber value="${ jogoPerfil.precoVenda - 30}" type="currency"></fmt:formatNumber></b> via Boleto Bancário</h5>
			    <form action="CarrinhoCompra" method="POST">
			    	<input type="hidden" name="id" value='<c:out value="${ jogoPerfil.id }"></c:out>'>
				    <button type="submit" class="btn btn-success w-50 offset-md-6" name="btnOperacao" value="ADICIONAR">Comprar</button>
			    </form>
			    
			    <div class="input-group mt-2">
					<input type="text" class="form-control col-md-3" value="08750-330" placeholder="CEP">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button" id="button-addon2">Calcular</button>
					</div>
					
				</div>
		  	</div>
			    
		</div>
    </div>
      
  </div>
  <!-- /.container -->

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
