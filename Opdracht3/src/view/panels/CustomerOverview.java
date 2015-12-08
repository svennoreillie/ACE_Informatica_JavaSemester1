package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import common.factories.AddressFactory;
import common.factories.CustomerFactory;
import common.factories.PersonFactory;
import controller.CustomerTableModel;
import model.Address;
import model.Customer;
import model.Person;
import testing.CustomerTableIOTest;
import view.MainWindow;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;

public class CustomerOverview extends JPanel {
	private static final long serialVersionUID = 3080524381208533700L;
	private MainWindow mainWindow;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfAdress;
	private JTextField tfNumber;
	private JTextField tfBox;
	private JTextField tfZip;
	private JTextField tfCity;
	private JTextField tfCountry;
	private JTextField tfCustomerID;
	private JButton btnRegister;
	private JButton btnSearch;
	private JButton btnClear;
	private DefaultTableModel tableModel;
	private JTable tableCustomers;
	private ArrayList<Customer> customerList;
	private CustomerTableIOTest iotest;
	private CustomerDetail detail;

	/**
	 * Create the panel.
	 * @param mainWindow 
	 */
	public CustomerOverview(MainWindow mainWindow) {
		customerList = new ArrayList<>();
		
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 405, 65, 14);
		add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setEnabled(false);
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(85, 402, 150, 20);
		add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Surname");
		lblLastName.setBounds(10, 433, 65, 14);
		add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setEnabled(false);
		tfLastName.setColumns(10);
		tfLastName.setBounds(85, 430, 150, 20);
		add(tfLastName);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(355, 402, 46, 14);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(430, 399, 160, 20);
		add(tfEmail);
		
		JLabel label_3 = new JLabel("Adress");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(61, 481, 46, 14);
		add(label_3);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(61, 506, 46, 14);
		add(lblStreet);
		
		tfAdress = new JTextField();
		tfAdress.setEnabled(false);
		tfAdress.setColumns(10);
		tfAdress.setBounds(105, 503, 170, 20);
		add(tfAdress);
		
		JLabel lblNumber = new JLabel("Nr.");
		lblNumber.setBounds(285, 506, 30, 14);
		add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setEnabled(false);
		tfNumber.setColumns(10);
		tfNumber.setBounds(311, 503, 50, 20);
		add(tfNumber);
		
		JLabel lblBox = new JLabel("Box");
		lblBox.setBounds(371, 506, 30, 14);
		add(lblBox);
		
		tfBox = new JTextField();
		tfBox.setEnabled(false);
		tfBox.setColumns(10);
		tfBox.setBounds(411, 503, 50, 20);
		add(tfBox);
		
		JLabel lblZip = new JLabel("ZIP");
		lblZip.setBounds(61, 537, 46, 14);
		add(lblZip);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(201, 537, 30, 14);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setEnabled(false);
		tfCity.setColumns(10);
		tfCity.setBounds(227, 534, 86, 20);
		add(tfCity);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(333, 537, 46, 14);
		add(lblCountry);
		
