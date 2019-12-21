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
			<div class="card col-md-6 m-3" style="width: 18rem;">
			  	<div class="card-header separador-card">
	   				<b>endereço de entrega</b>
				</div>
			  	<div class="card-body ">
				    <h6 class="card-subtitle mb-2 text-muted">HENRIQUE VALENTIM NUNES</h6>
				    <p class="card-text card_botton">Rua Benedicto dos Santos, 200, Casa de esquina com a rua pedro paulo dos santos</p>
				    <p class="card-text card_botton">Jundiapeba</p>
				    <p class="card-text card_botton">Mogi das Cruzes - SP</p>
				    <p class="card-text">CEP 08750-330</p>
				    
				    <div class="row">
					<!-- Botao para acionar modal -->
					<button name="btnModalEndereco" type="button" class="btn btn-success ml-4" data-toggle="modal" data-target="#modalExemplo">
			 		adicionar endereço
					</button>
			
					<!-- Modal -->
					<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  		<div class="modal-dialog" role="document">
					    	<div class="modal-content">
						      	<div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">Cadastrar Endereço</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
							        <span aria-hidden="true">&times;</span>
							        </button>
						      	</div>
								<div class="modal-body">
									<form method="POST" action="ControlePagamentoEndereco">
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="txtNomeEndereco">Nome do Endereco</label>
												<input id="txtNomeEndereco" name="txtNomeEndereco" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).nome}"/></c:if>'class="form-control"/>
											</div>
										</div>
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="cep">CEP</label>
												<input id="txtCep" name="txtCep" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).cep}"/></c:if>'class="form-control"/>
											</div>
										</div>
										
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="numero">Numero</label>
												<input id="txtNumero" name="txtNumero" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).numero}"/></c:if>'class="form-control"/>
											</div>
										</div>
										
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="logradouro">Logradouro</label>
												<input id="txtLogradouro" name="txtLogradouro" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).logradouro}"/></c:if>'class="form-control"/>
											</div>
										</div>
									
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="bairro">Bairro</label>
												<input id="txtBairro" name="txtBairro" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bairro}"/></c:if>'class="form-control"/>
											</div>
										</div>
										
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="complemento">Complemento</label>
												<input id="txtComplemento" name="txtComplemento" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).complemento}"/></c:if>'class="form-control"/>
											</div>
										</div>
										
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="tipologradouro">Tipo de logradouro</label>
													<select id="txtTpLogradouro" name="txtTpLogradouro" name="txtTpLogradouro" class="form-control">
														<option value="<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).tipoLogradouro}"/></c:if>"><c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).tipoLogradouro}"/></c:if></option>
														<option value="AVENIDA">Avenida</option>
														<option value="RUA">Rua</option>
														<option value="TRAVESSA">Travessa</option>
														<option value="OUTRO">Outro</option>
													</select>
											</div>
										
											<div class="offset-md-2 col-md-8  form-group">
											<label for="sabor" class="control-label">Tipo de residência</label>
													<select id="txtTpResidencia" name="txtTpResidencia" class="form-control">
														<option value="<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).tipoResidencia}"/></c:if>"><c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).tipoResidencia}"/></c:if></option>
														<option value="APARTAMENTO">Apartamento</option>
														<option value="CASA">Casa</option>
														<option value="COMERCIAL">Comercial</option>
														<option value="OUTRO">Outro</option>
													</select>
											</div>
										</div>
			
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="cidade">Cidade</label>
												<input id="txtCidade" name="txtCidade" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).cidade.nome}"/></c:if>'class="form-control"/>
											</div>
										</div>
									
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="estado">Estado</label>
												<input id="txtEstado" name="txtEstado" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).cidade.estado.nome}"/></c:if>'class="form-control"/>
											</div>
										</div>
										
										<div class="row">
											<div class="offset-md-2 col-md-8  form-group">
												<label class="control-label" for="pais">País</label>
												<input id="txtPais" name="txtPais" type="text"
												value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).cidade.estado.pais.nome}"/></c:if>'class="form-control"/>
											</div>
										</div>
										<input type="hidden" name="txtEnderecoEntrega" value="ENTREGA"/>
			
										<input type="hidden" name="txtId" value="${ UsuarioAutenticado.entidades.get(0).id }">
									<div class="modal-footer">
							        	<button type="submit" class="btn btn-success w-25" name="btnOperacao" value="SALVAR">Salvar</button>
										<button type="button" class="btn btn-secondary" name="btnFecharModal" data-dismiss="modal">Fechar</button>
							      	</div>
  								</form>
							</div>
      					</div>
    				</div>
				</div>
						
					
					
					<!-- Botao para acionar modal -->
					<button name="btnModalAlterarEndereco" type="button" class="btn btn-primary ml-4" data-toggle="modal" data-target="#modalExemplo2">
			 		alterar endereço
					</button>
			
					<!-- Modal -->
					<div class="modal fade" id="modalExemplo2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  		<div class="modal-dialog" role="document">
					    	<div class="modal-content">
					      	<div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Escolher endereço</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
						        <span aria-hidden="true">&times;</span>
						        </button>
					      	</div>
					      	<div class="modal-body">
					        	<div class="offset-md-1 col-md-10  form-group">
					        	<c:forEach var="end" items="${ enderecos }">
					        		<c:if test="${ end.entrega == true }">
										<input type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
								  		<label class="form-check-label" for="exampleRadios1">
								    		${ end.logradouro }, ${ end.numero }, ${ end.complemento }<br>
											${ end.bairro }<br>
											${ end.cidade.nome } - ${ end.cidade.estado.nome }<br>
											CEP ${ end.cep }<br>
								  		</label>
								  		<br>
							  		</c:if>
							  	</c:forEach>
									
								</div>
					      	<div class="modal-footer">
						        <button type="button" class="btn btn-primary">Alterar</button>
						        <button type="button" class="btn btn-secondary" data-dismiss="modal" name="btnFecharModal" >Fechar</button>
					      	</div>
					      	</div>
					    	</div>
				  		</div>
					</div>
					</div>
			  	</div>
			</div>		
			
			<div class="card col-md-5 m-3" style="width: 18rem;">
				    <div class="card-header separador-card">
		   				<b>Opções de entrega</b>
					</div>
			  	<div class="card-body">
				    <div class="col-md-10  form-group">
						<div class="row mt-3">
							<div class="col-md-4">
								<input type="radio" name="exampleRadios1" id="exampleRadios1" value="option1" >
						  		<label class="form-check-label" for="exampleRadios1">SEDEX</label>
					  		</div>
					  		<div class="offset-md-4 col-md-4">
						  		<label class="form-check-label" for="exampleRadios1">R$32,90</label>
					  		</div>
				  		</div>
				  		<br>
						<div class="row">
							<div class="col-md-5">
								<input type="radio" name="exampleRadios1" id="exampleRadios1" value="option1" checked>
						  		<label class="form-check-label" for="exampleRadios1">NORMAL</label>
					  		</div>
					  		<div class="offset-md-3 col-md-4">
						  		<label class="form-check-label" for="exampleRadios1">R$15,30</label>
					  		</div>
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
	
  	<div class="row">
  		<div class="col-md-12">
		  	<nav>
			  <div class="nav nav-tabs" id="nav-tab" role="tablist">
			     <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
			      aria-controls="nav-home" aria-selected="true">Cartão de Credito</a>
			   	 <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
			      aria-controls="nav-profile" aria-selected="false">2 Cartões de Credito</a>
			   	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab"
			      aria-controls="nav-contact" aria-selected="false">Boleto Bancario</a>
			  </div>
			</nav>
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				  <div class="row">
				  <c:forEach var="cartoes" items="${ cartoes }">
					  	<div class="card col-md-3 m-3">
						  	<div class="card-body">
					  			<div class="form-inline">
						    		<p class="card-text card_botton">Terminado em ${ cartoes.numero.substring(cartoes.numero.length() - 4) }</p>
					  			</div>
							    <p class="card-text card_botton">${ cartoes.bandeira }</p>
							</div>
							<div class="card-footer" style="background-color: white;">
								<div class="row">
							    	<div class="col-md-12">
										<input type="radio" name="exampleRadios" id="exampleRadios1" value="option1">
								  		<label class="form-check-label" for="exampleRadios1">Pagar com este cartão</label>
							  		</div>
						  		</div>
							</div>
						</div>
					</c:forEach>
					
						<div style="margin-top:130px;">
							<button id="btnAddCartao" type="button" class="btn btn-success ml-4" data-toggle="modal" data-target="#exampleModalCenter">
					 		adicionar cartão
							</button>
						</div>
						
						<!-- Modal -->
						<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">Cadastrar Cartão</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <form method="POST" action="ControlePagamentoCartao">
						      <div class="modal-body">
							        <div class="row">
										<div class="offset-md-2 col-md-8  form-group">
											<label class="control-label" for="txtNomeCartao">Nome impresso no cartão</label>
											<input id="txtNomeCartao" name="txtNomeCartao" type="text"
											value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bairro}"/></c:if>'class="form-control"/>
										</div>
									</div>
									
									<div class="row">
										<div class="offset-md-2 col-md-8  form-group">
											<label class="control-label" for="txtNumeroCartao">Numero do cartão</label>
											<input id="txtNumeroCartao" name="txtNumeroCartao" type="text"
											value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bairro}"/></c:if>'class="form-control"/>
										</div>
									</div>
									
									<div class="row">
										<div class="offset-md-2 col-md-8  form-group">
											<label for="sabor" class="control-label">Bandeira</label>
											<select id="txtBandeiraCartao" name="txtBandeiraCartao" class="form-control">
												<option value=""></option>
												<option value="Visa">Visa</option>
												<option value="MasterCard">Mastercard</option>
												<option value="Elo">Elo</option>
												<option value="American Express">American Express</option>
												<option value="Hipercard">Hipercard</option>
											</select>
										</div>
							      	</div>
							      	
							      	<div class="row">
										<div class="offset-md-2 col-md-8  form-group">
											<label class="control-label" for="txtCodigoCartao">Código de segurança (CSV)</label>
											<input id="txtCodigoCartao" name="txtCodigoCartao" type="text"
											value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).bairro}"/></c:if>'class="form-control"/>
										</div>
										<input type="hidden" name="txtIdCliente" value="${ UsuarioAutenticado.entidades.get(0).id }" />
									</div>
							      </div>
							      <div class="modal-footer">
							        <button type="submit" name="btnOperacao" value="SALVAR" class="btn btn-primary">Salvar</button>
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
							      </div>
						      </form>
						    </div>
						  </div>
						</div>
						
				  </div>
			  </div>
			  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			  	
			  	<div class="row">
				  	<c:forEach var="cartoes" items="${ cartoes }">
					  	<div class="card col-md-3 m-3">
						  	<div class="card-body">
					  			<div class="form-inline">
						    		<p class="card-text card_botton">Terminado em ${ cartoes.numero.substring(cartoes.numero.length() - 4) }</p>
					  			</div>
							    <p class="card-text card_botton">${ cartoes.bandeira }</p>
							</div>
							<div class="card-footer" style="background-color: white;">
								<div class="row">
							    	<div class="col-md-12">
										<input type="radio" name="exampleRadios" id="exampleRadios1" value="option1">
								  		<label class="form-check-label" for="exampleRadios1">Pagar com este cartão</label>
							  		</div>
						  		</div>
							</div>
						</div>
					</c:forEach>
				</div>
			  	
			  </div>
			  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
				  <div class="card col-md-3 m-3">
					  	<div class="card-body">
						    <p class="card-text card_botton">Imprima o boleto e pague no banco</p>
						    <p class="card-text card_botton">ou pague pela internet utilizando o código de barras do boleto</p>
						    <p class="card-text card_botton">o prazo de validade do boleto é de 1 dia útil</p>
						</div>
						<div class="card-footer" style="background-color: white;">
					    	<div class="form-check">
							  	<button type="button" class="btn btn-success">Pagar com boleto</button>
							</div>
						</div>
					</div>
			  </div>
			</div>
		</div>
  	</div>
  	
  	<div class="row">
		<div class="card col-md-10 m-3">
		  	<div class="card-body">
			    <h5 class="card-title">Produtos</h5>
			    <fmt:setLocale value="${user.locale}" />
			    <c:forEach var="item" items="${ carrinho.itens }">
				    <div class="row">
					    <h6 class="card-subtitle mb-2 col-md-5 text-muted">${ item.jogo.titulo }</h6>
					    <h6 class="card-subtitle mb-2 col-md-2 text-muted">${ item.quantidade }</h6>
					    
					    <h6 class="card-subtitle mb-2 col-md-3 text-muted"><fmt:formatNumber value='${ item.jogo.precoVenda }'	type="currency"></fmt:formatNumber></h6>
				    </div>
				</c:forEach>
				
				<div class="row">
					<div class="col-md-4 mt-4">
					    <h6 class="card-subtitle mb-2 col-md-8 text-muted"><b>Cupom de desconto</b></h6>
			    	</div>
				</div>
				
				<div class="row">
				    <div class="form-inline m-2">
						<input name="txtCupom" id="txtCupom" type="text" class="form-control">
						<button type="button" class="btn btn-primary">APLICAR</button>
				    </div>
				</div>
				
				<div class="card-footer" style="background-color: white;">
				<div class="row">
			    	<div class="form-check offset-md-9 col-md-3">
					  	<a href="ConfirmaPagamento.jsp" id="btnFinalizarCompra" type="button" class="btn btn-success">FINALIZAR COMPRA</a>
					</div>
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
