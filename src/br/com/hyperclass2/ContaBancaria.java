package br.com.hyperclass2;

import java.util.ArrayList;
import java.util.List;

public class ContaBancaria implements OperacoesBancarias{
	
	private final String numeroConta;
	private double saldo;
	private final List<Extrato> listExtrato;

	public ContaBancaria(String numeroConta, double saldo) {
		super();
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.listExtrato = new ArrayList<>();
	}

	@Override
	public double saldo() {
		// TODO Auto-generated method stub
		return getSaldo();
	}

	@Override
	public void saque(double valor) {
		if(getSaldo() > valor && valor > 0){
			this.saldo -= valor;
			this.listExtrato.add(new Extrato("Saque", "Saque valor: " + valor));
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public void deposito(double valor, ContaBancaria contaBancaria) {
		if(valor > 0){
			contaBancaria.saldo += valor;
			this.listExtrato.add(new Extrato("Deposito", "Deposito valor: " + valor));
		}
	}

	@Override
	public void transferencia(double valor, ContaBancaria contaBancaria) {
		if(valor > 0 && this.saldo > valor){
			contaBancaria.saldo += valor;
			this.saldo -= valor;
			this.listExtrato.add(new Extrato("Transfer�ncia", "Transfer�ncia valor: " + valor + "para conta: " + contaBancaria.numeroConta));
		}
		
	}

	@Override
	public List<Extrato> extrato() {
		return listExtrato;
	}
	
	@Override
	public String numeroConta() {
		// TODO Auto-generated method stub
		return getNumeroConta();
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

}