		tfCountry = new JTextField();
		tfCountry.setEnabled(false);
		tfCountry.setColumns(10);
		tfCountry.setBounds(389, 534, 86, 20);
		add(tfCountry);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 375, 580, 2);
		add(separator);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(355, 430, 65, 14);
		add(lblCustomerId);
		
		tfCustomerID = new JTextField();
		tfCustomerID.setEnabled(false);
		tfCustomerID.setBounds(430, 427, 160, 20);
		add(tfCustomerID);
		tfCustomerID.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnSearch.getText() == "Register" || btnSearch.getText() == "Search"){
					defaultMode();
				}
				else{
					clearAll();
				}
			}
		});
		btnClear.setBounds(467, 566, 89, 23);
		add(btnClear);
		
		JButton btnSearch = new JButton("Search...");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (btnSearch.getText() == "Register"){
					//Create a new customer
					Person newPerson = new Person(tfFirstName.getText(), tfLastName.getText());
					Address newAdress = new Address(tfAdress.getText(), tfNumber.getText(), tfBox.getText(), tfZip.getText(), tfCity.getText(), tfCountry.getText());
					try {
						Customer newCustomer = new Customer(newPerson, newAdress, tfEmail.getText());
						customerList.add(newCustomer);
						iotest.addCustomer(customerList);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error on creating customer.");
						e1.printStackTrace();
					}
				}
				
				if (btnSearch.getText() == "Search"){
					
					// TODO ANDRE: filter the customer table according to the text field contents
					
				}
				else{
					searchMode();
				}
			}
		});
		btnSearch.setBounds(343, 566, 89, 23);
		add(btnSearch);
		
		JButton btnRegister = new JButton("New...");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				registrationMode();
			}
		});
		btnRegister.setBounds(226, 566, 89, 23);
		add(btnRegister);
		
		tfZip = new JTextField();
		tfZip.setEnabled(false);
		tfZip.setBounds(105, 534, 86, 20);
		add(tfZip);
		tfZip.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 339);
		add(scrollPane);
		CustomerTableModel tableModel = new CustomerTableModel();
		tableCustomers = new JTable(tableModel);
		tableCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					//detailMode();
					detail = new CustomerDetail(customerList.get(tableCustomers.getSelectedRow()));
					mainWindow.changeViewPanel(detail);
				}
				else{
					try{
						fillForm(customerList.get(tableCustomers.getSelectedRow()));
					}
					catch (Exception e){
						System.err.println(e);
					}
					enableAll();
					tfCustomerID.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(tableCustomers);
		
		JButton btnLaunchFactory = new JButton("Launch factory");
		btnLaunchFactory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<Customer> tempList = new ArrayList<>();

				for (int i = 0; i < 10; i++){
					tempList.add(CustomerFactory.createCustomer());
				}
				
				for (int i = 0; i < tempList.size(); i++){
					tableModel.addCustomer(tempList.get(i));
					customerList.add(tempList.get(i));
				}
				
			}
		});
			
		btnLaunchFactory.setBounds(10, 566, 139, 23);
		add(btnLaunchFactory);
	}
	
	/**
	 * Enables all text fields on the pane (even the Customer ID), so they can be edited.
	 */
	private void enableAll(){
		//enables all text fields
		this.tfAdress.setEnabled(true);
		this.tfBox.setEnabled(true);
		this.tfCity.setEnabled(true);
		this.tfCountry.setEnabled(true);
		this.tfEmail.setEnabled(true);
		this.tfFirstName.setEnabled(true);
		this.tfLastName.setEnabled(true);
		this.tfNumber.setEnabled(true);
		this.tfZip.setEnabled(true);
		this.tfCustomerID.setEnabled(true);
	}
	
	/**
	 * Disables all text fields on the pane, so they can't be edited.
	 */
	private void disableAll(){
		//Disables all text fields
		this.tfAdress.setEnabled(false);
		this.tfBox.setEnabled(false);
		this.tfCity.setEnabled(false);
		this.tfCountry.setEnabled(false);
		this.tfEmail.setEnabled(false);
		this.tfFirstName.setEnabled(false);
		this.tfLastName.setEnabled(false);
		this.tfNumber.setEnabled(false);
		this.tfZip.setEnabled(false);
		this.tfCustomerID.setEnabled(false);
	}
	
	/**
	 * Clears out all text fields on the pane.
	 */
	private void clearAll(){
		//Clears out all text fields
		this.tfAdress.setText("");
		this.tfBox.setText("");
		this.tfCity.setText("");
		this.tfCountry.setText("");
		this.tfEmail.setText("");
		this.tfFirstName.setText("");
		this.tfLastName.setText("");
		this.tfNumber.setText("");
		this.tfZip.setText("");
		this.tfCustomerID.setText("");
	}
	
	/**
	 * Sets the pane's behavior so new customers can be added using the text fields on the bottom.
	 */
	private void registrationMode(){
		//Enable all text fields, save for Customer ID
		enableAll();
		this.tfCustomerID.setEnabled(false);
		
		//Change the button layout and behavior
		this.btnRegister.setEnabled(false);
		this.btnRegister.setVisible(false);
		this.btnSearch.setText("Register");
		this.btnClear.setText("Cancel");
	}
	
	/**
	 * Sets the pane's behavior so the text fields on the bottom can be used as search filters.
	 */
	private void searchMode(){
		//Enable all text fields, including Customer ID
		enableAll();
		
		//Change the button layout and behavior
		this.btnRegister.setEnabled(false);
		this.btnRegister.setVisible(false);
		this.btnSearch.setText("Search");
		this.btnClear.setText("Cancel");
	}
	
	/**
	 * Sets the pane's behavior to its default state
	 */
	private void defaultMode(){
		//Clear out and disable all text fields
		clearAll();
		disableAll();
		
		//Reset button layout and behavior
		this.btnRegister.setEnabled(true);
		this.btnRegister.setVisible(true);
		this.btnSearch.setText("Search...");
		this.btnClear.setText("Clear");
	}
	
	public void overViewMode(){
		mainWindow.changeViewPanel(this);
	}
	
	/**
	 * Fills out the form on the bottom according to which customer is selected on the table above.
	 * 
	 * @param customer the customer whose details are displayed
	 */
	private void fillForm(Customer customer){
		tfAdress.setText(customer.getAddress().getStreet());
		tfBox.setText(customer.getAddress().getBox());
		tfCity.setText(customer.getAddress().getCity());
		tfCountry.setText(customer.getAddress().getCountry());
		tfCustomerID.setText(Integer.toString(customer.getId()));
		tfEmail.setText(customer.getEmail());
		tfFirstName.setText(customer.getPerson().getFirstName());
		tfLastName.setText(customer.getPerson().getLastName());
		tfNumber.setText(customer.getAddress().getNumber());
		tfZip.setText(customer.getAddress().getZip());
	}
}
