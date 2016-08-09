package br.com.hyperclass2;

public class Cedula {
	
	private final int valor;
	private int quantidade;
	
	public Cedula(int valor, int quantidade) {
		super();
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

}
