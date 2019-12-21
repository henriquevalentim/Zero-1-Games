<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<title>Zero1Games - cadastrar produto</title>
	<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/vendors/upload.min.css"/>
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
			Estoque Geral
		</h1>
	</div>
</div>

<div class="container-fluid">

	<h2>Visão geral do estoque de jogos</h2>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="filtroestoque">Filtro</label>
					<select id="filtroestoque" class="form-control">
						<option value="">Plataforma</option>
						<option value="">Gênero</option>
						<option value="">Desenvolvedora</option>
						<option value="">Distribuidora</option>
					</select>
			</div>
	</div>
		  <p>Quantidade de jogos por Plataforma</p>            
		  <table class="table table-bordered">
		      <tr>
		      	<th>PS4</th>
		        <td>1500</td>
		      </tr> 
		      <tr>
		      	<th>PS3</th>
		        <td>750</td>
		      </tr>
		      <tr>
		      	<th>XBOX ONE</th>
		        <td>1200</td>
		      </tr>
		      <tr>
		      	<th>XBOX 360</th>
		        <td>800</td>
		      </tr>
		      
		    
		  </table>
	
	
</div>

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

	
<script src="../../assets/javascripts/vendors/upload.min.js"></script>
<script src="../../assets/javascripts/vendors/uikit.min.js"></script>

<script src="../../assets/javascripts/vendors.js"></script>
<script src="../../assets/javascripts/zero1games.js"></script>

<script type="text/javascript">
	$(function() {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)'
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop($('#upload-drop'), settings);
	});
</script>

</body>
</html>