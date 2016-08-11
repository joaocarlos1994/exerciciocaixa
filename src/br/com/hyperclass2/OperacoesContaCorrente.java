package br.com.hyperclass2;

import java.util.Collection;

public interface OperacoesContaCorrente {
	
	public double saldo();
	public void saque(double valor);
	public void deposito(double valor, ContaCorrente contaCorrente);
	public void transferencia(double valor, ContaCorrente contaCorrente);
	public Collection<EventoTrasacional> extrato();
	public String getNumeroContaCorrente();

}
