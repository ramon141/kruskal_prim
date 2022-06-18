package gui;

import graph.Vertex;
import utils.tree.Node;
import utils.tree.Tree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;


public class TreePanel extends JPanel{
	
	private final int WIDTH_NODE = 50;
	private final int PADDING_NODE = 0;
	Tree<Vertex> tree = new Tree<Vertex>();
	
	public TreePanel(Tree<Vertex> tree) {
		this.tree = tree;
	}
	
	private void drawNode(Graphics g, Node<Vertex> node, int y, int x) {
		g.setColor(Color.BLUE);
		g.fillOval(x * (WIDTH_NODE + PADDING_NODE), y * (WIDTH_NODE + PADDING_NODE), WIDTH_NODE, WIDTH_NODE);
		g.setColor(Color.WHITE);
		g.drawString(node.getValue().getName(), x * (WIDTH_NODE + PADDING_NODE) + (int) (WIDTH_NODE / 2.5), y * (WIDTH_NODE + PADDING_NODE) + (int)(WIDTH_NODE / 1.5));
	}
	
	private Map<Node<Vertex>, Point> drawNodes(Graphics g, Object[][] matrixVertices) {
		Map<Node<Vertex>, Point> onDrawVertices = new HashMap<>();
		
		for(int i = 0; i < matrixVertices.length; i++) {
			for(int j = 0; j < matrixVertices[i].length; j++) {
				if(matrixVertices[i][j] != null) {
					Node<Vertex> node = (Node<Vertex>) matrixVertices[i][j];
					onDrawVertices.put(node, new Point(j, i));
					drawNode(g, node, i, j);
				}
			}
		}
		
		return onDrawVertices;
	}
	
	private void drawLines(Graphics g, Map<Node<Vertex>, Point> onDrawVertices) {
		
		g.setColor(Color.BLACK);
		
		for(Node<Vertex> node: tree.toListNode()) {
			if(node.getDad() != null) {
				Point pointStart  = onDrawVertices.get(node.getDad());
				Point pointFinish = onDrawVertices.get(node);
				
				g.drawLine(pointStart.x * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
						pointStart.y * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
						
						pointFinish.x * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
						pointFinish.y * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2));
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Object[][] matrixVertices = tree.matrixMapping();
		g.setFont(new Font("Arial",	0, 20));
		Map<Node<Vertex>, Point> onDrawVertices = drawNodes(g, matrixVertices);
		drawLines(g, onDrawVertices);
	}
}
