package utils;

import graph.Vertex;

public class AttrVertex implements Comparable<AttrVertex>{

	public Vertex currentVertex;
	public Vertex pi;
	public double key;
	
	
	public AttrVertex(Vertex currentVertex) {
		this.currentVertex = currentVertex;
		pi = null;
		key = Double.POSITIVE_INFINITY;
	}


	@Override
	public int compareTo(AttrVertex o) {
		if(this.key - o.key > 0) return 1;
		if(this.key - o.key < 0) return -1;
		
		return currentVertex.getName().compareTo(o.currentVertex.getName());
	}
	
	@Override
	public String toString() {
		return "π: " + pi + ", key: " + key;
	}
	
}
