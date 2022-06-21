package gui.table;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Header extends JPanel{
	
	int width = 0, height = 0;
	private int columns;
	private JLabel[] infoHeader = new JLabel[2];
	
	public Header(int columns) {
		setLayout(null);
		
		this.columns = columns;
		
		infoHeader[0] = new JLabel("<html> <body> Aresta<br>Processada </body> </html> ");
		infoHeader[1] = new JLabel("Conjuntos Disjuntos");
		
		infoHeader[0].setOpaque(true);
		infoHeader[1].setOpaque(true);
		
		infoHeader[0].setHorizontalAlignment(0);
		infoHeader[1].setHorizontalAlignment(0);
		
		infoHeader[0].setBackground( new Color(228, 131, 18) );
		infoHeader[1].setBackground( new Color(228, 131, 18) );
		
		infoHeader[0].setForeground(Color.WHITE);
		infoHeader[1].setForeground(Color.WHITE);
		
		add(infoHeader[0]);
		add(infoHeader[1]);
	}
	
	
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		resizeColumns(width, height);
	}

	public void resizeColumns(int width, int height) {
		if(columns != 0) {
			this.width = width;
			this.height = height;
			
			infoHeader[0].setBounds(3, 0, width / columns - 1, height);
			infoHeader[1].setBounds(width / columns + 3, 0, (columns - 1) * (width / columns) - 1, height);
		}
	}
}
