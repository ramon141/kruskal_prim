package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Controls extends JPanel{

	JButton nextStep = new JButton();
	JButton prevStep = new JButton();
	
	public Controls() {
		setLayout(null);
		configuringSizeButtons();
		addButtons();
	}
	
	public void configuringSizeButtons() {
		nextStep.setBounds(0, 0, 100, 100);
		prevStep.setBounds(0, 100, 100, 100);
	}
	
	public void addButtons() {
		add(nextStep);
		add(prevStep);
	}
	
}
