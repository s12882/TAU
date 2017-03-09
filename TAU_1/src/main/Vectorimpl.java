package main;
import java.util.ArrayList;
import java.util.List;

public class Vectorimpl implements Vector {
	
	public int vectorSize;
	public ArrayList<Integer> vector;

	public Vectorimpl(int vectorSize,  ArrayList<Integer> vector ){
		this.vectorSize = vectorSize;
		this.vector = vector;
	}
	
	public Vectorimpl(){
		
	}
	
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

	public Vector setVector(List<Integer> a){
	
		ArrayList<Integer> result = new ArrayList<Integer>(a);
		if(result.isEmpty()){
			throw new RuntimeException("Emppty List");
		}else{
			Vectorimpl vect = new Vectorimpl(result.size(), result);		
			return vect;	
		}		
	}
	
	public void setVectorVoid(List<Integer> a){
		
		ArrayList<Integer> result = new ArrayList<Integer>(a);
		if(result.isEmpty()){
			throw new RuntimeException("Emppty List");
		}else{
			this.vectorSize = result.size();
			this.vector = result;							
		}		
	}

	public List<Integer> getVector() {
		ArrayList<Integer> result = new ArrayList<Integer>(vector);
		return result;
	}
	
	public List<Integer> getVectorBy(Vectorimpl v) {
		System.out.println(v.vector.size());
		ArrayList<Integer> result = new ArrayList<Integer>(v.vector);
		return result;
	}
	
}
