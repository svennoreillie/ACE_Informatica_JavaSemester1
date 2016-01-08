package view.panels;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.AntiMagicStrings;
import common.DBException;
import common.DBMissingException;
import common.enums.*;
import controller.SpamRegistratieController;
import controller.WinkelController;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemManagementPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711869879888796677L;
	private JTextField txt_Titel;
	private JComboBox<String> cmb_Soort;
	private JComboBox<String> cmb_Type;
	private JTextField txt_Uitleenprijs;
	private JLabel lbl_Titel;
	private JLabel lbl_Soort;
	private JLabel lbl_Type;
	private JButton btn_CreateNewItem;
	private JLabel lbl_Uitleenprijs;

	public ItemManagementPanel() {

		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);

		lbl_Titel = new JLabel("Titel");
		lbl_Titel.setBounds(45, 35, 70, 14);
		add(lbl_Titel);

		txt_Titel = new JTextField();
		txt_Titel.setBounds(218, 32, 193, 20);
		add(txt_Titel);
		txt_Titel.setColumns(10);

		lbl_Soort = new JLabel("Type");
		lbl_Soort.setBounds(45, 80, 70, 14);
		add(lbl_Soort);

		cmb_Type = new JComboBox<String>();
		cmb_Type.setEnabled(false);
		cmb_Type.setBounds(218, 126, 193, 20);
		add(cmb_Type);

		cmb_Soort = new JComboBox<String>();
		cmb_Soort.setBounds(218, 77, 193, 20);
		add(cmb_Soort);

		lbl_Type = new JLabel("Category");
		lbl_Type.setBounds(45, 129, 82, 14);
		add(lbl_Type);

		btn_CreateNewItem = new JButton("Create New Item");
		btn_CreateNewItem.setBounds(195, 236, 162, 54);
		add(btn_CreateNewItem);

		lbl_Uitleenprijs = new JLabel("Loan rate");
		lbl_Uitleenprijs.setBounds(45, 154, 82, 42);
		add(lbl_Uitleenprijs);

		txt_Uitleenprijs = new JTextField();
		txt_Uitleenprijs.setBounds(218, 165, 193, 20);
		add(txt_Uitleenprijs);

		cmb_Soort.addItem("CD");
		cmb_Soort.addItem("DVD");
		cmb_Soort.addItem("GAME");
		cmb_Soort.setSelectedIndex(-1);

		cmb_Soort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cmb_Type.setEnabled(true);
					cmb_Type.removeAllItems();
					switch (cmb_Soort.getSelectedIndex()) {
					case 0:
						for (EnumTypeCd type : EnumTypeCd.values()) {
							cmb_Type.addItem(type.toString());
						}
						break;
					case 1:
						for (EnumTypeDvd type : EnumTypeDvd.values()) {
							cmb_Type.addItem(type.toString());
						}
						break;
					case 2:
						for (EnumTypeGame type : EnumTypeGame.values()) {
							cmb_Type.addItem(type.toString());
						}
						break;
					}
				}
			}
		});

		btn_CreateNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean input = true;
				String titel = txt_Titel.getText();
				if (titel == "") {
					JOptionPane.showMessageDialog(null, AntiMagicStrings.TitleInvalid);
					input = false;
				}

				if (cmb_Soort.getSelectedIndex() == -1 || cmb_Type.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, AntiMagicStrings.ComboBoxSelectionInvalid);
					input = false;
				}
				double uitleenprijs = 0;
				try {
					uitleenprijs = Double.parseDouble(txt_Uitleenprijs.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, AntiMagicStrings.LoanRateInvalid);
					input = false;
				}
				
				if (input) {
					BigDecimal bd = new BigDecimal(String.valueOf(uitleenprijs));
					SpamRegistratieController spamreg = new SpamRegistratieController();
					switch (cmb_Soort.getSelectedItem().toString()) {
					case "CD":
						Cd cd = new Cd(titel,bd,uitleenprijs,EnumTypeCd.valueOf(cmb_Type.getSelectedItem().toString()));
						WinkelController<Cd> winkelControllerCd = new WinkelController<Cd>(Cd.class);
						winkelControllerCd.addObserver(spamreg);
						winkelControllerCd.AddItemToStore(cd);
						break;
					case "DVD":
						Dvd dvd = new Dvd(titel,bd,uitleenprijs,EnumTypeDvd.valueOf(cmb_Type.getSelectedItem().toString()));
						WinkelController<Dvd> winkelControllerDvd = new WinkelController<Dvd>(Dvd.class);
						winkelControllerDvd.addObserver(spamreg);
						winkelControllerDvd.AddItemToStore(dvd);
						break;
					case "GAME":
						Game game = new Game(titel,bd,uitleenprijs,EnumTypeGame.valueOf(cmb_Type.getSelectedItem().toString()));
						WinkelController<Game> winkelControllerGame = new WinkelController<Game>(Game.class);
						winkelControllerGame.addObserver(spamreg);
						winkelControllerGame.AddItemToStore(game);
						break;
					}
				}
				txt_Titel.setText(null);
				cmb_Soort.setSelectedIndex(-1);
				cmb_Type.setSelectedIndex(-1);
				txt_Uitleenprijs.setText(null);
				JOptionPane.showMessageDialog(null, AntiMagicStrings.ItemAdded);
			}
		});
	}
	
//	private void sendNewsLetter (String titel) throws DBMissingException, DBException{
//		DataService <Customer> dataBaseCustomer = DataStrategy.getDataService(Customer.class); 
//		List<Customer> customerList =  dataBaseCustomer.getAll();
//		for (Customer customer : customerList) {
//			if (customer.getSpam()) {
//				System.out.println("Newsletter sent to " + customer.getPerson().getFirstName() + " " + customer.getPerson().getLastName());
//			}
//		}
//		
//	}
}
