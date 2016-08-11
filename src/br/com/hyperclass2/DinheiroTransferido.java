package br.com.hyperclass2;

public class DinheiroTransferido extends EventoTrasacional {

	public DinheiroTransferido(double valor) {
		super(valor, TypeEvent.DinheiroTransferido);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
