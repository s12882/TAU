package pl.edu.pjatk.tau.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import pl.edu.pjatk.tau.domain.Computer;
import pl.edu.pjatk.tau.domain.Shop;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
@Rollback
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ComputerShopTest {
	
	@Autowired
	ComputerShop computerShop;
	
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    @ExpectedDatabase(value = "/dataset-addShop.xml",
	            assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void addShopTest() {
		 
		 Shop shop = new Shop();
		 shop.setName("MediaMarkt");
		 computerShop.addShop(shop);

	        assertEquals(3, computerShop.getAllShops().size());
	        assertEquals("MediaMarkt",computerShop.findShopByName("MediaMarkt").getName());
	    }
	 
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    @ExpectedDatabase(value = "/dataset-editShop.xml",
	            assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void editShopTest() {
		 
		 Shop shopToEdit = new Shop();
		 shopToEdit.setName("MediaMarkt");
		 computerShop.addShop(shopToEdit);

		 	assertEquals("MediaMarkt", computerShop.findShopById(shopToEdit.getId()));

		 	shopToEdit.setName("MediaMarkt Premium");
		 	computerShop.editShop(shopToEdit);
	        assertEquals("MediaMarkt",computerShop.findShopByName("MediaMarkt Premium").getName());
	       
	    }
	 
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    @ExpectedDatabase(value = "/dataset-deleteShop.xml",
	            assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void deleteShopTest() {
		 
		 Shop shop = new Shop();
		 shop.setName("AppleOfficial");
		 computerShop.addShop(shop);
	        assertEquals(3,computerShop.getAllShops().size());
	        assertEquals("AppleOfficial",computerShop.findShopById(2).getName());
	        computerShop.deleteShop(shop);
	        assertEquals(2,computerShop.getAllShops().size());
	    }
	 
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    public void selectShopTest(){
	        assertEquals(2,computerShop.getAllShops().size());
	        assertEquals("AppleOfficial",computerShop.findShopByName("AppleOfficial").getName());
	    }
	 
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    public void sellComputerTest(){
	        assertEquals(2, computerShop.getAllComputers().size());
	        Shop shop = computerShop.findShopById(3);
	        Computer comp = computerShop.getComputerById(1);

	        Long shopId = shop.getId();
	        Long compId  = comp.getId();

	        computerShop.sellComputer(shopId, compId);
	        assertEquals(0,shop.getComputers().size());
	        assertEquals(comp.getPrice(),shop.getRevenue());
	        
	    }
}
