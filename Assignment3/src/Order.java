public class Order implements Anonymous {

	private int volume;
	private String id; 
	private double price;
	
	
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
		return(" " + Math.abs(this.price)+ " " + this.volume);
	}
	
	
	public String printFullDetails(){
		return   " " + id + " " + Math.abs(price) + " " + volume;
	}
	
	/*If the volume of instance variable is smaller than 
	 * the volume of the order at a particular index
	 * the volume of instance variable is decremented by 
	 * the volume of order at a particular index
	 */
	public boolean subVolume(int volumeAtIndex){
		if(this.volume >= volumeAtIndex){
			this.volume = this.volume - volumeAtIndex;
			return true;
		}
		else
			return false; 
	}
	
}
