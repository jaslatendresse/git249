

/**
 * The Class OfferOrder.
 */
public class OfferOrder extends Order {
	
	/**
	 * Instantiates a new offer order.
	 *
	 * @param id the id
	 * @param price the price
	 * @param volume the volume
	 */
	public OfferOrder(String id, double price, int volume){
		super(id, price, volume);
	}
	
	/**
	 * Instantiates a new offer order.
	 */
	public OfferOrder(){
		super(); 
	}
	
	/* (non-Javadoc)
	 * @see Order#toString()
	 */
	public String toString(){
		return("Offer: " + super.toString());
	}

	/* (non-Javadoc)
	 * @see Order#printFullDetails()
	 */
	public String printFullDetails(){
		return super.printFullDetails();
	}
}
