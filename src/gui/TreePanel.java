package gui;

import graph.Vertex;
import utils.tree.Node;
import utils.tree.Tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TreePanel extends JPanel{
	
	private final String REGEX_TO_SEPARE = ", ";
	private final int WIDTH_NODE = 50;
	private final int PADDING_NODE = 0;
	
	private Tree<Vertex> tree = new Tree<Vertex>();
	
	public TreePanel(Tree<Vertex> tree) {
		setTree(tree);
	}
		
	public Tree<Vertex> getTree() {
		return tree;
	}

	public void setTree(Tree<Vertex> tree) {
		this.tree = tree;
		repaint();
		
		int width =  2 * ((tree.size() + tree.height()) * (WIDTH_NODE + PADDING_NODE) + (int)(WIDTH_NODE / 1.5));
		int height = tree.height() * (WIDTH_NODE + PADDING_NODE) + (int)(WIDTH_NODE / 1.5);
		
		setPreferredSize( new Dimension( width, height) );
	}

	private void drawLabelNode(Graphics g, Node<Vertex> node, int y, int x) {
		g.setFont(new Font("Arial",	0, 15));
		
		if(node.getValue().getData() != null) {
			g.setColor(Color.RED);
			String data = node.getValue().getData().toString();
			String elements[] = data.split(REGEX_TO_SEPARE);
			
			int yStart = y * (WIDTH_NODE + PADDING_NODE) - (int)(WIDTH_NODE / 1.5);
			
			for(String ele: elements) {
				g.drawString(ele,
							 x * (WIDTH_NODE + PADDING_NODE) - (int)(WIDTH_NODE / 2.0),
							 yStart);
				yStart += 30;
			}
			
		}
	}
	
	private void drawNode(Graphics g, Node<Vertex> node, int y, int x) {
		g.setColor( new Color(228,131,18) );
		g.fillOval(x * (WIDTH_NODE + PADDING_NODE), y * (WIDTH_NODE + PADDING_NODE), WIDTH_NODE, WIDTH_NODE);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",	0, 20));
		g.drawString(node.getValue().getName(), x * (WIDTH_NODE + PADDING_NODE) + (int) (WIDTH_NODE / 2.5), y * (WIDTH_NODE + PADDING_NODE) + (int)(WIDTH_NODE / 1.5));
		
		drawLabelNode(g, node, y, x);
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
	
	private void drawLines(Graphics g, Node<Vertex> nodeDad, Node<Vertex> node, Map<Node<Vertex>, Point> onDrawVertices) {
		
		if(nodeDad != null && node != null) {
			Point pointStart  = onDrawVertices.get(nodeDad);
			Point pointFinish = onDrawVertices.get(node);
			
			g.drawLine(pointStart.x * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
					pointStart.y * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
					
					pointFinish.x * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2),
					pointFinish.y * (WIDTH_NODE + PADDING_NODE) + (WIDTH_NODE / 2));
		}
		
		if(node != null) {
			drawLines(g, node, node.getLeft(), onDrawVertices);
			drawLines(g, node, node.getRight(), onDrawVertices);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Object[][] matrixVertices = tree.matrixMapping();
		Map<Node<Vertex>, Point> onDrawVertices = drawNodes(g, matrixVertices);
		g.setColor(Color.BLACK);
		drawLines(g, null, tree.getRoot(), onDrawVertices);
		
	}
}
