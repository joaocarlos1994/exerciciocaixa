package br.com.hyperclass2;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class EventoTrasacional implements Comparable<EventoTrasacional> {
	
	private final Map<String, List<Cedula>> valores;
	private final Date date;
	private final TypeEvent typeEvent;
	
	public EventoTrasacional(Map<String, List<Cedula>> valores, Date date, TypeEvent typeEvent) {
		super();
		this.valores = valores;
		this.date = date;
		this.typeEvent = typeEvent;
	}

	public Map<String, List<Cedula>> getValores() {
		return valores;
	}

	public Date getDate() {
		return date;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}

}
