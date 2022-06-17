package utils.tree;

public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public String printNode(Node<T> node) {
		if(node == null) return "";
		
		StringBuilder str = new StringBuilder();
		
		//Imprime na ordem central, ou seja, na ordem crescente (do menor para o maior elemento)
		str.append(printNode(node.getLeft()));
		str.append(node + ", ");
		str.append(printNode(node.getRight()));
		
		return str.toString();
	}
		
	private Node<T> add(Node<T> root, T value){
		if(root == null) {
			root = new Node<>(value);
		
		} else {
			int cmpTo = value.compareTo(root.value);
			
			if(cmpTo > 0) //Aloca a direita
				root.setRight(add(root.getRight(), value));
			else if(cmpTo < 0) //Aloca a esquerda
				root.setLeft(add(root.getLeft(), value));			
		}
		
		return root;
	}
	
	public void add(T value){
		this.root = add(this.root, value);
	}
	
	public int size() {
		return size(this.root);
	}
	
	private int size(Node<T> node) {
		if(node == null) return 0;
		return size(node.getLeft()) + size(node.getRight()) + 1;
	}
		
	private boolean contains(Node<T> node, T value) {
		if(node == null) return false;
		
		int cmpTo = value.compareTo(node.getValue());
		
		//Se for igual     //Se o elemento estiver no lado direito           //Se o elemento estiver no lado esquerdo
		if((cmpTo == 0) || (cmpTo > 0 && contains(node.getRight(), value)) || (cmpTo < 0 && contains(node.getLeft(), value)) )
			return true;
		
		return false;
	}
	
	public boolean contains(T value) {
		return contains(this.root, value);
	}
	
	private Node<T> remove(Node<T> node, T value) {
		if(node == null) return null;
		int cmpTo = value.compareTo(node.getValue());
		
		if(cmpTo == 0) {
			//Se for no folha
			if(node.getLeft() == null && node.getRight() == null) {
				node = null;
				return node;
			
			} else if(node.getLeft() == null ^ node.getRight() == null) {
				if(node.getRight() != null) return node.getRight();
				else return node.getLeft();
				
			}else {
				
			}
			
			
			return node;
		} else {
			if(cmpTo > 0) //Remove a direita
				node.setRight(remove(node.getRight(), value));
			else //Remove a esquerda
				node.setLeft(remove(node.getLeft(), value));
			
			return node;
		}
	}
	
	public void remove(T value) {
		this.root = remove(this.root, value);
	}
	
	@Override
	public String toString() {
		String str = printNode(this.root);
		return "[" + str.substring(0, str.length() == 0? 0 : str.length() - 2) + "]";
	}
	
}
