
public class Market {
	static OrderBook placedOrders = new OrderBook();
	
	public static void main(String[] args) {
		
		placedOrders.add(new BidOrder("1", 148, 75));
        placedOrders.add(new BidOrder("1", 147, 200));
        placedOrders.add(new BidOrder("1", 146.6, 100));
        placedOrders.add(new BidOrder("1", 146.5, 50));
        placedOrders.add(new OfferOrder("1", 155, 200));
        placedOrders.add(new OfferOrder("1", 152.5, 120));
        placedOrders.add(new OfferOrder("1", 152, 100));
        placedOrders.add(new OfferOrder("1", 1, 300));
       
        System.out.println(placedOrders.outputBBO());
        System.out.println();
        placedOrders.matchingEngine();
        System.out.println();
	    System.out.println(placedOrders.outputBook());
	    
	   OrderInput windowInput = new OrderInput();
	   
	}
}
