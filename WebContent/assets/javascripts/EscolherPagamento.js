
var CheckMaximo = 2;
function verificar(id) {
	var Marcados = 1;
	var objCheck = document.forms['formPagamento'].elements['cartao2'];

	//Percorrendo os checks para ver quantos foram selecionados:
	for (var iLoop = 0; iLoop<objCheck.length; iLoop++) {
		//Se o número máximo de checkboxes ainda não tiver sido atingido, continua a verificação:
		if (Marcados <= CheckMaximo) {
			if (objCheck[iLoop].checked) {
				Marcados++;
			}
			//Habilitando todos os checkboxes, pois o máximo ainda não foi alcançado.
			for (var i=0; i<objCheck.length; i++) {
//				objCheck[i].disabled = false;
			}       
			//Caso contrário, desabilitar o checkbox;
			//Nesse caso, é necessário percorrer todas as opções novamente, desabilitando as não checadas;
		} else {
			for (var i=0; i<objCheck.length; i++) {
				if(objCheck[i].checked == false) {
//					objCheck[i].disabled = true;
				}       
			}
		}
	}
	habilitaCampo(id)
}

function habilitaCampo(id){
//	if(document.getElementById("txtValorCartao"+id).disabled){
//		document.getElementById("txtValorCartao"+id).disabled = false;
//	}else {
//		document.getElementById("txtValorCartao"+id).disabled = true;
//		document.getElementById("txtValorCartao"+id).value = "";
//	}
}

function exibirDoisCartao() {
	limparRadios('cartao1');
	document.getElementById("umCartao").style.display = "none";
	document.getElementById("doisCartoes").style.display = "block";
}

function exibirUmCartao() {
	limparRadios('cartao2');
	document.getElementById("doisCartoes").style.display = "none";
	document.getElementById("umCartao").style.display = "block";
}

function exibirBoleto() {
	document.getElementById("doisCartoes").style.display = "none";
	document.getElementById("umCartao").style.display = "none";
}

function limparRadios( radioname ) {
	for( i = 0; i < document.formPagamento[radioname].length; i++ )
	document.formPagamento[radioname][i].checked = false;
}





