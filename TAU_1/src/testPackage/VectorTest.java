package testPackage;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import main.Vectorimpl;

public class VectorTest {

	@Test(expected = RuntimeException.class)
	public void New2ElementsVectorCreating(){
		Vectorimpl testVector = new Vectorimpl();
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(1);
		vector.add(4);
		testVector.setVector(vector);
	}
	
	@Test(expected = RuntimeException.class)
	public void addVectorToCurrent(){
		Vectorimpl testVector = new Vectorimpl();
		Vectorimpl testVector1 = new Vectorimpl();
		
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
	
	@Test(expected = RuntimeException.class)
	public void addVectorToCurrentWithDifferentSizes(){
		Vectorimpl testVector = new Vectorimpl();
		Vectorimpl testVector1 = new Vectorimpl();
		
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
	
	@Test(expected = RuntimeException.class) 
	public void addTwoVectors(){
		Vectorimpl testVector = new Vectorimpl();
		Vectorimpl testVector1 = new Vectorimpl();
		Vectorimpl testVectorResult = new Vectorimpl();
		
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
	
	@Test(expected = RuntimeException.class)
	public void getVector(){
		Vectorimpl testVector = new Vectorimpl();
		
		List<Integer> vector = new ArrayList<Integer>(); 
		vector.add(2);
		vector.add(6);		
		testVector.setVector(vector);
		testVector.getVector();
	}

}
