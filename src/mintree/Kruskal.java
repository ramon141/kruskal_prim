package mintree;

import utils.Triggers;
import utils.set.ConjuntoDisjunto;
import utils.tree.Tree;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Kruskal {
	
	public static List<Edge> exec(Graph graph) {
		return exec(graph, new Triggers(false));
	}
	
	/**
	 * @param graph Uma instância de um grafo que já possui vértices e arestas definidas.
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
		
		ConjuntoDisjunto<Vertex> cd = new ConjuntoDisjunto<>();
		
		List<Edge> A = new ArrayList<>();//A = Ø 
		
		for(Vertex vertex: graph.vertices()) { //for each vertex v ∈ G.V
			cd.makeSet(vertex); //MAKE-SET(v)
			trigger.onChange(cd, "conjunto disjunto etapa");
		}
//		trigger.onChange(cd, "conjunto disjunto feito");
		
		//sort the edges of G.E into nondecreasing order by weight w
		Tree<Edge> edges = new Tree<>(graph.edges());

		for (Edge edge: edges.toList()) { //for each edge(u, v) ∈ G.E
			
			//FIND-SET(u) != FIND-SET(v)
			if(! cd.findSet(edge.u()).getRepresentative().equals(cd.findSet(edge.v()).getRepresentative())) {
				A.add(edge); //A = A U {(u,v)}
				cd.union(edge.u(), edge.v()); //UNION(u,v)
				trigger.onChange(edge, "encontrou uma ligacao");
			
			} else trigger.onChange(edge, "nao encontrou uma ligacao");
		}
		
		trigger.onChange(A, "terminou");
		return A;//return A
	}
	
	

}
