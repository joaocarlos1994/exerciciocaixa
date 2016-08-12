package br.com.hyperclass2.singleton;

import br.com.hyperclass2.Caixa;

public class CaixaSingleton {
	
	private static Caixa caixa = null;
	
	public static Caixa getCaixa(){
		if(caixa == null){
			caixa = new Caixa();
			return caixa;
		}
		return caixa;
	}

}
