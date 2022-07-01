package utils.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class DisjointSet{

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

		if (conjuntos[xroot].rank < conjuntos[yroot].rank)
			conjuntos[xroot].parent = yroot;
		else if (conjuntos[xroot].rank > conjuntos[yroot].rank)
			conjuntos[yroot].parent = xroot;
		else {
			conjuntos[yroot].parent = xroot;
			conjuntos[xroot].rank++;
		}
	}
}
