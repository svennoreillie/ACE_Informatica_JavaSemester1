package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;

import common.DBException;
import common.DBMissingException;
import controller.UitleenController;
import model.Customer;
import model.Uitlening;
import view.tableModels.ItemReturnTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemTeruggave2 extends JPanel {
	private JTable itemTable;
	private ItemReturnTableModel tableModel;
	private ArrayList<Uitlening> uitleningenLijst = new ArrayList<Uitlening>();
	private UitleenController controller = new UitleenController();

	/**
	 * Create the panel.
	 */
	public ItemTeruggave2(Customer customer) {

		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 580, 507);
		add(scrollPane);
		
		tableModel = new ItemReturnTableModel(customer);
		itemTable = new JTable(tableModel);
		try {
			tableModel.updateTable();
		} catch (NoSuchElementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DBMissingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		itemTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Uitlening selectedItem = controller.getList().get(itemTable.getSelectedRow());
					if (!isItemBeingReturned(selectedItem)) {
						checkItem(selectedItem);
						addItemToReturnList(selectedItem);
					}
					else{
						uncheckItem(selectedItem);
						removeItemFromReturnList(selectedItem);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(itemTable);
		
		JLabel lblSelectReturnedItems = new JLabel("Select returned items:");
		lblSelectReturnedItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectReturnedItems.setBounds(10, 11, 568, 14);
		add(lblSelectReturnedItems);
		
		JButton btnNext = new JButton("Next >");
		btnNext.setBounds(501, 566, 89, 23);
		add(btnNext);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(402, 566, 89, 23);
		add(btnBack);
		
	}

	private void addItemToReturnList(Uitlening uitlening){
		uitleningenLijst.add(uitlening);
	}
	
	private void removeItemFromReturnList(Uitlening uitlening){
		uitleningenLijst.remove(uitlening);
	}
	
	private boolean isItemBeingReturned(Uitlening item){
		return item.getWordtGedeponeerd();
	}
	

	private void checkItem(Uitlening uitlening) {
		uitlening.setWordtGedeponeerd(true);
	}
	private void uncheckItem(Uitlening uitlening) {
		uitlening.setWordtGedeponeerd(false);
	}

}
