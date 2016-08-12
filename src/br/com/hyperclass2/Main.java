package br.com.hyperclass2;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import br.com.hyperclass2.singleton.CaixaSingleton;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		boolean iniciarCaixa = true;
		Scanner entrada = new Scanner(System.in);
		
		Caixa caixa = CaixaSingleton.getCaixa();
		
		System.out.println("Digite o n�mero da conta: ");
		String numeroConta = entrada.next();
		
		
		while(iniciarCaixa = true && caixa.validarLogin(numeroConta)){
			
			
			System.out.println("1 - Saldo");
			System.out.println("2 - Saque");
			System.out.println("3 - D�posito");
			System.out.println("4 - Transf�rencia");
			System.out.println("5 - Extrato");
			System.out.println("6 - Sair");
			
			int opcoes = entrada.nextInt();
			
			switch (opcoes) {
			case 1:
				System.out.print("\n");
				System.out.println("Saldo: " + caixa.saldo());
				System.out.print("\n");
				break;
			case 2:
				
				System.out.print("\n");
				Map<Valor, Collection<Cedula>> cedulasCaixa = caixa.getCedulasCaixa();
				for (Valor valor : cedulasCaixa.keySet()) {
					System.out.println("Cedula: " + valor + " Quantidade: " + cedulasCaixa.get(valor).size());
				}
				
				System.out.print("\n");
				
				System.out.println("Digite o valor do saque: ");
				double valorSaque = entrada.nextDouble();
				
				System.out.print("\n");
				
				Map<Valor, Collection<Cedula>> notasSaque = caixa.saque(valorSaque);
				for(Valor valor : notasSaque.keySet()){
					System.out.println("Saque - Notas: " + valor + " Quantidade: " + notasSaque.get(valor).size());
				}
				break;
			case 3:
				System.out.println("Digite o n�mero da conta de deposito");
				String numeroContaDeposito = entrada.next();
				
				System.out.println("Digite o valor do dep�sito");
				double valorDeposito = entrada.nextDouble();
				
				caixa.deposito(valorDeposito, numeroContaDeposito);
				break;
			case 4:
				System.out.println("Digite o n�mero da conta de Transf�rencia");
				String numeroContaTransferencia = entrada.next();
				
				System.out.println("Digite o valor do dep�sito");
				double valorTransferencia = entrada.nextDouble();
				caixa.transferencia(valorTransferencia, numeroContaTransferencia);
				break;
			case 5:
				Collection<EventoTrasacional> listaEventos = caixa.extrato();
				for (EventoTrasacional eventoTrasacional : listaEventos) {
					System.out.println("Evento: " + eventoTrasacional.getTypeEvent() + " Valor: " + eventoTrasacional.getValores());
				}
				break;
			case 6:
				iniciarCaixa = false;
				caixa = null;
				main(args);
				break;	
			default:
				System.out.println("Op��es inv�lida");
				break;
			}
			
		}		
		
	}

}
