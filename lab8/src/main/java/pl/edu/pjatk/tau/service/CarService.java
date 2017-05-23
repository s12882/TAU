package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Car;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
	
	 private Connection connection;

	 private String url = "jdbc:mysql://localhost:3306/cars?";
	 
	    private String createTable = "CREATE TABLE " + "Car( `id` INT NOT NULL AUTO_INCREMENT , `mark` VARCHAR(350) NOT NULL , " +
	            "`price` INT NOT NULL , `description` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

	    private PreparedStatement addCar;
	    private PreparedStatement deleteCar;
	    private PreparedStatement editCar;
	    private PreparedStatement selectCar;
	    private PreparedStatement getAllCars;
	    private Statement statement;
	    
	    public CarService() throws SQLException{
	    	connection = DriverManager.getConnection(url, "root", "");
	        statement = connection.createStatement();

	        ResultSet rs = connection.getMetaData().getTables(null, null, "%",
	                null);

	        boolean tableExist = false;

	        while (rs.next()){
	            if("cars".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
	                tableExist = true;
	                break;
	            }
	        }

	        if(!tableExist)
	            statement.executeUpdate(createTable);

	        addCar = connection.prepareStatement("INSERT INTO cars (id,mark,price,description) VALUES (?,?,?,?)");
	        editCar = connection.prepareStatement("UPDATE `cars` SET `mark`= ?,`price`= ?,`description`=? WHERE `id` = ?");
	        deleteCar = connection.prepareStatement("DELETE FROM `cars` WHERE `id` = ?");
	        selectCar = connection.prepareStatement("SELECT * FROM `cars` WHERE `id` = ?");
	        getAllCars = connection.prepareStatement("SELECT * FROM cars");
	        
	    }
	    
	    public Connection getConnection(){return connection;}
	    
	    
	    public int addCar(Car car){
	        int count = 0;
	        try{
	        	addCar.setLong(1,car.getId());
	        	addCar.setString(2,car.getMark());
	        	addCar.setInt(3,car.getPrice());
	        	addCar.setString(4,car.getDescription());

	            count = addCar.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
	    }

	    public int deleteCar(Car car) throws SQLException{
	        int count = 0;
	        try{
	        	deleteCar.setLong(1,car.getId());
	            count =  deleteCar.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
	    }
	    
	    public int editCar(Car car) throws SQLException{
	        int count = 0;

	        try{
	        	editCar.setString(1,car.getMark());
	        	editCar.setInt(2,car.getPrice());
	        	editCar.setString(3,car.getDescription());
	        	editCar.setLong(4,car.getId());
	            count = editCar.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }

	        return count;
	    }
	    
	
	    public Car selectCar(int id) throws SQLException{
	        int count = 0;
	        Car c = new Car();
	        try{
	            selectCar.setInt(1,id);
	            ResultSet rs = selectCar.executeQuery();
	            while(rs.next()){
	                c.setId(rs.getInt("id"));
	                c.setMark(rs.getString("mark"));
	                c.setPrice(rs.getInt("price"));
	                c.setDescription(rs.getString("description"));
	                count = 1;
	            }
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return c;
	    }
	    public List<Car> getAllCars(){
	        List<Car> cars = new ArrayList<Car>();

	        try{
	            ResultSet rs = getAllCars.executeQuery();
	            while (rs.next()){
	                Car car = new Car();
	                car.setId(rs.getInt("id"));
	                car.setMark(rs.getString("mark"));
	                car.setPrice(rs.getInt("price"));
	                car.setDescription(rs.getString("description"));

	                cars.add(car);
	            }
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return cars;
	    }
	    
	    public void delete() throws SQLException{
	        connection.prepareStatement("DELETE FROM car").executeUpdate();
	    }
	    
	    public void clear() throws SQLException{
	        try{
	            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Cars");
	           if(stmt.executeUpdate() == 1){
	           }
	        }catch(SQLException e){
	            e.printStackTrace();
	        }
	    }
	    
	    

}
