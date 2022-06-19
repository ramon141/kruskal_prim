package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import graph.AdjacencyListGraph;
import graph.Graph;
import graph.Vertex;
import utils.tree.Tree;

@SuppressWarnings("serial")
public class Main extends JFrame{
	Controls controls = new Controls();
	
	Graph graph = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
	GraphPanel graphPanel = new GraphPanel(graph);
	JScrollPane scrGraphPanel = new JScrollPane( graphPanel );
	
	Tree<Vertex> treeVertices = new Tree<Vertex>(graph.vertices());
	
	TreePanel treePanel = new TreePanel(treeVertices);
	JScrollPane scrTreePanel = new JScrollPane( treePanel );
	
	private double sizeHeightScrolls = 1.3; 
		
	public Main() {
		configFrame();
		
		updateSizeComponents();
		
		scrTreePanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrGraphPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		add(scrGraphPanel);
		add(scrTreePanel);
		add(controls);
		
		onResizeWindow();
	}
	
	public double getSizeHeightScrolls() {
		return sizeHeightScrolls;
	}

	public void setSizeHeightScrolls(double sizeHeightScrolls) {
		this.sizeHeightScrolls = sizeHeightScrolls;
		updateSizeComponents();
	}

	public void configFrame() {
		setTitle("Árvore Geradora Mínima");
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
	}
	
	public void updateSizeComponents() {
		int height = getHeight();
		int width = getWidth();
		
		scrTreePanel.setSize( width / 2 , (int) (height / sizeHeightScrolls));
		scrGraphPanel.setBounds(width / 2, 0, width / 2, (int) ( height / sizeHeightScrolls));
		controls.setBounds(0, (int) (height / sizeHeightScrolls), width, (int) ( height -  height / sizeHeightScrolls) - 30);
		
		repaint();
	}
	
	public void onResizeWindow() {
		addComponentListener( new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				updateSizeComponents();
			}
		});
	}
		
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
	
}
