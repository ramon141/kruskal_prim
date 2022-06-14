package utils;

public class No<T>{

	private T value;
	private No<T> right;
	private No<T> left;
	
	public No(T value) {
		setValue(value);
		this.right = null;
		this.left = null;
	}

	public No<T> getRight() {
		return right;
	}

	public void setRight(No<T> right) {
		this.right = right;
	}

	public No<T> getLeft() {
		return left;
	}

	public void setLeft(No<T> left) {
		this.left = left;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

//	@Override
//	public int compareTo(T obj) {
//		
//		
//		if(obj instanceof graph.Vertex)
//			return ((graph.Vertex) this.value).compareTo((graph.Vertex) obj);
//		else if(obj instanceof graph.Edge)
//			return ((graph.Edge) this.value).compareTo((graph.Edge) obj);
//	}
	
	
}