package utils.set;

import java.util.ArrayList;

public class ConjuntoDisjunto<T> {
	
	//Coleção de conjuntos
	private ArrayList<Conjunto<T>> collection = new ArrayList<>();
	
	//Cria um conjunto neste conjunto disjunto
	public void makeSet(T value) {
		//Cria o conjunto
		Conjunto<T> conjunto = new Conjunto<>();
		conjunto.add(value);
		
		collection.add(conjunto);
	}
	
	//Retorna o conjunto onde se encontra o parâmetro dado
	public Conjunto<T> findSet(T value) {
		
		//Percorre a coleção de conjunto para encontrar onde o valor se encontra
		for(int i = 0; i < collection.size(); i++) {
			Conjunto<T> set = collection.get(i);
			
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
		collection.remove(y);
	}
	
	public ArrayList<Conjunto<T>> getCollection() {
		return collection;
	}
	
	public int size() {
		return this.getCollection().size();
	}

	@Override
	public String toString() {		
		return collection.toString();
	}
	
	
	
}
