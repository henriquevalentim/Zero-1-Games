package br.edu.fatec.les.web.util;

import java.math.BigDecimal;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

import br.edu.fatec.les.dominio.MetodoEntrega;

public class WebServiceCorreio {
	

	static String nCdEmpresa = null;
	static String sDsSenha = null;
	static String nCdServico = "40010"; //sedex
	static String sCepOrigem = "08750330";
	static String sCepDestino;
	static String nVlPeso = "0.4";
	static int nCdFormato = 1; // caixa
	static BigDecimal nVlComprimento = new BigDecimal(16);
	static BigDecimal nVlAltura = new BigDecimal(6);
	static BigDecimal nVlLargura = new BigDecimal(16);
	static BigDecimal nVlDiametro = new BigDecimal(0);
	static String sCdMaoPropria = "S";
	static BigDecimal nVlValorDeclarado = new BigDecimal(150);
	static String sCdAvisoRecebimento = "S";
    	
        
        public static MetodoEntrega calculaPrecoPrazo(String cepDestino) {
        	MetodoEntrega metodoEntrega = null;
        	try {
				CalcPrecoPrazoWSSoapProxy service = new CalcPrecoPrazoWSSoapProxy();
				CResultado retornoCorreios = service.getCalcPrecoPrazoWSSoap().calcPrecoPrazo("", "", 
					nCdServico, 
					sCepOrigem, 
					cepDestino, 
					nVlPeso, 
					nCdFormato, 
					nVlComprimento, 
					nVlAltura, 
					nVlLargura, 
					nVlDiametro, 
					sCdMaoPropria, 
					nVlValorDeclarado, 
					sCdAvisoRecebimento);
				
				CServico[] cservico = retornoCorreios.getServicos();
				
				metodoEntrega = new MetodoEntrega();
				metodoEntrega.setValor(Double.valueOf(cservico[0].getValor().replace(",", ".")));
				metodoEntrega.setQuantidadeDias(Integer.valueOf(cservico[0].getPrazoEntrega()));
					
				return metodoEntrega;
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
        	return metodoEntrega;
			
        }
		
}
