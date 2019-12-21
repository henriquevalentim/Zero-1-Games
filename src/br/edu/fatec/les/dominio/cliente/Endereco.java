package br.edu.fatec.les.dominio.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Endereco extends EntidadeDominio{

	private String nome;
	private String cep;
	private String logradouro;
	private String bairro;
	private String complemento;
	private String numero;
	private String tipoResidencia;
	private String tipoLogradouro;
	private Boolean entrega;
	private Boolean cobranca;
	private Cidade cidade;
	
	public Boolean getEntrega() {
		return entrega;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = entrega;
	}

	public Boolean getCobranca() {
		return cobranca;
	}

	public void setCobranca(Boolean cobranca) {
		this.cobranca = cobranca;
	}

	public Endereco() {
		super();
	}
	
	public Endereco(String nome, String cep, String logradouro, String bairro, String complemento, String numero,
			String tipoResidencia, String tipoLogradouro, Cidade cidade) {
		super();
		this.nome = nome;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.complemento = complemento;
		this.numero = numero;
		this.tipoResidencia = tipoResidencia;
		this.tipoLogradouro = tipoLogradouro;
		this.cidade = cidade;
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getTipoResidencia() {
		return tipoResidencia;
	}
	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	
	
	
	
}
