package test;

import java.util.List;

import graph.AdjacencyListGraph;
import graph.Edge;
import graph.Graph;
import mintree.Kruskal;
import mintree.Prim;

public class AlgorithmsTest {

	public static void main(String[] args) {
		//Tests realizados:
		// - Não-Dirigido: Ok
		// - Ponderado: Ok
		// - Conexo: Ok
		
		Graph g;
		try {
			g = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
			
			List<Edge> resultKruskal = Kruskal.exec(g);
			List<Edge> resultPrim = Prim.exec(g, g.vertexAt(0));
			
			resultKruskal.sort(null);
			resultPrim.sort(null);
			
			
//			System.out.println(resultKruskal);
			System.out.println(resultPrim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
