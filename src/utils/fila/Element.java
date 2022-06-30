package utils.fila;

public class Element<T extends Comparable<T>> implements Comparable<T> {

	public int quantSons = 0;
	public boolean isMarked = false;
	
	public Element<T> next;
	public Element<T> prev;
	
	public Element<T> parent;
	public Element<T> child;
	
	private T value;

	public Element(T value) {
		setValue(value);
		this.next = this.prev = this;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int compareTo(T o) {
		return this.value.compareTo(o);
	}
	
	
}
