package pl.edu.pjatk.tau.service;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.*;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
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

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;


@RunWith(JUnit4.class)
public class ComputerServiceTest extends DBTestCase {


	ComputerService computerService = new ComputerServiceImpl();
	

	public ComputerServiceTest() throws SQLException {
	}
	
	 @Override
	    protected IDataSet getDataSet() throws Exception {
	        return this.getDataSet("dataset-pm-add.xml");
	    }

	private IDataSet getDataSet(String string) throws Exception {
		 URL url = getClass().getClassLoader().getResource("dataset-pm.xml");
	        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
	        return ret;
	}
	
	 protected DatabaseOperation getSetUpOperation() throws Exception {
	        return DatabaseOperation.INSERT;
	    }

	    protected DatabaseOperation getTearDownOperation() throws Exception {
	        return DatabaseOperation.TRUNCATE_TABLE;
	    }
	    
	    @Before
		public void setUp() throws Exception {
			super.setUp();
		}

	    @After
		public void tearDown() throws Exception {
			super.tearDown();
		}
	        
	@Test
	public void checkConnection() {
	    assertNotNull(computerService.getConnection());
	}
	
	@Test
	public void addingTest() throws Exception {
		Computer comp = new Computer("Apple", 5400, "Apple Laptop");
		assertEquals(1, computerService.addComputer(comp));
					
		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("Computer");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-adding.xml");
		ITable expectedTable = expectedDataSet.getTable("Computer");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void deleteTest() throws Exception{
		Computer comp = new Computer("Lenovo", 3000, "Lenovo Laptop");
		assertEquals(1,computerService.deleteComputer(comp));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("Computer");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-deleting.xml");
		ITable expectedTable = expectedDataSet.getTable("Computer");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void updateTest() throws Exception{
		Computer comp = new Computer("MSI", 4000,"MSI Laptop");
		assertEquals(1,computerService.editComputer(comp));

		IDataSet dbDataSet = this.getConnection().createDataSet();
		ITable actualTable = dbDataSet.getTable("Computer");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id"});
		IDataSet expectedDataSet = getDataSet("dataset-updating");
		ITable expectedTable = expectedDataSet.getTable("Computer");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void getComputerTest() throws Exception {
		Computer comp = new Computer("Apple", 5400, "Apple Laptop");
		assertEquals(1,computerService.addComputer(comp));

		assertEquals("Apple", computerService.getComputer(comp).getMark());
		assertEquals("Apple Laptop", computerService.getComputer(comp).getDescription());

		IDataSet expectedDataSet = getDataSet("dataset-pm-get.xml");
		ITable expectedTable = expectedDataSet.getTable("Computer");

		assertEquals(expectedTable.getValue(2,"mark"), computerService.getComputer(comp).getMark());
	}
	
}
