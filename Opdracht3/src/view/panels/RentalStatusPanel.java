package view.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.BoxLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.SwingConstants;

import controller.RentalStatusController;
import view.ViewException;

import model.Item;

public class RentalStatusPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel itemsPanel;
	private JScrollPane scrollableItemsPanel;
	private RentalStatusController controller;
	
	private List<Item> items;
	private List<Item> rentedItems;
	
	public RentalStatusPanel(){
		super();
		this.setSize(600,600);
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowWeights = new double[]{0.0, 1.0};
		gbl.columnWeights = new double[]{0.0, 1.0};
		gbl.columnWidths = new int[] {5, 598};
		gbl.rowHeights = new int[] {50,550};
		setLayout(gbl);
		
		JLabel HuurStatusLabel = new JLabel("Huur Status");
		HuurStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_HuurStatusLabel = new GridBagConstraints();
		gbc_HuurStatusLabel.anchor = GridBagConstraints.WEST;
		gbc_HuurStatusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_HuurStatusLabel.gridx = 1;
		gbc_HuurStatusLabel.gridy = 0;
		add(HuurStatusLabel, gbc_HuurStatusLabel);
		
		itemsPanel = new JPanel();
		scrollableItemsPanel = new JScrollPane(itemsPanel);
		itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill = GridBagConstraints.BOTH;		
		add(scrollableItemsPanel,gbc);
	}
	
	public void setController(RentalStatusController controller){
		
		this.controller=controller;
		items = controller.getItems();
		rentedItems=items;
		//rentedItems = controller.getRentedItems();
		
		for(Item i : items){
			addItemPanel(i, rentedItems.contains(i));
		}
	}
	
	private void addItemPanel(Item item,boolean rented){
		RentalItemPanel itemPanel= new RentalItemPanel(item,rented);
		itemsPanel.add(itemPanel);
	}
}
