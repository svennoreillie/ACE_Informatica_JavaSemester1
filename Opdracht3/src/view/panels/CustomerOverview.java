package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerOverview extends JPanel {
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
	private JTextField lblCustomerID;

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
		
		tableCustomers = new JTable();
		scrollPane.setViewportView(tableCustomers);
		
		JButton btnRegister = new JButton("New...");
		btnRegister.setBounds(226, 566, 89, 23);
		add(btnRegister);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 405, 65, 14);
		add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(85, 402, 150, 20);
		add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(10, 433, 65, 14);
		add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(85, 430, 150, 20);
		add(tfLastName);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(355, 405, 46, 14);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(430, 402, 160, 20);
		add(tfEmail);
		
		JLabel label_3 = new JLabel("Adress");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(61, 481, 46, 14);
		add(label_3);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(61, 506, 46, 14);
		add(lblStreet);
		
		tfAdress = new JTextField();
		tfAdress.setColumns(10);
		tfAdress.setBounds(105, 503, 170, 20);
		add(tfAdress);
		
		JLabel lblNumber = new JLabel("Nr.");
		lblNumber.setBounds(285, 506, 30, 14);
		add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setColumns(10);
		tfNumber.setBounds(311, 503, 50, 20);
		add(tfNumber);
		
		JLabel lblBox = new JLabel("Box");
		lblBox.setBounds(371, 506, 30, 14);
		add(lblBox);
		
		tfBox = new JTextField();
		tfBox.setColumns(10);
		tfBox.setBounds(411, 503, 50, 20);
		add(tfBox);
		
		JLabel lblZip = new JLabel("ZIP");
		lblZip.setBounds(61, 537, 46, 14);
		add(lblZip);
		
		tfZip = new JTextField();
		tfZip.setColumns(10);
		tfZip.setBounds(105, 534, 70, 20);
		add(tfZip);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(201, 537, 30, 14);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(227, 534, 86, 20);
		add(tfCity);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(333, 537, 46, 14);
		add(lblCountry);
		
		tfCountry = new JTextField();
		tfCountry.setColumns(10);
		tfCountry.setBounds(389, 534, 86, 20);
		add(tfCountry);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 375, 580, 2);
		add(separator);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(355, 433, 65, 14);
		add(lblCustomerId);
		
		lblCustomerID = new JTextField();
		lblCustomerID.setBounds(430, 430, 160, 20);
		add(lblCustomerID);
		lblCustomerID.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(467, 566, 89, 23);
		add(btnClear);
		
		JButton btnSearch = new JButton("Search...");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSearch.setBounds(343, 566, 89, 23);
		add(btnSearch);
	}
}
