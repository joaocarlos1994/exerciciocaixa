package br.com.hyperclass2;

public class Pessoa {
	
	private final String nome;
	private OperacoesContaCorrente operacoesContaCorrente; //Abstração para poder utilizar n tipo de conta.
	
	public Pessoa(String nome, OperacoesContaCorrente operacoesContaCorrente) {
		super();
		this.nome = nome;
		this.operacoesContaCorrente = operacoesContaCorrente;
	}

	public String getNome() {
		return nome;
	}

	public OperacoesContaCorrente getOperacoesBancarias() {
		return operacoesContaCorrente;
	}
	
	


}
