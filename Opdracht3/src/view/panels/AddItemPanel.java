package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import common.DBException;
import common.DBMissingException;
import common.enums.*;
import common.factories.AddressFactory;
import common.factories.CustomerFactory;
import common.factories.PersonFactory;
import controller.CustomerController;
import controller.event.MainWindowChangedFiringSource;
import model.Address;
import model.Customer;
import model.Person;
import view.MainWindow;
import view.tableModels.CustomerTableModel;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;

public class AddItemPanel extends JPanel{
	/**
	 * Authors Peter Vervoort and Geert Van Weyenbergh
	 */
	private static final long serialVersionUID = -467240657158316693L;
	private JTextField txtField_ItemTitel;
	//private JLabel 
	//<String> toegevoegd bij cmb_Soort
	private JComboBox<String> cmb_Soort;
	private JComboBox<String> cmbType;
	private JTextField txtField_Uitleenprijs;
	
	public AddItemPanel (){
		
		Dimension dimension = new Dimension(600, 600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel lblTitel = new JLabel("Titel");
		lblTitel.setBounds(45, 35, 70, 14);
		add(lblTitel);
		
		txtField_ItemTitel = new JTextField();
		txtField_ItemTitel.setBounds(150, 32, 193, 20);
		add(txtField_ItemTitel);
		txtField_ItemTitel.setColumns(10);
		
		JLabel lblSoort = new JLabel("Soort");
		lblSoort.setBounds(45, 80, 46, 14);
		add(lblSoort);
		
		List<String> soortArray = new ArrayList<String>();
		soortArray.add("CD");
		soortArray.add("DVD");
		soortArray.add("GAMES");
		//JComboBox<String> cmbType = new JComboBox<String>();
		JComboBox<String> cmb_Soort = new JComboBox<String>();
		for (String string : soortArray) {
			cmb_Soort.addItem(string);
		}
		cmb_Soort.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()== ItemEvent.SELECTED) {
					cmbType.setEnabled(true);
					//leegmaken moet voorzien zijn indien iemand zijn selectiekeuze wijzigt
					cmbType.removeAll();
					switch (cmb_Soort.getSelectedIndex()) {
					case 0:
						for (EnumTypeCd type : EnumTypeCd.values()) {
							cmbType.addItem(type.toString());
						}
						break;
					case 1:
						for (EnumTypeDvd type : EnumTypeDvd.values()) {
							cmbType.addItem(type.toString());
						}
						break;
					case 2:
						for (EnumTypeGame type : EnumTypeGame.values()) {
							cmbType.addItem(type.toString());
						}
						break;
					}
				}
				else {
					cmbType.setEnabled(false);
				}
				
			}
		});
		cmb_Soort.setBounds(150, 77, 193, 20);
		add(cmb_Soort);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(45, 129, 46, 14);
		add(lblType);
		
		JComboBox<String> cmbType = new JComboBox<String>();
		cmbType.setEnabled(false);
		cmbType.setBounds(150, 126, 193, 20);
		add(cmbType);
		
		JButton btnCreateNewItem = new JButton("Create New Item");
		btnCreateNewItem.setBounds(150, 235, 162, 54);
		add(btnCreateNewItem);
		
		JLabel lblUitleenprijs = new JLabel("Uitleenprijs");
		lblUitleenprijs.setBounds(45, 154, 82, 42);
		add(lblUitleenprijs);
		
		txtField_Uitleenprijs = new JTextField();
		txtField_Uitleenprijs.setBounds(150, 165, 193, 20);
		add(txtField_Uitleenprijs);
		txtField_Uitleenprijs.setColumns(10);
			
		
	}
	private void enableAll(){
		//enables all text fields
		this.txtField_Uitleenprijs.setEnabled(true);	
		}
}
