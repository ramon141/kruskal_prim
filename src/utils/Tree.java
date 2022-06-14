package utils;

import graph.Graph;

public class Tree<T extends Comparable> {
	
	private No<T> root;
	
	public Tree() {
		root = null;
	}
	
	public void add(T value) {
		No<T> element = new No<>(value);
		
		if(root == null) {
			this.root = element;
		
		}// else if()
	}

}
