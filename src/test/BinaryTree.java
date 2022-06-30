package test;

import utils.tree.Node;
import utils.tree.Tree;

public class BinaryTree {

	public static void main(String[] args) {
		
		class Teste implements Comparable<Teste>{
			public Integer value;

			public Teste(Integer g) {
				this.value = g;
			}
			
			@Override
			public int compareTo(Teste o) {
				return this.value.compareTo(o.value);
			}
			
			@Override
			public String toString() {
				return value + "";
			}
		}
		
		Teste t1 = new Teste(1);
		Teste t2 = new Teste(2);
		Teste t3 = new Teste(3);
		Teste t4 = new Teste(4);
		Teste t5 = new Teste(5);
		Teste t6 = new Teste(6);
		
		
		Tree<Teste> numeros = new Tree<>();
		
		
		numeros.add(t1);
		numeros.add(t2);
		numeros.add(t3);
		numeros.add(t4);
		numeros.add(t5);
		numeros.add(t6);
		
//		System.out.println( numeros.extractMin() );
		
//		Node<Teste> d = numeros.getNodeByValue(t2);
//		t2.value = 7;
//		numeros.resortElement(d);
		
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
		
		
		System.out.println(numeros);
//		System.out.println( numeros.printNode( numeros.getRoot() ) );
		
	}
	
}
