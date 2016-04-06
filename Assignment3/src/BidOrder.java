
public class BidOrder extends Order {

	public BidOrder(String id, double price, int volume){
		super(id, price, volume);
	}
	
	public BidOrder(){
		super(); 
	}
	
	public String toString(){
		return("Bid: " + super.toString());
	}
	
	public String printFullDetails(){
		return super.printFullDetails();
	}
}
