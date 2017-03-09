package pl.edu.pjwstk.lab2.lab2;

import java.util.List;

public interface Vector {
	
	public void add(Vector a);
	public Vector addVectors(Vector a, Vector b);
	public void setVector(List<Integer> a);
    public List<Integer> getVector();	

}
