package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.FormatInvalid;
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
		
		runKruskal.setEnabled(false);
		runPrim.setEnabled(false);
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
	        	JFileChooser chooser = new JFileChooser("./");
	        	
	        	int returnVal = chooser.showOpenDialog(null);
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	try {
	            		Graph graph = AdjacencyListGraph.graphFromFile(chooser.getSelectedFile().getPath(), false);
		            	
		            	if(graph.isDirected())
		            		JOptionPane.showMessageDialog(null, "O grafo selecionado não deve ser dirigido");
		            	else if(!graph.isGraphConnected())
		            		JOptionPane.showMessageDialog(null, "O grafo selecionado deve ser conexo");
		            	else if(!graph.isWeighted())
		            		JOptionPane.showMessageDialog(null, "O grafo selecionado deve ser ponderado");
		            	else {
		            		runKruskal.setEnabled(true);
		            		runKruskal.setText("Executar Kruskal");
		            		
		            		runPrim.setEnabled(true);
		            		runPrim.setText("Executar Prim");
		            		
		            		onLoadGraph(graph);
		            	}	
					
	            	} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
	            	
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
	        		runKruskal.setEnabled(false);
	        		loadGraph.setEnabled(false);
	        		
	        		runPrim.setText("Parar Prim");
		        	onRunPrim();
	        	
	        	} else if (runPrim.getText().equals("Parar Prim")) {
	        		nextStep.setEnabled(false);
	        		runKruskal.setEnabled(true);
	        		loadGraph.setEnabled(true);
	        		
	        		runPrim.setText("Executar Prim");
		        	onStopPrim(runPrim);
				}
	        	
	        }
	    });
	}
	
	private void addActionOnRunKruskal() {
		runKruskal.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	if(runKruskal.getText().equals("Executar Kruskal")) {
	        		nextStep.setEnabled(true);
	        		runPrim.setEnabled(false);
	        		loadGraph.setEnabled(false);
	        		
	        		runKruskal.setText("Parar Kruskal");
		        	onRunKruskal();
	        	
	        	} else if (runKruskal.getText().equals("Parar Kruskal")) {
	        		nextStep.setEnabled(false);
	        		runPrim.setEnabled(true);
	        		loadGraph.setEnabled(true);
	        		
	        		runKruskal.setText("Executar Kruskal");
	        		onStopKruskal(runKruskal);
	        		
				}
	        	
	        }
	    });
	}
	
	public void onLoadGraph(Graph graph) {}
	
	public void onNextStep() {}
	
	public void onRunPrim() {}
	
	public void onStopPrim(JButton btn) {}
	
	public void onRunKruskal() {}
	
	public void onStopKruskal(JButton btn) {}
	
	public void addButtons() {
		add(runKruskal);
		add(runPrim);
		add(nextStep);
		add(loadGraph);
	}
	
	
	
}
