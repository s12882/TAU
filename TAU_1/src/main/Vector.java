package main;
import java.util.List;

public interface Vector {
	
	public Vector add(Vector a);
	public Vector addVectors(Vector a, Vector b);
	public void setVector(List<Integer> a);
    public List<Integer> getVector();	

}
