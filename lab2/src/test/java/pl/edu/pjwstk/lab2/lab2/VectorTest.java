package pl.edu.pjwstk.lab2.lab2;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;

public class VectorTest {

	public void New2ElementsVectorCreatingNoException(){
		VectorImpl testVector = new VectorImpl();
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(1);
		vector.add(4);
		testVector.setVector(vector);
	}
	
	public void NewEmptyVectorCreatingExceptionThrown(){
		VectorImpl testVector = new VectorImpl();
		List<Integer> emptyVector = new ArrayList<Integer>(); 
		testVector.setVector(emptyVector);
	}
		
	public void addVectorToCurrent(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(2);
		vector.add(6);
		
		List<Integer> vector1 = new ArrayList<Integer>(); 
		vector.add(5);
		vector.add(1);
		
		testVector.setVector(vector);
		testVector1.setVector(vector1);
		
		testVector.add(testVector1);	
		
	}
	
	public void addVectorToCurrentWithDifferentSizes(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(2);
		vector.add(6);
		
		List<Integer> vector1 = new ArrayList<Integer>(); 
		vector.add(5);
		vector.add(1);
		vector.add(7);
		
		testVector.setVector(vector);
		testVector1.setVector(vector1);
		
		testVector.add(testVector1);		
	}
	
	public void addTwoVectors(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		VectorImpl testVectorResult = new VectorImpl();
		
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(2);
		vector.add(6);
		
		List<Integer> vector1 = new ArrayList<Integer>(); 
		vector.add(5);
		vector.add(1);
		
		testVector.setVector(vector);
		testVector1.setVector(vector1);
		
		testVectorResult.addVectors(testVector, testVector1);	
	}
	
	public void getVector(){
		VectorImpl testVector = new VectorImpl();		
		List<Integer> vector = new ArrayList<Integer>(); 
		
		vector.add(2);
		vector.add(6);
		
		testVector.setVector(vector);
		testVector.getVector();
		
	}

}
