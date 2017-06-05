package pl.edu.pjatk.tau.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.edu.pjatk.tau.domain.Computer;

public interface ComputerService {
	
	Connection getConnection();
    int addComputer(Computer computer) throws SQLException;
    public Computer getComputer(Computer comp) throws SQLException;
    int deleteComputer(Computer computer) throws SQLException;
    int editComputer(Computer computer) throws SQLException;
    Computer selectComputer(long l) throws SQLException;
    List<Computer> getAllComputers() throws  SQLException;
    int count() throws SQLException;
    public void clear() throws SQLException;

}
