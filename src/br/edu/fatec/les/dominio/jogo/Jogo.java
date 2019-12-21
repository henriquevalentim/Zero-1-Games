package br.edu.fatec.les.dominio.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Jogo extends EntidadeDominio {

	private String codigo;
	private String titulo;
	private Desenvolvedor desenvolvedor;
	private Distribuidora distribuidora;
	private String plataforma;
	private String categoria;
	private GrupoPrecificacao grupoPrecificacao;
	private String generoJogo;
	private String classificacaoIndicativa;
	private Boolean status;
	private Integer numeroJogadoresOnline;
	private Integer numeroJogadoresOffline;
	private Double precoCompra;
	private Double precoVenda;
	private Integer quantidade;
	private String descricao;
	private String urlFoto;
	private String motivo;

	public Jogo() {
		super();
	}

	public Jogo(String codigo, String titulo, Desenvolvedor desenvolvedor, Distribuidora distribuidora,
			String plataforma, String categoria, GrupoPrecificacao grupoPrecificacao, String generoJogo,
			String classificacaoIndicativa, Integer numeroJogadoresOnline, Integer numeroJogadoresOffline,
			Double precoCompra, Double precoVenda, Integer quantidade, String descricao, String urlFoto) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.desenvolvedor = desenvolvedor;
		this.distribuidora = distribuidora;
		this.plataforma = plataforma;
		this.categoria = categoria;
		this.grupoPrecificacao = grupoPrecificacao;
		this.generoJogo = generoJogo;
		this.classificacaoIndicativa = classificacaoIndicativa;
		this.numeroJogadoresOnline = numeroJogadoresOnline;
		this.numeroJogadoresOffline = numeroJogadoresOffline;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.urlFoto = urlFoto;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public Distribuidora getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Distribuidora distribuidora) {
		this.distribuidora = distribuidora;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	public void setClassificacaoIndicativa(String classificacaoIndicativa) {
		this.classificacaoIndicativa = classificacaoIndicativa;
	}

	public Integer getNumeroJogadoresOnline() {
		return numeroJogadoresOnline;
	}

	public void setNumeroJogadoresOnline(Integer numeroJogadoresOnline) {
		this.numeroJogadoresOnline = numeroJogadoresOnline;
	}

	public Integer getNumeroJogadoresOffline() {
		return numeroJogadoresOffline;
	}

	public void setNumeroJogadoresOffline(Integer numeroJogadoresOffline) {
		this.numeroJogadoresOffline = numeroJogadoresOffline;
	}

	public Double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public GrupoPrecificacao getGrupoPrecificacao() {
		return grupoPrecificacao;
	}

	public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
		this.grupoPrecificacao = grupoPrecificacao;
	}

	public String getGeneroJogo() {
		return generoJogo;
	}

	public void setGeneroJogo(String generoJogo) {
		this.generoJogo = generoJogo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
