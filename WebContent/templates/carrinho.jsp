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
  <link rel="stylesheet" type="text/css" href="../assets/stylesheets/carrinhoCompra.css"/>

</head>

<body>

<c:import url="${ UsuarioAutenticado != null ? 'fragmentos/navbarLogado.jsp' : 'fragmentos/navbar.jsp'}" />

  <!-- Page Content -->
  <div class="container">
      
      <h4 class="mt-4">Clique em "Finalizar compra" para efetuar o seu pedido.</h4>
      
      <c:if test="${ msg != null }">
		  	<div class="alert alert-danger col-md-6" role="alert">
			  	<c:out value="${ msg }"></c:out>
			</div>
	  </c:if>
      
				<form action="EscolherEndereco.jsp" method="POST">
      <div class="table-responsive  zg-tabela-simples">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th class="table-jogos-col-codigo"></th>
						<th class="table-jogos-col-titulo text-center">Título</th>
						<th class="table-jogos-col-valor text-center">Preço unitário</th>
						<th class="table-jogos-col-quantidade text-center">Quantidade</th>
						<th class="table-jogos-col-valor text-center">Subtotal</th>
						<th class="table-jogos-col-acoes text-center">Excluir</th>
					</tr>
				</thead>
				<fmt:setLocale value="${user.locale}" />
				<tbody>
				<c:set var="total" value="0.00"/>
					<c:forEach var="item" items="${ carrinho.itens }">
						<c:set var="total" value="${ total + (item.jogo.precoVenda * item.quantidade) }"/>
						<tr>
							<td class="text-center">
								<img src="${ item.jogo.urlFoto }" style="width:50px;" class="img-responsive"/>
							</td>
							<td>${ item.jogo.titulo }</td>
							<td id="precoUnitario${ item.jogo.id }" class="text-center"><fmt:formatNumber value="${ item.jogo.precoVenda }"	type="currency"></fmt:formatNumber></td>
							<td>
								<div class="form-inline" style="margin-left:30%;">
									<a href="CarrinhoCompra?id=${ item.jogo.id }&btnOperacao=REM&qtde${ item.jogo.id }=${ item.quantidade }"><i class="fa fa-minus" id="idMinus"></i></a>
									<input id="quantidade${ item.jogo.id }" name="${ item.jogo.id }" class="form-control text-center col-md-4" value="${ item.quantidade }" disabled>
									<a href="CarrinhoCompra?id=${ item.jogo.id }&btnOperacao=ADD&qtde${ item.jogo.id }=${ item.quantidade }"><i class="fa fa-plus" id="idPlus" class="pr-3"></i></a>
								</div>
							</td>
							<td id="subTotalLinha${ item.jogo.id }" class="text-center jsquantidade"><fmt:formatNumber value="${ item.jogo.precoVenda * item.quantidade }" type="currency"></fmt:formatNumber></td>
							<td class="text-center">
								<a href="CarrinhoCompra?id=${ item.jogo.id }&btnOperacao=REMOVER"><i class="fa fa-trash"></i></a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6">
							<div class="row">
								<div class="form-inline offset-md-9 col-md-3">
									<h4>Total: <fmt:formatNumber value="${ total }" type="currency"></fmt:formatNumber></h4>
									<c:set var="total" value="${ total }" scope="session"/>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="float-right mb-3">
				<a href="index.jsp"><button type="button" class="btn btn-secondary">Continuar comprando</button></a>
				<button type="submit" class="btn btn-success" name="btnOperacao" >Finalizar compra</button>
			</div>
		</div>
	</form>
	
	<%
		request.removeAttribute("msg");
	%>
      
  </div>
  <!-- /.container -->
  
  <script src="../assets/javascripts/carrinho.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>
 
<c:import url="fragmentos/footer.jsp" />

</body>

</html>
