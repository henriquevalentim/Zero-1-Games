<%@page import="br.edu.fatec.les.dominio.CarrinhoCompra"%>
<%@page import="br.edu.fatec.les.dominio.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.les.dominio.cliente.Cartao"%>
<%@page import="br.edu.fatec.les.dominio.Pedido"%>
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

<c:import url="fragmentos/navbarLogado.jsp" />



  <!-- Page Content -->
  	<div class="container">
  		<form action="ControlePedido" method="POST">
			<h3 class="card-title my-3">Obrigado pela compra</h3>
			<h5 class="card-title mt-4"><b>Detalhes da compra</b></h5>
			
	<div class="row">
		<div class="card col-md-8 m-3">
		  	<div class="card-body">
			    <h5 class="card-title">Produtos</h5>
			    <fmt:setLocale value="" />
			    <c:forEach var="item" items="${ pedido.itens }">
				    <div class="row">
					    <h6 class="card-subtitle mb-2 col-md-5 text-muted">${ item.jogo.titulo }</h6>
					    <h6 class="card-subtitle mb-2 col-md-2 text-muted"></h6>
					    
					    <h6 class="card-subtitle mb-2 col-md-3 text-muted"><fmt:formatNumber value='' type="currency"></fmt:formatNumber></h6>
				    </div>
				</c:forEach>
				
				<div class="card-footer" style="background-color: white;">
				<div class="row">
			    	<div class="form-check offset-md-6 col-md-3">
					  	<a type="button" href="index.jsp" id="btnOperacao" class="btn btn-success w-100">Continuar Comprando</a>
					</div>
					<div class="form-check col-md-3">
					  	<a type="button" href="ControlePedido?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).usuario.id }" id="btnOperacao" name="btnPedidos"  class="btn btn-primary">Ir para pedidos</a>
					</div>
				</div>
				</div>
			</div>
		</div>
		</form>
		
  	</div>
  	
  	</div>
  	<%
		request.getSession().setAttribute("carrinho", new CarrinhoCompra());
	  	request.getSession().setAttribute("pedido", new Pedido());
	%>
  <br><br><br><br><br>
  <!-- /.container -->

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
