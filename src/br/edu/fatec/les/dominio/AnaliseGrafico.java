package br.edu.fatec.les.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.jogo.Jogo;

public class AnaliseGrafico extends EntidadeDominio{

	private ArrayList<Jogo> jogos;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int tipoAnalise;
    private List<String> periodos;
    private List<String> dados;
    
	public ArrayList<Jogo> getJogos() {
		return jogos;
	}
	public void setJogos(ArrayList<Jogo> jogos) {
		this.jogos = jogos;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public int getTipoAnalise() {
		return tipoAnalise;
	}
	public void setTipoAnalise(int tipoAnalise) {
		this.tipoAnalise = tipoAnalise;
	}
	public List<String> getPeriodos() {
		return periodos;
	}
	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}
	public List<String> getDados() {
		return dados;
	}
	public void setDados(List<String> dados) {
		this.dados = dados;
	}
   
}
