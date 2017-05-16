package pl.edu.pjatk.tau;

import static org.junit.Assert.*;

import java.sql.SQLException;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.service.CarService;

import org.junit.After;
import org.junit.Test;

public class CarServiceTest {
	
	 public CarServiceTest() throws  SQLException{
		 
	  }
	
	CarService carService = new CarService();
	
	
	@After
    public void cleanup() throws SQLException {
       carService.delete();
    }

    @Test
    public void AddingNewCarTest() throws SQLException{
        carService.delete();
        Car car = new Car(55, "Opel", 12000, "Opel Meriva 2007r.");
        assertEquals(1, carService.addCar(car));
    }
    
    @Test
    public void editCarTest() throws SQLException{
    	Car car = new Car(57, "Opel", 12000, "Opel Meriva 2007r.");
    	carService.addCar(car);
    	car.setPrice(13000);
        assertEquals(1,carService.editCar(car));
    }
    
    @Test
    public void deleteCarTest() throws SQLException{
    	carService.delete();
    	Car car = new Car(55, "Opel", 12000, "Opel Meriva 2007r.");
    	carService.addCar(car);
        assertEquals(1,carService.deleteCar(car));
    }
    
}
