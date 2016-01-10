package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import common.DBException;
import common.DBMissingException;
import controller.CustomerController;
import controller.event.MainWindowChangedFiringSource;
import model.Address;
import model.Customer;
import model.Person;
import view.tableModels.CustomerTableModel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Andr� N�brega
 *
 */
public class CustomerOverview extends JPanel {
	private static final long serialVersionUID = 3080524381208533700L;
	private JTextField tfFirstName;
	private JLabel lblFirstName;
	private JTextField tfLastName;
	private JLabel lblLastName;
	private JTextField tfEmail;
	private JLabel lblEmail;
	private JTextField tfAdress;
	private JLabel lblAddress;
	private JLabel lblStreet;
	private JTextField tfNumber;
	private JLabel lblNumber;
	private JTextField tfBox;
	private JLabel lblBox;
	private JTextField tfZip;
	private JLabel lblZip;
	private JTextField tfCity;
	private JLabel lblCity;
	private JTextField tfCountry;
	private JLabel lblCountry;
	private JTextField tfCustomerID;
	private JLabel lblCustomerId;
	private JButton btn1;
	private JButton btn2;
	private CustomerTableModel tableModel;
	private JTable tableCustomers;
	private JTextField tfSearch;
	private JLabel lblSearch;
	private CustomerController controller = new CustomerController();
	
