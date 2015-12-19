package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import controller.event.MainWindowChangedFiringSource;
import model.Customer;

import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerDetail extends JPanel {
	private JTable tableCustomerRentedItems;

	/**
	 * Create the panel.
	 */
	public CustomerDetail(Customer customer) {
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 580, 14);
		lblName.setText(customer.getPerson().getFirstName() + " " + customer.getPerson().getLastName());
		add(lblName);
		
		JLabel lblAddressLine1 = new JLabel("Address line 1");
		lblAddressLine1.setBounds(10, 61, 580, 14);
		StringBuffer sb = new StringBuffer();
		sb.append(customer.getAddress().getStreet() + " " + customer.getAddress().getNumber());
		if (customer.getAddress().getBox() != ""){
			sb.append(", box " + customer.getAddress().getBox());
		}
		String address = sb.toString();
		lblAddressLine1.setText(address);
		add(lblAddressLine1);
		
		JLabel lblAddressLine2 = new JLabel("Address line 2");
		lblAddressLine2.setBounds(10, 86, 580, 14);
		lblAddressLine2.setText(customer.getAddress().getZip() + " " + customer.getAddress().getCity().toUpperCase() + " " + customer.getAddress().getCountry());
		add(lblAddressLine2);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 136, 580, 14);
		lblEmail.setText(customer.getEmail());
		add(lblEmail);
		
		JToggleButton tglbtnNewsletter = new JToggleButton("Newsletter");
		tglbtnNewsletter.setBounds(10, 161, 121, 23);
		add(tglbtnNewsletter);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 220, 580, 2);
		add(separator);
		
		JLabel lblMyRentedItems = new JLabel("My rented items");
		lblMyRentedItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMyRentedItems.setBounds(20, 233, 99, 14);
		add(lblMyRentedItems);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 580, 285);
		add(scrollPane);
		
		tableCustomerRentedItems = new JTable();
		scrollPane.setViewportView(tableCustomerRentedItems);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//MainWindowChangedFiringSource.getInstance().fireChanged(panel);
			}
		});
		btnClose.setBounds(501, 566, 89, 23);
		add(btnClose);
		
	}
}
