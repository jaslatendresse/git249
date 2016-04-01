
public class OrderBook2 {

	private Node tail; 
	private Node head; 
	private Node bestBid;
	private Node bestOffer;
	
	public OrderBook2(){
		this.bestBid = new Node(); 
		this.bestOffer = new Node(); 
		this.head = new Node(); 
		this.tail = new Node(); 
		this.head.next = this.tail; 
		this.tail.prev = this.head; 
	}
	
	private int getSize(){
		int size = -1; 
		for(Node node = head; node.next!=null; node = node.next)
			size++;
		return size;
	}
	
	public boolean isEmpty(){
		return getSize() == 0; 
	}
	
	private Node getNode(int i){
		i--;
		int size = getSize(); 
		if(i > size || i < 0){
			throw new IndexOutOfBoundsException();
		}
		else{
			Node node = head.getNext(); 
			
			for(int j = 0; j<i; j++){
				node = node.getNext(); 
			}
			return node; 
		}
	}
	
	private Order get(int i){
		return getNode(i+1).getOrder();
	}
	
	/*Removes an order at specific index*/
	private void remove(int index) {
	   int size = getSize(); 
	   Node temp; 
       /*Checks if index is out of bounds*/
	   if(index < 0 || index > size) {
		   throw new IndexOutOfBoundsException(); 
	   /* if index is not out of bounds, we create a node pointing
	    * at this index and link the next and previous nodes together*/
	   } else {
		   temp = new Node(getNode(index+1));
	       temp.getPrev().setNext(temp.next);
	       temp.getNext().setPrev(temp.prev);
	       }
	   }
	   
	/*Adds an order at specific index*/
	private void add(int index, Order o){
		int size = getSize(); 
		/*Checks if index is out of bounds*/
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		 /* If index is not out of bounds, create node at this index, link 
		  * the previous node to next and stores the order in a node with 
		  * its pointers*/
		else{
			Node next = getNode(index+1);
			Node prev = next.getPrev(); 
			Node temp = new Node(o, next, prev);
			
			prev.setNext(temp);
			next.setPrev(temp);
		}
	}
	
	public boolean isBid(Order o){
		if(o instanceof BidOrder){
			return true; 
		}
		else
			return false; 
	}
	
	/*Adds the order and keeps the list sorted*/
	public void add(Order o){
		
		int size = getSize(); 
		System.out.println("Adding > " + o.toString());
		/*If list is empty, size is 0, then we add the order at the beginning*/
		if(isEmpty()){
			add(size, o);
		}
		/*if price of order to be added is greater than price
		 * of last order, we add the order at the end of the list*/
		else if(o.getPrice() > get(size - 1).getPrice()){
			add(size, o);
		}
		/* if price of order to be added is neither greater than last price
		 * or list isn't empty, check if the price is smaller than every order
		 * in the list and add it at the index it corresponds to*/
		else{
			for(int i = 0; i<size; i++){
				if(o.getPrice() <= get(i).getPrice()){
					add(i, o);
					break; 
				}
			}
		}
		/*
		 * if the order to be added is of type BidOrder, 
		 * increment the index referencing to the bestOffer
		 */
		if(isBid(o)){
			bestOffer = bestOffer.next;
		}
	}
	
	public void matchingEngine(Order currentOrder){
		int size = getSize(); 
		for(int i = 0; i<size; i++){
			/*If the order at index of bestBid had a price greater or equal to
			 *an order at index bestOffer, then there is a match
			 *Use of Math.abs - Constructor of BidOrder sets price to negative to
			 *differentiate from OfferOrder in the list. For programmer use only*/
			if(Math.abs(bestBid.getOrder().getPrice()) >= bestOffer.getOrder().getPrice()){
				System.out.println();
				System.out.println("Match found");
				/*printing the full details of the bid and offer*/
				System.out.println(bestBid.getOrder().printFullDetails());
				System.out.println(bestOffer.getOrder().printFullDetails());
				
				if(bestOffer.getOrder().subVolume(bestBid.getOrder().getVolume())){
					/*If volume of order at index bestOffer is 0
					 * the order is removed*/
					if(bestOffer.getOrder().getVolume() == 0){
						remove(bestOffer.getOrder());
					}
					/*remove the order at best bid and decrement
					 * the index*/
					remove(bestBidIndex);
					bestOfferIndex--;
				}
			}
		}
	}
	
	public String outputBook(){
		int size = getSize(); 
		String result = "";
		System.out.println("------Orders------");
		
		for(int i = size - 1; i>=0;i--){
			result+=(get(i).toString()) + "\n";	
		}
		return result;
	}
	
	
	public String outputBBO(){
		System.out.println("------Best bid and offer------");
		return bestBid.getOrder().toString() + "\n" + bestOffer.getOrder().toString();
	}
	
	
	/*Inner class Node*/
	private class Node{
		Order order; 
		Node next; 
		Node prev; 
		
		public Node(){
			this.order = null; 
			this.next = null;
			this.prev = null; 
		}
		
		public Node(Order order, Node next, Node prev){
			this.prev = prev; 
			this.next = next; 
			this.order = order; 
		}
		
		public Order getOrder(){return order;}
		
		public Node getNext(){return next;}
		public void setNext(Node next){this.next = next;}
		public void setPrev(Node prev){this.prev = prev;}
		public Node getPrev(){return prev;}
		
		public Node(Node node){
			this.next = node.next; 
			this.prev = node.prev;
			this.order = node.order;
		}
	}
}

