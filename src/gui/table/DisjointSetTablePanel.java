package gui.table;

import java.awt.GridLayout;

import javax.swing.JPanel;

import graph.Vertex;
import utils.set.ConjuntoDisjunto;

@SuppressWarnings("serial")
public class DisjointSetTablePanel extends JPanel {

	private int width = 0;
	private int height = 0;
	private ConjuntoDisjunto<Vertex> cd;
	
	private Header header;
	private Row row;
	
	public DisjointSetTablePanel(ConjuntoDisjunto<Vertex> cd) {
		this.cd = cd;
		header = new Header(cd.size() + 1);
		row = new Row(cd);
		
		System.out.println();
		
		setLayout(new GridLayout(2, 1));
		
		add(header);
		add(row);
	}
	
	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public ConjuntoDisjunto<Vertex> getCd() {
		return cd;
	}
	
	public void setCd(ConjuntoDisjunto<Vertex> cd) {
		this.cd = cd;
		header.setColumns(cd.size());
		row.setCd(cd);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		header.resizeColumns(width, height / 2);
		super.setBounds(x, y, width, height);
	}
	
	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		header.resizeColumns(width, height / 2);
		super.setSize(width, height);
	}
	
}
