package utils.tree;

public class Node<T> {

	T value;
	
	//Parentes do nó
	Node<T> right;
	Node<T> left;
	Node<T> dad;
	
	public Node(T value) {
		setValue(value);
		this.left = null;
		this.right = null;
		this.dad = null;
	}
	
	public Node(T value, Node<T> dad) {
		setValue(value);
		setDad(dad);
		this.left = null;
		this.right = null;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getDad() {
		return dad;
	}

	public void setDad(Node<T> dad) {
		this.dad = dad;
	}
	
	@Override
	public String toString() {
//		String value = getValue().toString();
//		String ret = "/";
//		for(int i = 0; i < value.length(); i++) ret += "-";
//		ret += "\\\n";
//		ret += "|" + value + "|\n";
//		ret += "\\";
//		for(int i = 0; i < value.length(); i++) ret += "-";
//		ret += "/";
		
		return value.toString();
	}
	
}
