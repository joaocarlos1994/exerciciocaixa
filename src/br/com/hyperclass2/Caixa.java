package br.com.hyperclass2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caixa {
	
	/** 
	 * Poderia haver uma classe abstrata "Disposito" extendo para o Caixa para melhor modelar o aplicação, 
	 * podendo assim crescer no caso de haver outras formas de fazer consulta de saldo e transferência (Internet Banking)
	 * 
	 */
	
	private final Map<String, Collection<Cedula>> cedulasCaixa;
	private final Map<String, Pessoa> listaContaPessoa;
	private OperacoesContaCorrente operacoesContaCorrente;
	
	public Caixa(OperacoesContaCorrente operacoesContaCorrente, Pessoa pessoa) {
		super();
		this.cedulasCaixa = new HashMap<String, Collection<Cedula>>();
		this.listaContaPessoa = new HashMap<String, Pessoa>();
		this.operacoesContaCorrente = operacoesContaCorrente;
		initializeCedulasCaixa();
	}
	
	/**
	 * @param pessoa Recebendo uma pessoa para adicionar na lista do caia, funcionando assim como uma banco de dados.
	 * */
	private void adicionarPessoa(Pessoa pessoa){
		listaContaPessoa.put(operacoesContaCorrente.getNumeroContaCorrente(), pessoa);
	}
	
	/**
	 * @param key este parametro a referente a list de notas
	 * @param cedula Nota a ser adicionado ao caixa
	 * 
	 * Este método recupera a lista de notas que contém no caixa e adicona uma passsada por parametro de acordo com a chave 
	 * */
	private void adicionarCedulasCaixa(String key, Cedula cedula){
		Collection<Cedula> cedulasList = cedulasCaixa.get(key);
		
		if(cedulasList == null){
			cedulasList = new ArrayList<Cedula>();
		}
		
		cedulasList.add(cedula);
		cedulasCaixa.put(key, cedulasList);
	}
	
	public boolean saque(double valor){
		if(valor > 0){
			operacoesContaCorrente.saque(valor);
			return true;
		}
		return false;
	}
	
	public boolean deposito(double valor, String numeroContaCorrente){
		ContaCorrente contaCorrente = null;
		if(valor > 0 && contaCorrente != null){
			operacoesContaCorrente.deposito(valor, contaCorrente);
			return true;
		}
		return false;
	}
	
	public boolean transferencia(double valor, String numeroContaCorrente){
		ContaCorrente contaCorrente = null;
		if((valor > 0) && contaCorrente != null){
			operacoesContaCorrente.transferencia(valor, contaCorrente);
			return true;
		}
		return false;
	}
	
	public Map<String, Collection<Cedula>> getCedulasCaixa() {
		return cedulasCaixa;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initializeCedulasCaixa(){

		
		
		for(int i = 0; i < 3; i++){
			Cedula cedula = new Cedula(Valor.Cem);
			adicionarCedulasCaixa("100", cedula);
		}
		
		for(int i = 0; i < 4; i++){
			Cedula cedula = new Cedula(Valor.Cinquenta);
			adicionarCedulasCaixa("50", cedula);
		}
		
		for(int i = 0; i < 20; i++){
			Cedula cedula = new Cedula(Valor.Vinte);
			adicionarCedulasCaixa("20", cedula);
		}
		
		for(int i = 0; i < 10; i++){
			Cedula cedula = new Cedula(Valor.Dez);
			adicionarCedulasCaixa("10", cedula);
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
