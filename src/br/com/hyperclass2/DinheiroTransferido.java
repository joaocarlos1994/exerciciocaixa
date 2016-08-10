package br.com.hyperclass2;

import java.util.Date;

public class DinheiroTransferido extends EventoTrasacional {

	public DinheiroTransferido(double valor, Date date, TypeEvent typeEvent) {
		super(valor, date, typeEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
