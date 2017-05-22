package pl.edu.pjatk.tau.service;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.service.CarService;

public class CarServiceTest {

	CarService carService = new CarService();
	
	private final static String MARK_1 = "Opel";
	private final static int PRICE_1 = 12000;
	private final static String DESC_1 = "Test description";

	public CarServiceTest() throws SQLException {
	}

	@After
    public void cleanup() throws SQLException {
       carService.clear();
    }

	@Test
	public void checkConnection() {
	    assertNotNull(carService.getConnection());
	}
	
	@Test
	public void checkAdding() throws SQLException{
		Car car = new Car();
		car.setMark(MARK_1);
		car.setPrice(PRICE_1);
		car.setDescription(DESC_1);
		
		carService.clear();
		assertEquals(1,carService.addCar(car));
		
		List<Car> cars = carService.getAllCars();
		Car carRetrieved = cars.get(0);
		
		assertEquals(MARK_1, carRetrieved.getMark());
		assertEquals(PRICE_1, carRetrieved.getPrice());
		assertEquals(DESC_1, carRetrieved.getDescription());
	}

}
