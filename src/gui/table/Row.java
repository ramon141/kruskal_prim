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
public class Row extends JPanel{

	private Edge edgeProcess;
	private JLabel[] columns;
	private ConjuntoDisjunto<Vertex> cd;
	
	public Row(ConjuntoDisjunto<Vertex> cd) {
		setCd(cd);
	}
		
	public Edge getEdgeProcess() {
		return edgeProcess;
	}

	public void setEdgeProcess(Edge edgeProcess) {
		this.edgeProcess = edgeProcess;

		updateColumns();
	}
	
	public ConjuntoDisjunto<Vertex> getCd() {
		return cd;
	}

	public void setCd(ConjuntoDisjunto<Vertex> cd) {
		this.cd = cd;
		updateColumns();
	}
	
	public void updateColumns() {
		removeAll();
		setLayout(new GridLayout(1, cd.size() + 1, 1, 2));
		columns = new JLabel[cd.size() + 1 /*Aresta processada*/];
		
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
//		List<Conjunto<Vertex>> list = cd.getCollection();
//		int size = list.size();
		
		System.out.println(cd.getCollection());
		
		int i = 0;
		for(Conjunto<Vertex> ele: cd.getCollection()) {
			add(configureColumn(columns[i], ele.toString()));
			i++;
		}
//		for(int i = 0; i < list.size(); i++) {
//			Conjunto<Vertex> ele = list.get(i);
//			
//			add(configureColumn(columns[i], ele.toString()));
//		}	
	}
	
}
