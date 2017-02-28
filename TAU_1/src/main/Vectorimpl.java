package main;
import java.util.ArrayList;
import java.util.List;

public class Vectorimpl implements Vector {
	
	public int vectorSize;
	public ArrayList<Integer> vector;

	public Vectorimpl(){
		
	}
	
	@Override
	public Vector add(Vector a) {
		if(this.vectorSize != a.getVector().size()){
			 throw new RuntimeException("Vectors have different sizes");
		}else{
		
			for(int i=1; i<=this.vectorSize; i++){
				int value = this.vector.get(i) + a.getVector().get(i);
				this.vector.set(i, value);
			}	
		}
		return this;	
	}

	
	public Vector addVectors(Vector a, Vector b){
		
		Vectorimpl c = new Vectorimpl();
		List<Integer> vectorC = new ArrayList<Integer>();
		
		if(a.getVector().size() != b.getVector().size()){
			 throw new RuntimeException("Vectors have different sizes");
		}else{	
			for(int i=1; i<=a.getVector().size(); i++){
				int value = a.getVector().get(i) + b.getVector().get(i);
				vectorC.set(i, value);
			}	
			c.setVector(vectorC);
		}
		return c;		
	}

	public void setVector(List<Integer> a){
		this.vectorSize = a.size();
		for(int i=1; i<=a.size(); i++){
			this.vector.set(i, a.get(i));
		}	
	}

	@Override
	public List<Integer> getVector() {
		List<Integer> result = new ArrayList<Integer>();
		result = new ArrayList<Integer>(this.vector);
		return result;
	}
	
}
