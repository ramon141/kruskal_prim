package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controls extends JPanel{

	JButton nextStep = new JButton("Próximo passo");
	
	public Controls() {
		setLayout(new GridLayout(1,3));
		addButtons();
	}
		
	public void addButtons() {
		add(nextStep);
	}
	
}
