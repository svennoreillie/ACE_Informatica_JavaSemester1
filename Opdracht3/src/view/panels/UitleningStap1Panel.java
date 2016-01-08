package view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import model.Item;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;
import view.custom.Button;
import view.tableModels.UitleningTableModel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import common.enums.EventEnum;
import controller.WinkelController;
import controller.event.MainWindowChangedFiringSource;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import common.enums.EnumItemTypeItems;

public class UitleningStap1Panel extends JPanel{
	
	private List<Item> allItems;
	private JTextField textField;
	private JTable table;
	private UitleningTableModel tableModel;
	
	public UitleningStap1Panel() {
		tableModel = new UitleningTableModel();
		
		setSize(600,600);
		setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 11, 46, 14);
		add(lblType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=comboBox.getSelectedIndex();
				
				switch(i){
				case 1:
					tableModel.setItems(allItems);
					break;
				case 2:
					//cd
					tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Cd.class)).collect(Collectors.toList()));
					break;
				case 3:
					//dvd
					tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Dvd.class)).collect(Collectors.toList()));
					break;
				case 4:
					//game
					tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Game.class)).collect(Collectors.toList()));
					break;
				default:
					tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Cd.class)).collect(Collectors.toList()));
				}
					
				
			}
		});
		String[] values= new String[EnumItemTypeItems.values().length+1];
		values[0]="ALL";
		
		for(int i=0;i<EnumItemTypeItems.values().length;i++){
			values[i+1]=EnumItemTypeItems.values()[i].toString();
		}
		
		comboBox.setModel(new DefaultComboBoxModel(values));
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
		
		

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		List<Item> allItems = new ArrayList<Item>();
		allItems.addAll(new WinkelController<Game>(Game.class).getAllSortedByName());
		allItems.addAll(new WinkelController<Cd>(Cd.class).getAllSortedByName());
		allItems.addAll(new WinkelController<Dvd>(Dvd.class).getAllSortedByName());
		
		setAllItems(allItems);
	}
	
	public void setAllItems(List<Item> items){
		this.allItems = items;
		tableModel.setItems(items);
		
	}
}
