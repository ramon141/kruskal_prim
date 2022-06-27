package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import graph.AdjacencyListGraph;
import graph.Graph;
import graph.Vertex;
import gui.table.DisjointSetTablePanel;
import graph.Edge;
import mintree.Kruskal;
import mintree.Prim;
import utils.Queue;
import utils.Triggers;
import utils.set.ConjuntoDisjunto;

@SuppressWarnings("serial")
public class Main extends JFrame{
	Triggers trigger;
	
	DisjointSetTablePanel<Queue<Vertex>> queuePanel;
	JScrollPane scrQueuePanel;
	
	DisjointSetTablePanel<ConjuntoDisjunto<Vertex>> disjointSetPanel;
	JScrollPane scrDisjointSetPanel;
	
	Controls controls;
	
	private Graph graph = new AdjacencyListGraph(0);
	GraphPanel graphPanel = new GraphPanel(graph);
	JScrollPane scrGraphPanel = new JScrollPane( graphPanel );
		
	Thread threadAlgorithm;
	
	private double sizeHeightScrolls = 1.3; 
		
	public Main() {
		//Configura/Substitui os métodos para servir para os propositos desta classe 
		configControls();
				
		//Depois de tudo configurado ela atribui informações do JFrame
		configFrame();
		
		//Atualiza o tamanho e posição dos componentes
		updateSizeComponents();
		
		//Impede que o java implemente formas de otimizar a visualização de scroll panels
		//A otimização "requer" que a cada scroll seja feita um repaint no componente, e por padrão
		//isto não ocorre, devido a isso o ideal é renderizar todo o panel e mostrar só parte dele,
		//do que mostrar somente a parte do panel que estará visível. O SIMPLE_SCROLL_MODE gasta mais
		//memória RAM, porém menos processador, já que não requer que um repaint seja feita a cada scroll
		//Vale ressaltar que nem sempre um repaint é necessário, logo ele pode gastar o mesmo nível de
		//processamento que SIMPLE_SCROLL_MODE, e utilizar menos RAM.
		scrGraphPanel.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		//Adiciona os componentes no local onde foram previamente configurados
		//Alguns componentes só sao adicionados em certas ocasiões
		add(scrGraphPanel);
		add(controls);
		
		//Implementa um trigger, que fica aguardando a janela mudar de tamanho para alterar o tamanho
		//dos componentes
		onResizeWindow();
	}
		
