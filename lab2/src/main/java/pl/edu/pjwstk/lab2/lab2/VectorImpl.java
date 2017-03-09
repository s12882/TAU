package pl.edu.pjwstk.lab2.lab2;

import java.util.ArrayList;
import java.util.List;


public class VectorImpl  implements Vector {
	
	public int vectorSize;
	public ArrayList<Integer> vector;

	public VectorImpl(int vectorSize,  ArrayList<Integer> vector ){
		this.vectorSize = vectorSize;
		this.vector = vector;
	}
	
	public VectorImpl(){
		
	}

	public void add(Vector a) {
		
		if(this.vectorSize != a.getVector().size()){
			 throw new RuntimeException("Vectors have different sizes");
		}else{
		
			for(int i=0; i<this.vectorSize; i++){
				int value1 = this.vector.get(i);
				int value2 = a.getVector().get(i);
				int result = value1+value2;
				this.vector.set(i, result);
			}	
		}
	}

	public Vector addVectors(Vector a, Vector b) {
		
		Vector c = new VectorImpl();
		List<Integer> vectorC = new ArrayList<Integer>();
		
		if(a.getVector().size() != b.getVector().size()){
			 throw new RuntimeException("Vectors have different sizes");
		}else{	
			for(int i=0; i<a.getVector().size(); i++){
				int value = a.getVector().get(i) + b.getVector().get(i);
				vectorC.set(i, value);
			}	
			c.setVector(vectorC);
		}
		return c;
	}

	public void setVector(List<Integer> a) {

		ArrayList<Integer> result = new ArrayList<Integer>(a);
		if(result.isEmpty()){
			throw new RuntimeException("Emppty List");
		}else{
			this.vectorSize = result.size();
			this.vector = result;							
		}				
	}

	public List<Integer> getVector() {
		// TODO Auto-generated method stub
		return null;
	}

}
