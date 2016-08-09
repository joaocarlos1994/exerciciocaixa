package br.com.hyperclass;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Caixa caixa = new Caixa();
		Pessoa pessoa = caixa.retornaPessoaNumeroConta("54125-9");
		Caixa caixa2 = new Caixa(pessoa.operacoesBancarias(), pessoa);
		
		System.out.println("Cliente: " + caixa2.getPessoa().getNome() + " Saldo: " + caixa2.saldo());
		caixa2.saque(10);
		
		for(Extrato extrato : caixa2.geExtratos()){
			System.out.println("Título: " + extrato.getTituloOperacao());
			System.out.println("Operação: " + extrato.getOperacaoRealizada());
		}
		
		System.out.println("Cliente: " + caixa2.getPessoa().getNome() + " Saldo: " + caixa2.saldo());
		
		System.out.println("");
		
		Map<String, Cedula> cedulasCaixa = caixa2.getCedulasCaixa();
		
		for(Cedula cedula : cedulasCaixa.values()){
			System.out.println("Valor: " + cedula.getValor() + " Quantidade: " + cedula.getQuantidade());
		}
	}

}
