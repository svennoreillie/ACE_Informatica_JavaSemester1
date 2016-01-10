package view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;

import common.enums.EventEnum;
import controller.WinkelController;
import controller.event.MainWindowChangedFiringSource;
import database.DataService;
import database.DataStrategy;
import javax.swing.JScrollPane;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import common.AntiMagicStrings;
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

	private JTextField searchTF;
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
					tableModel.setItemsToShow(Item.class);
					break;
				case 1:
					tableModel.setItemsToShow(Cd.class);
					break;
				case 2:
					tableModel.setItemsToShow(Dvd.class);
					break;
				case 3:
					tableModel.setItemsToShow(Game.class);
					break;
				default:
					tableModel.setItemsToShow(Item.class);
				}
					
				searchItems();
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
		
		searchTF = new JTextField();
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				searchItems();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchItems();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				searchItems();
			}
		};
		searchTF.getDocument().addDocumentListener(documentListener);
		searchTF.setBounds(193, 8, 134, 20);
		searchTF.setColumns(10);
		add(searchTF);
		
		Button btnNext = new Button("Next");
		btnNext.setBounds(501, 566, 89, 23);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getSelectedItems()==null || getSelectedItems().isEmpty()){
					JOptionPane.showMessageDialog(null, "At least one item has to be selected.");
				}else{
					UitleningStap2Panel panel = new UitleningStap2Panel();
					panel.setItems(getSelectedItems());
					MainWindowChangedFiringSource.getInstance().fireChanged(panel);
				}
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
			tableModel.setUitleningen(uitlenignen);
		} catch(DBMissingException | DBException e1){
			System.out.println(AntiMagicStrings.DBReadError);
		}
		tableModel.setItems(allItems);
		tableModel.setItemsToShow(Item.class);
	}

	public List<Item> getSelectedItems(){
		return tableModel.getSelectedItems();
	}

	public void setSelectedItems(List<Item> items) {
		tableModel.setSelectedItems(items);
	}
	
	private void searchItems(){
		try {
			tableModel.searchTable(searchTF.getText());
		} catch (NoSuchElementException e) {
			System.out.println(AntiMagicStrings.DBReadError);
		} catch (DBMissingException e) {
			System.out.println(AntiMagicStrings.DBReadError);
		} catch (DBException e) {
			System.out.println(AntiMagicStrings.DBReadError);
		}
		
	}
}
