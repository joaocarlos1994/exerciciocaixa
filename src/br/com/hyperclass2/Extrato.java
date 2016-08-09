package br.com.hyperclass2;

public class Extrato {
	
	private final String tituloOperacao;
	private final String operacaoRealizada;
	
	public Extrato(String tituloOperacao, String operacaoRealizada) {
		super();
		this.tituloOperacao = tituloOperacao;
		this.operacaoRealizada = operacaoRealizada;
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public String getOperacaoRealizada() {
		return operacaoRealizada;
	};
	
	

}
