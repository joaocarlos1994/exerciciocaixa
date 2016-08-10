package br.com.hyperclass2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaCorrente {
	
	private double saldo;
	private final Map<String, List<EventoTrasacional>> eventoTrasacionais;
	
	public ContaCorrente(double saldo, Map<String, EventoTrasacional> eventoTrasacionals) {
		super();
		this.saldo = saldo;
		this.eventoTrasacionais = new HashMap<String, List<EventoTrasacional>>();
	}
	
	public void addEventTransaction(String key, EventoTrasacional eventoTrasacional){
		
		if(eventoTrasacionais.containsKey(key)){
			List<EventoTrasacional> eventoTrasacionaisList = eventoTrasacionais.get(key);
			eventoTrasacionaisList.addAll(eventoTrasacionaisList);
			eventoTrasacionais.put(key, eventoTrasacionaisList);
		}
	}
	
	public void EventTransaction(String event){
		
	}
	

}
