package br.com.hyperclass2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caixa {
	
	private final Map<String, List<Cedula>> cedulasCaixa;
	private final Map<String, List<ContaCorrente>> listaContaCorrente;
	private ContaCorrente contaCorrente;
	
	public Caixa(ContaCorrente contaCorrente) {
		super();
		this.contaCorrente = contaCorrente;
		listaContaCorrente = new HashMap<String, List<ContaCorrente>>();
		cedulasCaixa = new HashMap<String, List<Cedula>>();
	}
	
	
	
	
	
	
	
	
	

}
