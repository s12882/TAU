package pl.edu.pjatk.tau.domain;

import java.util.List;
import javax.persistence.*;


@Entity
@NamedQueries({
@NamedQuery(name = "Shop.getAll", query = "Select sh from Shop sh"),
@NamedQuery(name = "Shop.byId", query = "Select sh from Shop sh where sh.id = :id"),
@NamedQuery(name="Shop.byName", query = "Select sh from Shop sh where sh.name = :name")
})
public class Shop {
	
	private long id; 
	private String name;
	private int dayRevenue;
	private List<Computer> computers;
	
	public Shop(){
		
	}
	
	public Shop(int id, String name){
		this.id = id; 
		this.name = name;
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId(){
		return id;
	}
	
	 public void setId(Long id) {
	        this.id = id;
	    }
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	 public int getRevenue(){
		 return dayRevenue;
	 }
	 
	 public void increseRevenue(int value){
		 this.dayRevenue += value;
		 
	 }
	
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    public List<Computer> getComputers() {
	        return computers;
	    }
	 
	 public void setComputers(List<Computer> computers) {
	        this.computers = computers;
	    }
	 
	


}
