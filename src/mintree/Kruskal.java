package mintree;

import utils.Triggers;
import utils.set.DisjointSet;
import utils.tree.Tree;
import utils.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Kruskal {
	
	/**
	 * @param graph Uma instância de um grafo que já possui vértices e arestas definidas.
	 * 
	 * @return Uma lista que pode ser convertida no formato de árvore. Nesta está contido
	 *         as arestas (instâncias de Edge) que formam o caminnho mínimo.
	 *         
	 * Ao lado de cada trecho de código estará o seu correspondente do livro de Cormem (Figura 23.4)
	 * 
     */
	public static List<Edge> exec(Graph graph) {	
		if(graph.isDirected() || !graph.isGraphConnected() || !graph.isWeighted())
			throw new RuntimeException("O grafo informado deve ser não dirigido, ponderado e conexo!");
		
		List<Edge> result = new ArrayList<>();//A = Ø
		
		DisjointSet cd = new DisjointSet(graph.maxVertices());

		for(Vertex vertex: graph.vertices()) { //for each vertex v ∈ G.V
			cd.makeSet(vertex.index()); //MAKE-SET(v)
		}
		
		//sort the edges of G.E into nondecreasing order by weight w
		List<Edge> sortedEdgeList = graph.edgesWithList();
		sortedEdgeList.sort(null);

		
		for(Edge next_edge: sortedEdgeList){ //for each edge(u, v) ∈ G.E
			
			//FIND-SET(u) != FIND-SET(v)
			if (cd.find(next_edge.u().index()) != cd.find(next_edge.v().index())) {
				
				result.add(next_edge);//A = A U {(u,v)}
				
				cd.Union(next_edge.u().index(), next_edge.v().index()); //UNION(u,v)
			}
		}
		
		return result;
	}
	
	/**
	 * @param graph Uma instância de um grafo que já possui vértices e arestas definidas.
	 * @param trigger Uma instância de Trigger que servirá para ouvir as ações a cada passo.
	 * 
	 * @return Uma lista que pode ser convertida no formato de árvore. Nesta está contido
	 *         as arestas (instâncias de Edge) que formam o caminnho mínimo.
	 *         
	 * Ao lado de cada trecho de código estará o seu correspondente do livro de Cormem (Figura 23.4)
	 * 
     */
	public static List<Edge> exec(Graph graph, Triggers trigger) {
		
		if(graph.isDirected() || !graph.isGraphConnected() || !graph.isWeighted())
			throw new RuntimeException("O grafo informado deve ser não dirigido, ponderado e conexo!");
		
		List<Edge> result = new ArrayList<>();//A = Ø
		
		int V = graph.numberOfVertices();

		DisjointSet cd = new DisjointSet(graph.maxVertices());

		for(Vertex vertex: graph.vertices()) { //for each vertex v ∈ G.V
			cd.makeSet(vertex.index()); //MAKE-SET(v)
			trigger.onChange("conjunto disjunto etapa", cd);
		}
		
		//sort the edges of G.E into nondecreasing order by weight w
		List<Edge> sortedEdgeList = graph.edgesWithList();
		sortedEdgeList.sort(null);

		
		for(Edge next_edge: sortedEdgeList){ //for each edge(u, v) ∈ G.E
			
			//FIND-SET(u) != FIND-SET(v)
			if (cd.find(next_edge.u().index()) != cd.find(next_edge.v().index())) {
				
				result.add(next_edge);//A = A U {(u,v)}
				
				cd.Union(next_edge.u().index(), next_edge.v().index()); //UNION(u,v)
				
				trigger.onChange("nao ligados", next_edge);
			}
		}
		
		trigger.onChange("terminou", result);

		return result;
	}
	
	

}
