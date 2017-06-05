package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Computer;

import javax.xml.transform.Result;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
	
	 private Connection connection;

	 private String url = "jdbc:mysql://localhost:3306/cars?";
	 
	    private String createTable = "CREATE TABLE " + "Car( `id` LONG NOT NULL AUTO_INCREMENT , `mark` VARCHAR(350) NOT NULL , " +
	            "`price` INT NOT NULL , `description` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

	    private PreparedStatement addComputer;
	    private PreparedStatement deleteComputer;
	    private PreparedStatement editComputer;
	    private PreparedStatement selectComputer;
	    private PreparedStatement getAllComps;
	    private PreparedStatement getComputer;
	    private Statement statement;
	    
	    public ComputerServiceImpl() throws SQLException{
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

	        addComputer = connection.prepareStatement("INSERT INTO cars (id,mark,price,description) VALUES (?,?,?,?)");
	        editComputer = connection.prepareStatement("UPDATE `cars` SET `mark`= ?,`price`= ?,`description`=? WHERE `id` = ?");
	        deleteComputer = connection.prepareStatement("DELETE FROM `cars` WHERE `id` = ?");
	        selectComputer = connection.prepareStatement("SELECT * FROM `cars` WHERE `id` = ?");
	        getComputer = connection.prepareStatement("SELECT * FROM `cars` WHERE `mark` = ?");
	        getAllComps = connection.prepareStatement("SELECT * FROM cars");
	        
	    }
	    
	    public Connection getConnection() {
	        return connection;
	    }
	    
	    
	    public int addComputer(Computer computer){
	        int count = 0;
	        try{
	        	addComputer.setLong(1,computer.getId());
	        	addComputer.setString(2,computer.getMark());
	        	addComputer.setInt(3,computer.getPrice());
	        	addComputer.setString(4,computer.getDescription());

	            count = addComputer.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
	    }

	    public int deleteComputer(Computer computer) throws SQLException{
	        int count = 0;
	        try{
	        	deleteComputer.setLong(1,computer.getId());
	            count = deleteComputer.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
	    }
	    
	    public int editComputer(Computer computer) throws SQLException{
	        int count = 0;

	        try{
	        	editComputer.setString(1,computer.getMark());
	        	editComputer.setInt(2,computer.getPrice());
	        	editComputer.setString(3,computer.getDescription());
	        	editComputer.setLong(4,computer.getId());
	            count = editComputer.executeUpdate();
	        }catch (SQLException e){
	            e.printStackTrace();
	        }

	        return count;
	    }
	    
	
	    public Computer selectComputer(long l) throws SQLException{
	        int count = 0;
	        Computer c = new Computer();
	        try{
	            selectComputer.setLong(1,l);
	            ResultSet rs = selectComputer.executeQuery();
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
	    
	  //GET
		public Computer getComputer(Computer comp) throws SQLException {
			Computer c = null;
			int count = 0;
			try {
				getComputer.setString(1,comp.getMark());
				ResultSet rs = getComputer.executeQuery();

				while (rs.next()) {
					c = new Computer();
					   c.setId(rs.getInt("id"));
		                c.setMark(rs.getString("mark"));
		                c.setPrice(rs.getInt("price"));
		                c.setDescription(rs.getString("description"));
					//count = 1;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return c;
		}
	    
	    public List<Computer> getAllComputers(){
	        List<Computer> computers = new ArrayList<Computer>();

	        try{
	            ResultSet rs = getAllComps.executeQuery();
	            while (rs.next()){
	                Computer computer = new Computer();
	                computer.setId(rs.getInt("id"));
	                computer.setMark(rs.getString("mark"));
	                computer.setPrice(rs.getInt("price"));
	                computer.setDescription(rs.getString("description"));

	                computers.add(computer);
	            }
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return computers;
	    }
	    
	    public void delete() throws SQLException{
	        connection.prepareStatement("DELETE FROM car").executeUpdate();
	    }
	    
	    
	    public int count() throws SQLException {
	        int count = 0;
	        try{
	            ResultSet rs = statement.executeQuery("SELECT count(*) as numberOfCars FROM cars");
	            while(rs.next()){
	                count = rs.getInt("numberOfCars");
	            }
	        }catch (SQLException e){
	            e.printStackTrace();
	        }
	        return count;
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
