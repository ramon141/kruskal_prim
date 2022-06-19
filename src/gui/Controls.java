package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controls extends JPanel{

	JButton runKruskal = new JButton("Executar kruskal");
	JButton runPrim = new JButton("Executar Prim");
	JButton loadGraph = new JButton("Carregar Grafo");
	JButton nextStep = new JButton("Próximo passo");
	
	public Controls() {
		setLayout(new GridLayout(4, 1));
		addButtons();
	}
		
	public void addButtons() {
		add(runKruskal);
		add(runPrim);
		add(nextStep);
		add(loadGraph);
	}
	
	
	
}
