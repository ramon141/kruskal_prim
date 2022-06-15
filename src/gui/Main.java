package gui;

import javax.swing.JFrame;

import graph.AdjacencyListGraph;
import graph.Graph;

public class Main extends JFrame{
	
	Controls controls = new Controls();
	
	Graph graph = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
	GraphPanel graphPanel = new GraphPanel(graph);
	
	public Main() {
		super("Árvore Geradora Mínima");
		setBounds(0, 0, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(graphPanel);
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
	
}
