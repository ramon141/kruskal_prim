package utils.set;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ConjuntoDisjunto<T> extends ArrayList<Conjunto<T>>{
			
	//Cria um conjunto neste conjunto disjunto
	public void makeSet(T value) {
		//Cria o conjunto
		Conjunto<T> conjunto = new Conjunto<>();
		conjunto.add(value);
		
		add(conjunto);
	}
	
	//Retorna o conjunto onde se encontra o parâmetro dado
	public Conjunto<T> findSet(T value) {
		
		//Percorre a coleção de conjunto para encontrar onde o valor se encontra
		for(int i = 0; i < size(); i++) {
			Conjunto<T> set = get(i);
			
			if(set.contains(value)) //Pesquisa em árvore binária, complexidade O(log n)
				return set;
			
		}
		
		return null;
	}
	
	//O primeiro parâmetro (no caso o conjunto x) receberá a junção dos dois conjuntos
	public void union(T value1, T value2) {
		Conjunto<T> x = findSet(value1);
		Conjunto<T> y = findSet(value2);
		
		//Adiciona todos os elementos do conjunto Y ao conjunto X
		for(T obj: y) {
			x.add(obj);
		}
		
		//Remove o y da coleção
		remove(y);
	}
	
	

	@Override
	public String toString() {		
		return super.toString();
	}
	
	
	
}
