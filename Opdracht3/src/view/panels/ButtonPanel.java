package view.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import common.enums.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import view.custom.Button;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Button blueButton;
	Button yellowButton;
	Button redButton;
	Button rentalStatusButton;
	Button customerOverviewButton;
	Button addItemButton;

	public ButtonPanel() {
		super();
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 50,100,50 };
		int numberOfRows=14;
		int[] heights=new int[numberOfRows];
		int windowHeight=580;
		for(int i=0;i<numberOfRows;i++){
			heights[i]=windowHeight/numberOfRows;
		}
		gbl.rowHeights = heights;
		setLayout(gbl);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		blueButton = new Button("Blue");
		blueButton.addActionListener(listener);
		blueButton.setActionCommand(EventEnum.BLUEBUTTONEVENT);
		add(blueButton,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		redButton = new Button("Red");
		redButton.addActionListener(listener);
		redButton.setActionCommand(EventEnum.REDBUTTONEVENT);
		add(redButton,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		yellowButton = new Button("Yellow");
		yellowButton.addActionListener(listener);
		yellowButton.setActionCommand(EventEnum.YELLOWBUTTONEVENT);
		add(yellowButton,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		rentalStatusButton=new Button("Huur status");
		rentalStatusButton.addActionListener(listener);
		rentalStatusButton.setActionCommand(EventEnum.HUURSTATUSBUTTONEVENT);
		add(rentalStatusButton,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		customerOverviewButton = new Button("Customer overview");
		customerOverviewButton.addActionListener(listener);
		customerOverviewButton.setActionCommand(EventEnum.CUSTOMEROVERVIEWBUTTONEVENT);
		add(customerOverviewButton,gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		customerOverviewButton = new Button("Rent");
		customerOverviewButton.addActionListener(listener);
		customerOverviewButton.setActionCommand(EventEnum.RENTBUTTON1);
		add(customerOverviewButton,gbc);
		
		
	
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		addItemButton = new Button("Add Item");
		addItemButton.addActionListener(listener);
		addItemButton.setActionCommand(EventEnum.ADDITEMBUTTONEVENT);
		add(addItemButton,gbc);
	}
}
