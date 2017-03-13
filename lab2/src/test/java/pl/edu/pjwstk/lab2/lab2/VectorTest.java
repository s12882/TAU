package pl.edu.pjwstk.lab2.lab2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import junit.framework.Assert;
import org.junit.Test;
import junit.framework.TestCase;


public class VectorTest{

	@Test
	public void New2ElementsVectorCreating(){
		VectorImpl testVector = new VectorImpl();
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(1);
		vector.add(4);
		testVector.setVector(vector);
		assertFalse(testVector.getVector().isEmpty());
	}
	
	@Test(expected = RuntimeException.class)
	public void NewEmptyVectorCreatingExceptionThrown(){
		VectorImpl testVector = new VectorImpl();
		List<Integer> emptyList = new ArrayList<Integer>(); 
		testVector.setVector(emptyList);
		assertTrue(testVector.getVector().isEmpty());
	}
	
	@Test
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
		assertFalse(testVector.getVector().isEmpty());
		
	}
	
	@Test(expected = RuntimeException.class)
	public void addVectorToCurrentWithDifferentSizesExceptionThrown(){
		
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		ArrayList<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		list1.add(7);
		list1.add(1);
		
		VectorImpl testVector = new VectorImpl(2, list);
		VectorImpl testVector1 = new VectorImpl(4, list1);
		
		testVector1.add(testVector);		
	}
	
	@Test
	public void addTwoVectors(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		Vector testVectorResult = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(3);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		testVectorResult = testVectorResult.addVectors(testVector, testVector1);	
		assertTrue(testVectorResult.getVector().get(0) == (list.get(0)+list1.get(0)) 
				&& testVectorResult.getVector().get(1) == (list.get(1)+list1.get(1)));
				
	}
	
	@Test(expected = RuntimeException.class)
	public void addTwoVectorsWithDefferentSizesExceptionThrown(){
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		Vector testVectorResult = new VectorImpl();
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
		
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		list1.add(4);
		
		testVector.setVector(list);
		testVector1.setVector(list1);
		
		assertFalse(testVector.getVector().size() == testVector1.getVector().size());
		testVectorResult = testVectorResult.addVectors(testVector, testVector1);
		assertTrue(testVectorResult.getVector().isEmpty());
	}
	
	@Test
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
		assertFalse(testVector.getVector().isEmpty());
		
		testVector1.setVector(list1);
		assertFalse(testVector1.getVector().isEmpty());
		
		testVector.sub(testVector1);
		assertFalse(testVector.getVector().isEmpty());
	}
	
	@Test(expected = RuntimeException.class)
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

	@Test
	public void subTwoVectors(){
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
	
		assert(testVector.getVector().size() == testVector1.getVector().size());
		testVectorResult.subVectors(testVector, testVector1);	
	}

	@Test(expected = RuntimeException.class)
	public void subTwoVectorsWithDefferentSizesExceptionThrown(){
		
		VectorImpl testVector = new VectorImpl();
		VectorImpl testVector1 = new VectorImpl();
		VectorImpl testVectorResult = new VectorImpl();
	
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(2);
		list.add(6);
	
		List<Integer> list1 = new ArrayList<Integer>(); 
		list1.add(5);
		list1.add(1);
		list1.add(4);
	
		testVector.setVector(list);
		testVector1.setVector(list1);
	
		testVectorResult.subVectors(testVector, testVector1);	
	}
	
	@Test
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
