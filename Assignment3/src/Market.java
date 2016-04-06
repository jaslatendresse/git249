
public class Market {
	static OrderBook book = new OrderBook();
	
	public static void main(String[] args) {
		
		book.execute(new OfferOrder("1", 155, 200));
      
        book.execute(new BidOrder("1", 146.6, 100));
  
        book.execute(new BidOrder("1", 146.5, 50));
 
        book.execute(new OfferOrder("1", 152.5, 120));
  
        book.execute(new BidOrder("1", 148, 75));
 
        book.execute(new OfferOrder("1", 152, 100));
 
        book.execute(new BidOrder("1", 147, 200));

        
	   OrderInput windowInput = new OrderInput();
	   
	}
}
