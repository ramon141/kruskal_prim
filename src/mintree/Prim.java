package mintree;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import utils.AttrVertex;
import utils.Triggers;
import utils.Queue;

public class Prim {

	public static List<Edge> exec(Graph graph, Vertex vertexInit) {
		return Prim.exec(graph, vertexInit, new Triggers(false));
	}
	
	public static List<Edge> exec(Graph graph, Vertex vertexInit, Triggers trigger) {
		if(graph.isDirected() || !graph.isGraphConnected() || !graph.isWeighted())
			throw new RuntimeException("O grafo informado deve ser não dirigido, ponderado e conexo!");
		

		List<Edge> caminhoMinimo = new ArrayList<>();
		
		for(Vertex vertex: graph.vertices()) {
			vertex.setData(new AttrVertex(vertex));
		}
		
		((AttrVertex) vertexInit.getData()).key = 0;
		
		trigger.onChange(graph, "iniciando");
		
		Queue<Vertex> Q = new Queue<Vertex>(graph.vertices());

		while(!Q.isEmpty()) {
			Vertex u = Q.extractMin();
			
			for(Edge edge: graph.edgesIncidentFrom(u)) {
				Vertex v = edge.v();
				
				if(Q.contains(v) && edge.weight() < ((AttrVertex) v.getData()).key) {
					((AttrVertex) v.getData()).pi = u;
					((AttrVertex) v.getData()).key = edge.weight();
					trigger.onChange(u, "mudou caminho");
					
				}
			}
		}
		
		//Este trecho nao faz de fato parte do algoritmo, ele serve para manter um formato padrão
		//das funções de árvores geradoras mínimas
		//Percorrer os vertices e obtem seus pi
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
