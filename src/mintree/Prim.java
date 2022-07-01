package mintree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import utils.AttrVertex;
import utils.Triggers;
import utils.fila.FibonacciHeap;

public class Prim {

	/*
	 * Para o algoritmo de Prim realizamos duas formas de implementação.
	 * 
	 * 1ª: Utilizando o PriorityQueue
	 * 	
	 * 
	 * */
	
	public static List<Edge> exec(Graph graph, Vertex vertexInit) {
		if(graph.isDirected() || !graph.isGraphConnected() || !graph.isWeighted())
			throw new RuntimeException("O grafo informado deve ser não dirigido, ponderado e conexo!");
		
		List<Edge> caminhoMinimo = new ArrayList<>();
		boolean include[] = new boolean[graph.maxVertices()];
		
		
		for(int i = 0; i < graph.numberOfVertices(); i++) {
			graph.vertexAt(i).setData(new AttrVertex(graph.vertexAt(i)));
			include[ graph.vertexAt(i).index() ] = true;
		}
		
		((AttrVertex) vertexInit.getData()).key = 0;
		
		FibonacciHeap<Vertex> Q = new FibonacciHeap<Vertex>(graph.vertices());
		
		while(!Q.isEmpty()) {
			Vertex u = Q.extractMin();
			include[u.index()] = false;
			
			for(Edge edge: graph.edgesIncidentFrom(u)) {
				Vertex v = edge.v();

				if(include[v.index()] && edge.weight() < ((AttrVertex) v.getData()).key) {
					((AttrVertex) v.getData()).pi = u;
					((AttrVertex) v.getData()).key = edge.weight();
					Q.resortElement(v);
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
	
	public static List<Edge> exec(Graph graph, Vertex vertexInit, Triggers trigger) {
		if(graph.isDirected() || !graph.isGraphConnected() || !graph.isWeighted())
			throw new RuntimeException("O grafo informado deve ser não dirigido, ponderado e conexo!");
		
		List<Edge> caminhoMinimo = new ArrayList<>();
		boolean include[] = new boolean[graph.maxVertices()];
		
		for(int i = 0; i < graph.numberOfVertices(); i++) {
			graph.vertexAt(i).setData(new AttrVertex(graph.vertexAt(i)));
			include[ graph.vertexAt(i).index() ] = true;
		}
		
		((AttrVertex) vertexInit.getData()).key = 0;
		
		FibonacciHeap<Vertex> Q = new FibonacciHeap<>(graph.vertices());
		
		trigger.onChange("fila carregada", Q);
		
		while(!Q.isEmpty()) {
			//log(v)
			Vertex u = Q.extractMin();
			include[u.index()] = false;
			
			for(Edge edge: graph.edgesIncidentFrom(u)) {
				Vertex v = edge.v();

				if(include[v.index()] && edge.weight() < ((AttrVertex) v.getData()).key) {
					((AttrVertex) v.getData()).pi = u;
					((AttrVertex) v.getData()).key = edge.weight();
					Q.resortElement(v);
				}
			}		
			trigger.onChange("vertice processada", u, Q);		
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
		
		trigger.onChange("terminou", caminhoMinimo);
		
		return caminhoMinimo;
	}
	
}
