
/**
 * The Class BidOrder.
 */
public class BidOrder extends Order {

	/**
	 * Instantiates a new bid order.
	 *
	 * @param id the id
	 * @param price the price
	 * @param volume the volume
	 */
	public BidOrder(String id, double price, int volume){
		super(id, price, volume);
	}
	
	/**
	 * Instantiates a new bid order.
	 */
	public BidOrder(){
		super(); 
	}
	
	/* (non-Javadoc)
	 * @see Order#toString()
	 */
	public String toString(){
		return("Bid: " + super.toString());
	}
	
	/* (non-Javadoc)
	 * @see Order#printFullDetails()
	 */
	public String printFullDetails(){
		return super.printFullDetails();
	}
}
