package view.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Item;

public class RentalItemPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JLabel rentedLabel;

	public RentalItemPanel(Item item,boolean rented) {
		super();
		
		nameLabel = new JLabel(item.getTitel());
		add(nameLabel);
		
		if(rented){
			rentedLabel = new JLabel("Rented");
		}else{
			rentedLabel = new JLabel("Available");
		}
		add(rentedLabel);
	}
	
}
