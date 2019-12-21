<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - cartão de crédito</title>

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
	
	<h4 class="m-3"><b>Cartão de Crédito</b></h4>
	
	<div class="row">
	<c:forEach var="cartao" items="${ resultadoConsultarCartao.entidades }">
		<div class="card col-md-5 m-2">
		  	<div class="card-body">
		  			<div class="form-inline">
		  				<p class="card-title">${ cartao.nome }</p>
			    		<div class="col-md-1 offset-md-6">
				  			<a href="ControleCartao?btnOperacao=EXCLUIR&id=${ cartao.id }"><i id="excluirCartao" class="fa fa-trash icon-medium col-md-6" style="margin-left:65px;"></i></a>
			    		</div>
		  			</div>
			    	<p class="card-text">Terminado em ${ cartao.numero.substring(cartao.numero.length() - 4) }</p>
			    <p class="card-text card_botton">${ cartao.bandeira.nome }</p>
			</div>
			<div class="card-footer" style="background-color: white;">
		    	<div class="form-check">
				  	<input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
				  	<label class="form-check-label" for="exampleRadios1">
				    	Selecionar como principal
				  	</label>
				</div>
			</div>
		</div>
	</c:forEach>
		
			<div class="card card-dashed col-md-5 m-2">
		    	<a id="novoCartaoLink" href="CadastrarCartao.jsp">
					<div class="offset-md-5">
				  		<i class="fa fa-plus-circle icon-plus-cartao ml-4"></i>
					</div>
					<div class="offset-md-4">
				  		<p>Novo Cartão de Crédito</p>
					</div>
		    	</a>
			</div>
	</div>
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
