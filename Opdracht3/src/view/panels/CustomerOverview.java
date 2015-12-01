package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Adress;
import model.Customer;
import model.CustomerTableModel;
import model.Person;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class CustomerOverview extends JPanel {
	private static final long serialVersionUID = 3080524381208533700L;
	private JTable tableCustomers;
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
	private JCheckBox chckbxSpam;
	private CustomerTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public CustomerOverview() {
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 350);
		add(scrollPane);
		
		
		//tableModel = new CustomerTableModel();
		tableCustomers = new JTable(tableModel);
		/*
		String[] columnNames = {"Customer", "First name", "Last name", "E-mail"};
		tableCustomers = new JTable(Object[][] rowData, columnNames);
		*/
		scrollPane.setViewportView(tableCustomers);
		
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 405, 65, 14);
		add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setEnabled(false);
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(85, 402, 150, 20);
		add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
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
					Adress newAdress = new Adress(tfAdress.getText(), tfNumber.getText(), tfBox.getText(), tfZip.getText(), tfCity.getText(), tfCountry.getText());
					try {
						Customer newCustomer = new Customer(newPerson, newAdress, tfEmail.getText());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error on creating customer.");
						e1.printStackTrace();
					}
				}
				
				if (btnSearch.getText() == "Search"){
					
					// TODO: filter the customer table according to the text field contents
					
				}
				else{
					searchMode();
				}
			}
		});
		btnSearch.setBounds(343, 566, 89, 23);
		add(btnSearch);
		
		JButton btnRegister = new JButton("New...");
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
	}
	
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
	
	private void searchMode(){
		//Enable all text fields, including Customer ID
		enableAll();
		
		//Change the button layout and behavior
		this.btnRegister.setEnabled(false);
		this.btnRegister.setVisible(false);
		this.btnSearch.setText("Search");
		this.btnClear.setText("Cancel");
	}
	
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
}
