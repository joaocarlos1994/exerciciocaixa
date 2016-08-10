package br.com.hyperclass2;

import java.util.Collection;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		
		ContaCorrente contaCorrente = new ContaCorrente(1000, "1000");
		Pessoa pessoa = new Pessoa("João da Silva", contaCorrente);
		Caixa caixa = new Caixa(contaCorrente, pessoa);
		
		Map<String, Collection<Cedula>> listaCedula = caixa.getCedulasCaixa();
		
		for(String key : listaCedula.keySet()){
			System.out.println("Notas de " + key + ": " + listaCedula.get(key).size());
		}
		
	}

}
