package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class UitleningStap2Panel extends JPanel{
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	
	public UitleningStap2Panel(){
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
	
}
