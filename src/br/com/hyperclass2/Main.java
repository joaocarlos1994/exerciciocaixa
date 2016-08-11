package br.com.hyperclass2;

public class Main {
	
	public static void main(String[] args) {
		
		Caixa caixa = new Caixa();
		Pessoa pessoa = caixa.retornaPessoaNumeroConta("54125-9");
		
		Caixa caixa2 = new Caixa(pessoa.getOperacoesBancarias(), pessoa);
		System.out.println("Saldo: " + caixa2.saldo());
		System.out.println("Saque: " + caixa2.saque(10));
		System.out.println("Saldo: " + caixa2.saldo());
		
		
	}

}
