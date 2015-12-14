package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicBorders.ToggleButtonBorder;

import controller.CustomerRentedItemTableModel;
import controller.SpamRegistratieService;
import model.Customer;
import view.MainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerDetail extends JPanel {
	private Customer customer;
	private CustomerOverview overview;
	private JTable tableCustomerRentedItems;
	private CustomerRentedItemTableModel tableModel;
	private JLabel lblCustomerName;
	private JLabel lblAddressLine_1;
	private JLabel lblAddressLine_2;
	private JLabel lblEmailAddress;
	private JToggleButton tglbtnNewsletter;
	private SpamRegistratieService spamService;

	/**
	 * Create the panel.
	 */
	public CustomerDetail(Customer customer){
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 196, 580, 2);
		add(separator);
		
		JLabel lblMyRentedItems = new JLabel("My rented items");
		lblMyRentedItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMyRentedItems.setBounds(10, 209, 115, 14);
		add(lblMyRentedItems);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 234, 580, 321);
		add(scrollPane);
		tableModel = new CustomerRentedItemTableModel();
		tableCustomerRentedItems = new JTable(tableModel);
		scrollPane.setViewportView(tableCustomerRentedItems);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnClose.setBounds(501, 566, 89, 23);
		add(btnClose);
		
		lblCustomerName = new JLabel("Customer name");
		lblCustomerName.setBounds(10, 11, 568, 14);
		add(lblCustomerName);
		
		lblAddressLine_1 = new JLabel("Address line 1");
		lblAddressLine_1.setBounds(10, 60, 568, 14);
		add(lblAddressLine_1);
		
		lblAddressLine_2 = new JLabel("Address line 2");
		lblAddressLine_2.setBounds(10, 85, 580, 14);
		add(lblAddressLine_2);
		
		lblEmailAddress = new JLabel("E-mail address");
		lblEmailAddress.setBounds(10, 137, 568, 14);
		add(lblEmailAddress);
		
		tglbtnNewsletter = new JToggleButton("Newsletter");
		tglbtnNewsletter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tglbtnNewsletter.isSelected()){
					tglbtnNewsletter.setText("Newsletter ON");
					// TODO Spamservice laten inschrijven
					spamService.activerenSpam(customer);
					// PETER: de customer hier bevat data
				}
				else{
					tglbtnNewsletter.setText("Newsletter OFF");
					// TODO Spamservice laten uitschrijven
					spamService.stopSpam(customer);
				}
			}
		});
		tglbtnNewsletter.setBounds(10, 162, 121, 23);
		add(tglbtnNewsletter);
		
		StringBuffer buff = new StringBuffer();
		buff.append(customer.getPerson().getFirstName() + " " + customer.getPerson().getLastName());
		lblCustomerName.setText(buff.toString());
		
		buff = new StringBuffer();
		buff.append(customer.getAddress().getStreet() + " " + customer.getAddress().getNumber());
		if(customer.getAddress().getBox() != ""){
			buff.append(", box " + customer.getAddress().getBox());
		}
		lblAddressLine_1.setText(buff.toString());
		
		buff = new StringBuffer();
		buff.append(customer.getAddress().getZip() + " " + customer.getAddress().getCity().toUpperCase() + ", " + customer.getAddress().getCountry());
		lblAddressLine_2.setText(buff.toString());
		
		lblEmailAddress.setText(customer.getEmail());

		if (customer.getSpam()){
			tglbtnNewsletter.setText("Newsletter ON");
			tglbtnNewsletter.setSelected(true);
		}
		else {
			tglbtnNewsletter.setText("Newsletter OFF");
			tglbtnNewsletter.setSelected(false);
		}
	}
	
	/**
	 * Replaces the text on the labels with the customer's details
	 * @param customer the customer whose details have to be displayed
	 */
	/*
	public void loadCustomer(Customer customer){
		StringBuffer buff = new StringBuffer();
		buff.append(customer.getPerson().getFirstName() + " " + customer.getPerson().getLastName());
		lblCustomerName.setText(buff.toString());
		
		buff = new StringBuffer();
		buff.append(customer.getAddress().getStreet() + " " + customer.getAddress().getNumber());
		if(customer.getAddress().getBox() != ""){
			buff.append(", box " + customer.getAddress().getBox());
		}
		lblAddressLine_1.setText(buff.toString());
		
		buff = new StringBuffer();
		buff.append(customer.getAddress().getZip() + " " + customer.getAddress().getCity().toUpperCase() + " " + customer.getAddress().getCountry());
		lblAddressLine_2.setText(buff.toString());
		
		lblEmailAddress.setText(customer.getEmail());

		if (customer.getSpam()){
			tglbtnNewsletter.setText("Newsletter ON");
			tglbtnNewsletter.setSelected(true);
		}
		else {
			tglbtnNewsletter.setText("Newsletter OFF");
			tglbtnNewsletter.setSelected(false);
		}
	}
	
	public void toggleSpam(Boolean bool){
		if (bool){
			tglbtnNewsletter.setText("Newsletter ON");
			customer.setSpam(true);
		}
		else{
			tglbtnNewsletter.setText("Newsletter OFF");
			customer.setSpam(false);
		}
	}
	*/
}
