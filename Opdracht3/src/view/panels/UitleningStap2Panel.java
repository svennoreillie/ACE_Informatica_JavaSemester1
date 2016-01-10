
package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.joda.time.DateTime;

import controller.UitleenController;
import controller.event.MainWindowChangedFiringSource;
import model.Customer;
import model.Item;
import view.tableModels.CustomerSelectionForRentTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

/**
 * 
 * @author Huybrechts
 *
 */

public class UitleningStap2Panel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6900153860626626189L;
	private JTextField searchTF;
	private JTable table;
	private JTextField numberOfDaysTF;
	private CustomerSelectionForRentTableModel tableModel;
	
	private List<Item> items;
	
 	public UitleningStap2Panel(){
 		items = new ArrayList<Item>();
 		
		setSize(600,600);
		setLayout(null);
		
		JLabel lblNaam = new JLabel("Search:");
		lblNaam.setBounds(10, 14, 46, 14);
		add(lblNaam);
		
		searchTF = new JTextField();
		searchTF.setBounds(56, 11, 86, 20);
		add(searchTF);
		searchTF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 524);
		add(scrollPane);
		
		tableModel = new CustomerSelectionForRentTableModel();
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblNumberOfDays = new JLabel("Number of Days:");
		lblNumberOfDays.setBounds(10, 575, 99, 14);
		add(lblNumberOfDays);
		
		numberOfDaysTF = new JTextField();
		numberOfDaysTF.setBounds(108, 572, 86, 20);
		add(numberOfDaysTF);
		numberOfDaysTF.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int numberOfDays;
				Customer selectedCustomer;
				
				try{
					numberOfDays=Integer.parseInt(numberOfDaysTF.getText());
					selectedCustomer = tableModel.getSelectedCustomer();
					DateTime date = DateTime.now();
					if(selectedCustomer!=null){
						UitleenController controller = new UitleenController();
						for(Item i:items){
							controller.aanmakenVanEenUitlening(i, selectedCustomer, numberOfDays, date);
						}
						JOptionPane.showMessageDialog(null, "De uitleningen zijn aangemaakt.");
						MainWindowChangedFiringSource.getInstance().fireChanged(new UitleningStap1Panel());
					}else{
						JOptionPane.showMessageDialog(null, "A customer has to be selected.");
					}
				}catch(Exception exc){
					JOptionPane.showMessageDialog(null, "The number of days is not a number");
				}
				
				
			}
		});
		btnConfirm.setBounds(501, 571, 89, 23);
		add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(405, 571, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UitleningStap1Panel panel = new UitleningStap1Panel();
				panel.setSelectedItems(items);
				MainWindowChangedFiringSource.getInstance().fireChanged(panel);
			}
		});
		add(btnBack);
	}
	
	public void setItems(List<Item> items){
		this.items = items;
	}
}
