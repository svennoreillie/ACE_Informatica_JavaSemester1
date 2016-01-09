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
import controller.CustomerController;
import controller.event.MainWindowChangedFiringSource;
import model.Customer;
import view.tableModels.CustomerTableModel;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author André Nóbrega
 *
 */
public class ItemTeruggave1 extends JPanel {

	private static final long serialVersionUID = 2005501532857087964L;
	private JLabel lblCustomer;
	private JLabel lblName;
	private JLabel lblAddressLine1;
	private JLabel lblAddressLine2;
	private JLabel lblCustomerId;
	private JLabel lblEmail;
	private JButton btnNext;
	private JTextField tfSearchCustomer;
	private JTable tableCustomers;
	private CustomerTableModel tableModel;
	private Customer customer;
	private CustomerController controller = new CustomerController();

	/**
	 * Create the panel.
	 */
	public ItemTeruggave1() {

		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomer.setBounds(10, 11, 70, 14);
		add(lblCustomer);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 67, 250, 14);
		add(lblName);
		
		lblAddressLine1 = new JLabel("Address line 1");
		lblAddressLine1.setBounds(10, 92, 580, 14);
		add(lblAddressLine1);
		
		lblAddressLine2 = new JLabel("Address line 2");
		lblAddressLine2.setBounds(10, 117, 580, 14);
		add(lblAddressLine2);
		
		lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(300, 67, 290, 14);
		add(lblCustomerId);
		
		lblEmail = new JLabel("E-mail");
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
		
		btnNext = new JButton("Next >");
		btnNext.setEnabled(false);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItemTeruggave2 uitleningLijst = new ItemTeruggave2(customer);
				MainWindowChangedFiringSource.getInstance().fireChanged(uitleningLijst);
			}
		});
		btnNext.setBounds(501, 566, 89, 23);
		add(btnNext);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 580, 380);
		add(scrollPane);
		tableModel = new CustomerTableModel();
		tableCustomers = new JTable(tableModel);
		tableCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					fillForm(controller.getList().get(tableCustomers.getSelectedRow()));
				}
				catch (IndexOutOfBoundsException ioob){
					ioob.printStackTrace();
				}
				catch (Exception e){
					System.err.println(e);
				}
			}
		});
		try {
			tableModel.updateTable();
		} catch (DBMissingException e) {
			System.err.println("Could not update table model; database is missing.");
			e.printStackTrace();
		} catch (DBException e) {
			System.err.println("A database exception has occurred; cannot update table model.");
			e.printStackTrace();
		}
		scrollPane.setViewportView(tableCustomers);
		
		hideLabels();
	}
	
	private void hideLabels(){
		lblAddressLine1.setVisible(false);
		lblAddressLine2.setVisible(false);
		lblCustomerId.setVisible(false);
		lblEmail.setVisible(false);
		lblName.setVisible(false);
	}
	
	private void showLabels(){
		lblAddressLine1.setVisible(true);
		lblAddressLine2.setVisible(true);
		lblCustomerId.setVisible(true);
		lblEmail.setVisible(true);
		lblName.setVisible(true);
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
	
	private void fillForm(Customer custToDisplay){
		StringBuffer sb = new StringBuffer();
		sb.append(custToDisplay.getAddress().getStreet() + " " + custToDisplay.getAddress().getNumber());
		if (!custToDisplay.getAddress().getBox().trim().isEmpty()){
			sb.append(", box " + customer.getAddress().getBox());
		}
		String address1 = sb.toString();
		this.lblAddressLine1.setText(address1);
		
		this.lblAddressLine2.setText(custToDisplay.getAddress().getZip() + " " + custToDisplay.getAddress().getCity().toUpperCase() + " " + custToDisplay.getAddress().getCountry());
		
		this.lblCustomerId.setText("Customer ID: " + String.valueOf(custToDisplay.getId()));
		
		this.lblEmail.setText(custToDisplay.getEmail());
		
		this.lblName.setText(custToDisplay.getPerson().getFirstName() + " " + custToDisplay.getPerson().getLastName());
		
		showLabels();
		customer = custToDisplay;
		btnNext.setEnabled(true);
	}
}
