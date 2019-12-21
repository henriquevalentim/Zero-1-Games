//window.onload = function(){ 
//	 alert("Hi there");
//}

var total; 

function adiciona(id){
	let quantidade = document.getElementById("quantidade"+id).value;
//	alert(valor);
	quantidade++;
	document.getElementById("quantidade"+id).value = quantidade;
	subTotalLinha (id);
}

function subtrai(id){
	let quantidade = document.getElementById("quantidade"+id).value;
//	alert(quantidade);
	if (quantidade != 1){
		quantidade--;
	}
	document.getElementById("quantidade"+id).value = quantidade;
	subTotalLinha (id);
}

function subTotalLinha (id){
	let quantidade = document.getElementById("quantidade"+id).value;
	let precoUnitario = document.getElementById("precoUnitario"+id).innerHTML;
	let subtotal;
	
	precoUnitario = precoUnitario.replace("R$ ", "");
	precoUnitario = (parseInt(precoUnitario, 10));
	
	subtotal = precoUnitario * quantidade; 
	document.getElementById("subTotalLinha"+id).innerHTML = "R$ " + subtotal.toFixed(2).replace(".",",");
}

function totalCompra(){
	alert("TESTANDO");
	
}

window.onload = function(){ 

}

