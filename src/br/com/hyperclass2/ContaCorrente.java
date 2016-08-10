package br.com.hyperclass2;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaCorrente implements OperacoesContaCorrente {
	
	//Utilizar Enum map
	//Evitar usar String como key no Map
	
	private double saldo;
	private final String numeroConta;
	private final Map<String, List<EventoTrasacional>> eventoTrasacionais;
	
	public ContaCorrente(double saldo, String numeroConta) {
		super();
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.eventoTrasacionais = new HashMap<String, List<EventoTrasacional>>();
	}
	
	public void addEventTransaction(String key, EventoTrasacional eventoTrasacional){
		
		if(eventoTrasacionais.containsKey(key)){
			List<EventoTrasacional> eventoTrasacionaisList = eventoTrasacionais.get(key);
			eventoTrasacionaisList.add(eventoTrasacional);
			eventoTrasacionais.put(key, eventoTrasacionaisList);
		}
	}

	@Override
	public double saldo() {
		// TODO Auto-generated method stub
		return getSaldo();
	}

	/**
	 * @param valor está varáivel se referente ao valor que será subtraido da conta corrente.
	 * */
	@Override
	public void saque(double valor) {
		if(saldo > valor){
			saldo -= valor;
			//Implementar lógica das notas
			addEventTransaction("dinheiroSacado", new DinherioSacado(valor, new Date(), TypeEvent.DinheiroSacado));
		}
		
		throw new IllegalArgumentException();
		
	}
	
	/**
	 * @param valor Este parametro referece ao valor a ser depositado
	 * @param contaCorrente variável que ira receber o valor do depósito.
	 * */
	@Override
	public void deposito(double valor, ContaCorrente contaCorrente) {
		if(valor > 0){
			contaCorrente.saldo += valor;
			//Implementar lógica das notas
			//Arrumar Classes de evento
			addEventTransaction("dinheiroDepositado", new DinheiroDepositado(valor, new Date(), TypeEvent.DinheiroDepositado));
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * @param valor Referente a valor que deseja transferir
	 * @param contaCorrente cliente onde será depositado o valor da transferência
	 * */
	@Override
	public void transferencia(double valor, ContaCorrente contaCorrente) {
		if(saldo > valor && valor > 0){
			saldo -= valor;
			contaCorrente.saldo += valor;
			addEventTransaction("dinheiroTransferido", new DinheiroTransferido(valor, new Date(), TypeEvent.DinheiroTransferido));
		}
		throw new IllegalArgumentException();
	}
	
	public double getSaldo() {
		return saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Map<String, List<EventoTrasacional>> getEventoTrasacionais() {
		return eventoTrasacionais;
	}

	@Override
	public String getNumeroContaCorrente() {
		// TODO Auto-generated method stub
		return getNumeroConta();
	}
	
	
}
