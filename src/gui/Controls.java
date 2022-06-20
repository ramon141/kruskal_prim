package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import graph.AdjacencyListGraph;
import graph.Graph;

@SuppressWarnings("serial")
public class Controls extends JPanel{

	JButton runKruskal = new JButton("Executar kruskal");
	JButton runPrim = new JButton("Executar Prim");
	JButton loadGraph = new JButton("Carregar Grafo");
	JButton nextStep = new JButton("Próximo passo");
	
	public Controls() {
		setLayout(new GridLayout(4, 1));
		addButtons();
		
		addActionOnClickLoadGraph();
	}
	
	
		
	private void addActionOnClickLoadGraph() {
		loadGraph.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//Abre o seletor de arquivos
	        	JFileChooser chooser = new JFileChooser();
	        	
	        	int returnVal = chooser.showOpenDialog(null);
	            if(returnVal == JFileChooser.APPROVE_OPTION) {	            	
	            	Graph graph = AdjacencyListGraph.graphFromFile(chooser.getSelectedFile().getPath(), false);
	            	onLoadGraph(graph);
	            } else {
	            	JOptionPane.showMessageDialog(null, "Por favor selecione um arquivo.");
	            }
	        }
	    });
	}
	
	public void onLoadGraph(Graph graph) {}

	public void addButtons() {
		add(runKruskal);
		add(runPrim);
		add(nextStep);
		add(loadGraph);
	}
	
	
	
}
