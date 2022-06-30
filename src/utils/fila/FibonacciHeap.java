package utils.fila;

import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap<T extends Comparable<T>> {

	//Um ponteiro para o menor elemento 
	private Element<T> minElement;
	
	private int size = 0;
	
	public FibonacciHeap() {}
	
	public FibonacciHeap(Iterable<T> iterable) {
		for(T value : iterable)
			add(value);
	}
	
	public Element<T> add(T value){
		Element<T> element = new Element<>(value);
		
		minElement = mergeLists(minElement, element);
		
		size++;
		
		return element;
	}
	
	private static <T extends Comparable<T>> Element<T> mergeLists(Element<T> ele1, Element<T> ele2){
		
		if(ele1 == null && ele2 == null)
			return null;
		
		if(ele1 != null && ele2 == null)
			return ele1;
		
		if(ele1 == null && ele2 != null)
			return ele2;
		
		
		Element<T> nextOne = ele1.next;
		ele1.next = ele2.next;
		ele1.next.prev = ele1;
        ele2.next = nextOne;
        ele2.next.prev = ele2;
		
		
        int cmpTo = ele1.compareTo(ele2.getValue());
        
        if(cmpTo < 0)
        	return ele1;
        else
        	return ele2;
	}
	
	public Element<T> min() {
        if (isEmpty())
            throw new RuntimeException("O heap está vazio");
        return minElement;
    }
	
	public boolean isEmpty() {
        return minElement == null;
    }
	
	public int size() {
        return this.size;
    }
	
	
	public static <T extends Comparable<T>> FibonacciHeap<T> merge(FibonacciHeap<T> ele1, FibonacciHeap<T> ele2){
		
		FibonacciHeap<T> result = new FibonacciHeap<>();
		
		result.minElement = mergeLists(ele1.minElement, ele2.minElement);
		
		result.size = ele1.size + ele2.size;
		
		ele1.minElement = null;
		ele2.minElement = null;
		
		return result;
	}
	
	/*Complicado demais fazer isso*/
	public T extractMin(){
		if(isEmpty())
			throw new RuntimeException("O heap está vazio");
		
		//Atualiza a quantidade de elementos
		--size;
		
		//Guarda o elemento que será removido
		Element<T> minElementLocal = this.minElement;
		
		//Se houver somente 1 elemento
		if(this.minElement.next == this.minElement) {
			this.minElement = null; //Entao "limpa" a fila
		
		} else {
			this.minElement.prev.next = this.minElement.next;
			this.minElement.next.prev = this.minElement.prev;
			this.minElement = this.minElement.next;
		}
		
		if(minElementLocal.child != null) {
			Element<?> child = minElementLocal.child;
			
			do {
				child.parent = null;
				child = child.next;
			} while(child != minElementLocal.child);
		}
		
		this.minElement = mergeLists(this.minElement, minElementLocal.child);
		
		if (this.minElement == null) return minElementLocal.getValue();
		
		List<Element<T>> treeTable = new ArrayList<>();
		List<Element<T>> toVisit = new ArrayList<>();
		
		for (Element<T> curr = this.minElement; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.next)
            toVisit.add(curr);
		
		for (Element<T> curr: toVisit) {
			while(true) {
				while (curr.quantSons >= treeTable.size())
                    treeTable.add(null);

				if (treeTable.get(curr.quantSons) == null) {
                    treeTable.set(curr.quantSons, curr);
                    break;
                }
				
				Element<T> other = treeTable.get(curr.quantSons);
                treeTable.set(curr.quantSons, null);
                
                Element<T> min = (other.compareTo(curr.getValue()) < 0 )? other : curr;
                Element<T> max = (other.compareTo(curr.getValue()) < 0 )? curr  : other;
                
                max.next.prev = max.prev;
                max.prev.next = max.next;
				
                max.next = max.prev = max;
                min.child = mergeLists(min.child, max);
                
                max.parent = min;
                
                max.isMarked = false;
                
                ++min.quantSons;
                
                curr = min;
			}
			
			if(curr.compareTo(this.minElement.getValue()) <= 0)
				this.minElement = curr;
		}
		
		
		return minElementLocal.getValue();
	}
	
	
	private void contains(T key, Element<T> c) {
		if (found != null || c == null)
			return;
	    else {
	    	Element<T> temp = c;
	    	do {
	    		if ( key.compareTo(temp.getValue()) == 0 )
		          found = temp;
		        else {
		          Element<T> k = temp.child;
		          contains(key, k);
		          temp = temp.next;
		        }
		    } while (temp != c && found == null);
	    }
	}
	
	
	Element<T> found;
	public boolean contains(T k) {
		found = null;
		contains(k, this.minElement);
		return found != null;
	}
	
	public Element<T> getElmentByValue(T k) {
		found = null;
		contains(k, this.minElement);
		return found;
	}
	
	public void resortElement(T valueElement) {
		Element<T> entry = getElmentByValue(valueElement);
		
        if (entry.parent != null && entry.compareTo( entry.parent.getValue() ) <= 0)
            cutNode(entry);

        if (entry.compareTo( this.minElement.getValue() ) <= 0)
            this.minElement = entry;
    }
	
	private void cutNode(Element<T> entry) {
        entry.isMarked = false;

        if (entry.parent == null) return;

        if (entry.next != entry) {
            entry.next.prev = entry.prev;
            entry.prev.next = entry.next;
        }

        if (entry.parent.child == entry) {
            if (entry.next != entry) {
                entry.parent.child = entry.next;
            }
            else {
                entry.parent.child = null;
            }
        }

        --entry.parent.quantSons;

        entry.prev = entry.next = entry;
        minElement = mergeLists(minElement, entry);

        if (entry.parent.isMarked)
            cutNode(entry.parent);
        else
            entry.parent.isMarked = true;

        entry.parent = null;
    }
	
	
	
	private String toString(Element<T> c) {
		StringBuilder ret = new StringBuilder();
		
	    if (c == null) {
	      return ret.toString();
	    } else {
	      Element<T> temp = c;
	      do {
	        ret.append( temp.getValue() + ", " );
	        Element<T> k = temp.child;
	        ret.append(toString(k));
	        temp = temp.next;
	      } while (temp != c);
	    }
	    
	    return ret.toString();
	  }
	
	
	@Override
	public String toString() {
		return "(" + toString(this.minElement) + ")";
	}
	
}
