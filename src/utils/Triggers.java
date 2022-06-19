package utils;

import javax.swing.JOptionPane;

//Esta classe tem o objetivo agir como gatilhos que ocorrerão a cada passo dos algoritmos de
//kruskal e prim

//A cada modificação de uma estrutura (como conjunto disjunto, ou o proprio grafo) deverá chamar
//esta classe, as classes que estenderem ou tiverem uma instância de Triggers implementará as funções
//e recerá as informações dos gatilhos

//Basicamente uma brincadeira com polimorfismo

public class Triggers {

	private boolean go = false;
	
	public Triggers(boolean waitThread) {
	}
	
	/**
	 * @param obj Novo valor da estrutura que foi modificada
	 * @param name Nome da estrutura modificada, será utilizada para identificação
	 * */
	public void onChange(Object obj,  String name) {
	}
	
	
	public boolean isGo() {
		return go;
	}

	public void setGo(boolean go) {
		this.go = go;
		if(!go) waitThread();
	}

	//Força que a operação pare
	private void waitThread() {
		while(!go) {
			try {
				Thread.sleep(100);
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
	}
	
}
