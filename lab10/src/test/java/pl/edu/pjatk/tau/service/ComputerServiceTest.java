package pl.edu.pjatk.tau.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.*;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import pl.edu.pjatk.tau.domain.Computer;
import pl.edu.pjatk.tau.service.ComputerServiceImpl;
import pl.edu.pjatk.tau.service.ServiceTests;

import java.net.URL;


@RunWith(JUnit4.class)
public class ComputerServiceTest extends DBTestCase {
 
	ComputerService computerService;
	
	public ComputerServiceTest() throws Exception {
		super("ComputerServiceImpl test");
	}
	
	 protected DatabaseOperation getSetUpOperation() throws Exception {
	        return DatabaseOperation.INSERT;
	    }

	    protected DatabaseOperation getTearDownOperation() throws Exception {
	        return DatabaseOperation.TRUNCATE_TABLE;
	    }
	
	 @Override
	    protected IDataSet getDataSet() throws Exception {
	        return this.getDataSet("dataset-pm.xml");
	    }

	private IDataSet getDataSet(String string) throws Exception {
		 URL url = getClass().getClassLoader().getResource(string);
	        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
	        return ret;
	}
	
	 @After
		public void tearDown() throws Exception {	
			super.tearDown();		
		}    
	
	 @Before
     public void setUp() throws Exception {
         super.setUp();
         computerService = new ComputerServiceImpl(this.getConnection().getConnection());
     }
	   
	        
	@Test
	public void checkConnection() {
	    assertNotNull(computerService.getConnection());
	}
	
	@Test
	public void addingTest() throws Exception {
		Computer comp = new Computer("Apple", 5400, "Apple Laptop");
		Computer comp2 = new Computer(22, "Lenovo", 3000, "Lenovo Laptop 2");
		Computer comp3 = new Computer(23, "Lenovo", 3000, "Lenovo Laptop 3");
		Computer comp4 = new Computer(24, "Lenovo", 3000, "Lenovo Laptop 4");
		Computer comp5 = new Computer(25, "Lenovo", 3000, "Lenovo Laptop 5");
		Computer comp6 = new Computer(27, "MSI", 4700, "MSI Laptop 2");
		Computer comp7 = new Computer(28, "MSI", 4700, "MSI Laptop 3");
		Computer comp8 = new Computer(29, "MSI", 4700, "MSI Laptop 4");
		
		assertEquals(1, computerService.addComputer(comp));
		assertEquals(1, computerService.addComputer(comp2));
		assertEquals(1, computerService.addComputer(comp3));
		assertEquals(1, computerService.addComputer(comp4));
		assertEquals(1, computerService.addComputer(comp5));
		assertEquals(1, computerService.addComputer(comp6));
		assertEquals(1, computerService.addComputer(comp7));
		assertEquals(1, computerService.addComputer(comp8));
					
		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("computers");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-adding.xml");
		ITable expectedTable = expectedDataSet.getTable("computers");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void deleteTest() throws Exception{

		Computer comp2 = new Computer(22, "Lenovo", 3000, "Lenovo Laptop 2");
		Computer comp3 = new Computer(23, "Lenovo", 3000, "Lenovo Laptop 3");
		Computer comp4 = new Computer(24, "Lenovo", 3000, "Lenovo Laptop 4");
		Computer comp5 = new Computer(25, "Lenovo", 3000, "Lenovo Laptop 5");
		Computer comp6 = new Computer(26, "Lenovo", 3000, "Lenovo Laptop 6");
		Computer comp7 = new Computer(27, "MSI", 4700, "MSI Laptop 2");
		Computer comp8 = new Computer(28, "MSI", 4700, "MSI Laptop 3");
		Computer comp9 = new Computer(29, "MSI", 4700, "MSI Laptop 4");
	
		
		computerService.addComputer(comp2);
		computerService.addComputer(comp3);
		computerService.addComputer(comp4);
		computerService.addComputer(comp5);
		computerService.addComputer(comp6);
		computerService.addComputer(comp7);
		computerService.addComputer(comp8);
		computerService.addComputer(comp9);
			
		assertEquals(1,computerService.deleteComputer(comp4));
		assertEquals(1,computerService.deleteComputer(comp5));
		assertEquals(1,computerService.deleteComputer(comp6));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("computers");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-deleting.xml");
		ITable expectedTable = expectedDataSet.getTable("computers");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void updateTest() throws Exception{
		Computer comp = new Computer(21, "MSI", 4500, "MSI Laptop");
		Computer comp2 = new Computer(22, "Lenovo", 3000, "Lenovo Laptop 2");
		Computer comp3 = new Computer(23, "Lenovo", 3000, "Lenovo Laptop 3");
		Computer comp4 = new Computer(24, "Lenovo", 3000, "Lenovo Laptop 4");
		Computer comp5 = new Computer(25, "Lenovo", 3000, "Lenovo Laptop 5");
		Computer comp6 = new Computer(27, "MSI", 4700, "MSI Laptop 2");
		Computer comp7 = new Computer(28, "MSI", 4700, "MSI Laptop 3");
		Computer comp8 = new Computer(29, "MSI", 4700, "MSI Laptop 4");
		
		Computer edited = new Computer(21, "MSI", 4000, "MSI Laptop");
		Computer edited2 = new Computer(22, "Lenovo", 3200, "Lenovo Laptop 2");
		Computer edited3 = new Computer(23, "Lenovo", 3200, "Lenovo Laptop 3");
		
		assertEquals(1, computerService.addComputer(comp));
		assertEquals(1, computerService.addComputer(comp2));
		assertEquals(1, computerService.addComputer(comp3));
		assertEquals(1, computerService.addComputer(comp4));
		assertEquals(1, computerService.addComputer(comp5));
		assertEquals(1, computerService.addComputer(comp6));
		assertEquals(1, computerService.addComputer(comp7));
		assertEquals(1, computerService.addComputer(comp8));
		
		assertEquals(1,computerService.editComputer(edited));
		assertEquals(1,computerService.editComputer(edited2));
		assertEquals(1,computerService.editComputer(edited3));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("computers");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-updating.xml");
		ITable expectedTable = expectedDataSet.getTable("computers");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void getComputerTest() throws Exception {
		Computer comp = new Computer("Apple", 5400, "Apple Laptop");
		assertEquals(1,computerService.addComputer(comp));

		assertEquals("Apple", computerService.getComputer(comp).getMark());
		assertEquals("Apple Laptop", computerService.getComputer(comp).getDescription());

		IDataSet expectedDataSet = getDataSet("dataset-pm-get.xml");
		ITable expectedTable = expectedDataSet.getTable("computers");

		assertEquals(expectedTable.getValue(2,"mark"), computerService.getComputer(comp).getMark());
	}
	
}
