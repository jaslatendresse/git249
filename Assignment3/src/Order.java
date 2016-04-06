public class Order implements Anonymous {

	private int volume;
	private String id; 
	private double price;
	
	Order next; 
	Order prev;
	
	public Order(String id, double price, int volume){
		this.volume = volume; 
		this.id = id; 
		this.price = price; 
	}
	
	public Order(){
		this.volume = 0; 
		this.id = ""; 
		this.price = 0.0; 
	}
	
	public int getVolume(){
		return volume;
	}
	
	public String getId(){
		return id; 
	}
	
	public double getPrice(){
		return price; 
	}
	
	public void setVolume(int volume){
		this.volume = volume; 
	}
	
	public void setId(String id){
		this.id = id; 
	}
	
	public void setPrice(double price){
		this.price = price; 
	}
	
	public String toString(){
		return(" " +this.price+ " " + this.volume);
	}
	
	
	public String printFullDetails(){
		return   " " + id + " " + price + " " + volume;
	}
}
