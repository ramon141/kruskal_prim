package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import graph.AdjacencyListGraph;
import graph.Graph;
import graph.Vertex;
import graph.Edge;
import gui.table.DisjointSetTablePanel;
import mintree.Kruskal;
import utils.Triggers;
import utils.set.ConjuntoDisjunto;
import utils.tree.Tree;

@SuppressWarnings("serial")
public class Main extends JFrame{
	static boolean inStep = false;
	String algorithmOnExec = "kruskal";
	
	Triggers trigger;
	
	DisjointSetTablePanel disjointSetPanel;
	JScrollPane scrDisjointSetPanel;
	
	Controls controls = new Controls();
	
	Graph graph = AdjacencyListGraph.graphFromFile("input/cormen_23.1", false);
	GraphPanel graphPanel = new GraphPanel(graph);
	JScrollPane scrGraphPanel = new JScrollPane( graphPanel );
	
	Tree<Vertex> treeVertices = new Tree<Vertex>(graph.vertices());
	
	TreePanel treePanel = new TreePanel(treeVertices);
	JScrollPane scrTreePanel = new JScrollPane( treePanel );
	
	private double sizeHeightScrolls = 1.3; 
		
	public Main() {
		trigger = new Triggers(true) {
			@Override
			public void onChange(Object obj,  String name) {
				callback(obj, name);
				trigger.setGo(false);
			}
		};
				
		Thread threadKruskal = new Thread() {
			@Override
			public void run() {
				Kruskal.exec(graph, trigger);
			}
		};
		
		threadKruskal.start();
		
		configFrame();
		
		controls.nextStep.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	trigger.setGo(true);
	        }
	    });
		
		
		updateSizeComponents();
		
		scrTreePanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scrGraphPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		add(scrGraphPanel);
		add(scrTreePanel);
		add(controls);
		
		onResizeWindow();
	}
	
	//Um trigger com uma mensagem específica é enviada a cada etapa do algoritmo (seja de Kruskal ou Prim)
	//Para definir as etapas que o trigger deve ser chamado é necessário adicionar no Kruskal.exec ou Prim.exec
	public void callback(Object obj,  String name) {
		//Se for Kruskal
		if(algorithmOnExec.equals("kruskal"))
			kruskalTriggers(obj, name);
		else if(algorithmOnExec.equals("prim"))
			return;		
	}
	
	public void kruskalTriggers(Object obj,  String name) {		
		if(name.equals("conjunto disjunto etapa")) {
			if(scrDisjointSetPanel == null) {
				//Inicia as estruturas
				disjointSetPanel = new DisjointSetTablePanel( (ConjuntoDisjunto<Vertex>) obj );
				scrDisjointSetPanel = new JScrollPane( disjointSetPanel );
				add(scrDisjointSetPanel);
			
			} else {
				disjointSetPanel.setCd( (ConjuntoDisjunto<Vertex>) obj );
			}
			
		} else if(name.equals("nao ligados")) {
			disjointSetPanel.getRow().setEdgeProcess( (Edge) obj );
			graphPanel.highlightLine( (Edge) obj );
		
		} else if(name.equals("ja haviam ligados")) {
			disjointSetPanel.getRow().setEdgeProcess( (Edge) obj );
			graphPanel.highlightLine( (Edge) obj );
			JOptionPane.showMessageDialog(null, "Vale relembrar que os vértices da aresta\nprocessada já estãono mesmo conjunto.\nE por consequência a aresta não será ressaltada.");
		}

		updateSizeComponents();
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
		setBounds(0, 0, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
	}
	
	public void updateSizeComponents() {
		int height = getHeight();
		int width = getWidth();
		
		if(scrTreePanel != null)
			scrTreePanel.setSize( width / 2 , (int) (height / sizeHeightScrolls));
		
		if(scrGraphPanel != null)
			scrGraphPanel.setBounds(width / 2, 0, width / 2, (int) ( height / sizeHeightScrolls));
		
		if(controls != null)
			controls.setBounds((int)(width / 1.3), (int) (height / sizeHeightScrolls), width - ((int)(width / 1.3)), (int) ( height -  height / sizeHeightScrolls) - 30);
		
		if(scrDisjointSetPanel != null)
			scrDisjointSetPanel.setBounds(0, (int) (height / sizeHeightScrolls),  (width - (width - ((int)(width / 1.3)))) , (int) ( height -  height / sizeHeightScrolls) - 30);
		
		revalidate();
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
