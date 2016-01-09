
package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Item;

import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	
	private List<Item> items;
	
 	public UitleningStap2Panel(){
 		items = new ArrayList<Item>();
 		
		setSize(600,600);
		setLayout(null);
		
		JLabel lblNaam = new JLabel("Naam: ");
		lblNaam.setBounds(10, 11, 46, 14);
		add(lblNaam);
		
		textField = new JTextField();
		textField.setBounds(56, 8, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 524);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNumberOfDays = new JLabel("Number of Days:");
		lblNumberOfDays.setBounds(10, 575, 99, 14);
		add(lblNumberOfDays);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 571, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(501, 571, 89, 23);
		add(btnConfirm);
	}
	
	public void setItems(List<Item> items){
		this.items = items;
	}
}
