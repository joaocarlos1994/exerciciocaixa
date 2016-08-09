package br.com.hyperclass2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaCorrente {
	
	private double saldo;
	private Map<String, List<EventoTrasacional>> eventoTrasacionals;
	
	public ContaCorrente(double saldo, Map<String, EventoTrasacional> eventoTrasacionals) {
		super();
		this.saldo = saldo;
		this.eventoTrasacionals = new HashMap<String, List<EventoTrasacional>>();
	}
	
	public void addEventTransaction(String event){
		if(eventoTrasacionals.containsKey(event)){
			eventoTrasacionals.put(event, null);
		}
	}
	
	public void EventTransaction(String event){
		
	}
	

}
