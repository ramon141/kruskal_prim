package gui;

import javax.swing.JFrame;

import graph.AdjacencyListGraph;
import graph.Graph;
import graph.Vertex;
import utils.tree.Tree;

public class Main extends JFrame{
	
	Controls controls = new Controls();
	
	Graph graph = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
	GraphPanel graphPanel = new GraphPanel(graph);
	
	Tree<Vertex> treeVertices = new Tree<Vertex>();
	TreePanel treePanel;
	
	public Main() {
		super("Árvore Geradora Mínima");
		setBounds(0, 0, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		treeVertices.add(new Vertex("d"));
		treeVertices.add(new Vertex("b"));
		treeVertices.add(new Vertex("a"));
		
		
		treeVertices.add(new Vertex("c"));
		treeVertices.add(new Vertex("f"));
		treeVertices.add(new Vertex("e"));
		treeVertices.add(new Vertex("g"));
		

		System.out.println(treeVertices);
		
		
		treePanel  = new TreePanel(treeVertices);
		
		add(treePanel);
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
	
}
