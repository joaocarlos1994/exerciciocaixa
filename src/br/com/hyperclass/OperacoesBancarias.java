package br.com.hyperclass;

import java.util.List;

public interface OperacoesBancarias {
	
	public double saldo();
	public void saque(double valor);
	public void deposito(double valor, ContaBancaria contaBancaria);
	public void transferencia(double valor, ContaBancaria contaBancaria);
	public List<Extrato> extrato();
	public String numeroConta();

}
