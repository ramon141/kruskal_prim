package gui;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class GraphPanel extends JPanel{

	
	private final int INITIAL_COLUMN = 3;
	private final int WIDTH_VERTICES = 50;
	private final int PADDING_VERTICES = 50;
	
	
	private Graph graph;
	
	public GraphPanel(Graph graph) {
		this.graph = graph;
	}
		
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
		repaint();
	}
	
	//Retorna um booleano para indicar se o vértice foi adicionado na panel, retornando true para este caso.
	//Retornando false caso o vértice já se encontrava na telas
	private boolean drawVertex(Graphics g, Vertex v, List<Vertex> verticesProcessed, int x, int y) {
		if(!verticesProcessed.contains(v)) {
			g.setColor(Color.BLUE);
			g.fillOval(x * (WIDTH_VERTICES + PADDING_VERTICES), y * (WIDTH_VERTICES + PADDING_VERTICES), WIDTH_VERTICES, WIDTH_VERTICES);
			
			g.setColor(Color.WHITE);
			g.drawString(v.getName(), x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 3), y * (WIDTH_VERTICES + PADDING_VERTICES) + (int)(WIDTH_VERTICES / 1.5));
						
			return true;
		}
		
		return false;
	}

	private Map<Vertex, Point> drawVertexs(Graphics g, Iterable<Vertex> vertices) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		
		List<Vertex> verticesProcessed = new ArrayList<>();
		Map<Vertex, Point> onDrawVertices = new HashMap<>();
		
		for(int i = 0, y = 0; i < graph.numberOfVertices(); i++) {
			boolean draw = false;
			Vertex v = graph.vertexAt(i);
			
			if(drawVertex(g, v, verticesProcessed, INITIAL_COLUMN, y)) {
				verticesProcessed.add(v);
				onDrawVertices.put(v, new Point(INITIAL_COLUMN, y));
				draw = true;
				y++;
			}
			
			int x = INITIAL_COLUMN;
			
			for(Vertex vetCount: graph.adjacentVertices(v))
				if(!verticesProcessed.contains(vetCount))
					x--;
			
			x = (int)(x / 2.0)  + 2;
			
			for(Vertex vAdj: graph.adjacentVertices(v)) {
				if(drawVertex(g, vAdj, verticesProcessed, x, y)) {
					verticesProcessed.add(vAdj);
					onDrawVertices.put(vAdj, new Point(x, y));
					draw = true;
					x++;
				}
			}
			
			if(draw) y++;
		}
		
		return onDrawVertices;		
	}
	
	private void drawEdges(Graphics g, Map<Vertex, Point> onDrawVertices, Iterable<Edge> edges) {
		
		g.setColor(Color.BLACK);
		
		for(Edge edge: edges) {
			Point pointStart  = onDrawVertices.get(edge.u());
			Point pointFinish = onDrawVertices.get(edge.v());
			
			g.drawLine(pointStart.x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2),
					pointStart.y * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2),
					
					pointFinish.x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2),
					pointFinish.y * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2));
		}
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		Map<Vertex, Point> onDrawVertices = drawVertexs(g, graph.vertices());
		drawEdges(g, onDrawVertices, graph.edges());
	}
	
}
