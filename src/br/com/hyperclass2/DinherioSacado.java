package br.com.hyperclass2;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DinherioSacado extends EventoTrasacional {

	public DinherioSacado(Map<String, List<Cedula>> valores, Date date, TypeEvent typeEvent) {
		super(valores, date, typeEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
