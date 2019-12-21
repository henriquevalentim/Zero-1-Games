<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

  <c:import url="${ UsuarioAutenticado != null ? 'fragmentos/navbarLogado.jsp' : 'fragmentos/navbar.jsp'}" />

  <!-- Page Content -->
  	<div class="container">
	
  	<h4 class="m-3">Minha conta</h4>
	
	<c:import url="fragmentos/navbarCliente.jsp" />
	
	<h4 class="m-3"><b>Pedidos</b></h4>
	<c:forEach var="pedido" items="${ resultado.entidades }">
	<div class="row">
	<div class="col-md-7">
		<div class="card mb-4">
	  		<div class="form-inline card-header">
		  			<div class="col-md-4">Pedido:<br><b>${ pedido.numeroPedido }</b></div>
		  			<div class="col-md-4">Valor total:<br><b><fmt:formatNumber value="${ pedido.subtotal }" type="currency"></fmt:formatNumber></b></div>
		  			<div class="col-md-4">Status:<br><b>${ pedido.statusPedido.nome }</b></div>
	  		</div>
	  		<c:if test="${ pedido.statusPedido.nome == 'ENTREGUE' }">
		  		<div class="card-footer bg-transparent border-success">
		  			<div class="row">
						<div class="col-md-5 offset-md-4">
							<!-- Botão para acionar modal -->
							<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalExemplo${ pedido.id }">
							  Devolver Produtos
							</button>
							
							<!-- Modal -->
							<div class="modal fade" id="modalExemplo${ pedido.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">Cancelar compra?</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <form action="ControleDevolucao" method="POST">
								      <div class="modal-body">
								        	<div class="col-md-8  form-group">
												<label class="control-label">Motivo:</label>
												<input type="text" class="form-control"/>
												<input type="hidden" name="idPedido" value="${ pedido.id }" class="form-control"/>
												<input type="hidden" name="txtStatus" value="${ pedido.statusPedido.id }" class="form-control"/>
												<input type="hidden" name="txtSolicitacao" value="${ pedido.solicitacao }" class="form-control"/>
											</div>
								      </div>
								      <div class="modal-footer">
								        <button type="submit" class="btn btn-danger" name="btnOperacao" value="ALTERAR">Confirmar</button>
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
								      </div>
							      </form>
							    </div>
							  </div>
							</div>
							
						</div>
		  			</div>
				</div>
			</c:if>
		</div>
	</div>
	</div>
	</c:forEach>	
  	</div>
  <!-- /.container -->
		<br><br>

<c:import url="fragmentos/footer.jsp" />

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>

</body>

</html>
