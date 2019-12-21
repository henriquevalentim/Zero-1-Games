<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - Dashboard</title>
	
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/bootstrap-toggle.min.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/extra-dashboard.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/zero1games.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/style.css"/>
	
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

<div class="zg-layout-loading  js-loading-overlay">
	<div class="zg-layout-loading__container">
		<span class="zg-balls-spinner">Carregando...</span>
	</div>
</div>

    <c:import url="../fragmentos/navbaradm.jsp" />

	<section class="zg-layout-content  js-content">


<div class="page-header">
	<div class="container-fluid">
		<h1>
			Dashboard - Zero1Games
		</h1>
	</div>
</div>

<div class="container-fluid">
    
    <div class="row">
    <div class="col-md-6">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-bank  fa-3x"></i>
        </div>
        <div class="zg-box__value" ><fmt:formatNumber value="${dadosDashboard.get(0).faturamentoTotal}" type="currency"></fmt:formatNumber></div>
        <div class="zg-box__title">Faturamento total</div>
      </div>
    
    </div>
    
    <div class="col-md-6">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-users  fa-3x"></i>
        </div>
        <div class="zg-box__value">${dadosDashboard.get(0).qtdClientes}</div>
        <div class="zg-box__title">Total de clientes cadastrados</div>
      </div>

    </div>
  </div>
    
    <div class="row">
    <div class="col-md-6">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-battery-empty  fa-3x"></i>
        </div>
        <div class="zg-box__value">${dadosDashboard.get(0).produtosSemEstoque}</div>
        <div class="zg-box__title">Produtos sem estoque</div>
      </div>
    
    </div>
    
    <div class="col-md-6">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-truck  fa-3x"></i>
        </div>
        <div class="zg-box__value">${dadosDashboard.get(0).estoqueTotal}</div>
        <div class="zg-box__title">Estoque total</div>
      </div>

    </div>
  </div>
  
</div>
</div>




<script src="../../assets/javascripts/vendors.js"></script>
<script src="../../assets/javascripts/vendors/jquery.maskMoney.min.js"></script>
<script src="../../assets/javascripts/sweetalert2.js"></script>
<script src="../../assets/javascripts/sweetalert.init.js"></script>
<script src="../../assets/javascripts/util.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>
<script src="../../assets/javascripts/vendors/bootstrap-toggle.min.js"></script>

</body>
</html>