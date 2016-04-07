
/**
 * The Class Order.
 */
public class Order implements Anonymous {

	/** The volume. */
	private int volume;
	
	/** The id. */
	private String id; 
	
	/** The price. */
	private double price;
	
	/** The next. */
	Order next; 
	
	/** The prev. */
	Order prev;
	
	/**
	 * Instantiates a new order.
	 *
	 * @param id the id
	 * @param price the price
	 * @param volume the volume
	 */
	public Order(String id, double price, int volume){
		this.volume = volume; 
		this.id = id; 
		this.price = price; 
	}
	
	/**
	 * Instantiates a new order.
	 */
	public Order(){
		this.volume = 0; 
		this.id = ""; 
		this.price = 0.0; 
	}
	
	/**
	 * Gets the volume.
	 *
	 * @return the volume
	 */
	public int getVolume(){
		return volume;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId(){
		return id; 
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice(){
		return price; 
	}
	
	/**
	 * Sets the volume.
	 *
	 * @param volume the new volume
	 */
	public void setVolume(int volume){
		this.volume = volume; 
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id){
		this.id = id; 
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price){
		this.price = price; 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return(" " +this.price+ " " + this.volume);
	}
	
	
	/* (non-Javadoc)
	 * @see Anonymous#printFullDetails()
	 */
	public String printFullDetails(){
		return   " " + id + " " + price + " " + volume;
	}
}
