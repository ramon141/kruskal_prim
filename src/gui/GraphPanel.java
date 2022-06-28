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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GraphPanel extends JPanel{

	private final Point pointsVertices[] = {
			new Point(9, 6),
			new Point(12, 10),
			new Point(13, 2),
			new Point(17, 2),
			new Point(17, 9),
			new Point(21, 6),
			new Point(12, 7),
			new Point(17, 7),
			new Point(24, 12),
			new Point(24, 0),
			new Point(6, 0),
			new Point(6, 22),
			new Point(0, 10),
			new Point(22, 16),
	};
	
	private List<Vertex> verticesProcessed = new ArrayList<>();
	private List<Edge> edgeProcessed = new ArrayList<>();
	
	private Map<Vertex, Point> onDrawVertices = new HashMap<>();
	
	private final String REGEX_TO_SEPARE = ", ";
	
	public final int WIDTH_VERTICES = 50;
	public final int PADDING = 50;
	
	public int widthVertices = 50;
	public int padding = 40;
	
	JButton btnUpScale = new JButton("+");
	JButton btnDownScale = new JButton("-");
	
	double scale = 0.25;
		
	private Graph graph;
	
	private final int INITIAL_COLUMN = 1;
	
	public GraphPanel(Graph graph) {
		setLayout(null);
		
		btnUpScale.setBounds(0, 0, 50, 25);
		add(btnUpScale);
		
		btnDownScale.setBounds(0, 25, 50, 25);
		add(btnDownScale);
		
		btnUpScale.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scale += 0.1;
				updateSize();
				repaint();
				revalidate();
			}
		} );
		
		btnDownScale.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scale -= 0.1;
				updateSize();
				repaint();
				revalidate();
			}
		} );
		
		setGraph(graph);
	}
		
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
		updateSize();
		repaint();
		revalidate();
	}
	
	public void updateSize() {
		Point maxPoint = getMaxPoint(graph.numberOfVertices());
		
		setPreferredSize(new Dimension(
										(int) (maxPoint.x * (WIDTH_VERTICES + PADDING) * scale),
										(int) (maxPoint.y * (WIDTH_VERTICES + PADDING) * scale)
						));
	}
	
	public Point getMaxPoint(int size) {
		Point maxPoint = new Point(-0x80000000, -0x80000000);
		
		for(int i = 0; i < size && pointsVertices.length > i; i++) {
			Point current = pointsVertices[i];
			
			if(maxPoint.x < current.x)
				maxPoint.x = current.x - 1;
			
			if(maxPoint.y < current.y)
				maxPoint.y = current.y + 1;	
		}
		
		return maxPoint;
	}
	
	private void drawVertex(Graphics g, Vertex v, int x, int y) {
		if(verticesProcessed.contains(v)) {
			g.setColor( Color.black );
		} else {
			g.setColor( new Color(228,131,18) );
		}
		
		g.fillOval(x * (widthVertices + padding), y * (widthVertices + padding), widthVertices, widthVertices);
		
		g.setColor(Color.WHITE);
		g.drawString(v.getName() , x * (widthVertices + padding) + (widthVertices / 3), y * (widthVertices + padding) + (int)(widthVertices / 1.5));
		

		onDrawVertices.put(v, new Point(x, y));
	}

	private Point getMinPoint(int size) {
		if(size > pointsVertices.length)
			return new Point(0, 0);
		
		Point initialPoint = new Point(0x7FFFFFFF/*2^(4*8)/2-1 limite inteiro*/, 0x7FFFFFFF);
		
		for(int i = 0; i < size; i++) {
			Point current = pointsVertices[i];
			
			if(initialPoint.x > current.x)
				initialPoint.x = current.x - INITIAL_COLUMN;
			
			if(initialPoint.y > current.y)
				initialPoint.y = current.y - INITIAL_COLUMN;
		}
		
		return initialPoint;
	}
	
	private Map<Vertex, Point> drawVertices(Graphics g, Iterable<Vertex> vertices) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(widthVertices * 0.9)));
		
		Point initialPoint = getMinPoint(graph.numberOfVertices());
		
		int i = 0;
		for(Vertex vertex: vertices) {
			Point pointDraw = pointsVertices[i];
			drawVertex(g, vertex, pointDraw.x - initialPoint.x, pointDraw.y - initialPoint.y);
			i++;
		}
		
		return onDrawVertices;		
	}
	
	private void drawWeight(Graphics g, int startX, int startY, int finishX, int finishY, Edge edge) {
		g.setFont(new Font("TimesRoman", Font.BOLD, (int)(widthVertices * 0.8)));
		//Desce metade da aresta
		int x = (startX + finishX) / 2;
		int y = (startY + finishY) / 2;
		
		g.drawString(edge.weight() + "", x, y);
	}
	
	private void drawEdges(Graphics g, Map<Vertex, Point> onDrawVertices, Iterable<Edge> edges, Color lineColor) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(widthVertices * 0.9)));
		g.setColor(lineColor);
		
		for(Edge edge: edges) {
			Point pointStart  = onDrawVertices.get(edge.u());
			Point pointFinish = onDrawVertices.get(edge.v());
			
			int startX = pointStart.x * (widthVertices + padding) + (widthVertices / 2);
			int startY = pointStart.y * (widthVertices + padding) + (widthVertices / 2);
			
			int finishX = pointFinish.x * (widthVertices + padding) + (widthVertices / 2);
			int finishY = pointFinish.y * (widthVertices + padding) + (widthVertices / 2);
			
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
	
	public void reset() {
		verticesProcessed.clear();
		edgeProcessed.clear();
		
		for(int i = 0; i < graph.numberOfVertices(); i++)
			graph.vertexAt(i).setData(null);
		
		repaint();
		revalidate();
	}
	
	public void drawDataVertices(Graphics g, Map<Vertex, Point> onDrawVertices) {
		g.setFont(new Font("TimesRoman", Font.BOLD, (int)(widthVertices * 0.9)));
		
		for(Vertex vertex: graph.vertices()) {
			if(vertex.getData() != null) {
				Point point = onDrawVertices.get(vertex);
				
				int x = point.x * (widthVertices + padding);
				int y = point.y * (widthVertices + padding) - (int)(widthVertices / 2.0);
				
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
		super.paint(g);
		
		if(graph.numberOfVertices() > pointsVertices.length) {
			JOptionPane.showMessageDialog(null, "Não é possível mapear mais de " + pointsVertices.length + " vértices");
			return;
		}
		
		
		if(graph != null && graph.numberOfVertices() > 0) {
			widthVertices = (int) (WIDTH_VERTICES * scale);
			padding = (int) (PADDING * scale);
			
			onDrawVertices.clear();
			drawVertices(g, graph.vertices());
			drawEdges(g, onDrawVertices, graph.edges(), Color.BLACK);
			drawDataVertices(g, onDrawVertices);
		}
	}
	
}
