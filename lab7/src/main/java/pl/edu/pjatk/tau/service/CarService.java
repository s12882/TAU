package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Car;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
	
	 private Connection connection;

	    private String url = "jdbc:mysql://localhost:3306/repozetorium?"
	          + "user=Andrii&password=sql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	    private String createTableCars = "CREATE TABLE " +
	            "Car( `id` INT NOT NULL AUTO_INCREMENT , `mark` VARCHAR(350) NOT NULL , " +
	            "`price` INT NOT NULL , `description` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

	    private PreparedStatement addCarStmt;
	    private PreparedStatement deleteCarStmt;
	    private PreparedStatement editCarStmt;
	    private PreparedStatement selectCarStmt;
	    private PreparedStatement getAllCarsStmt;
	    private Statement statement;
	    
	    public CarService() throws SQLException{
	        connection = DriverManager.getConnection(url);
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
	            statement.executeUpdate(createTableCars);

	        addCarStmt = connection.prepareStatement("INSERT INTO cars (id,mark,date,content) VALUES (?,?,?,?)");
	        deleteCarStmt = connection.prepareStatement("UPDATE `cars` SET `mark`= ?,`price`= ?,`description`=? WHERE `id` = ?");
	        editCarStmt = connection.prepareStatement("DELETE FROM `cars` WHERE `id` = ?");
	        selectCarStmt = connection.prepareStatement("SELECT * FROM `cars` WHERE `id` = ?");
	        getAllCarsStmt = connection.prepareStatement("SELECT * FROM cars");
	        
	    }
	    
	    public Connection getConnection(){return connection;}

	    public int deleteCar(Car car) throws SQLException{
	        int count = 0;
	        try{
	            deleteCarStmt.setInt(1,car.getId());
	            count =  deleteCarStmt.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
	    }
	    
	    public int editCar(Car car) throws SQLException{
	        int count = 0;

	        try{
	        	editCarStmt.setString(1,car.getMark());
	        	editCarStmt.setInt(2,car.getPrice());
	        	editCarStmt.setString(3,car.getDescription());
	        	editCarStmt.setInt(4,car.getId());
	            count = editCarStmt.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }

	        return count;
	    }
	    
	    public int selectCar(Car car) throws SQLException{
	        int count = 0;

	        try{
	        	selectCarStmt.setInt(1,car.getId());
	            count = selectCarStmt.executeUpdate();

	        }catch (SQLException e){
	            e.printStackTrace();
	        }

	        return count;
	   }
	    
	    public List<Car> getAllCars(){
	        List<Car> cars = new ArrayList<Car>();

	        try{
	            ResultSet rs = getAllCarsStmt.executeQuery();
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

}
