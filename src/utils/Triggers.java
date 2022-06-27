package utils;

//Esta classe tem o objetivo agir como gatilhos que ocorrerão a cada passo dos algoritmos de
//kruskal e prim

//A cada modificação de uma estrutura (como conjunto disjunto, ou o proprio grafo) deverá chamar
//esta classe, as classes que estenderem ou tiverem uma instância de Triggers implementará as funções
//e recerá as informações dos gatilhos

//Basicamente uma brincadeira com polimorfismo

public class Triggers {

	private String name = "";
	private int counterSteps = -1;
	private String RESERVED_WORDS[] = {"restart process"};   
	private boolean finishProcess = false, notificationFinishProcess = false;
	private boolean go = false;
	
	public Triggers(String name) {
		this.name = name;
	}
	
	public void onChange(String name, Object... obj) {
		if(name.isEmpty())
			throw new RuntimeException("A identificação da etapa não pode ser vazia");
		else if(isReservedWord(name))
			throw new RuntimeException("A identificação \""+ name +"\" não pode ser escolhida, pois faz parte das palavras reservadass");
		
		this.counterSteps++;

		if(finishProcess && !notificationFinishProcess) {
			callback(this, "restart process", name, obj);
			notificationFinishProcess = true;
			counterSteps = -1;
		
		} else if(finishProcess) {
			setGo(false);
			counterSteps = -1;
		
		} else
			callback(this, name, obj);
	}
	
	public int getCounterSteps() {
		return counterSteps;
	}
	
	public String getName() {
		return name;
	}

	private boolean isReservedWord(String word) {
		for(String reservedWord: RESERVED_WORDS)
			if(reservedWord.equals(word))
				return true;
		
		return false;
	}
	
	public void callback(Triggers trigger, String name, Object... obj) {}
	
	public boolean isGo() {
		return go;
	}

	public void setGo(boolean go) {
		this.go = go;
		if(!go) waitThread();
	}

	//Ao chamar essa função as alterações (onChange) não serão mais capturadas
	//E terminará o processo sem mostrar o resultado
	public void finishProcess() {
		//Para o "tracamento" do thread
		this.finishProcess = true;
		
		//Continua os demais passos
		this.setGo(false);
	}
	
	public void restartProcess() {}
	
	//Força que a operação pare
	private void waitThread() {
		while(!go && !finishProcess) {
			try {
				Thread.sleep(100);
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
	}
	
}
