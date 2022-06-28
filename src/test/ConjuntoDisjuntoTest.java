package test;

import graph.AdjacencyListGraph;
import graph.Graph;
import mintree.Kruskal;
import mintree.Prim;

public class ConjuntoDisjuntoTest {

	public static void main(String[] args) {
		//Tests realizados:
		// - Não-Dirigido: Ok
		// - Ponderado: Ok
		// - Conexo: Ok
		
		Graph g;
		try {
			g = AdjacencyListGraph.graphFromFile("input/cormen_22.2", false);
			System.out.println(g);
			
			System.out.println(Kruskal.exec(g));
			System.out.println(Prim.exec(g, g.vertexAt(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
