package utils.set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import graph.Vertex;

public class Conjunto<T> extends TreeSet<T>{
	
	//Primeiro elemento a ser adicionado
	private T representative;
	
	public T getRepresentative() {
		return representative;
	}
	
	public void setRepresentative(T representative) {
		this.representative = representative;
	}
	
	@Override
	public boolean add(T obj) {
		if(representative == null)
			representative = obj;
		
		return super.add(obj);
	}
	
	@Override
	public String toString() {
		return super.toString() + " --> Representative: " + getRepresentative();
	}
	
}
