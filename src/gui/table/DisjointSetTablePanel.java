package gui.table;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import graph.Vertex;

@SuppressWarnings("serial")
public class DisjointSetTablePanel<T extends List> extends JPanel {

	private int width = 0;
	private int height = 0;
	private T cd;
	
	private Header header;
	private Row row;
	
	public DisjointSetTablePanel(T cd) {
		this.cd = cd;
		header = new Header(cd.size() + 1);
		row = new Row(cd);
		
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

	public T getCd() {
		return cd;
	}
	
	public void setCd(T cd) {
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
