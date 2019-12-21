<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Zero1Games - Cadastro de cliente</title>

  <!-- Bootstrap core CSS -->
  <link href="../assets/stylesheets/vendors/bootstrap.min.css" rel="stylesheet">
  
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../assets/stylesheets/shop-homepage.css" rel="stylesheet">
  <link href="../assets/stylesheets/style.css" rel="stylesheet">
  
  <script>
	function validarSenha() {
		var senha1 = document.getElementsByName('txtSenha')[0].value;
		var senha2 = document.getElementsByName('txtSenha2')[0].value;
		if (senha1 != senha2) {
			alert("SENHAS DIFERENTES!\\nFAVOR DIGITAR SENHAS IGUAIS");
			return false;
		}
		return true;
	}
</script>
  
  <style>
  	p{
  		margin-bottom: 0px;
  	}
  
  </style>

</head>

<body>

 <c:import url="fragmentos/navbar.jsp" />
  
  <br>
  <br>
  
  <div class="row">
	<div class="offset-md-5 col-md-4">
		<h2>Cadastre-se</h2>
	</div>
  </div>
  
  <c:if test="${ resultado != null && !resultado.msg.contains('sucesso') }">
	  	<div class="alert alert-danger col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  
  <c:if test="${ resultado != null && resultado.msg.contains('sucesso') }">
  		<div class="alert alert-success col-md-4 offset-md-4" role="alert">
		  	<c:forTokens items = "${ resultado.msg }" delims = "!" var = "erro">
	    		<c:out value = "${erro}"/><br>
	      	</c:forTokens>
		</div>
  </c:if>
  <br>
  <br>
  
  <form action="ControleCliente" method="POST" onsubmit="return validarSenha();">
	<div class="row">
		<div class="offset-md-4 col-md-4  form-group">
			<label class="control-label" for="email">E-mail</label>
			<input id="txtEmail" name="txtEmail" type="text" 
			value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).email}"/></c:if>'class="form-control"/>
		</div>
	</div>
	<div class="row">
		<div class="offset-md-4 col-md-4  form-group">
			<label class="control-label" for="senha">Senha</label>
			<input id="txtSenha" name="txtSenha" type="password"
			value='' class="form-control"/>
		</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="confirmesenha">Confirme sua senha</label>
				<input id="txtSenha2" name="txtSenha2" type="password"
				value='' class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="nome">Nome</label>
				<input id="txtNome" name="txtNome" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).nome}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="datanascimento">Data de nascimento</label>
				<input id="txtDtNascimento" name="txtDtNascimento" type="text" 
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).dataNascimento}"/></c:if>'class="form-control js-date"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cpf">CPF</label>
				<input id="txtCpf" name="txtCpf" type="text" 
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).cpf}"/></c:if>'class="form-control js-cpf"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="genero">Gênero</label><br>
				<input type="radio" id="txtGeneroM" name="txtGenero" value="M" checked>
				  	<label class="form-check-label" for="exampleRadios1">
				    	Masculino
				  	</label>
				<input type="radio" id="txtGeneroF" name="txtGenero" value="F">
				  	<label class="form-check-label" for="exampleRadios1">
				    	Feminino
				  	</label>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="telefone">Telefone</label>
				<input id="txtNumeroTelefone" name="txtNumeroTelefone" type="text" 
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).telefone.numero}"/></c:if>'class="form-control js-phone-number"/>
			</div>
	</div>
	
	<br>
    <br>
	
	<div class="row">
		<div class="offset-md-5 col-md-4">
			<h4>Endereço residencial</h4>
		</div>
	</div>
	
	<br>
    <br>
    
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cep">CEP</label>
				<input id="txtCep" name="txtCep" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.cep}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="logradouro">Logradouro</label>
				<input id="txtLogradouro" name="txtLogradouro" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.logradouro}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="bairro">Bairro</label>
				<input id="txtBairro" name="txtBairro" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.bairro}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="complemento">Complemento</label>
				<input id="txtComplemento" name="txtComplemento" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.complemento}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="numero">Numero</label>
				<input id="txtNumero" name="txtNumero" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.numero}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="tiporesidencia">Tipo de residência</label>
					<select id="txtTpResidencia" name="txtTpResidencia" class="form-control">
						<option value="<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.tipoResidencia}"/></c:if>"><c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.tipoResidencia}"/></c:if></option>
						<option value="APARTAMENTO">Apartamento</option>
						<option value="CASA">Casa</option>
						<option value="COMERCIAL">Comercial</option>
						<option value="OUTRO">Outro</option>
					</select>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="tipologradouro">Tipo de logradouro</label>
					<select id="txtTpLogradouro" name="txtTpLogradouro" class="form-control">
						<option value="<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.tipoLogradouro}"/></c:if>"><c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.tipoLogradouro}"/></c:if></option>
						<option value="AVENIDA">Avenida</option>
						<option value="RUA">Rua</option>
						<option value="TRAVESSA">Travessa</option>
						<option value="OUTRO">Outro</option>
					</select>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="cidade">Cidade</label>
				<input id="txtCidade" name="txtCidade" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.cidade.nome}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="Estado">Estado</label>
				<input id="txtEstado" name="txtEstado" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.cidade.estado.nome}"/></c:if>'class="form-control"/>
			</div>
	</div>
	<div class="row">
			<div class="offset-md-4 col-md-4  form-group">
				<label class="control-label" for="pais">País</label>
				<input id="txtPais" name="txtPais" type="text"
				value='<c:if test="${ resultado.entidades != null }"><c:out value="${resultado.entidades.get(0).endereco.cidade.estado.pais.nome}"/></c:if>'class="form-control"/>
			</div>
	</div>
	
	<br>
	<br>
	<input id="txtNomeEndereco" name="txtNomeEndereco" type="hidden" value="Endereco 1"/>
	<input type="hidden" value="COBRANCA" name="txtEnderecoCobranca"/>
	<input type="hidden" value="ENTREGA" name="txtEnderecoEntrega"/>
	
	<div class="row">
		<div class="offset-md-4 col-md-4">
			<button type="submit" class="btn btn-success w-25" id="btnOperacao" name="btnOperacao" value="SALVAR">Salvar</button>
			<button type="submit" class="btn btn-light w-25">Cancelar</button>
		</div>
	</div>
	
	<br>
	<br>
	
  </form>
  
<c:import url="fragmentos/footer.jsp" />
  
  <!-- Bootstrap core JavaScript -->
  <script src="../assets/javascripts/vendors/jquery.min.js"></script>
  <script src="../assets/javascripts/vendors/bootstrap.min.js"></script>
  <script src="../assets/javascripts/vendors/jquery.mask.min.js"></script>
  <script src="../assets/javascripts/mascarasCliente.js"></script>
  
      <!-- Adicionando Javascript -->
    <script type="text/javascript" >
        $(document).ready(function() {
            
            //Quando o campo cep perde o foco.
            $("#txtCep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep != "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#txtLogradouro").val("...");
                        $("#txtBairro").val("...");
                        $("#txtCidade").val("...");
                        $("#txtEstado").val("...");
                        $("#txtPais").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#txtLogradouro").val(dados.logradouro);
                                $("#txtBairro").val(dados.bairro);
                                $("#txtCidade").val(dados.localidade);
                                $("#txtEstado").val(dados.uf);
                                $("#txtPais").val("Brasil");
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
        });

    </script>
  
</body>


</html>