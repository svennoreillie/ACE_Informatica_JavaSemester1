package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class CustomerDetail extends JPanel {
	private JTable tableCustomerRentedItems;

	/**
	 * Create the panel.
	 */
	public CustomerDetail() {
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 580, 14);
		add(lblName);
		
		JLabel lblAddressLine = new JLabel("Address line 1");
		lblAddressLine.setBounds(10, 61, 580, 14);
		add(lblAddressLine);
		
		JLabel lblAddressLine_1 = new JLabel("Address line 2");
		lblAddressLine_1.setBounds(10, 86, 580, 14);
		add(lblAddressLine_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 136, 46, 14);
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
		scrollPane.setBounds(10, 270, 564, 285);
		add(scrollPane);
		
		tableCustomerRentedItems = new JTable();
		scrollPane.setViewportView(tableCustomerRentedItems);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(485, 566, 89, 23);
		add(btnClose);
		
	}
}
