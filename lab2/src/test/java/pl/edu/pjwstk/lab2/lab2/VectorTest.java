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
		List<Integer> emptyList = new ArrayList<Integer>(); 
		testVector.setVector(emptyList);
	}
	
	public void addVectorToCurrent(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		testVector.add(testVector1);	
		
	}
	
	public void addVectorToCurrentWithDifferentSizesExceptionThrown(){
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
		vector1.add(5);
		vector1.add(1);
		
		testVector.setVector(vector);
		testVector1.setVector(vector1);
		
		testVectorResult.addVectors(testVector, testVector1);	
	}
	
	public void addTwoVectorsWithDefferentSizesExceptionThrown(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		VectorImpl testVectorResult = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		testVectorResult.addVectors(testVector, testVector1);	
	}
	
	public void subVectorFromCurrent(){
		
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		testVector.sub(testVector1);		
	}
	
public void subVectorFromCurrentDefferentSizesExceptionThrown(){
		
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		list1.add(3);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		testVector.sub(testVector1);		
	}
	
	public void getVector(){
		VectorImpl testVector = new VectorImpl();		
		List<Integer> list = new ArrayList<Integer>(); 
		List<Integer> resultlist = new ArrayList<Integer>(); 
		
		list.add(2);
		list.add(6);
		
		testVector.setVector(list);
		resultlist = testVector.getVector();		
	}

}