	/**
	 * Create the panel.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public CustomerOverview() {
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		
		lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 405, 65, 14);
		add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setEnabled(false);
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(85, 402, 150, 20);
		add(tfFirstName);
		
		lblLastName = new JLabel("Last name");
		lblLastName.setBounds(10, 433, 65, 14);
		add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setEnabled(false);
		tfLastName.setColumns(10);
		tfLastName.setBounds(85, 430, 150, 20);
		add(tfLastName);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(311, 405, 46, 14);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(411, 402, 160, 20);
		add(tfEmail);
		
		lblAddress = new JLabel("Adress");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(61, 481, 46, 14);
		add(lblAddress);
		
		lblStreet = new JLabel("Street");
		lblStreet.setBounds(61, 506, 46, 14);
		add(lblStreet);
		
		tfAdress = new JTextField();
		tfAdress.setEnabled(false);
		tfAdress.setColumns(10);
		tfAdress.setBounds(105, 503, 170, 20);
		add(tfAdress);
		
		lblNumber = new JLabel("Nr.");
		lblNumber.setBounds(285, 506, 30, 14);
		add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setEnabled(false);
		tfNumber.setColumns(10);
		tfNumber.setBounds(311, 503, 50, 20);
		add(tfNumber);
		
		lblBox = new JLabel("Box");
		lblBox.setBounds(371, 506, 30, 14);
		add(lblBox);
		
		tfBox = new JTextField();
		tfBox.setEnabled(false);
		tfBox.setColumns(10);
		tfBox.setBounds(411, 503, 50, 20);
		add(tfBox);
		
		lblZip = new JLabel("ZIP");
		lblZip.setBounds(61, 537, 46, 14);
		add(lblZip);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(201, 537, 30, 14);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setEnabled(false);
		tfCity.setColumns(10);
		tfCity.setBounds(227, 534, 86, 20);
		add(tfCity);
		
		lblCountry = new JLabel("Country");
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
		
		lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(311, 433, 90, 14);
		add(lblCustomerId);
		
		tfCustomerID = new JTextField();
		tfCustomerID.setEnabled(false);
		tfCustomerID.setBounds(411, 430, 160, 20);
		add(tfCustomerID);
		tfCustomerID.setColumns(10);
		
		btn1 = new JButton("New...");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btn1.getText() == "New..."){
					registrationMode();
				}
				
				else if (btn1.getText() == "Save"){
					try {
						saveCustomer();
					} catch (DBMissingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					defaultMode();
				}
				
				else if (btn1.getText() == "Edit"){
					try {
						editCustomer();
					} catch (DBMissingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					defaultMode();
				}
			}
		});
		btn1.setBounds(384, 566, 89, 23);
		add(btn1);
		
		btn2 = new JButton("Search...");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (btn2.getText() == "Search..."){
					searchMode();
				}
				else if (btn2.getText() == "Cancel"){
					defaultMode();
				}
			}
		});
		btn2.setBounds(501, 566, 89, 23);
		add(btn2);
		
		tfZip = new JTextField();
		tfZip.setEnabled(false);
		tfZip.setBounds(105, 534, 86, 20);
		add(tfZip);
		tfZip.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 339);
		add(scrollPane);
		tableModel = new CustomerTableModel();
		tableCustomers = new JTable(tableModel);
		tableCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					CustomerDetail detail;
					try {
						//detail = new CustomerDetail(controller.getList().get(tableCustomers.getSelectedRow()));
						detail = new CustomerDetail(tableModel.getCustomerAtRow(tableCustomers.getSelectedRow()));
						MainWindowChangedFiringSource.getInstance().fireChanged(detail);
					} catch (DBMissingException | DBException e1) {
						System.out.println(e1.toString());
					} 
				}
				else{
					try{
						fillForm(tableModel.getCustomerAtRow(tableCustomers.getSelectedRow()));
						editMode();
					}
					catch (IndexOutOfBoundsException ioob){
						ioob.printStackTrace();
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
		
		tfSearch = new JTextField();
		DocumentListener documentListener = new DocumentListener() {
		      public void changedUpdate(DocumentEvent documentEvent) {
		    	  searchCustomers();
		      }
		      public void insertUpdate(DocumentEvent documentEvent) {
		    	  searchCustomers();
		      }
		      public void removeUpdate(DocumentEvent documentEvent) {
		    	  searchCustomers();
		      }
		    };
		tfSearch.getDocument().addDocumentListener(documentListener);
		
		tfSearch.setBounds(95, 461, 420, 20);
		add(tfSearch);
		tfSearch.setColumns(10);
		tfSearch.setEnabled(false);
		tfSearch.setVisible(false);
		
		lblSearch = new JLabel("Search...");
		lblSearch.setBounds(10, 464, 75, 14);
		add(lblSearch);
		lblSearch.setVisible(false);
		
		try {
			tableModel.updateTable();
		} catch (DBMissingException | DBException e1) {
			System.out.println(e1.toString());
		} 
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
	
	private void hideAll(){
		this.tfAdress.setVisible(false);
		this.tfBox.setVisible(false);
		this.tfCity.setVisible(false);
		this.tfCountry.setVisible(false);
		this.tfEmail.setVisible(false);
		this.tfFirstName.setVisible(false);
		this.tfLastName.setVisible(false);
		this.tfNumber.setVisible(false);
		this.tfZip.setVisible(false);
		this.tfCustomerID.setVisible(false);
		
		this.lblAddress.setVisible(false);
		this.lblBox.setVisible(false);
		this.lblCity.setVisible(false);
		this.lblCountry.setVisible(false);
		this.lblCustomerId.setVisible(false);
		this.lblEmail.setVisible(false);
		this.lblFirstName.setVisible(false);
		this.lblLastName.setVisible(false);
		this.lblNumber.setVisible(false);
		this.lblStreet.setVisible(false);
		this.lblZip.setVisible(false);
	}
	
	private void showAll(){	
		this.tfAdress.setVisible(true);
		this.tfBox.setVisible(true);
		this.tfCity.setVisible(true);
		this.tfCountry.setVisible(true);
		this.tfEmail.setVisible(true);
		this.tfFirstName.setVisible(true);
		this.tfLastName.setVisible(true);
		this.tfNumber.setVisible(true);
		this.tfZip.setVisible(true);
		this.tfCustomerID.setVisible(true);
		
		this.lblAddress.setVisible(true);
		this.lblBox.setVisible(true);
		this.lblCity.setVisible(true);
		this.lblCountry.setVisible(true);
		this.lblCustomerId.setVisible(true);
		this.lblEmail.setVisible(true);
		this.lblFirstName.setVisible(true);
		this.lblLastName.setVisible(true);
		this.lblNumber.setVisible(true);
		this.lblStreet.setVisible(true);
		this.lblZip.setVisible(true);
	}
	
	/**
	 * Sets the pane's behavior so new customers can be added using the text fields on the bottom.
	 */
	private void registrationMode(){
		//Enable all text fields, save for Customer ID
		enableAll();
		this.tfCustomerID.setEnabled(false);
		
		//Change the button layout and behavior
		btn1.setVisible(true);
		btn1.setText("Save");
		btn2.setText("Cancel");
	}
	
