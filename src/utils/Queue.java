package utils;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> extends ArrayList<T>{
	
	public Queue(Iterable<T> d){
		for(T t: d)
			add(t);
		
		sort(null);
	}
	
	public T extractMin() {
		sort(null);
		return remove(0);
	}
}
