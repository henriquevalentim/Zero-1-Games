<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="index.jsp" id="logo">Zero1Games</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
	  
	  <form class="form-inline" action="categorias.jsp">
		<div class="navbar-center" >
			<input class="form-control w-35" type="search" placeholder="O QUE VOCÊ PROCURA?" aria-label="Search">
			<button class="btn btn-outline-success" type="submit">buscar</button>
	     </div>
       </form>
	  
		<div class="collapse navbar-collapse" id="navbarResponsive">
	      	<ul class="navbar-nav ml-auto">
		        <li class="nav-item">
            		<a class="nav-link" href="index.jsp">Home
             	 		<span class="sr-only">(current)</span>
            		</a>
         		</li>
		       	<li class="nav-item dropdown ml-3 mr-3">
				    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><c:out value="${ UsuarioAutenticado.entidades.get(0).nome }"></c:out></a>
				    <div class="dropdown-menu">
					    <a class="dropdown-item" href="AlterarCliente.jsp">Minha conta</a>
					    <a class="dropdown-item" href="ControlePedido?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).usuario.id }">Meus pedidos</a>
				    <div class="dropdown-divider"></div>
				    	<a class="dropdown-item" href="logout.jsp">Sair</a>
				    </div>
			  	</li>
		       	<li class="nav-item">
		        	<a class="nav-link" href="carrinho.jsp">Carrinho</a>
		       	</li>
	     	</ul>
      	</div>
      	
    </div>
  </nav>
  
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
	  
		<div class="collapse navbar-collapse" id="navbarResponsive">
		      	<ul class="navbar-nav">
			        <li class="nav-item">
					      	<a class="nav-link link-categoria" id="PS4" href="ConsultarJogo?btnOperacao=CONSULTAR&txtPlataforma=PS4">PS4
					       		<span class="sr-only">(current)</span>
					      	</a>
			     	</li>
		     	<li class="nav-item nav-item-categoria">
			     		<a class="nav-link link-categoria" id="PS3" href="ConsultarJogo?btnOperacao=CONSULTAR&txtPlataforma=PS3">PS3</a>
		    	</li>
		     	<li class="nav-item nav-item-categoria">
			      		<a class="nav-link link-categoria" id="XONE" href="ConsultarJogo?btnOperacao=CONSULTAR&txtPlataforma=XBOX+ONE">XBOX ONE</a>
		     	</li>
		     	<li class="nav-item nav-item-categoria">
			      		<a class="nav-link link-categoria" id="X360" href="ConsultarJogo?btnOperacao=CONSULTAR&txtPlataforma=XBOX+360">XBOX 360</a>
		     	</li>
		     	</ul>
		    </div>
     	</div>
      	
  </nav>