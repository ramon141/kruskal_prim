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
		
		nextStep.setEnabled(false);
		
		addActionOnClickLoadGraph();
		addActionOnNextStep();
		addActionOnRunPrim();
		addActionOnRunKruskal();
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
	
	private void addActionOnNextStep() {
		nextStep.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	onNextStep();
	        }
	    });
	}
	
	private void addActionOnRunPrim() {
		runPrim.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(runPrim.getText().equals("Executar Prim")) {
	        		nextStep.setEnabled(true);
	        		runPrim.setText("Parar Prim");
	        	
	        	} else if (runPrim.getText().equals("Parar Prim")) {
	        		nextStep.setEnabled(false);
	        		runPrim.setText("Executar Prim");
				}
	        	
	        	onRunPrim();
	        }
	    });
	}
	
	private void addActionOnRunKruskal() {
		runKruskal.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(runKruskal.getText().equals("Executar kruskal")) {
	        		nextStep.setEnabled(true);
	        		runKruskal.setText("Parar Kruskal");
		        	onRunKruskal();
	        	
	        	} else if (runKruskal.getText().equals("Parar Kruskal")) {
	        		nextStep.setEnabled(false);
	        		runKruskal.setText("Executar kruskal");
	        		onStopKruskal();
	        		
				}
	        	
	        }
	    });
	}
	
	public void onLoadGraph(Graph graph) {}
	
	public void onNextStep() {}
	
	public void onRunPrim() {}
	
	public void onRunKruskal() {}
	
	public void onStopKruskal() {}

	public void addButtons() {
		add(runKruskal);
		add(runPrim);
		add(nextStep);
		add(loadGraph);
	}
	
	
	
}
