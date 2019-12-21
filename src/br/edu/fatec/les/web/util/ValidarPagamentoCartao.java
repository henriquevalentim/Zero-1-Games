package br.edu.fatec.les.web.util;

import java.util.HashMap;

public class ValidarPagamentoCartao {
	
	public static Boolean validaCartao(String numeroCartao,String codigoSeguranca) {
		HashMap<String, String> cartoes = new HashMap<String, String>();
		
		cartoes.put("1111222233334444", "123");
		cartoes.put("2222111155558888", "321");
		cartoes.put("7777888899996666", "526");
		cartoes.put("1111222233335555", "852");
		cartoes.put("9999888877772222", "523");
		cartoes.put("2222000011111111", "951");
		cartoes.put("4444333322221111", "963");
		
		String codSeguranca = cartoes.get(numeroCartao);
		
		if(codigoSeguranca.equals(codSeguranca)) {
			System.out.println("true");
			return true;
		}else {
			System.out.println("false");
			return false;
		}
	}
}
