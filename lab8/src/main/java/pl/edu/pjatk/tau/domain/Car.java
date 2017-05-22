package pl.edu.pjatk.tau.domain;

public class Car {
	
	private long id; 
	private String mark;
	private int price; 
	private String description;
	
	
	public Car(int id, String mark, int price, String description){
		super();
		this.id = id;
		this.mark = mark;
		this.price = price;
		this.description = description;
	}
	
	public Car(){
		
	}
	
	public long getId(){
		return id;
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
	
	public void setId(long l){
		this.id = l;
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
