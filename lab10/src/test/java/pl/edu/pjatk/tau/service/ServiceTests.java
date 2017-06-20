package pl.edu.pjatk.tau.service;

import static org.junit.Assert.*;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.Test;
import pl.edu.pjatk.tau.service.ComputerServiceImpl;



	@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComputerServiceTest.class
})

public class ServiceTests {
	    @BeforeClass
	    public static void before() throws Exception {
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:hsql://localhost/workdb" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "SA" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );

	     
	        JdbcDatabaseTester databaseTester = new PropertiesBasedJdbcDatabaseTester();
	        new ComputerServiceImpl(databaseTester.getConnection().getConnection());

	        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(
	                ServiceTests.class.getClassLoader().
	                        getResource("dataset-pm.xml").openStream()
	        );

	        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
	        databaseTester.setDataSet(dataSet);
	        databaseTester.onSetup();
	    }

	    @AfterClass
	    public static void after() {
	    }
	    
	
}
