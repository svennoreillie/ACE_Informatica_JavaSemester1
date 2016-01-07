package view.panels;

import java.util.List;

import javax.swing.JPanel;

import model.Item;
import view.custom.Button;
import view.tableModels.UitleningTableModel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.enums.EventEnum;
import controller.event.MainWindowChangedFiringSource;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UitleningStap1Panel extends JPanel{
	
	List<Item> items;
	private JTextField textField;
	private JTable table;
	
	public UitleningStap1Panel() {
		setSize(600,600);
		setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 11, 46, 14);
		add(lblType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "Cd", "Dvd", "Game"}));
		comboBox.setBounds(46, 8, 81, 20);
		add(comboBox);
		
		JLabel lblSearch = new JLabel("Search: ");
		lblSearch.setBounds(137, 11, 60, 14);
		add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(193, 8, 134, 20);
		add(textField);
		textField.setColumns(10);
		
		Button btnNext = new Button("Next");
		btnNext.addActionListener(MainWindowChangedFiringSource.getInstance());
		btnNext.setActionCommand(EventEnum.RENTBUTTON2);
		btnNext.setBounds(501, 566, 89, 23);
		add(btnNext);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 580, 517);
		add(scrollPane);
		
//		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Description", "Selected"
//			}
//		));\
		UitleningTableModel tableModel = new UitleningTableModel();
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
	}
	
	public void setItems(List<Item> items){
		this.items = items;
		for(Item i : items){
			//table.set
		}
		
	}
}
