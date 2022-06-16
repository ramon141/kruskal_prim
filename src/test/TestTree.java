package test;

import utils.tree.Tree;

public class TestTree {

	
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>();
		
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		
		System.out.println(tree);
	}
}
