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
		
		ContaBancaria contaBancaria = new ContaBancaria("1111-1", 1000);
		Pessoa pessoa = new Pessoa("João Silva", contaBancaria);
		
		Caixa caixa = new Caixa(contaBancaria, pessoa);
		caixa.adicionarNotaCaixa("10", cedula10);
		caixa.adicionarNotaCaixa("20", cedula20);
		caixa.adicionarNotaCaixa("50", cedula50);
		caixa.adicionarNotaCaixa("100", cedula100);
		
		Map<String, Cedula> cedulasSaque = caixa.saque(900);
		
		System.out.println("Saldo: " + caixa.saldo());
		
		for (String chave : cedulasSaque.keySet()) {
			Cedula cedula = cedulasSaque.get(chave);
			System.out.println("Valor: " + cedula.getValor() + " Quantidade: " + cedula.getQuantidade() + "\n");
		}

	}

}