	/**
	 * Sets the pane's behavior so the text fields on the bottom can be used as search filters.
	 */
	private void searchMode(){
		//Enable all text fields, including Customer ID
		hideAll();
		disableAll();
		
		lblSearch.setVisible(true);
		tfSearch.setVisible(true);
		tfSearch.setEnabled(true);
		
		//Change the button layout and behavior
		this.btn1.setVisible(false);
		this.btn2.setText("Cancel");
		this.lblSearch.setVisible(true);
		this.tfSearch.setVisible(true);
		this.tfSearch.setEnabled(true);
	}
	
	/**
	 * Allows to update the data of the currently selected customer.
	 */
	private void editMode(){
		btn1.setText("Edit");
		btn2.setText("Cancel");
	}
	
	/**
	 * Sets the pane's behavior to its default state
	 */
	private void defaultMode(){
		//Clear out and disable all text fields
		clearAll();
		disableAll();
		showAll();
		
		lblSearch.setVisible(false);
		tfSearch.setVisible(false);
		tfSearch.setEnabled(false);
		
		//Reset button layout and behavior
		btn1.setVisible(true);
		btn1.setText("New...");
		btn2.setText("Search...");
	}
	
	/**
	 * Fills out the form on the bottom according to which customer is selected on the table above.
	 * 
	 * @param customer the customer whose details are to be displayed
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
	
	/**
	 * Makes a new customer using the text fields at the bottom of the panel, and saves it.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	private void saveCustomer() throws DBMissingException, DBException{
		if (checkForEmptyFields()){
			
		}
		else{			
			controller.addCustomer(getCustomerFromForm());
			tableModel.updateTable();
			JOptionPane.showMessageDialog(null, "Customer registered.");
		}
	}
	
	private void editCustomer() throws DBMissingException, DBException{
		if (checkForEmptyFields()) {
			JOptionPane.showMessageDialog(null, "Please fill in all required fields");
		} else {
			controller.updateCustomer(getCustomerFromForm());
			tableModel.updateTable();
			JOptionPane.showMessageDialog(null, "Customer updated");
		}
	}
	
	private void searchCustomers(){
		try {
			tableModel.updateTable(tfSearch.getText());
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean checkForEmptyFields(){
		if (tfAdress.getText().trim().isEmpty()
				|| tfCity.getText().trim().isEmpty()
				|| tfCountry.getText().trim().isEmpty()
				|| tfEmail.getText().trim().isEmpty()
				|| tfFirstName.getText().trim().isEmpty()
				|| tfLastName.getText().trim().isEmpty()
				|| tfNumber.getText().trim().isEmpty()
				|| tfZip.getText().trim().isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	private Customer getCustomerFromForm(){
		Person newPers = new Person();
		newPers.setFirstName(tfFirstName.getText());
		newPers.setLastName(tfLastName.getText());
		
		Address newAdd = new Address();
		newAdd.setBox(tfBox.getText());
		newAdd.setCity(tfCity.getText());
		newAdd.setCountry(tfCountry.getText());
		newAdd.setNumber(tfNumber.getText());
		newAdd.setStreet(tfAdress.getText());
		newAdd.setZip(tfZip.getText());
		
		Customer newCust = new Customer();
		newCust.setEmail(tfEmail.getText());
		String customerId = new String();
		if (!customerId.isEmpty()) {
			customerId = tfCustomerID.getText();
			newCust.setId(Integer.parseInt(customerId));
		}
		
		newCust.setPerson(newPers);
		newCust.setAddress(newAdd);
		
		return newCust;
	}
}