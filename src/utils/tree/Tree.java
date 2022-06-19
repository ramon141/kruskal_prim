package utils.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public Tree() {
		this.root = null;
	}
	
	public Tree(Iterable<T> iterable) {
		for(T ele: iterable)
			add(ele);
	}
	
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
	
	private int bigger(int a, int b) {
		return a > b? a : b;
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
		
		root.setHeight( bigger( height(root.getLeft()),
						        height(root.getRight()) )
				        + 1 );
		
		return balance(root);
	}
	
	public Node<T> balance(Node<T> node){
		if(node == null) return null;
		
		int factorBalancingRoot = factorBalancing(node);
		int factorBalancingLeft = factorBalancing(node.getLeft());
		int factorBalancingRight = factorBalancing(node.getRight());
		
		if(factorBalancingRoot < -1 && factorBalancingRight <= 0)
			node = rotateToLeft(node);
		else if(factorBalancingRoot > 1 && factorBalancingLeft >= 0)
			node = rotateToRight(node);
		else if(factorBalancingRoot > 1 && factorBalancingLeft < 0)
			node = rotateToLeftRight(node);
		else if(factorBalancingRoot < -1 && factorBalancingRight > 0)
			node = rotateToRightLeft(node);
		
		return node;
	}
	
	private Node<T> rotateToRightLeft(Node<T> node) {
		node.setRight( rotateToRight(node.getRight()) );
		return rotateToLeft(node);
	}

	private Node<T> rotateToLeftRight(Node<T> node) {
		node.setLeft( rotateToLeft(node.getLeft()) );
		return rotateToRight(node);
	}

	private Node<T> rotateToRight(Node<T> node) {
		Node<T> auxLeft = node.getLeft();
		Node<T> auxRight = auxLeft.getRight();
		
		auxLeft.setRight(node);
		node.setLeft(auxRight);
		
		node.setHeight( bigger( height(node.getLeft()),
				                height(node.getRight()) )
					    + 1 );
		
		auxLeft.setHeight( bigger( height(auxLeft.getLeft()),
				                   height(auxLeft.getRight()) )
					       + 1 );
		
		return auxLeft;
	}

	private Node<T> rotateToLeft(Node<T> node) {
		
		Node<T> auxRight = node.getRight();
		Node<T> auxLeft= auxRight.getLeft();
		
		auxRight.setLeft(node);
		node.setRight(auxLeft);
		
		node.setHeight( bigger( height(node.getLeft()),
			                    height(node.getRight()) )
					    + 1 );
		
		auxRight.setHeight( bigger( height(auxRight.getLeft()),
				                    height(auxRight.getRight()) )
					        + 1 );
		
		return auxRight;
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
		
		treeInList.clear();
		toList(node);
//		System.out.println("Comparando " + value + " com " + node.getValue() + " | Res: " + cmpTo + " na lista " + treeInList );
		treeInList.clear();
		
		if(cmpTo == 0) {
			//Se for no folha
			if(node.getLeft() == null && node.getRight() == null) {
				node = null;
				return null;
			
			} else if(node.getLeft() != null && node.getRight() != null) {
				Node<T> aux = node.getLeft();
				
				while(aux.getRight() != null)
					aux = aux.getRight();
				
				node.setValue(aux.getValue());
				aux.setValue(value);
				node.setLeft(remove(node.getLeft(), value));
				
				return node;
				
			}else {
				Node<T> aux;
				
				if(node.getLeft() != null)
					aux = node.getLeft();
				else
					aux = node.getRight();
				
				node = null;
				return aux;
			}
		} else {
			if(cmpTo > 0) //Remove a direita
				node.setRight(remove(node.getRight(), value));
			else //Remove a esquerda
				node.setLeft(remove(node.getLeft(), value));
		}
		
		node.setHeight( bigger( height(node.getLeft()),
				                height(node.getRight()) )
					    + 1 );
		
		node = balance(node);
		
		return node;
	}
		
	public boolean remove(T value) {
		//Verifica se de fato removeu o elemento
		int sizeBeforeRemove = size();
		this.root = remove(this.root, value);
		return size() != sizeBeforeRemove;
	}
	
	private int counterGet = 0;
	private T elementGet;
	public void get(Node<T> node, int key) {
		if(node == null) return;
		
		get(node.getLeft(), key);
		if(key == counterGet)
			elementGet = node.getValue();

		counterGet++;
		get(node.getRight(), key);
	}
	
	public T get(int key) {
		counterGet = 0;
		elementGet = null;
		get(this.root, key);
		return elementGet;
	}

	public T extractMin() {
		T minValue = get(0);
		remove(minValue);		
		return minValue;
	}
	
	//É utilizado caso, o método compareTo da classe T seja alterado
	public void forceSort() {
		List<T> elements = this.toList();
		
		// """""limpa"""" a árvore binária
		this.root = null;
		
		for(T ele : elements) {
			this.add(ele);
		}
	}
	
	public T extractMax() {
		T maxValue = get(size() - 1);
		remove(maxValue);
		return maxValue;
	}	
		
	private List<Node<T>> treeInListWithNode = new ArrayList<>();
	private void toListNode(Node<T> node) {
		if(node == null) return;
		
		toListNode(node.getLeft());
		treeInListWithNode.add(node);
		toListNode(node.getRight());
	}
	
	public List<Node<T>> toListNode() {
		treeInListWithNode.clear();
		toListNode(this.root);
		return treeInListWithNode;
	}
	
	private List<T> treeInList = new ArrayList<T>();
	private void toList(Node<T> node) {
		if(node == null) return;
		
		toList(node.getLeft());
		treeInList.add(node.getValue());
		toList(node.getRight());
	}
	
	public List<T> toList() {
		treeInList.clear();
		toList(this.root);
		return treeInList;
	}

	public int height(Node<T> node) {
		if(node == null) return -1;
		return node.getHeight();
		
//		if(node == null)
//			return 0;
//		else {
//			int heightLeft  = 1 + height(node.getLeft());
//			int heightRight = 1 + height(node.getRight());
//			
//			return (heightLeft > heightRight)? heightLeft : heightRight;
//		}
	}

	public int height() {
		return height(this.root);
	}
	
	private Integer width(Node<T> node, T value, int padding) {
		if(node == null) return 0;
		int x = 0;
		Node<T> auxRoot = node;
		int minValue = size() + 1;
		
		
		while(auxRoot != null) {
			int cmpTo = auxRoot.getValue().compareTo(value);
			
			if(cmpTo < 0) {
				auxRoot = auxRoot.getRight();
				x += size(auxRoot) + padding;
			} else if (cmpTo > 0) {
				auxRoot = auxRoot.getLeft();
				x -= (size(auxRoot) + padding);
				if(minValue > x)
					minValue = x;
			} else return x; 
		}
		
		System.out.println("width: " + node);
		return x;
	}
	
	public Integer width(T value) {
		return width(this.root, value, 1);
	}
		
	public Object[][] matrixMapping(int padding) {
		List<Node<T>> list = toListNode();
		
		int minValue = Math.abs(width(this.root, this.get(0), padding));
		int maxValue = Math.abs(width(this.root, this.get(size() - 1), padding));
		
		int valueToSum = minValue > maxValue? minValue : maxValue;
		int height = height();
		
		Object[][] matrix = new Object[height + 1][valueToSum * 2 + 1];
		
		for(Node<T> ele: list) {
			int x = height - height(ele);
			int y = width(this.root, ele.getValue(), padding) + valueToSum;
			
			matrix[x][y] = ele;
		}
		
		return matrix;
	}
	
	public Object[][] matrixMapping() {
		return matrixMapping(1);
	}
				
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public int factorBalancing(Node<T> node) {
		if(node == null) return 0;
		return height(node.getLeft()) - height(node.getRight()); 		
	}
	
	@Override
	public String toString() {
		if(root == null) return "";
		
		StringBuilder str = new StringBuilder();
		
		int quantSpaces = root.getValue().toString().length();
		Object[][] m = matrixMapping();
		String spacing = "";
		while(quantSpaces-- > 0) spacing += " ";
		
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length; j++) {
				if(m[i][j] == null) str.append(spacing);
				else str.append((m[i][j]).toString());
			}
			str.append("\n");
		}
		
		return str.toString();
		
//		String str = printNode(this.root);
//		return "[" + str.substring(0, str.length() == 0? 0 : str.length() - 2) + "]";
	}
	
}
