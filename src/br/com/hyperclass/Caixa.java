package br.com.hyperclass;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Caixa {
	
	private OperacoesBancarias operacoesBancarias;
	private final Map<String, Cedula> cedulasCaixa;
	
	public Caixa(OperacoesBancarias operacoesBancarias, Map<String, Cedula> cedulasCaixa) {
		super();
		this.operacoesBancarias = operacoesBancarias;
		
		if (totalCaixa(cedulasCaixa) <  100){
			this.cedulasCaixa = cedulasCaixa;
		} else {
			this.cedulasCaixa = null;
		}
		
	}

	public OperacoesBancarias getOperacoesBancarias() {
		return operacoesBancarias;
	}

	public Map<String, Cedula> getCedulasCaixa() {
		return cedulasCaixa;
	}
	
	private double totalCaixa(final Map<String, Cedula> cedulasCaixa){
		
		double total = 0;
		
		Set<String> chaves = cedulasCaixa.keySet();
		for (String chave : chaves) {
			Cedula cedula = cedulasCaixa.get(chave);
			total += (cedula.getQuantidade() * cedula.getValor());
		}
		
		return total;
	}
	
	public void saque(final double valor){
		
		if(valor >= 10 && valor < 20){}
		if(valor >= 20 && valor < 50){}
		if(valor >= 50 && valor < 100){}
	}
	
	private int notas10(){
		return 0;
	}
	
	private int notas20(){
		return 0;
	}
	
	private int notas50(){
		return 0;
	}
	
	private int notas100(){
		return 0;
	}
	
	


}
