package view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;
import view.custom.Button;
import view.tableModels.UitleningTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.enums.EventEnum;
import controller.WinkelController;
import controller.event.MainWindowChangedFiringSource;
import database.DataService;
import database.DataStrategy;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import common.DBException;
import common.DBMissingException;
import common.enums.EnumItemTypeItems;

/**
 * 
 * @author Huybrechts
 *
 */

public class UitleningStap1Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4382671641959396105L;

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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=comboBox.getSelectedIndex();
				
				switch(i){
				case 0:
					//tableModel.setItems(allItems);
					tableModel.setItemsToShow(Item.class);
					break;
				case 1:
					//tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Cd.class)).collect(Collectors.toList()));
					tableModel.setItemsToShow(Cd.class);
					break;
				case 2:
					//tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Dvd.class)).collect(Collectors.toList()));
					tableModel.setItemsToShow(Dvd.class);
					break;
				case 3:
					//tableModel.setItems(allItems.stream().filter(item -> item.getClass().equals(Game.class)).collect(Collectors.toList()));
					tableModel.setItemsToShow(Game.class);
					break;
				default:
					tableModel.setItems(allItems);
				}
					
				
			}
		});
		String[] values= new String[EnumItemTypeItems.values().length+1];
		values[0]="ALL";
		
		for(int i=0;i<EnumItemTypeItems.values().length;i++){
			values[i+1]=EnumItemTypeItems.values()[i].toString();
		}
		
		comboBox.setModel(new DefaultComboBoxModel<String>(values));
		comboBox.setBounds(46, 8, 81, 20);
		add(comboBox);
		
		JLabel lblSearch = new JLabel("Search: ");
		lblSearch.setBounds(137, 11, 60, 14);
		add(lblSearch);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(193, 8, 134, 20);
		add(textField);
		textField.setColumns(10);
		
		Button btnNext = new Button("Next");
		btnNext.setBounds(501, 566, 89, 23);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UitleningStap2Panel panel = new UitleningStap2Panel();
				panel.setItems(getSelectedItems());
				MainWindowChangedFiringSource.getInstance().fireChanged(panel);
			}
		});
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
		
		try{
			List<Uitlening> uitlenignen = DataStrategy.getDataService(Uitlening.class).getAll();
			//DataStrategy.getDataService(Uitlening.class).remove(entity);
			tableModel.setUitleningen(uitlenignen);
		} catch(DBMissingException | DBException e1){
			
		}
		
		
		
		setAllItems(allItems);
		tableModel.setItemsToShow(Item.class);
	}
	
	public void setAllItems(List<Item> items){
		this.allItems = items;
		tableModel.setItems(items);
		
	}

	public List<Item> getSelectedItems(){
		return tableModel.getSelectedItem();
	}
}
