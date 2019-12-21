package br.edu.fatec.les.dominio;

public class Cupom extends EntidadeDominio{

	private Double valor;
	private boolean status;
	private String codigo;
	
	
	public Cupom(Double valor, boolean status, String codigo) {
		super();
		this.valor = valor;
		this.status = status;
		this.codigo = codigo;
	}

	public Cupom() {
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
