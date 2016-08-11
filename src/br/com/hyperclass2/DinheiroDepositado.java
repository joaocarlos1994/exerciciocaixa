package br.com.hyperclass2;

public class DinheiroDepositado extends EventoTrasacional {

	public DinheiroDepositado(double valor) {
		super(valor, TypeEvent.DinheiroDepositado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
