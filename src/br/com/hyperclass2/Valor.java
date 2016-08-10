package br.com.hyperclass2;

public enum Valor {
	
	Cem(100), Cinquenta(50), Vinte(20), Dez(10);
	
	private final int value;
	
	private Valor(final int value) {
		this.value = value;
	}

}
