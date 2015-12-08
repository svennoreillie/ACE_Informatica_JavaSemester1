package view.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ButtonPanelController;
import controller.RentalStatusController;
import database.DataStrategy;
import model.Shop;
import view.MainWindow;
import view.panels.testpanels.BluePanel;
import view.panels.testpanels.RedPanel;
import view.panels.testpanels.YellowPanel;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton blueButton;
	JButton yellowButton;
	JButton redButton;
	JButton rentalStatusButton;
	JButton customerOverviewButton;
	
	BluePanel bluePanel;
	YellowPanel yellowPanel;
	RedPanel redPanel;
	RentalStatusPanel rentalStatusPanel;
	CustomerOverview customerOverview;
	CustomerDetail customerDetail;
	
	ButtonPanelController controller;
	
	MainWindow mainWindow;

	public ButtonPanel(MainWindow mainWindow) {
		super();
		
		this.mainWindow = mainWindow;
		
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
		
		bluePanel=new BluePanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		blueButton = new JButton("Blue");
		blueButton.addActionListener(new BlueButtonListener());
		add(blueButton,gbc);
		
		redPanel=new RedPanel();
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		redButton = new JButton("Red");
		redButton.addActionListener(new RedButtonListener());
		add(redButton,gbc);
		
		yellowPanel=new YellowPanel();
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(new YellowButtonListener());
		add(yellowButton,gbc);
		
		rentalStatusPanel=new RentalStatusPanel();
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		rentalStatusButton=new JButton("Huur status");
		rentalStatusButton.addActionListener(new RentalStatusButtonListener());
		add(rentalStatusButton,gbc);
		
		customerOverview = new CustomerOverview(mainWindow);
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		customerOverviewButton = new JButton("Customer overview");
		customerOverviewButton.addActionListener(new CustomerOverviewButtonListener());
		add(customerOverviewButton,gbc);
	}
	
	public JPanel getHomePanel(){
		return bluePanel;
	}

	class BlueButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(bluePanel);
			bluePanel.repaint();
		}
	}

	class RedButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(redPanel);
			redPanel.repaint();
		}
	}

	class YellowButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(yellowPanel);
			yellowPanel.repaint();
		}
	}
	
	class RentalStatusButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(rentalStatusPanel);
			rentalStatusPanel.repaint();
		}
	}
	
	class CustomerOverviewButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(customerOverview);
			customerOverview.repaint();
		}
		
	}

	public void setController(ButtonPanelController controller) {
		this.controller = controller;
		rentalStatusPanel.setController(new RentalStatusController(controller.getShop()));
	}

}
