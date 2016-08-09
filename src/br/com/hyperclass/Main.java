package br.com.hyperclass;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		Cedula cedula10 = new Cedula(10, 10);
		Cedula cedula20 = new Cedula(20, 20);
		Cedula cedula50 = new Cedula(50, 4);
		Cedula cedula100 = new Cedula(100, 3);
		
		Map<String, Cedula> cedulas = new HashMap<>();
		cedulas.put("10", cedula10);
		cedulas.put("20", cedula20);
		cedulas.put("50", cedula50);
		cedulas.put("100", cedula100);
		
		ContaBancaria contaBancaria = new ContaBancaria("1111-1", 1000);
		Pessoa pessoa = new Pessoa("João Silva", contaBancaria);
		
		Caixa caixa = new Caixa(contaBancaria, pessoa, cedulas);
		
		Map<String, Cedula> cedulasSaque = caixa.saque(10);
		
		System.out.println(caixa.saldo());
		
		Set<String> chaves = cedulasSaque.keySet();
		for (String chave : chaves) {
			Cedula cedula = cedulasSaque.get(chave);
			System.out.println("Valor: " + cedula.getValor() + " Quantidade: " + cedula.getQuantidade() + "\n");
		}

	}

}
