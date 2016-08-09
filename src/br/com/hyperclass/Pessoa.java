package br.com.hyperclass;

public class Pessoa {
	
	private final String nome;
	private OperacoesBancarias operacoesBancarias; //Abstração para poder utilizar n tipo de conta.
	
	public Pessoa(String nome, OperacoesBancarias operacoesBancarias) {
		super();
		this.nome = nome;
		this.operacoesBancarias = operacoesBancarias;
	}

	public String getNome() {
		return nome;
	}

	public OperacoesBancarias operacoesBancarias() {
		return operacoesBancarias;
	}

}
