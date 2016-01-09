package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import common.DBException;
import common.DBMissingException;
import controller.ReceiptController;
import controller.UitleenController;
import controller.event.MainWindowChangedFiringSource;
import model.ConcreteReceipt;
import model.Customer;
import model.Item;
import model.Uitlening;
import view.tableModels.ItemReturnTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author André Nóbrega
 *
 */
public class ItemTeruggave2 extends JPanel {
	private JTable itemTable;
	private ItemReturnTableModel tableModel;
	private UitleenController controller = new UitleenController();

	/**
	 * Create the panel.
	 */
	public ItemTeruggave2(Customer customer) {

		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 519);
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
		scrollPane.setViewportView(itemTable);
		
		JLabel lblSelectReturnedItems = new JLabel("Select returned item(s):");
		lblSelectReturnedItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectReturnedItems.setBounds(10, 11, 568, 14);
		add(lblSelectReturnedItems);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ArrayList<Item> returnedItems = new ArrayList<Item>();
				for (Uitlening uitlening : tableModel.getSelectedItems()) {
					returnedItems.add(uitlening.getUitgeleendItem());
				}
				
				ConcreteReceipt receipt = new ConcreteReceipt();
				receipt.setItems(returnedItems);
				ReceiptController.printReceipt(receipt);
				
				controller.uitleningVanMeerdereItemsStoppen(tableModel.getSelectedItems());
				JOptionPane.showMessageDialog(null, "Receipt has been printed, check console for details");
				backToItemReturnOverview();
			}
		});
		btnCheckout.setBounds(501, 566, 89, 23);
		add(btnCheckout);
		
		JButton btnBack = new JButton("< Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				backToItemReturnOverview();
			}
		});
		btnBack.setBounds(402, 566, 89, 23);
		add(btnBack);
		
	}
	
	private void backToItemReturnOverview() {
		ItemTeruggave1 uitleningLijst = new ItemTeruggave1();
		MainWindowChangedFiringSource.getInstance().fireChanged(uitleningLijst);
	}

}
