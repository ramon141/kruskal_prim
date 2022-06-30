package utils.set;

import java.util.Iterator;

public class DisjointSet implements Iterable<Subset>{

	int counter = 0;
	Subset conjuntos[];
	
	int maxElements;
	
	public DisjointSet(int maxElements) {
		this.maxElements = maxElements;
		conjuntos = new Subset[maxElements];
	}
	
	public void makeSet(int i) {
		if(counter >= maxElements)
			throw new RuntimeException("O limite foi extrapolado");
		
		conjuntos[counter] = new Subset();
		conjuntos[counter].parent = i;
		conjuntos[counter].rank = 0;
		counter++;
	}
	
	public int find(int i){
		if (conjuntos[i].parent != i)
			conjuntos[i].parent = find(conjuntos[i].parent);

		return conjuntos[i].parent;
	}
	
	public void Union(int x, int y)
	{
		int xroot = find(x);
		int yroot = find(y);

		if (conjuntos[xroot].rank
			< conjuntos[yroot].rank)
			conjuntos[xroot].parent = yroot;
		else if (conjuntos[xroot].rank
				> conjuntos[yroot].rank)
			conjuntos[yroot].parent = xroot;
		else {
			conjuntos[yroot].parent = xroot;
			conjuntos[xroot].rank++;
		}
	}

	@Override
	public Iterator<Subset> iterator() {
		return new DisjointSetIterable();
	}	
	
	private class DisjointSetIterable implements Iterable<Subset>, Iterator<Subset>{

		private int counterIterable = 0;
		
		@Override
		public boolean hasNext() {
			return counter > counterIterable;
		}

		@Override
		public Subset next() {
			return conjuntos[counterIterable++];
		}

		@Override
		public Iterator<Subset> iterator() {
			return this;
		}
		
	}

	
}
