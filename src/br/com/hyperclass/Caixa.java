package br.com.hyperclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caixa {
	
	private OperacoesContaBancaria operacoesBancarias;
	private Pessoa pessoa;
	private final Map<String, Pessoa> listaPessoa;
	private final Map<String, Cedula> cedulasCaixa;
	private Map<String, Cedula> cedulasSaque;

	public Caixa(OperacoesContaBancaria operacoesBancarias, Pessoa pessoa) {
		super();
		this.operacoesBancarias = operacoesBancarias;
		this.pessoa = pessoa;
		this.cedulasCaixa = new HashMap<>();
		this.listaPessoa = new HashMap<String, Pessoa>();
		inicializarNotasCaixa();
		inicializarPessoas();
		
	}
	
	public Caixa(){
		this.cedulasCaixa = new HashMap<>();
		this.listaPessoa = new HashMap<String, Pessoa>();
		inicializarNotasCaixa();
		inicializarPessoas();
	}

	public OperacoesContaBancaria getOperacoesBancarias() {
		return operacoesBancarias;
	}

	public Map<String, Cedula> getCedulasCaixa() {
		return cedulasCaixa;
	}
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	private double totalCaixa(final Map<String, Cedula> cedulasCaixa){
		
		double total = 0;
		
		for(Cedula cedula : cedulasCaixa.values()){
			total += cedula.getValor() * cedula.getQuantidade();
		}
		
		return total;
	}
	
	public Map<String, Cedula> saque(final double valor){
		cedulasSaque = new HashMap<String, Cedula>();
		
		if(totalCaixa(getCedulasCaixa()) > valor){
			notas100(valor);
			operacoesBancarias.saque(valor);
		}
		
		return cedulasSaque;
	}
	
	public double saldo(){
		return operacoesBancarias.saldo();
	}
	
	public List<Extrato> geExtratos(){
		return operacoesBancarias.extrato();
	}
	
	private Cedula getCedula(String key){
		Cedula cedula = cedulasCaixa.get(key);
		return cedula;
	}
	
	private void adicionarNotaCaixa(String key, Cedula cedula){
		cedulasCaixa.put(key, cedula);
	}
	
	private void adcionarPessoa(Pessoa pessoa){
		listaPessoa.put(pessoa.operacoesBancarias().numeroConta(), pessoa);
	}
	
	private void removerNotasCaixa(String key){
		cedulasCaixa.remove(key);
	}
	
	public Pessoa retornaPessoaNumeroConta(String numeroConta){
		Pessoa pessoa = listaPessoa.get(numeroConta);
		return pessoa;
	}
	
	private void inicializarNotasCaixa(){
		Cedula cedula10 = new Cedula(10, 10);
		Cedula cedula20 = new Cedula(20, 20);
		Cedula cedula50 = new Cedula(50, 4);
		Cedula cedula100 = new Cedula(100, 3);
		
		adicionarNotaCaixa("10", cedula10);
		adicionarNotaCaixa("20", cedula20);
		adicionarNotaCaixa("50", cedula50);
		adicionarNotaCaixa("100", cedula100);
		
	}
	
	private void inicializarPessoas(){
		ContaBancaria contaBancaria = new ContaBancaria("54125-9", 10854.78);
		Pessoa pessoa = new Pessoa("João da Silva", contaBancaria);
		
		ContaBancaria contaBancaria2 = new ContaBancaria("25214-8", 1050.99);
		Pessoa pessoa2 = new Pessoa("Pedro Otávio Magalhãesa", contaBancaria2);
		
		ContaBancaria contaBancaria3 = new ContaBancaria("88452-1", 7696.00);
		Pessoa pessoa3 = new Pessoa("Maria Green", contaBancaria3);
		
		ContaBancaria contaBancaria4 = new ContaBancaria("15935-7", 412.13);
		Pessoa pessoa4 = new Pessoa("Stephan Pereira", contaBancaria4);
		
		
		adcionarPessoa(pessoa);
		adcionarPessoa(pessoa2);
		adcionarPessoa(pessoa3);
		adcionarPessoa(pessoa4);
		
	}
	
	private void notas10(final double valor){
		if((valor >= 10 && valor < 20) && cedulasCaixa.containsKey("10")){
			int totalNotas = (int) valor / 10;
			double restoDivisao = valor % 10;
			cedulasSaque.put("10", new Cedula(10, totalNotas));
			cedulasCaixa.put("10", new Cedula(10, getCedula("10").getQuantidade() - totalNotas));
		}
	}
	
	private void notas20(final double valor){
		
		double restoDivisao = valor;
		Cedula cedula = getCedula("20");
		
		if((valor >= 20 && valor < 50) && cedulasCaixa.containsKey("20") || (!cedulasCaixa.containsKey("50") && cedulasCaixa.containsKey("20"))){
			int totalNotas = (int) valor / 20;
			int totalNotasCaixa = cedula.getQuantidade();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 20;
				cedulasCaixa.put("20", new Cedula(20, getCedula("20").getQuantidade() - totalNotas));
				cedulasSaque.put("20", new Cedula(50, totalNotas));
				
			} else {
				
				restoDivisao = valor  - (totalNotasCaixa * 20);
				cedulasCaixa.remove("20");
				cedulasSaque.put("20", new Cedula(20, totalNotasCaixa));
			}
			
		}
		notas10(restoDivisao);
	}
	
	private void notas50(final double valor){
		
		double restoDivisao = valor;
		Cedula cedula = getCedula("50");
		
		if((valor >= 50 && valor < 100) && cedulasCaixa.containsKey("50") || (!cedulasCaixa.containsKey("100") && cedulasCaixa.containsKey("50"))){
			int totalNotas = (int) valor / 50;
			int totalNotasCaixa = cedula.getQuantidade();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 50;
				cedulasCaixa.put("20", new Cedula(100, getCedula("50").getQuantidade() - totalNotas));
				cedulasSaque.put("50", new Cedula(50, totalNotas));
				
			} else {
				
				restoDivisao = valor - (totalNotasCaixa * 50);
				cedulasCaixa.remove("50");
				cedulasSaque.put("50", new Cedula(50, totalNotasCaixa));
			}
		}
		notas20(restoDivisao);
	}
	
	private void notas100(final double valor){
		
		double restoDivisao = valor;
		Cedula cedula = getCedula("100");
		
		if((valor >= 100) && cedulasCaixa.containsKey("100")){
			int totalNotas = (int) valor / 100;
			int totalNotasCaixa = cedula.getQuantidade();
			
			if(totalNotasCaixa > totalNotas){
				
				restoDivisao = valor % 100;
				cedulasCaixa.put("100", new Cedula(100, getCedula("100").getQuantidade() - totalNotas));
				cedulasSaque.put("100", new Cedula(100, totalNotas));
				
			} else {
				
				restoDivisao = valor - totalNotasCaixa * 100;
				removerNotasCaixa("100");
				cedulasSaque.put("100", new Cedula(100, totalNotasCaixa));
			}
			
		}
		notas50(restoDivisao);
	}

}
