package gui.table;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Edge;
import graph.Vertex;
import utils.set.Conjunto;
import utils.set.ConjuntoDisjunto;

@SuppressWarnings("serial")
public class Row<T extends List> extends JPanel{

	private Edge edgeProcess;
	private JLabel[] columns;
	private T list;
	
	public Row(T cd) {
		setList(cd);
	}
		
	public Edge getEdgeProcess() {
		return edgeProcess;
	}

	public void setEdgeProcess(Edge edgeProcess) {
		this.edgeProcess = edgeProcess;

		updateColumns();
	}
	
	public T getList() {
		return list;
	}

	public void setList(T list) {
		this.list = list;
		updateColumns();
	}
	
	public void updateColumns() {
		removeAll();
		setLayout(new GridLayout(1, list.size() + 1, 1, 2));
		columns = new JLabel[list.size() + 1 /*Aresta processada*/];
		
		add(configureColumn(columns[0], edgeProcess == null? "Inicial" : edgeProcess.toString()));
		drawColumns();
		
		revalidate();
	}

	public JLabel configureColumn(JLabel jl, String text) {
		if(jl == null)
			jl = new JLabel();
		
		jl.setText(text);
		jl.setOpaque(true);
		jl.setBackground( new Color(245, 217, 204) );
		jl.setHorizontalAlignment(0);
		
		return jl;
	}
	
	public void drawColumns() {
		int i = 0;
		for(Object ele: list) {
			add(configureColumn(columns[i], ele.toString() ));
			i++;
		}
	}
	
}
