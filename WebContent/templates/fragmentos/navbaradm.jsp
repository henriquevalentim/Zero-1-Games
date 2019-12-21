<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="zg-layout-page">

	<nav class="navbar  navbar-fixed-top  navbar-default  js-sticky-reference" id="main-navbar">
	  <div class="container-fluid">

	    <div class="navbar-header">
	      <a class="navbar-brand" href="dashboard.jsp">Zero1Games</a>

	      <ul class="nav  navbar-nav">
	        <li>
	          <a href="#" class="js-sidebar-toggle"><i class="fa  fa-bars"></i></a>
	        </li>
	      </ul>
	    </div>

	    <ul class="nav navbar-nav  navbar-right">
	    
	      <li>
	        <a href="#" class="js-search-modal-trigger-show"><i class="fa  fa-search"></i></a>
	      </li>
	      
	      <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
	          <i class="fa  fa-envelope"></i> <span class="label  label-danger  zg-label-corner">2</span>
	        </a>
	                  
	        <ul class="dropdown-menu">
	          <li><a href="javascript:;">Notificação 1</a></li>
	          <li><a href="javascript:;">Notificação 2</a></li>
	        </ul>
	      </li>
	      
	      <li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
	          <i class="fa  fa-user"></i>
	        </a>
	        
	        <ul class="dropdown-menu">
	          <li>
	            <div class="zg-logged-user">
	              <img src="https://api.adorable.io/avatars/80/joaodascouves" 
	                width="80" height="80" alt="João das Couves" class="zg-logged-user__picture" />
	              <span class="zg-logged-user__name">Gabriel Bittencourt</span>
	            </div>
	          </li>
	          <li role="separator" class="divider"></li>
	          <li><a href="#">Meu perfil</a></li>
	          <li><a href="#">Alterar senha</a></li>
	        </ul>
	      </li>
	      
	      <li>
	        <a href="#"><em class="fa  fa-sign-out"></em></a>
	      </li> 
	    </ul>

	  </div>
	</nav>

	<aside class="zg-layout-sidebar  js-sidebar">
		<div class="zg-layout-sidebar__content">

    <nav class="zg-menu  js-menu">
      <ul class="zg-menu__list">

        <li class="zg-menu__item">
          <a href="Principal.jsp"><i class="fa  fa-fw  fa-home"></i><span>Dashboard</span></a>
        </li>
        
        <li class="zg-menu__item is-expanded">
          <a href="#">
            <i class="fa  fa-fw  fa-file-text"></i><span>Gráficos de Análise</span>
            <i class="zg-menu__navigation-icon  fa"></i>
          </a>
        
        <ul class="zg-menu__list  zg-menu__list--sublist">
            <li class="zg-menu__item  zg-menu__item--link"><a href="dashboard2.jsp">Análise de Vendas por Gênero</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="dashboard3.jsp">Análise de Lucro por Categoria</a></li>
          </ul>
        </li>

        <li class="zg-menu__item is-expanded">
          <a href="#">
            <i class="fa  fa-fw  fa-file-text"></i><span>Adminstrador</span>
            <i class="zg-menu__navigation-icon  fa"></i>
          </a>
      
          <ul class="zg-menu__list  zg-menu__list--sublist">
            <li class="zg-menu__item  zg-menu__item--link"><a href="CadastroProduto.jsp">Cadastro de produto</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="PesquisaProduto.jsp">Pesquisa de produtos</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="PesquisaPedidos.jsp">Pesquisa de pedidos</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="PesquisaCliente.jsp">Pesquisa de Clientes</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="Entrega.jsp">Confirmar Entregas</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="Pendencias.jsp">Solicitações Troca</a></li>
			<li class="zg-menu__item  zg-menu__item--link"><a href="Troca.jsp">Confirmar Trocas</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="ControleEstoque.jsp">Controle de Estoque</a></li>
<!--             <li class="zg-menu__item  zg-menu__item--link"><a href="estoquegeral.jsp">Estoque Geral</a></li> -->
          </ul>
        </li>
		
		<!--  is-expanded ; is active -->
		
        <li class="zg-menu__item is-expanded">
          <a href="#">
            <i class="fa  fa-fw  fa-file-text"></i><span>Sistema</span>
            <i class="zg-menu__navigation-icon  fa"></i>
          </a>

          <ul class="zg-menu__list  zg-menu__list--sublist">
            <li class="zg-menu__item  zg-menu__item--link"><a href="../index.jsp">Home</a></li>
            <li class="zg-menu__item  zg-menu__item--link"><a href="../CadastroCliente.jsp">Cadastro de cliente</a></li>
          </ul>
        </li>

      </ul>
    </nav>

		</div>
	</aside>