	private void stopAlgorithm() {
		if(this.threadAlgorithm !=  null && this.threadAlgorithm.isAlive()) {
			this.trigger.finishProcess();
			
			//Espera que o processo termine
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	private void startKruskal() {
		//Reseta as configurações
		graphPanel.reset();
		repaint();
		
		this.trigger = createTrigger( "kruskal" );
		
		//Uma thread depois de finalizada nao pode ser novamente iniciada, logo é necessario criar outra
		this.threadAlgorithm = createThread( "kruskal", this.trigger );
		this.threadAlgorithm.start();
	}
	
	private void startPrim() {
		//Reseta as configurações
		graphPanel.reset();
		repaint();
		
		this.trigger = createTrigger( "prim" );
		
		//Uma thread depois de finalizada nao pode ser novamente iniciada, logo é necessario criar outra
		this.threadAlgorithm = createThread( "prim", this.trigger );
		this.threadAlgorithm.start();
	}
	
	private Thread createThread(String algorithm, Triggers trigger) {
		return new Thread() {
			@Override
			public void run() {
				if(algorithm.equals("kruskal"))
					Kruskal.exec(graph, trigger);
				else if(algorithm.equals("prim"))
					Prim.exec(graph, graph.vertexAt(0), trigger);
			}
		};
	}

	private void configControls() {
		controls = new Controls() {
			@Override
			public void onLoadGraph(Graph newGraph) {
				setGraph(newGraph);
			}
			
			@Override
			public void onNextStep() {
				trigger.setGo(true);
			}
			
			@Override
			public void onRunKruskal() {
				if(graph != null)
					startKruskal();
				else
					JOptionPane.showMessageDialog(null, "Por favor selecione um grafo clicando na opção \"Carregar Grafo\"");
			}
			
			@Override
			public void onStopKruskal(JButton btn) {
				stopAlgorithm();
			}
			
			@Override
			public void onRunPrim() {
				if(graph != null)
					startPrim();
				else
					JOptionPane.showMessageDialog(null, "Por favor selecione um grafo clicando na opção \"Carregar Grafo\"");
			}
		};
	}

	private Triggers createTrigger(String algorithm) {		
		return new Triggers(algorithm) {
			@Override
			public void callback(Triggers trigger, String name, Object... obj) {
				onStep(trigger, name, obj);
				setGo(false);
			}
		};
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
		this.graphPanel.setGraph(graph);
	}

	//Um trigger com uma mensagem específica é enviada a cada etapa do algoritmo (seja de Kruskal ou Prim)
	//Para definir as etapas que o trigger deve ser chamado é necessário adicionar no Kruskal.exec ou Prim.exec
	public void onStep(Triggers trigger, String name, Object... obj) {
		//Se for Kruskal
		if(trigger.getName().equals("kruskal"))
			kruskalTriggers(name, obj);
		else if(trigger.getName().equals("prim"))
			primTriggers(name, obj);
	}
	
	public void primTriggers(String name, Object... obj) {
		if(name.equals("fila carregada")) {		
			if(queuePanel == null) {
				queuePanel = new DisjointSetTablePanel<>( (Queue<Vertex>) obj[0] );
				scrQueuePanel = new JScrollPane(queuePanel);
				add(scrQueuePanel);
			}
			
		} else if(name.equals("vertice processada")) {
			graphPanel.highlightVertex( (Vertex) obj[0] );
			queuePanel.setList( (Queue<Vertex>) obj[1] );
		
		} else if(name.equals("restart process") || name.equals("terminou")) {
			remove(scrQueuePanel);
			queuePanel = null;
			scrQueuePanel = null;
			controls.nextStep.setEnabled(false);
			controls.runKruskal.setEnabled(true);
			controls.loadGraph.setEnabled(true);
			controls.runPrim.setText("Executar Prim");
			stopAlgorithm();
			repaint();
			JOptionPane.showMessageDialog(null, "O processo foi finalizado");
		}

		repaint();
		graphPanel.repaint();
		updateSizeComponents();
	}
	
	public void kruskalTriggers(String name, Object... obj) {
		if(name.equals("conjunto disjunto etapa")) {
			if(scrDisjointSetPanel == null) {
				//Inicia as estruturas
				disjointSetPanel = new DisjointSetTablePanel( (ConjuntoDisjunto<Vertex>) obj[0] );
				scrDisjointSetPanel = new JScrollPane( disjointSetPanel );
				add(scrDisjointSetPanel);
			
			} else {
				disjointSetPanel.setList( (ConjuntoDisjunto<Vertex>) obj[0] );
			}
			
		} else if(name.equals("nao ligados")) {
			disjointSetPanel.getRow().setEdgeProcess( (Edge) obj[0] );
			graphPanel.highlightLine( (Edge) obj[0] );
		
		} else if(name.equals("ja haviam ligados")) {
			JOptionPane.showMessageDialog(null, "Vale relembrar que os vértices da aresta\nprocessada já estão no mesmo conjunto.\nE por consequência a aresta não será ressaltada.");
		
		} else if(name.equals("restart process") || name.equals("terminou")) {
			remove(scrDisjointSetPanel);
			disjointSetPanel = null;
			scrDisjointSetPanel = null;
			controls.nextStep.setEnabled(false);
			controls.runPrim.setEnabled(true);
			controls.loadGraph.setEnabled(true);
			controls.runKruskal.setText("Executar Kruskal");
			repaint();
			JOptionPane.showMessageDialog(null, "O processo foi finalizado");
		}

		updateSizeComponents();
	}

	public double getSizeHeightScrolls() {
		return sizeHeightScrolls;
	}

	public void setSizeHeightScrolls(double sizeHeightScrolls) {
		this.sizeHeightScrolls = sizeHeightScrolls;
		updateSizeComponents();
	}

	public void configFrame() {
		setTitle("Árvore Geradora Mínima");
		setBounds(0, 0, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
	}
	
	public void updateSizeComponents() {
		int height = getHeight();
		int width = getWidth();
				
		if(scrGraphPanel != null)
			scrGraphPanel.setBounds(0, 0, width, (int) ( height / sizeHeightScrolls));
		
		if(controls != null)
			controls.setBounds((int)(width / 1.3), (int) (height / sizeHeightScrolls), width - ((int)(width / 1.3)), (int) ( height -  height / sizeHeightScrolls) - 30);
		
		if(scrDisjointSetPanel != null)
			scrDisjointSetPanel.setBounds(0, (int) (height / sizeHeightScrolls),  (width - (width - ((int)(width / 1.3)))) , (int) ( height -  height / sizeHeightScrolls) - 30);
		
		if(scrQueuePanel != null) 
			scrQueuePanel.setBounds(0, (int) (height / sizeHeightScrolls),  (width - (width - ((int)(width / 1.3)))) , (int) ( height -  height / sizeHeightScrolls) - 30);
		
		revalidate();
	}
	
	public void onResizeWindow() {
		addComponentListener( new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				updateSizeComponents();
			}
		});
	}
		
	public static void main(String[] args) {
		try {
			new Main().setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}
