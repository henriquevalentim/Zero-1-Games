<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - dashboard</title>

	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/extra-dashboard.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/zero1games.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/style.css"/>
	<script src="../../assets/javascripts/vendors/highcharts.js"></script>
	<script src="../../assets/javascripts/vendors/modules/series-label.js"></script>
	<script src="../../assets/javascripts/vendors/modules/exporting.js"></script>
	<script src="../../assets/javascripts/vendors/modules/export-data.js"></script>
	
	
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<style type="text/css">
		#container {
			min-width: 310px;
			max-width: 800px;
			height: 400px;
			margin: 0 auto
		}
	</style>
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
      Dashboard
    </h1>
  </div>
</div>

<div class="container-fluid">
  
  <div class="row">
    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-bank  fa-3x"></i>
        </div>
        <div class="zg-box__value">R$983.433,20</div>
        <div class="zg-box__title">Faturamento total</div>
      </div>
    
    </div>

    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-usd  fa-3x"></i>
        </div>
        <div class="zg-box__value">R$343.542,59</div>
        <div class="zg-box__title">Faturamento no ano</div>
      </div>
    
    </div>
    
    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-heartbeat  fa-3x"></i>
        </div>
        <div class="zg-box__value">R$40.233,40</div>
        <div class="zg-box__title">Faturamento no mês</div>
      </div>
    
    </div>
  </div>

  <div class="row">
    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-users  fa-3x"></i>
        </div>
        <div class="zg-box__value">1.298</div>
        <div class="zg-box__title">Total de clientes</div>
      </div>
    
    </div>
    
    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-battery-empty  fa-3x"></i>
        </div>
        <div class="zg-box__value">123</div>
        <div class="zg-box__title">Produtos sem estoque</div>
      </div>
    
    </div>
    
    <div class="col-sm-4">
    
      <div class="zg-box">
        <div class="zg-box__icon">
          <i class="fa  fa-truck  fa-3x"></i>
        </div>
        <div class="zg-box__value">3.344</div>
        <div class="zg-box__title">Estoque total</div>
      </div>
    
    </div>
  </div>

  <div class="row">
    <div class="col-sm-6">
      <div class="zg-graph-box">
        <div class="zg-graph-box__header">
          <h2 class="zg-graph-box__title">Faturamento mensal <small>Últimos 12 meses</small></h2>
        </div>
        <div class="zg-graph-box__content">
          <div class="zg-graph-box__no-data">
              <i class="fa  fa-line-chart  fa-2x"></i>
              		<!-- akiiiiiiii -->
          </div>

          <div>
            <canvas id="lineChart" height="160"></canvas>
          </div>
        </div>
      </div>
    </div>
    
    <div class="col-sm-6">
      <div class="zg-graph-box">
        <div class="zg-graph-box__header">
          <h2 class="zg-graph-box__title">Faturamento por representante <small>Últimos 12 meses</small></h2>
        </div>
        <div class="zg-graph-box__content" style="position: relative">
          <div class="zg-graph-box__no-data">
              <i class="fa  fa-line-chart  fa-2x"></i>
              <span>Não há dados</span>
          </div>

          <div>
            <canvas id="lineChart" height="160"></canvas>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
			Highcharts.chart('container', {
			
			    title: {
			        text: 'Solar Employment Growth by Sector, 2010-2016'
			    },
			
			    subtitle: {
			        text: 'Source: thesolarfoundation.com'
			    },
			
			    yAxis: {
			        title: {
			            text: 'Number of Employees'
			        }
			    },
			    legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'middle'
			    },
			
			    plotOptions: {
			        series: {
			            label: {
			                connectorAllowed: false
			            },
			            pointStart: 2010
			        }
			    },
			
			    series: [{
			        name: 'Installation',
			        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
			    }, {
			        name: 'Manufacturing',
			        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
			    }, {
			        name: 'Sales & Distribution',
			        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
			    }, {
			        name: 'Project Development',
			        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
			    }, {
			        name: 'Other',
			        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
			    }],
			
			    responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 500
			            },
			            chartOptions: {
			                legend: {
			                    layout: 'horizontal',
			                    align: 'center',
			                    verticalAlign: 'bottom'
			                }
			            }
			        }]
			    }
			
			});
		</script>

	</section>
	
	<footer class="zg-layout-footer  js-content">
		<div class="container-fluid">
			<span class="zg-footer-disclaimer">&copy; 2019 FATEC MOGI DAS CRUZES. Todos os direitos reservados.</span>
		</div>
	</footer>

</div>

<div class="zg-search-modal  js-search-modal">
	<form action="#" class="zg-search-modal__form">
		<input class="zg-search-modal__input  js-search-modal-input" type="text" placeholder="O que vocÃª estÃ¡ procurando?"/>
		<div class="zg-search-modal__input-icon">
			<i class="glyphicon  glyphicon-search  js-search-modal-go"></i>
		</div>
	</form>
	
	<div class="zg-search-modal__controls">
		<i class="glyphicon glyphicon-remove  js-search-modal-close"></i>
	</div>
</div>

<script src="../../assets/javascripts/vendors.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>

</body>
</html>