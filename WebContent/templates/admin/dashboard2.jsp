<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Highcharts Example</title>
		<link href="../../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
		<script src="../../assets/javascripts/vendors/jquery.min.js"></script>
		<script src="../../assets/javascripts/code/highcharts.js"></script>
		<script src="../../assets/javascripts/code/modules/series-label.js"></script>
		<script src="../../assets/javascripts/code/modules/exporting.js"></script>
		<script src="../../assets/javascripts/code/modules/export-data.js"></script>

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
	<div class="container">
	
			<div id="container"></div>
					<script type="text/javascript">
					
					function getStringDate(aDate){
				        var dd = aDate;
				        var yy = dd.getYear();
				        var mm = dd.getMonth() + 1;
				        dd = dd.getDate();
				        if (yy < 2000) { yy += 1900; }
				        if (mm < 10) { mm = "0" + mm; }
				        if (dd < 10) { dd = "0" + dd; }
				        var rs = yy + "-" + mm + "-" + dd;
				        return rs;
				    }
					
					$(document).ready(function(){
						  	var data = new Date();
						  
							var dataDe1 = data.getFullYear()+"-01-01";
							var dataAte1 = getStringDate(data);
							var quantidadeF1 = [];
							var mes1 = [];
							var quantidadeM1 = [];
						
							$.ajax({
								url : 'ControleGrafico?tipoAnalise=1',
								type: 'POST',
								data : {btnOperacao: "CONSULTAR", de : dataDe1, ate : dataAte1},
								dataType: "json",
								success: function(json)
								{
									var jsonCompleto = JSON.stringify(json); 
									
									strQuantidade = json[0].periodos;
									mes1 = json[0].dados;
					
									for (var i = 0; i < strQuantidade.length; i++) {
									   quantidadeF1[i] = Number(strQuantidade[i]);
									}
									
									$.ajax({
										url : 'ControleGrafico?tipoAnalise=2',
										type: 'POST',
										data : {btnOperacao: "CONSULTAR", de : dataDe1, ate : dataAte1},
										dataType: "json",
										success: function(json)
										{
											var jsonCompleto = JSON.stringify(json); 
											
											strQuantidade = json[0].periodos;
											var quantidadeM1 = [];
											for (var i = 0; i < strQuantidade.length; i++) {
											   quantidadeM1[i] = Number(strQuantidade[i]);
											}
											grafico(quantidadeF1, quantidadeM1, mes1);
									    }
									})
							    }
							})
					});
					
					  $(document).ready(function(){
							$("[name='btnOperacao']").click(function (){
								var dataDe = $("#de").val();
								var dataAte = $("#ate").val();
								var quantidadeF = [];
								var mes = [];
								var quantidadeM = [];
							
								$.ajax({
									url : 'ControleGrafico?tipoAnalise=1',
									type: 'POST',
									data : {btnOperacao: "CONSULTAR", de : dataDe, ate : dataAte},
									dataType: "json",
									success: function(json)
									{
										var jsonCompleto = JSON.stringify(json); 
										
										strQuantidade = json[0].periodos;
										mes = json[0].dados;
						
										for (var i = 0; i < strQuantidade.length; i++) {
										   quantidadeF[i] = Number(strQuantidade[i]);
										}
										
										$.ajax({
											url : 'ControleGrafico?tipoAnalise=2',
											type: 'POST',
											data : {btnOperacao: "CONSULTAR", de : dataDe, ate : dataAte},
											dataType: "json",
											success: function(json)
											{
												var jsonCompleto = JSON.stringify(json); 
												
												strQuantidade = json[0].periodos;
												var quantidadeM = [];
												for (var i = 0; i < strQuantidade.length; i++) {
												   quantidadeM[i] = Number(strQuantidade[i]);
												}
												grafico(quantidadeF, quantidadeM, mes);
										    }
										})
								    }
								})
								
							});
						});
					
					function grafico(qtdeF, qtdeM, mes) { 
						Highcharts.chart('container', {
						    title: {
						        text: 'Análise de vendas por genero'
						    },
						
						    subtitle: {
						        text: 'Source: thesolarfoundation.com'
						    },
						    xAxis: {
						        categories: mes
						    },
						    yAxis: {
						        title: {
						            text: 'Número de clientes'
						        }
						    },
						    legend: {
						        layout: 'vertical',
						        align: 'right',
						        verticalAlign: 'middle'
						    },
						
						    plotOptions: {
						        line: {
						            dataLabels: {
						                enabled: true
						            },
						            enableMouseTracking: false
						        }
						    },
							
						    series: [{
						        name: 'Mascunino',
						        data: qtdeM
						    }, {
						        name: 'Feminino',
						        data: qtdeF
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
					}
			</script>
		</div>

	<div class="row">
		<div class="offset-md-4 col-md-4 mt-3 form-group">
			<form>
				<div class="form-inline">
					<label class="control-label">de: </label>
					<input name="de" id="de" type="date">
					<label class="control-label">Ate:</label>
					<input name="ate" id="ate" type="date">
				</div>
				<div class="form-inline">
				</div>
				<div class="offset-md-4 col-md-4 mt-2">
					<button type="button" name="btnOperacao" class="btn btn-success" value="CONSULTAR">ANALISAR</button>
				</div>
			</form>
		</div>
	</div>



	</body>
</html>

