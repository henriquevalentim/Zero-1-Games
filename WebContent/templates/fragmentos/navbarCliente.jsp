<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg topico">
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<a class="navbar-brand" href="ControlePedido?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).usuario.id }">Pedidos</a>
	
	  	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
		    <li class="nav-item active">
		    <a class="nav-link" href="AlterarCliente.jsp">Cadastro <span class="sr-only">(current)</span></a>
		    </li>
		    <li class="nav-item">
		    <a class="nav-link" id="cartaoLink" href="ControleCartao?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).id }">Cartões</a>
		    </li>
		    <li class="nav-item">
		    <a class="nav-link" id="enderecoLink" href="ControleEndereco?btnOperacao=CONSULTAR&id=${ UsuarioAutenticado.entidades.get(0).id }">Endereços</a>
		    </li>
		    </ul>
	  	</div>
	</nav>