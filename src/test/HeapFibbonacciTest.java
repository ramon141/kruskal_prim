package test;

import utils.fila.FibonacciHeap;

public class HeapFibbonacciTest {

	
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
		
//		FibonacciHeap2<Integer> numeros = new FibonacciHeap2<>();
//		
//		numeros.enqueue(1, 1);
//		numeros.enqueue(2, 2);
//		numeros.enqueue(3, 3);
//		numeros.enqueue(4, 4);
//		numeros.enqueue(5, 5);
		
		
//		System.out.println( numeros.dequeueMin().getValue() );
//		System.out.println( numeros.dequeueMin().getValue() );
//		System.out.println( numeros.dequeueMin().getValue() );
//		System.out.println( numeros.dequeueMin().getValue() );
//		System.out.println( numeros.dequeueMin().getValue() );
		
		
		Teste teste1 = new Teste(1);
		Teste teste2 = new Teste(2);
		Teste teste3 = new Teste(3);
		Teste teste4 = new Teste(4);
		Teste teste5 = new Teste(5);
		Teste teste6 = new Teste(6);
		
		FibonacciHeap<Teste> numeros = new FibonacciHeap<>();
		
		numeros.add(teste1);
		numeros.add(teste2);
		numeros.add(teste3);
		numeros.add(teste4);
		numeros.add(teste5);
		numeros.add(teste6);
		
		
//		System.out.println( numeros.extractMin() );
		
		for(Teste t: numeros) {
			System.out.println(t);
		}
		
//		numeros.clone();
//		
//		System.out.println(numeros);
//		System.out.println(numeros);
//		System.out.println(numeros);
		
		
//		System.out.println( numeros.contains( new Teste(1) ) );
		
//		teste3.value = 6;
////		
		
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
//		System.out.println( numeros.extractMin() );
		
//		System.out.println(numeros);
		
	}
	
}
