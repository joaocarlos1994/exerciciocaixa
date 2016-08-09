package br.com.hyperclass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Caixa {
	
	private OperacoesBancarias operacoesBancarias;
	private Pessoa pessoa;
	private final Map<String, Cedula> cedulasCaixa;
	private Map<String, Cedula> cedulasSaque;
	
	public Caixa(OperacoesBancarias operacoesBancarias, Pessoa pessoa, Map<String, Cedula> cedulasCaixa) {
		super();
		this.operacoesBancarias = operacoesBancarias;
		this.pessoa = pessoa;
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
	
	public Map<String, Cedula> saque(final double valor){
		cedulasSaque = new HashMap<String, Cedula>();
		
		if(totalCaixa(getCedulasCaixa()) > valor){
			notas100(valor);
			operacoesBancarias.saque(valor);
		}
		
		return cedulasSaque;
	}
	
	public double saldo(){
		return operacoesBancarias.saldo();
	}
	
	public List<Extrato> geExtratos(){
		return operacoesBancarias.extrato();
	}
	
	private void notas10(final double valor){
		if((valor >= 10 && valor < 20) && cedulasCaixa.containsKey("10")){
			int totalNotas = (int) valor / 10;
			double restoDivisao = valor % 10;
			cedulasSaque.put("10", new Cedula(10, totalNotas));
			cedulasCaixa.put("10", new Cedula(50, getCedula("10").getQuantidade() - totalNotas));
		}
	}
	
	private void notas20(final double valor){
		
		double restoDivisao = valor;
		
		if((valor >= 20 && valor < 50) && cedulasCaixa.containsKey("20")){
			int totalNotas = (int) valor / 20;
			restoDivisao = valor % 20;
			cedulasSaque.put("20", new Cedula(20, totalNotas));
			cedulasCaixa.put("20", new Cedula(50, getCedula("20").getQuantidade() - totalNotas));
		}
		notas10(restoDivisao);
	}
	
	private void notas50(final double valor){
		
		double restoDivisao = valor;
		
		if((valor >= 50 && valor < 100) && cedulasCaixa.containsKey("50")){
			int totalNotas = (int) valor / 50;
			restoDivisao = valor % 50;
			cedulasSaque.put("50", new Cedula(50, totalNotas));
			cedulasCaixa.put("50", new Cedula(50, getCedula("50").getQuantidade() - totalNotas));
		}
		notas20(restoDivisao);
	}
	
	private void notas100(final double valor){
		
		double restoDivisao = valor;
		
		if((valor >= 100) && cedulasCaixa.containsKey("100")){
			int totalNotas = (int) valor / 100;
			restoDivisao = valor % 100;
			cedulasSaque.put("100", new Cedula(100, totalNotas));
			cedulasCaixa.put("100", new Cedula(100, getCedula("100").getQuantidade() - totalNotas));
		}
		notas50(restoDivisao);
	}
	
	public Cedula getCedula(String key){
		Cedula cedula = cedulasCaixa.get(key);
		return cedula;
	}


}
