package mintree;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import utils.AttrVertex;
import utils.Queue;

public class Prim {

	public static List<Edge> exec(Graph graph, Vertex vertexInit) {
		List<Edge> caminhoMinimo = new ArrayList<>();
		
		for(Vertex vertex: graph.vertices()) {
			vertex.setData(new AttrVertex(vertex));
		}
		
		((AttrVertex) vertexInit.getData()).key = 0;
		
		Queue<Vertex> Q = new Queue<Vertex>(graph.vertices());

		while(!Q.isEmpty()) {
			Vertex u = Q.extractMin();
			
			for(Edge edge: graph.edgesIncidentFrom(u)) {
				Vertex v = edge.v();
				
				if(Q.contains(v) && edge.weight() < ((AttrVertex) v.getData()).key) {
					((AttrVertex) v.getData()).pi = u;
					((AttrVertex) v.getData()).key = edge.weight();
					
				}
			}
		}
		
		//Percorrer os verices e obter seus pi
		for(Vertex v: graph.vertices()) {
			Vertex vertexPredecessor = ((AttrVertex) v.getData()).pi;
			if(vertexPredecessor != null)
				for(Edge edge: graph.edgesIncidentFrom(vertexPredecessor))
					if(edge.v().equals(v))
						caminhoMinimo.add(edge);
		}
		
		return caminhoMinimo;
	}
	
}
