package test;

import graph.Vertex;
import utils.tree.Tree;

public class TestTree {

	
	public static void main(String[] args) {
		Tree<Vertex> tree = new Tree<Vertex>();
		
		
//		for(int i = 0; i < 10; i++)
//			tree.add(i);
//			tree.add(1);
//			tree.add(2);
//			tree.add(3);
		
		tree.add(new Vertex("a", 4));
		tree.add(new Vertex("b", 2));
		tree.add(new Vertex("c", 1));
		tree.add(new Vertex("d", 3));
		
		
		
//		tree.add(4);
//		tree.add(2);
//		tree.add(1);
//		tree.add(3);
//		tree.add(6);
//		tree.add(5);
//		tree.add(7);
		
		System.out.println(tree);
				
//		System.out.println(tree.remove(1));
//		System.out.println(tree.size());
//		tree.remove(2);
//		tree.remove(3);
//		tree.remove(4);
//		tree.remove(5);
//		tree.remove(6);
//		tree.remove(7);
				
		
		
//		System.out.println(tree.contains(1));
//		System.out.println(tree.contains(2));
//		System.out.println(tree.contains(3));
//		System.out.println(tree.contains(4));
//		System.out.println(tree.contains(5));
//		System.out.println(tree.contains(6));
//		System.out.println(tree.contains(7));
//		System.out.println(tree.contains(8));
//		System.out.println(tree.contains(9));
//		System.out.println(tree.contains(10));
//		System.out.println(tree.contains(11));
//		System.out.println(tree.contains(12));
	}
}
