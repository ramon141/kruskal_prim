package mintree;

import utils.set.ConjuntoDisjunto;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Kruskal {
	
	public static List<Edge> exec(Graph graph) {
		List<Edge> A = new ArrayList<>();
		ConjuntoDisjunto<Vertex> cd = new ConjuntoDisjunto<>();
		
		for(Vertex vertex: graph.vertices()) {
			cd.makeSet(vertex);
		}
		
		List<Edge> edges = new ArrayList<Edge>();
		graph.edges().forEach(edges::add);
		edges.sort(null);

		for (Edge edge: edges) {
			if(! cd.findSet(edge.u()).getRepresentative().equals(cd.findSet(edge.v()).getRepresentative())) {
				A.add(edge);
				cd.union(edge.u(), edge.v());
			}
		}
		
		return A;
	}
	
	

}
