package gui;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GraphPanel extends JPanel{

	Graphics g;
	
	private List<Edge> edgeProcessed = new ArrayList<>();
	private Map<Vertex, Point> onDrawVertices = new HashMap<>();
	private final int INITIAL_COLUMN = 3;
	private final int WIDTH_VERTICES = 50;
	private final int PADDING_VERTICES = 50;
		
	private Graph graph;
	
	public GraphPanel(Graph graph) {
		setGraph(graph);
	}
		
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
		setPreferredSize(  new Dimension( ( graph.numberOfVertices() ) * (WIDTH_VERTICES + PADDING_VERTICES), graph.numberOfVertices() * (WIDTH_VERTICES + PADDING_VERTICES))  );
		
		revalidate();
	}
	
	//Retorna um booleano para indicar se o vértice foi adicionado na panel, retornando true para este caso.
	//Retornando false caso o vértice já se encontrava na telas
	private boolean drawVertex(Graphics g, Vertex v, List<Vertex> verticesProcessed, int x, int y) {
		if(!verticesProcessed.contains(v)) {
				
			g.setColor( new Color(228,131,18) );
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
	
	private void drawWeight(Graphics g, int startX, int startY, int finishX, int finishY, Edge edge) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 13));
		//Desce metade da aresta
		int x = (startX + finishX) / 2;
		int y = (startY + finishY) / 2;
		
		g.drawString(edge.weight() + "", x, y);
	}
	
	private void drawEdges(Graphics g, Map<Vertex, Point> onDrawVertices, Iterable<Edge> edges, Color lineColor) {
		 
		g.setColor(lineColor);
		
		for(Edge edge: edges) {
			Point pointStart  = onDrawVertices.get(edge.u());
			Point pointFinish = onDrawVertices.get(edge.v());
			
			int startX = pointStart.x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2);
			int startY = pointStart.y * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2);
			
			int finishX = pointFinish.x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2);
			int finishY = pointFinish.y * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 2);
			
			if(edgeProcessed.contains(edge)) {
				((Graphics2D) g).setStroke( new BasicStroke(5) );
				g.setColor( Color.RED);
			
			} else {
				((Graphics2D) g).setStroke( new BasicStroke(1) );
				g.setColor(lineColor);
			}
			
			g.drawLine(startX, startY, finishX, finishY);
			
			drawWeight(g, startX, startY, finishX, finishY, edge);
		}
		
	}
	
	public void highlightLine(Edge edge) {
		edgeProcessed.add(edge);
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		onDrawVertices.clear();
		drawVertexs(g, graph.vertices());
		drawEdges(g, onDrawVertices, graph.edges(), Color.BLACK);
		this.g = g;
	}
	
}
