package br.com.hyperclass2;

import java.util.Date;

public abstract class EventoTrasacional implements Comparable<EventoTrasacional> {
	
	private final double valor;
	private final Date date;
	private final TypeEvent typeEvent;
	
	public EventoTrasacional(double valor, TypeEvent typeEvent) {
		super();
		this.valor = valor;
		this.date = new Date();
		this.typeEvent = typeEvent;
	}

	public double getValores() {
		return valor;
	}

	public Date getDate() {
		return date;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}

}
