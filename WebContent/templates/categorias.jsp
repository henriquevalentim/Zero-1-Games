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
      <div class="col-lg-12">
		<br>
	    <div class="row">
	    
	    <c:forEach var="jogo" items="${ resultadoConsultaJogo.entidades }">
		    <c:if test="${ jogo.status }">
			   	<div class="col-lg-3 col-md-6 mb-4">
					<div class="card card-imagem h-100">
						<a href="PerfilJogo?btnOperacao=PERFIL&id=${ jogo.id }">
						<img class="card-img-top img-fluid" src="${ jogo.urlFoto }" alt="">
						<div class="card-body">
							<h6 class="card-title text-center">
							${ jogo.titulo }
							</h6>
							<h5 class="card-preco-red text-center">10x <fmt:formatNumber value="${ jogo.precoVenda / 10}" type="currency"></fmt:formatNumber></h5>
							<h5 class="text-center"><b>sem juros</b> no cartão</h5>
							<h5 class="card-preco-green text-center">À vista <fmt:formatNumber value="${ jogo.precoVenda - 30}" type="currency"></fmt:formatNumber></h5>
							<h5 class="text-center">no boleto com 12% <b>de desconto</b></h5>
						</div>
						</a>
					</div>
				</div>
			</c:if>
	   	</c:forEach>
	   	
	    </div>
	  </div>
    </div>
    <!-- /.row -->
      
    <nav aria-label="Page navigation example">
	  	<ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		    	<a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
		    </li>
		    <li class="page-item"><a class="page-link" href="#">1</a></li>
		    <li class="page-item"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item">
		    	<a class="page-link" href="#">Next</a>
		    </li>
	  	</ul>
	</nav>
</div>

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
