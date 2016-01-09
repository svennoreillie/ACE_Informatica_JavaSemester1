package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.DBException;
import common.DBMissingException;
import view.tableModels.CustomerTableModel;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ItemTeruggave1 extends JPanel {
	private JTextField tfSearchCustomer;
	private JTable tableCustomer;
	private CustomerTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public ItemTeruggave1() {

		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomer.setBounds(10, 11, 70, 14);
		add(lblCustomer);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 67, 250, 14);
		add(lblName);
		
		JLabel lblAddressLine = new JLabel("Address line 1");
		lblAddressLine.setBounds(10, 92, 580, 14);
		add(lblAddressLine);
		
		JLabel lblAddressLine_1 = new JLabel("Address line 2");
		lblAddressLine_1.setBounds(10, 117, 580, 14);
		add(lblAddressLine_1);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(300, 67, 290, 14);
		add(lblCustomerId);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 142, 580, 14);
		add(lblEmail);
		
		tfSearchCustomer = new JTextField();
		tfSearchCustomer.setBounds(10, 36, 580, 20);
		add(tfSearchCustomer);
		tfSearchCustomer.setColumns(10);
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
		tfSearchCustomer.getDocument().addDocumentListener(documentListener);
		
		JButton btnNext = new JButton("Next >");
		btnNext.setBounds(501, 566, 89, 23);
		add(btnNext);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 580, 380);
		add(scrollPane);
		tableModel = new CustomerTableModel();
		tableCustomer = new JTable(tableModel);
		try {
			tableModel.updateTable();
		} catch (DBMissingException e) {
			System.err.println("Could not update table model; database is missing.");
			e.printStackTrace();
		} catch (DBException e) {
			System.err.println("A database exception has occurred; cannot update table model.");
			e.printStackTrace();
		}
		scrollPane.setViewportView(tableCustomer);
		
	}
	
	private void searchCustomers(){
		try {
			tableModel.updateTable(tfSearchCustomer.getText());
		} catch (DBMissingException e) {
			System.err.println("Could not update table model; database is missing.");
			e.printStackTrace();
		} catch (DBException e) {
			System.err.println("A database exception has occurred; cannot update table model.");
			e.printStackTrace();
		}
	}
}
