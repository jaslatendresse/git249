
public class OfferOrder extends Order {
	
	public OfferOrder(String id, double price, int volume){
		super(id, price, volume);
	}
	
	public OfferOrder(){
		super(); 
	}
	
	public String toString(){
		return("Offer: " + super.toString());
	}

	public String printFullDetails(){
		return super.printFullDetails();
	}
}
