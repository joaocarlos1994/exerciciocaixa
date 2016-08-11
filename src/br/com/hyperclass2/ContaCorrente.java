package br.com.hyperclass2;

import java.util.ArrayList;
import java.util.Collection;

public class ContaCorrente implements OperacoesContaCorrente {
	
	//Utilizar Enum map
	//Evitar usar String como key no Map
	
	private double saldo;
	private final String numeroConta;
	private final Collection<EventoTrasacional> eventoTrasacionais;
	
	public ContaCorrente(double saldo, String numeroConta) {
		super();
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.eventoTrasacionais = new ArrayList<EventoTrasacional>();
		eventoTrasacionais.add(new ValorDisponibilizado(saldo));
	}
	
	public void addEventTransaction(final EventoTrasacional eventoTrasacional){
		eventoTrasacionais.add(eventoTrasacional);
	}

	@Override
	public double saldo() {
		// TODO Auto-generated method stub
		return getSaldo();
	}

	/**
	 * @param valor est� var�ivel se referente ao valor que ser� subtraido da conta corrente.
	 * */
	@Override
	public void saque(double valor) {
		if(saldo > valor){
			saldo -= valor;
			addEventTransaction(new DinherioSacado(valor * (- 1)));
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @param valor Este parametro referece ao valor a ser depositado
	 * @param contaCorrente vari�vel que ira receber o valor do dep�sito.
	 * */
	@Override
	public void deposito(double valor, ContaCorrente contaCorrente) {
		if(valor > 0){
			addEventTransaction(new DinheiroDepositado(valor));
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * @param valor Referente a valor que deseja transferir
	 * @param contaCorrente cliente onde ser� depositado o valor da transfer�ncia
	 * */
	@Override
	public void transferencia(double valor, ContaCorrente contaCorrente) {
		if(saldo > valor && valor > 0){
			addEventTransaction(new DinheiroTransferido(valor * (- 1)));
		}
		throw new IllegalArgumentException();
	}
	
	public double getSaldo() {
		
		double saldoEvents = 0;
		
		for (EventoTrasacional eventoTrasacional : eventoTrasacionais) {
			saldoEvents += eventoTrasacional.getValores();
		}
		
		this.saldo = saldoEvents;
		
		return saldoEvents;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Collection<EventoTrasacional> getEventoTrasacionais() {
		return eventoTrasacionais;
	}

	@Override
	public String getNumeroContaCorrente() {
		// TODO Auto-generated method stub
		return getNumeroConta();
	}
	
	
}
