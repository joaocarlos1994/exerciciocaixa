package br.com.hyperclass2;

import java.util.Date;

public class DinheiroDepositado extends EventoTrasacional {


	public DinheiroDepositado(double valor, Date date, TypeEvent typeEvent) {
		super(valor, date, typeEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
