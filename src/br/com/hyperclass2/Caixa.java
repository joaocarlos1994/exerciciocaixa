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
	private Map<Valor, Collection<Cedula>> cedulasSaque;
	private final Map<String, Pessoa> listaContaPessoa;
	private OperacoesContaCorrente operacoesContaCorrente;
	private Pessoa pessoa;
	
	public Caixa(Pessoa pessoa) {
		super();
		this.cedulasCaixa = new HashMap<Valor, Collection<Cedula>>();
		this.listaContaPessoa = new HashMap<String, Pessoa>();
		this.operacoesContaCorrente = pessoa.getOperacoesBancarias();
		this.pessoa = pessoa;
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
	
	public Map<Valor, Collection<Cedula>> saque(double valor){
		if(valor > 0 && valorTotalCaixa() > valor){
			operacoesContaCorrente.saque(valor);
			cedulasSaque = new HashMap<Valor, Collection<Cedula>>();
			notas100(valor);
		}
		return cedulasSaque;
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
	
	public Collection<EventoTrasacional> extrato(){
		return operacoesContaCorrente.extrato();
	}
	
	
	public boolean validarLogin(final String numeroConta){
		Pessoa pessoa = retornaPessoaNumeroConta(numeroConta);
		if (pessoa != null){
			setPessoa(pessoa);
			setOperacoesContaCorrente(pessoa.getOperacoesBancarias());
			return true;
		}
		throw new IllegalAccessError();
	}
	
	/**
	 * Retorna um objeto do tipo pessoa, atráves do número da conta.
	 * */
	private Pessoa retornaPessoaNumeroConta(String numeroConta){
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
	
	private Collection<Cedula> removerCedulasCaixa(final Valor valor, int quantidade){
		ArrayList<Cedula> cedula = (ArrayList<Cedula>) cedulasCaixa.get(valor);
		for (int i = 0; i < quantidade; i++) {
			cedula.remove(i);
		}
		return cedula;
	}
	
	private Collection<Cedula> adicionarNotasSaque(final Valor valor, final int quantidade){
		
		Collection<Cedula> listCedulaSaque = new ArrayList<Cedula>();
		
		for (int i = 0; i < quantidade; i++) {
			Cedula cedula = new Cedula(valor);
			listCedulaSaque.add(cedula);
		}
		return listCedulaSaque;
	}
	
	private double valorTotalCaixa(){
		
		double totalCaixa = 0;
		
		Map<Valor, Collection<Cedula>> listaNotasCaixa = cedulasCaixa;
		for (Valor key :  listaNotasCaixa.keySet()) {
			totalCaixa += listaNotasCaixa.get(key).size() * key.getValue();
		}
		return totalCaixa;
	}
	
	private void notas10(final double valor){
		
		double restoDivisao = valor;
		Collection<Cedula> cedula = cedulasCaixa.get(Valor.Dez);
		
		if((valor >= 10 && valor < 20) && cedulasCaixa.containsKey(Valor.Dez) || (!cedulasCaixa.containsKey(Valor.Vinte) && cedulasCaixa.containsKey(Valor.Dez))){
			int totalNotas = (int) valor / 10;
			int totalNotasCaixa = cedula.size();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 20;
				cedulasCaixa.put(Valor.Dez, removerCedulasCaixa(Valor.Dez, totalNotas));
				cedulasSaque.put(Valor.Dez, adicionarNotasSaque(Valor.Dez, totalNotas));
				
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	private void notas20(final double valor){
		
		double restoDivisao = valor;
		Collection<Cedula> cedula = cedulasCaixa.get(Valor.Vinte);
		
		if((valor >= 20 && valor < 50) && cedulasCaixa.containsKey(Valor.Vinte) || (!cedulasCaixa.containsKey(Valor.Cinquenta) && cedulasCaixa.containsKey(Valor.Vinte))){
			int totalNotas = (int) valor / 20;
			int totalNotasCaixa = cedula.size();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 20;
				cedulasCaixa.put(Valor.Vinte, removerCedulasCaixa(Valor.Vinte, totalNotas));
				cedulasSaque.put(Valor.Vinte, adicionarNotasSaque(Valor.Vinte, totalNotas));
				
			} else {
				
				restoDivisao = valor - (totalNotasCaixa * 20);
				cedulasCaixa.remove(Valor.Vinte);
				cedulasSaque.put(Valor.Vinte, adicionarNotasSaque(Valor.Vinte, totalNotasCaixa));
			}
		}
		notas10(restoDivisao);
	}
	
	private void notas50(final double valor){
		
		double restoDivisao = valor;
		Collection<Cedula> cedula = cedulasCaixa.get(Valor.Cinquenta);
		
		if((valor >= 50 && valor < 100) && cedulasCaixa.containsKey(Valor.Cinquenta) || (!cedulasCaixa.containsKey(Valor.Cem) && cedulasCaixa.containsKey(Valor.Cinquenta))){
			int totalNotas = (int) valor / 50;
			int totalNotasCaixa = cedula.size();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 50;
				cedulasCaixa.put(Valor.Cinquenta, removerCedulasCaixa(Valor.Cinquenta, totalNotas));
				cedulasSaque.put(Valor.Cinquenta, adicionarNotasSaque(Valor.Cinquenta, totalNotas));
				
			} else {
				
				restoDivisao = valor - (totalNotasCaixa * 50);
				cedulasCaixa.remove(Valor.Cinquenta);
				cedulasSaque.put(Valor.Cinquenta, adicionarNotasSaque(Valor.Cinquenta, totalNotasCaixa));
			}
		}
		notas20(restoDivisao);
	}
	
	private void notas100(final double valor){
		
		double restoDivisao = valor;
		Collection<Cedula> cedula = cedulasCaixa.get(Valor.Cem);
		
		if((valor >= 100) && cedulasCaixa.containsKey(Valor.Cem)){
			int totalNotas = (int) valor / 100;
			int totalNotasCaixa = cedula.size();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 100;
				cedulasCaixa.put(Valor.Cem, removerCedulasCaixa(Valor.Cem, totalNotas));
				cedulasSaque.put(Valor.Cem, adicionarNotasSaque(Valor.Cem, totalNotas));
				
			} else {
				
				restoDivisao = valor - totalNotasCaixa * 100;
				cedulasCaixa.remove(Valor.Cem);
				cedulasSaque.put(Valor.Cem, adicionarNotasSaque(Valor.Cem, totalNotasCaixa));
			}
			
		}
		notas50(restoDivisao);
	}
	
	public Map<Valor, Collection<Cedula>> getCedulasCaixa() {
		return cedulasCaixa;
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
	
	public void setOperacoesContaCorrente(OperacoesContaCorrente operacoesContaCorrente) {
		this.operacoesContaCorrente = operacoesContaCorrente;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}
