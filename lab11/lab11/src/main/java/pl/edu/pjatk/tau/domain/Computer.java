package pl.edu.pjatk.tau.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Computer.findAll", query = "Select c from Computer c"),
        @NamedQuery(name = "Computer.byId", query = "Select c from Computer  c where c.id = :id"),
        @NamedQuery(name = "Computer.byMark", query = "Select c from Computer  c where c.mark = :mark"),
        @NamedQuery(name = "Computer.byPrice", query = "Select c from Computer  c where c.price = :price")
})

public class Computer {
	
	private long id; 
	private String mark;
	private int price; 
	private String description;
	
	
	public Computer(int id, String mark, int price, String description){
		super();
		this.id = id;
		this.mark = mark;
		this.price = price;
		this.description = description;
	}
	
	public Computer(String mark, int price, String description){
		super();
		this.mark = mark;
		this.price = price;
		this.description = description;
	}
	
	public Computer(){
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId(){
		return id;
	}
	
	 public void setId(Long id) {
	        this.id = id;
	    }
	
	public String getMark(){
		return mark;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setMark(String mark){
		this.mark = mark;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
}

