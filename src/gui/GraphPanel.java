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

	private List<Vertex> verticesProcessed = new ArrayList<>();
	private List<Edge> edgeProcessed = new ArrayList<>();
	
	private Map<Vertex, Point> onDrawVertices = new HashMap<>();
	
	private final String REGEX_TO_SEPARE = ", ";
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
	private boolean drawVertex(Graphics g, Vertex v, List<Vertex> verticesAlreadyShown, int x, int y) {
		if(!verticesAlreadyShown.contains(v)) {
			
			if(verticesProcessed.contains(v)) {
				g.setColor( Color.black );
			} else {
				g.setColor( new Color(228,131,18) );
			}
			
			g.fillOval(x * (WIDTH_VERTICES + PADDING_VERTICES), y * (WIDTH_VERTICES + PADDING_VERTICES), WIDTH_VERTICES, WIDTH_VERTICES);
			
			g.setColor(Color.WHITE);
			g.drawString(v.getName(), x * (WIDTH_VERTICES + PADDING_VERTICES) + (WIDTH_VERTICES / 3), y * (WIDTH_VERTICES + PADDING_VERTICES) + (int)(WIDTH_VERTICES / 1.5));
						
			return true;
		}
		
		return false;
	}

	private Map<Vertex, Point> drawVertices(Graphics g, Iterable<Vertex> vertices) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		
		List<Vertex> verticesAlreadyShown = new ArrayList<>();
		
		for(int i = 0, y = 1; i < graph.numberOfVertices(); i++) {
			boolean draw = false;
			Vertex v = graph.vertexAt(i);
			
			if(drawVertex(g, v, verticesAlreadyShown, INITIAL_COLUMN, y)) {
				verticesAlreadyShown.add(v);
				onDrawVertices.put(v, new Point(INITIAL_COLUMN, y));
				draw = true;
				y++;
			}
			
			int x = INITIAL_COLUMN;
			
			for(Vertex vetCount: graph.adjacentVertices(v))
				if(!verticesAlreadyShown.contains(vetCount))
					x--;
			
			x = (int)(x / 2.0)  + 2;
			
			for(Vertex vAdj: graph.adjacentVertices(v)) {
				if(drawVertex(g, vAdj, verticesAlreadyShown, x, y)) {
					verticesAlreadyShown.add(vAdj);
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
	
	public void highlightVertex(Vertex vertex) {
		verticesProcessed.add(vertex);
		repaint();
	}
	
	public void drawDataVertices(Graphics g, Map<Vertex, Point> onDrawVertices) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 13));
		
		for(Vertex vertex: graph.vertices()) {
			if(vertex.getData() != null) {
				Point point = onDrawVertices.get(vertex);
				
				int x = point.x * (WIDTH_VERTICES + PADDING_VERTICES);
				int y = point.y * (WIDTH_VERTICES + PADDING_VERTICES) - (int)(WIDTH_VERTICES / 2.0);
				
				String elements[] = vertex.getData().toString().split(REGEX_TO_SEPARE);
				for(String ele: elements) {
					g.drawString(ele.replace("Infinity", "\u221E").replace("null", "\u2205"), x, y);
					y += 15;
				}
				
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		onDrawVertices.clear();
		drawVertices(g, graph.vertices());
		drawEdges(g, onDrawVertices, graph.edges(), Color.BLACK);
		drawDataVertices(g, onDrawVertices);
	}
	
}
