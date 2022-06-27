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
	private T list;
	
	private Header header;
	private Row row;
	
	public DisjointSetTablePanel(T list) {
		this.list = list;
		header = new Header(list.size() + 1);
		row = new Row(list);
		
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
		return list;
	}
	
	public void setList(T list) {
		this.list = list;
		header.setColumns(list.size());
		row.setList(list);
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
