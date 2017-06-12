package pl.edu.pjatk.tau.service;

import java.util.List;

import pl.edu.pjatk.tau.domain.Computer;
import pl.edu.pjatk.tau.domain.Shop;

public interface ComputerShop {
	
	void addShop(Shop shop);
    void editShop(Shop shop);
    void deleteShop(Shop shop);
    Shop findShopById(long l);
    Shop findShopByName(String name);
    List<Shop> getAllShops();
	
	Long addNewComputer(Computer comp);
	List<Computer> getAllComputers();
	void updateComputer(Computer comp);
	void deleteComputer(Computer comp);
	
	Computer getComputerById(int id);
	Computer getComputerByMark(String mark);
	Computer getComputerByPrice(int price);
	
	void sellComputer(Long idShop, Long idComputer);

}
