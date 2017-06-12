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

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/beans.xml"})
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
		 
		 	Shop shopToEdit = computerShop.findShopByName("Saturn");
		 	shopToEdit.setName("Saturn Premium");
		 	computerShop.editShop(shopToEdit);
	        assertEquals("Saturn Premium",computerShop.findShopByName("Saturn Premium").getName());
	       
	    }
	 
	 @Test
	    @DatabaseSetup("/dataset.xml")
	    @ExpectedDatabase(value = "/dataset-deleteShop.xml",
	            assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void deleteShopTest() {
		 
		 	assertEquals(2, computerShop.getAllShops().size());

	        Shop shop = computerShop.findShopByName("Saturn");
	        computerShop.deleteShop(shop);
	        assertEquals(1, computerShop.getAllShops().size());
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
	        assertEquals(3, computerShop.getAllComputers().size());
	        Shop shop = computerShop.findShopById(3);
	        Computer comp = computerShop.getComputerById(1);

	        Long shopId = shop.getId();
	        Long compId  = comp.getId();

	        assertEquals(2,shop.getComputers().size());
	        computerShop.sellComputer(shopId, compId);
	        assertEquals(1,shop.getComputers().size());
	        assertEquals(comp.getPrice(),shop.getRevenue());
	        
	    }
}
