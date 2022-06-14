package utils.set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Conjunto extends TreeSet<Object>{
	
	//Primeiro elemento a ser adicionado
	private String representative;
	
	public String getRepresentative() {
		return representative;
	}
	
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	
	@Override
	public boolean add(Object obj) {
		if(representative == null)
			representative = obj.toString();
		
		return super.add(obj);
	}

	@Override
	public String toString() {
		return super.toString() + " --> Representative: " + getRepresentative();
	}
	
}
