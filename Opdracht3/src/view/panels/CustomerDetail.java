package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import common.DBException;
import common.DBMissingException;
import controller.SpamRegistratieController;
import controller.event.MainWindowChangedFiringSource;
import model.Customer;
import view.tableModels.CustomerRentedItemTableModel;

import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Andr� N�brega
 *
 */
public class CustomerDetail extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1217574129144451867L;
	private JTable tableCustomerRentedItems;
	private JToggleButton tglbtnNewsletter;
	private SpamRegistratieController spamControl = new SpamRegistratieController();

	/**
	 * Create the panel.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public CustomerDetail(Customer customer) throws DBMissingException, DBException {
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
		if (!customer.getAddress().getBox().trim().isEmpty()){
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
		
		tglbtnNewsletter = new JToggleButton("Newsletter");
		if (customer.getSpam()){
			tglbtnNewsletter.setText("Newsletter ON");
			spamControl.activerenSpam(customer);
			
		}
		else{
			tglbtnNewsletter.setText("Newsletter OFF");
			spamControl.stopSpam(customer);
			}
		tglbtnNewsletter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tglbtnNewsletter.getText() == "Newsletter ON"){
					try {
						spamControl.stopSpam(customer);
					} catch (DBMissingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tglbtnNewsletter.setText("Newsletter OFF");
				}
				else if (tglbtnNewsletter.getText() == "Newsletter OFF"){
					try {
						spamControl.activerenSpam(customer);
					} catch (DBMissingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tglbtnNewsletter.setText("Newsletter ON");
				}
			}
		});
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
		CustomerRentedItemTableModel tableModel = new CustomerRentedItemTableModel(customer);
		tableCustomerRentedItems = new JTable(tableModel);
		try {
			tableModel.updateTable();
		} catch (DBMissingException | DBException e1) {
			System.out.println(e1.toString());
		} 
		scrollPane.setViewportView(tableCustomerRentedItems);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainWindowChangedFiringSource.getInstance().fireChanged(new CustomerOverview());
			}
		});
		btnClose.setBounds(501, 566, 89, 23);
		add(btnClose);		
	}
}
