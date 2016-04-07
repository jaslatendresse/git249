public class OrderBook {

	///---Instance variables of OrderBook class---///
	private Node tail;
	private Node head;
	private Node bestOffer;
	private Node bestBid;
	int size; 

	///---Default constructor---///
	/*Initializes bestBid and bestOffer nodes to null
	 * Creates new nodes for start and end of linked list
	 * links head and tail*/
	public OrderBook(){
		bestBid = null;
		bestOffer = null; 
		this.head = new Node(); 
		this.tail = new Node(); 
		this.head.next = this.tail;
		this.tail.prev = this.head; 
		this.size = 0; 
	}

	///---Checks if linked list is empty---///
	private boolean isEmpty(){
		return size == 0; 
	}
	
	///---Adding an offer---///
	public void addOffer(Order o){
		Node currOff = head; 
		Node n = new Node(); 
		Node p = new Node(); 
		Node tmp = new Node(o);
		
		if(isEmpty()){
			tail.prev = tmp; 
			head.next = tmp; 
			tmp.prev = head; 
			tmp.next = tail; 
			bestOffer = tmp;
		}
		/*If bestOffer node is null and bestBid node isn't null
		 * Then the offer is added before the bestBid*/
		if(bestOffer == null && bestBid != null){
			n = bestBid; 
			p = bestBid.prev;
			tmp.next = n; 
			tmp.prev = p; 
			n.prev = tmp; 
			p.next = tmp;
			bestOffer = tmp; 
		}
		/*If bestOffer node isn't null and the price of offer to be added is smaller than the order
		 * in bestOffer node, then the order is added after the bestOffer node and becomes the best offer.
		 */
		else if(bestOffer != null&&o.getPrice()<bestOffer.order.getPrice()){
			n = bestOffer.next; 
			p = bestOffer;
			tmp.next = n;
			tmp.prev = p; 
			n.prev = tmp; 
			p.next = tmp; 
			bestOffer = tmp; 
		}
		/*If order to be added has a higher price than the order in the bestOffer node, then it is added above the
		 * bestOffer node.*/
		else if(o.getPrice() > bestOffer.order.getPrice()){
			currOff = bestOffer;
			while(true){
				if(currOff!=head){
					if(currOff.prev == head){
						p = head; 
						n = head.next;
						tmp.next = n; 
						tmp.prev = p; 
						n.prev = tmp; 
						p.next = tmp; 
						break;
					}
					/*if order to be added has a smaller price than offer, then it is added below*/
					else if(o.getPrice()<currOff.order.getPrice()){
						p = currOff; 
						n = currOff.next;
						tmp.next = n; 
						tmp.prev = p; 
						n.prev = tmp; 
						p.next = tmp;
						break;
					}
					else{
						currOff = currOff.prev;
					}
				}
			}
		}
	}
	
	///---Adding a bid---///
	public void addBid(Order o){
		Node currBid = head; 
		Node n = new Node(); 
		Node p = new Node(); 
		Node tmp = new Node(o);
		
		if(isEmpty()){
			tail.prev = tmp; 
			head.next = tmp; 
			tmp.prev = head; 
			tmp.next = tail; 
			bestBid = tmp;
		}
		/*If bestBid node is null and bestOffer node isn't null
		 * Then the offer is added before the bestBid*/
		if(bestBid == null&&bestOffer != null){
			p = bestOffer;
			n = bestOffer.next;
			tmp.next = n; 
			tmp.prev = p; 
			p.next = tmp; 
			n.prev = tmp; 
			bestBid = tmp; 
		}
		/*If bestBid node isn't null and the price of bid to be added is smaller than the order
		 * in bestBid node, then the order is added above the bestBid node and becomes the best bid.
		 */
		else if(bestBid!=null && o.getPrice()>bestBid.order.getPrice()){
			n = bestBid;
			p = bestBid.prev;
			tmp.next = n;
			tmp.prev = p;
			n.prev = tmp; 
			p.next = tmp; 
			bestBid = tmp; 
		}
		/*If bestBid node isn't empty and the order to be added has a lower price than the order in
		 * the bestBid node, then it is added at the end of the list*/
		else if(bestBid!=null && o.getPrice() < bestBid.order.getPrice()){
			currBid = bestBid;
			while(true){
				/*if order is bestBid node is not at tail*/
				if(currBid!=tail){
					if(o.getPrice()>currBid.order.getPrice()){
						n = currBid;
						p = currBid.prev;
						tmp.next = n; 
						tmp.prev = p; 
						n.prev = tmp; 
						n.next = tmp; 
						break; 
					}
					/*If next node is the tail*/
					else if(currBid.next==tail){
						n = tail; 
						p = tail.prev;
						tmp.next = n; 
						tmp.prev = p; 
						n.prev = tmp; 
						p.next = tmp;
						break;
					}
					else{
						currBid = currBid.next; 
					}
				}
				break;
			}
		}
	}
	

	///---Removes a node---///
	private void remove(Node tmp){
		tmp.prev.next = tmp.next; 
		tmp.next.prev = tmp.prev;
	}

	///---Adds an order by keeping the list sorted from greater to smaller price---///
	public void add(Order o){
		System.out.println("Adding " + o.toString() + "\n");
			if(o instanceof OfferOrder){
				addOffer(o);
			}
			else if(o instanceof BidOrder){
				addBid(o);
			}
		
		size++; //Increments size of list after adding elements.
	}
	
	///---Matching engine---///
	public void execute(Order o){
		outputBBO();
		System.out.println("------Matching process------");
		System.out.println("Matching " + o.toString());
		Node curr;//Node that contains the order to be matched
		
		///---Order is of type BidOrder---///
		if(o instanceof BidOrder){
			/*If the bestOffer node is null then we can't match a bid.
			 * If the bid has a lower price than the offers, then no match can be found.
			 * Adding the order to the list*/
			if(bestOffer == null || o.getPrice()<bestOffer.order.getPrice()){
				System.out.println("No match found. Bid is added to the book.");
				add(o);
			}
			/*If price of bid is greater or equal to offer*/
			else if(o.getPrice() >= bestOffer.order.getPrice()){
				curr = bestOffer; 
				while(true){
					if(curr!=bestBid && curr!=head && curr!=tail){
						if(curr.prev !=head && o.getPrice()>curr.order.getPrice()){
							curr = curr.prev; 
						}
						/*Match is found if bid has a higher price than order*/
						else{
							System.out.println("Match found" + "\n" + o.printFullDetails() + "\n" + curr.order.printFullDetails());
							/*if volume is entirely satisfied*/
							if(o.getVolume() == curr.order.getVolume()){
								remove(curr);
								o.setVolume(0);
								break; 
							}
							else if(o.getVolume() > curr.order.getVolume()){
								remove(curr);
								o.setVolume(o.getVolume() - curr.order.getVolume());//decrements volume while remaining in book
								curr = curr.next; 
							}
							else if(o.getVolume()<curr.order.getVolume()){
								curr.order.setVolume(curr.order.getVolume() - o.getVolume());
							}
						}
					}
				}
				if(bestBid.prev == head){
					bestOffer = null;
				}
				else{
					bestOffer = bestBid.prev;
				}
				if(o.getVolume()!=0){
					add(o);
				}
			}
		}
		
		///---Order is of type OfferOrder---///
		else if(o instanceof OfferOrder){
			/*If there is no order in the best bid nodes or that the offer to be matched has a price greater than
			 * an order of type bid, then there is no match*/
			if(bestBid ==  null || o.getPrice()>bestBid.order.getPrice()){
				System.out.println("No match found. Offer is added to the book.");
				add(o);//Adding order when no match
			}
			/*if offer to be matched has a price less than or equal to than the best bid
			 * check if it can be matched*/
			else if(o.getPrice() <= bestBid.order.getPrice()){
				curr = bestBid;
				while(true){
					if(curr != bestOffer && curr!=head && curr!=tail){
						if(curr.next !=tail && o.getPrice()<curr.next.order.getPrice()){
							curr = curr.next;
						}
						else{
							/*If offer has a price smaller than bid, then a match is made*/
							System.out.println("Match found " + "\n" + o.printFullDetails()+"\n" + curr.order.printFullDetails());
							if(o.getVolume() == curr.order.getVolume()){//if volume is entirely satisfied
								remove(curr);
								o.setVolume(0);
								break; 
							}
							else if(o.getVolume() > curr.order.getVolume()){
								remove(curr);
								o.setVolume(o.getVolume() - curr.order.getVolume());
								curr = curr.prev;//keeps looking for match if volume is not satisfied.
							}
							/*If volume of offer is smaller than the match*/
							else if(o.getVolume() < curr.order.getVolume()){
								curr.order.setVolume(curr.order.getVolume() - o.getVolume());//decrements volume while remaining in the book
								o.setVolume(0);
								break; 
							}
						}
					}
				}
				if(o.getVolume()!=0){
					addOffer(o);
				}
				if(bestOffer.next == tail){
					bestBid = null; 
				}
				else{
					bestBid = bestOffer.next; 
				}
			}
		}
		outputBook();
	}///---End of matching---///

	///---Outputs the best bid and best offer---///
	public void outputBBO(){
		System.out.println("------Best bid and best offer------");
		if(bestBid == null){
			System.out.println("Bid is null");
		}
		else{
			System.out.println(bestBid.order.toString());
		}
		if(bestOffer == null ){
			System.out.println("Offer is null \n");
		}
		else{
			System.out.println(bestOffer.order.toString()+"\n");
		}
	}

	///---Outputs the content of the list---///
	public void outputBook(){
		Node tmp = head.next;
		System.out.println("------Orders------");
		while(tmp.next!=null){
			System.out.println(tmp.order.toString());
			tmp = tmp.next;
		}
	}
	
	///---Inner Node class---///
	private class Node{
		public Order order; 
		public Node next; 
		public Node prev; 

		///---Default constructor---///
		public Node(){
			this.order = null;
			this.next = null; 
			this.prev = null; 
		}

		///---Constructor to create a new Node containing an order---///
		public Node(Order o){
			this.order = o;
			this.next = null;
			this.prev = null; 
		}
	}
}
