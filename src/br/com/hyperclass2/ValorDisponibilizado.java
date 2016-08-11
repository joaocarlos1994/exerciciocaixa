package br.com.hyperclass2;

public class ValorDisponibilizado extends EventoTrasacional {

	public ValorDisponibilizado(double valor) {
		super(valor, TypeEvent.ValorDisponibilizado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(EventoTrasacional o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
