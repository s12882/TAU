package pl.edu.pjatk.tau.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.edu.pjatk.tau.domain.Car;

public interface CarService {
	
	Connection getConnection();
    int addCar(Car car) throws SQLException;
    int deleteCar(Car car) throws SQLException;
    int editCar(Car car) throws SQLException;
    Car selectCar(long l) throws SQLException;
    List<Car> getAllCars() throws  SQLException;
    int count() throws SQLException;
    public void clear() throws SQLException;

}
