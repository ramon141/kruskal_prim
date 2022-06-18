package utils;

import java.util.Scanner;

//Esta classe tem o objetivo agir como gatilhos que ocorrerão a cada passo dos algoritmos de
//kruskal e prim

//A cada modificação de uma estrutura (como conjunto disjunto, ou o proprio grafo) deverá chamar
//esta classe, as classes que estenderem ou tiverem uma instância de Triggers implementará as funções
//e recerá as informações dos gatilhos

//Basicamente uma brincadeira com polimorfismo

public class Triggers {

	public boolean waitThread = false;
	private boolean go = false;
	
	/**
	 * @param obj Novo valor da estrutura que foi modificada
	 * @param name Nome da estrutura modificada, será utilizada para identificação
	 * */
	public void onChange(Object obj,  String name) {
		System.out.println(obj + " - " + name);
		if(waitThread) waitThread();
	}
	
	//Força que a operação pare
	private void waitThread() {
		while(!go());
	}
	
	private boolean go() {
		return true;
	}
	
}
