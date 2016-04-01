import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OrderInput extends JFrame {
	
	/*Instances of the GUI*/
	private JFrame theWindow; 
	private JTextField name;
	private JTextField price; 
	private JTextField volume; 
	private JButton submit, clear; 
	private JPanel myPanel;
	private JLabel lastAdded; 
	private JLabel errorCatch; 
	private Font theFont; 
	private JTextField orderType; 
	
	/*Default constructor of the GUI*/
	OrderInput(){
		
		/*Initializing the instances*/
		theWindow = new JFrame("Order Generator");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit = new JButton("Submit");
		clear = new JButton("Clear");
		name = new JTextField("Enter name here");
		price = new JTextField("Enter price here");
		volume = new JTextField("Enter volume here");
		orderType = new JTextField("Enter order type here (bid/offer)");
		myPanel = new JPanel(); 
		lastAdded = new JLabel("Last order added - none", SwingConstants.CENTER);
		errorCatch = new JLabel("",SwingConstants.CENTER); 
		theFont = new Font("Verdana",Font.BOLD,12);
		
		/*adding the panel to the frame*/
		theWindow.add(myPanel);
		/*adding components to the panel*/
		myPanel.add(orderType);
		myPanel.add(name);
		myPanel.add(price);
		myPanel.add(volume);
		myPanel.add(submit);
		myPanel.add(clear);
		myPanel.add(lastAdded);
		myPanel.add(errorCatch);
		theWindow.pack();
		/*setting dimension and visibility of the frame*/
		theWindow.setVisible(true);
		theWindow.setResizable(false);
		theWindow.setSize(450,300);
		/*setting dimension of textfields and labels*/
		name.setPreferredSize(new Dimension(400,30));
		price.setPreferredSize(new Dimension(400,30));
		volume.setPreferredSize(new Dimension(400,30));
		orderType.setPreferredSize(new Dimension(400,30));
		errorCatch.setPreferredSize(new Dimension(350,40));
		lastAdded.setPreferredSize(new Dimension(350,40));
		
		/*adding action listener to each button*/
		submit.addActionListener(new ButtonListener());
		clear.addActionListener(new ButtonListener());
		
	}
	private class ButtonListener implements ActionListener{
		/*Instances of Order and OrderBook used in the ButtonListener class*/
		Order order; 
		OrderBook placedOrders;
		
		/*Checking if the input is a number*/
		public boolean isNumber(String str1){
			double price1;
			int volume1;
			double name1;
			
			/*For price and volume, if the input is a number
			 * return true
			 * return false otherwise
			 */
			if(str1.equals(price.getText())){
				try{
					price1 = Double.parseDouble(str1);
					return true;
				}
				catch(NumberFormatException e){
					return false; 
				}
			}
			else if(str1.equals(volume.getText())){
				try{
					volume1 = Integer.parseInt(str1);
					return true;
				}
				catch(NumberFormatException e){
					return false; 
				}
			}
			
			/*For name text field, if input is a number
			 * return false
			 * return true otherwise
			 */
			else if(str1.equals(name.getText())){
				try{
					name1 = Double.parseDouble(str1);
					return false; 
				}
				catch(NumberFormatException e){
					return true; 
				}
			}
			else
				return false; 
		}
		
		/* Checks if the type of the order is correct 
		 * true if order or bid
		 * false otherwise
		 */
		public boolean isOfType(String str){
			if(orderType.getText().equalsIgnoreCase("bid")){
				return true;
			}
			if(orderType.getText().equalsIgnoreCase("offer")){
				return true;
			}
			else{
				return false;
			}
		}
		
		/*Action perfomed by the buttons*/
		public void actionPerformed(ActionEvent event){
			/*If the button pressed is the submit button*/
			if(event.getSource() == submit){
				/*Checks if text field is empty*/
				if(name.getText().equals("")||price.getText().equals("")||volume.getText().equals("") || orderType.getText().equals("")){
					errorCatch.setText("Empty text field. Could not perform operation.");
					errorCatch.setForeground(Color.RED);
					errorCatch.setFont(theFont);
				}
				/*Checks if volume and price text fields are number
				 * Checks if name is not a number
				 * Check if orderType text field is either offer or bid
				 */
				else if(!isNumber(price.getText()) || !isNumber(volume.getText()) || !isNumber(name.getText()) || !isOfType(orderType.getText())){
					errorCatch.setText("Invalid input.");
					errorCatch.setForeground(Color.RED);
					errorCatch.setFont(theFont);
				}
				
				/*Otherwise*/
				else{
					/*Create name, price and volume from input*/
					String s = name.getText();
					double d = Double.parseDouble(price.getText());
					int i = Integer.parseInt(volume.getText());
					
					/*If orderType is bid, we create a new BidOrder*/
					if(orderType.getText().equalsIgnoreCase("bid")){
						order = new BidOrder(s,d,i);
						lastAdded.setText("Last order added: " + order.toString());
					}
					/*If orderType is offer, we create a new OfferOrder*/
					if(orderType.getText().equalsIgnoreCase("offer")){
						order = new OfferOrder(s,d,i);
						lastAdded.setText("Last order added - " + order.toString());
					}
					/*Reset the text fields*/
					name.setText("Enter name here");
					price.setText("Enter price here");
					volume.setText("Enter volume here");
					orderType.setText("Enter order type here (bid/offer)");
					errorCatch.setText("");
				}
			}
			/*Reset button function
			 * Resets all the text fields to initial values
			 */
			if(event.getSource() == clear){
				name.setText("Enter name here");
				price.setText("Enter price here");
				volume.setText("Enter volume here");
				orderType.setText("Enter order type here");
			}
		}
	}
}
