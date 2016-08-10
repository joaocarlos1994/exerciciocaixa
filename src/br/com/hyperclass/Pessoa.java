package br.com.hyperclass;

public class Pessoa {
	
	private final String nome;
	private OperacoesContaBancaria operacoesBancarias; //Abstração para poder utilizar n tipo de conta.
	
	public Pessoa(String nome, OperacoesContaBancaria operacoesBancarias) {
		super();
		this.nome = nome;
		this.operacoesBancarias = operacoesBancarias;
	}

	public String getNome() {
		return nome;
	}

	public OperacoesContaBancaria operacoesBancarias() {
		return operacoesBancarias;
	}

}
