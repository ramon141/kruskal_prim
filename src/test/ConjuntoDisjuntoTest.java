package test;

import graph.AdjacencyListGraph;
import graph.Graph;
import mintree.Kruskal;
import utils.set.*;

public class ConjuntoDisjuntoTest {

	public static void main(String[] args) {
		Graph g = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
		System.out.println(g);
		
		System.out.println(Kruskal.exec(g));
		
		
		//System.out.println(cd);
		
	}
	
}
