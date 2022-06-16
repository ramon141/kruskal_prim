package utils.tree;

public class Tree<T extends Comparable<T>> {

	//Não permite elementos repetidos
	private boolean distinctElements;
	private Node<T> root;

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public boolean isDistinctElements() {
		return distinctElements;
	}

	public void setDistinctElements(boolean distinctElements) {
		this.distinctElements = distinctElements;
	}

	private void addOnLeft(Node<T> node, T value) {
		if(node.getLeft() == null) {
			Node<T> newElement = new Node<>(value);
			node.setLeft(newElement);
		
		} else {
			int cmpTo = value.compareTo(node.getLeft().value);
			
			if(cmpTo > 0)
				addOnRight(node.getLeft(), value);
			else
				addOnLeft(node.getLeft(), value);
			
		}
	}
	
	private void addOnRight(Node<T> node, T value) {
		if(node.getRight() == null) {
			Node<T> newElement = new Node<>(value);
			node.setRight(newElement);
		
		} else {
			int cmpTo = value.compareTo(node.getRight().value);
			
			if(cmpTo > 0)
				addOnRight(node.getRight(), value);
			else
				addOnLeft(node.getRight(), value);
			
		}
	}
	
	public void add(Tree tree, T value) {
		if(tree.root == null) {
			Node<T> newElement = new Node<>(value);
			root = newElement;
		
		} else {
			int cmpTo = value.compareTo((T) tree.root.value);
			
			if(cmpTo > 0) addOnRight(tree.root, value);
			else  addOnLeft(tree.root, value);
		}
	}
	
	public void add(T value) {
		add(this, value);
	}
	
	
	public String printNode(Node<T> node) {
		if(node == null) return "";
		
		StringBuilder str = new StringBuilder();
		
		str.append(node + ", ");
		str.append(printNode(node.getLeft()));
		str.append(printNode(node.getRight()));
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		String str = printNode(this.root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}
	
}
