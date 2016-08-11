package br.com.hyperclass2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Caixa {
	
	/** 
	 * Poderia haver uma classe abstrata "Disposito" extendo para o Caixa para melhor modelar o aplica��o, 
	 * podendo assim crescer no caso de haver outras formas de fazer consulta de saldo e transfer�ncia (Internet Banking)
	 * 
	 */
	
	private final Map<Valor, Collection<Cedula>> cedulasCaixa;
	private final Map<String, Pessoa> listaContaPessoa;
	private OperacoesContaCorrente operacoesContaCorrente;
	
	public Caixa(OperacoesContaCorrente operacoesContaCorrente, Pessoa pessoa) {
		super();
		this.cedulasCaixa = new HashMap<Valor, Collection<Cedula>>();
		this.listaContaPessoa = new HashMap<String, Pessoa>();
		this.operacoesContaCorrente = operacoesContaCorrente;
		initializeCedulasCaixa();
		initializePessoaCaixa();
	}
	
	public Caixa(){
		super();
		this.cedulasCaixa = new HashMap<Valor, Collection<Cedula>>();
		this.listaContaPessoa = new HashMap<String, Pessoa>();
		initializeCedulasCaixa();
		initializePessoaCaixa();
	}
	
	public double saldo(){
		return operacoesContaCorrente.saldo();
	}
	
	public boolean saque(double valor){
		if(valor > 0 && valorTotalCaixa() > valor){
			operacoesContaCorrente.saque(valor);
			return true;
		}
		return false;
	}
	
	public boolean deposito(double valor, String numeroContaCorrente){
		Pessoa pessoa = retornaPessoaNumeroConta(numeroContaCorrente);
		ContaCorrente contaCorrente = (ContaCorrente) pessoa.getOperacoesBancarias();
		if(valor > 0 && contaCorrente != null){
			operacoesContaCorrente.deposito(valor, contaCorrente);
			return true;
		}
		return false;
	}
	
	public boolean transferencia(double valor, String numeroContaCorrente){
		Pessoa pessoa = retornaPessoaNumeroConta(numeroContaCorrente);
		ContaCorrente contaCorrente = (ContaCorrente) pessoa.getOperacoesBancarias();
		if((valor > 0) && contaCorrente != null){
			operacoesContaCorrente.transferencia(valor, contaCorrente);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Retorna um objeto do tipo pessoa, atráves do número da conta.
	 * */
	public Pessoa retornaPessoaNumeroConta(String numeroConta){
		Pessoa pessoa = listaContaPessoa.get(numeroConta);
		return pessoa;
	}
	
	/**
	 * @param pessoa Recebendo uma pessoa para adicionar na lista do caixa, funcionando assim como uma banco de dados.
	 * */
	private void adicionarPessoa(Pessoa pessoa){
		listaContaPessoa.put(pessoa.getOperacoesBancarias().getNumeroContaCorrente(), pessoa);
	}
	
	
	/**
	 * @param key este parametro a referente a list de notas
	 * @param cedula Nota a ser adicionado ao caixa
	 * 
	 * Este m�todo recupera a lista de notas que cont�m no caixa e adicona uma passsada por parametro de acordo com a chave 
	 * */
	private void adicionarCedulasCaixa(Valor valor, Cedula cedula){
		Collection<Cedula> cedulasList = cedulasCaixa.get(valor);
		
		if(cedulasList == null){
			cedulasList = new ArrayList<Cedula>();
		}
		
		cedulasList.add(cedula);
		cedulasCaixa.put(valor, cedulasList);
	}
	
	public Map<Valor, Collection<Cedula>> getCedulasCaixa() {
		return cedulasCaixa;
	}
	
	private double valorTotalCaixa(){
		
		double totalCaixa = 0;
		
		Map<Valor, Collection<Cedula>> listaNotasCaixa = cedulasCaixa;
		for (Valor key :  listaNotasCaixa.keySet()) {
			totalCaixa += listaNotasCaixa.get(key).size() * key.getValue();
		}
		return totalCaixa;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initializeCedulasCaixa(){
		
		for(int i = 0; i < 3; i++){
			Cedula cedula = new Cedula(Valor.Cem);
			adicionarCedulasCaixa(Valor.Cem, cedula);
		}
		
		for(int i = 0; i < 4; i++){
			Cedula cedula = new Cedula(Valor.Cinquenta);
			adicionarCedulasCaixa(Valor.Cinquenta, cedula);
		}
		
		for(int i = 0; i < 20; i++){
			Cedula cedula = new Cedula(Valor.Vinte);
			adicionarCedulasCaixa(Valor.Vinte, cedula);
		}
		
		for(int i = 0; i < 10; i++){
			Cedula cedula = new Cedula(Valor.Dez);
			adicionarCedulasCaixa(Valor.Dez, cedula);
		}
		
	}
	
	private void initializePessoaCaixa(){
		ContaCorrente contaCorrente = new ContaCorrente(10854.78, "54125-9");
		Pessoa pessoa = new Pessoa("João da Silva", contaCorrente);
		
		ContaCorrente contaCorrente2 = new ContaCorrente(1050.99, "25214-8");
		Pessoa pessoa2 = new Pessoa("Pedro Otávio Magalhães", contaCorrente2);
		
		ContaCorrente contaCorrente3 = new ContaCorrente(7696.00, "88452-1");
		Pessoa pessoa3 = new Pessoa("Maria Green", contaCorrente3);
		
		ContaCorrente contaCorrente4 = new ContaCorrente(412.13, "15935-7");
		Pessoa pessoa4 = new Pessoa("Stephan Pereira", contaCorrente4);
		
		adicionarPessoa(pessoa);
		adicionarPessoa(pessoa2);
		adicionarPessoa(pessoa3);
		adicionarPessoa(pessoa4);
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
