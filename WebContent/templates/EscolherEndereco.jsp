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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
  

  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">
  <link href="../assets/stylesheets/categorias.css" rel="stylesheet">
  

</head>

<body>

<c:import url="fragmentos/navbarLogado.jsp" />


  <!-- Page Content -->
  	<div class="container">
	
		<h4 class="card-title mt-4">Onde voce quer receber sua compra?</h4>
		<form action="EscolherPagamento.jsp" method="POST">
		  	<div class="row">
		  		<div class="col-md-12 mt-3">
						<c:forEach var="end" items="${ enderecos }">
							<div class="card col-md-8 mr-2">
							  	<div class="card-body">
						  			<div class="form-inline">
											<input type="radio" name="idEndereco" id="idEndereco" value="${ end.id }">
						  				<i class="fa fa-truck m-3" style="font-size:40px;"></i>
							  			<div class="form-group">
								    		<p class="card-text card_botton">${ end.logradouro }, ${ end.numero } </p><br>
							  			</div>
								    		<p class="card-text card_botton">${ end.bairro } - ${ end.cidade.nome } - ${ end.cidade.estado.nome }<br></p>
								    		<input type="hidden" id='cepEnd${end.id}' value='${ end.cep }'>
						  			</div>
								</div>
							</div>
						</c:forEach>
				 </div>
			</div>
		
			<div class="row">
				<div class="col-md-12 mt-3">
			  		<div class="card col-md-8">
						  <h4 class="card-title mt-2">Valor entrega</h4>
						  	<div class="card-body">
							    <div class="col-md-10  form-group">
									<!-- <div class="row mt-3">
										<div class="col-md-4">
											<input type="radio" name="metodoEntrega" id="metodoEntregaSedex" onclick="setNomeMetodoEntrega('metodoEntregaSedex')" value="30" >
									  		<label class="form-check-label" for="metodoEntrega">SEDEX</label>
								  		</div>
								  		<div class="offset-md-4 col-md-4">
									  		<label class="form-check-label" for="metodoEntrega">R$30,00</label>
								  		</div>
							  		</div>
							  		<br>
									<div class="row">
										<div class="col-md-5">
											<input type="radio" name="metodoEntrega" id="metodoEntregaNormal" onclick="setNomeMetodoEntrega('metodoEntregaNormal')" value="15" checked>
									  		<label class="form-check-label" for="metodoEntrega">NORMAL</label>
								  		</div>
								  		<div class="offset-md-3 col-md-4">
									  		<label class="form-check-label" for="metodoEntrega">R$15,00</label>
								  		</div>
							  		</div> -->
							  		<div class="form-inline">
							  			<h5>Quantidade de dias:</h5><h5 id="qtdeDias"></h5>
							  		</div>
							  			<input type="hidden" name="qtdeDiaEntrega" id="qtdeDiaEntrega" value="">
							  			<input type="hidden" name="valorEntregaSedex" id="valorEntregaSedex" value="">
							  			<input type="hidden" name="idMetodoEntrega" id="idMetodoEntrega" value="1">	
							  		<div class="form-inline">
							  			<h5>Valor total entrega: </h5><h5 id="valorEntrega"></h5>
							  		</div>
							  		
							  		<div class="row mt-5">
							  			<div class="col-md-12">
							  			<p class="card-text">O pedido sera liberado somente apos a aprovação do pagamento.</p>
							  			</div>
							  		</div>
								</div>
						  	</div>
						</div>
		  		</div>
		  	</div>
		  	
		  	<div class="row">
			  	<div class="col-md-8 ">
					<button type="submit" id="btnContinuar"
						class="btn btn-primary w-25 h-10 mt-3" disabled>Continuar</button>
					<a href="CadastrarEnderecoEntrega.jsp" type="button" id="btnEndereco" class="btn btn-success mt-3">adicionar endereço</a>
				</div>
		  	</div>
  		</form>
  	
  <br><br><br><br><br>
  	</div>
  <!-- /.container -->

<c:import url="fragmentos/footer.jsp" />

<script type="text/javascript">
	
function setNomeMetodoEntrega(input){
	if(input == 'metodoEntregaNormal'){
    	document.getElementById('idMetodoEntrega').value = '2';
	}else{
		document.getElementById('idMetodoEntrega').value = '1';
	}
}

</script>

  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>
  
  <script>
	  $(document).ready(function(){
			$("[name='idEndereco']").click(function (){
				var idEndereco = $("input:radio[name='idEndereco']:checked").val()
				var cepEnd = $("#cepEnd"+idEndereco).val();
				
				$.ajax({
					url : 'ControleWebService',
					type: 'GET',
					data : {cep: cepEnd,
						btnOperacao: "CONSULTAR"},
					dataType: "json",
					success: function(json)
					{
						var jsonCompleto = JSON.stringify(json); 
					
						$("#qtdeDias").html(json[0].quantidadeDias);
						$("#valorEntrega").html(json[0].valor);
						$("#qtdeDiaEntrega").val(json[0].quantidadeDias);
						$("#valorEntregaSedex").val(json[0].valor);
						$("#btnContinuar").attr("disabled", false);
				    }
				})
			});
		});
  </script>

</body>

</html>
