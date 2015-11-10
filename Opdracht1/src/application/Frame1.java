package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import common.CompositeException;
import model.DateBase;
import model.DateFactory;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Frame1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textLastName;
	private JTextField textFirstName;
	private JTextField textNumber;
	private JDateChooser dp = new JDateChooser();
	private JComboBox<House> unitsAvailable = new JComboBox<House>();
	private JButton btnRegister = new JButton("Register");
	

	private House selectedHouse = null;

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblReservationBungalows = new JLabel("Reservation bungalows");
		lblReservationBungalows.setBounds(0, 0, 434, 14);
		frame.getContentPane().add(lblReservationBungalows);

		JLabel lblFromDate = new JLabel("From date : ");
		lblFromDate.setBounds(24, 30, 76, 14);
		frame.getContentPane().add(lblFromDate);

		JLabel lblNumberOfNights = new JLabel("Number of nights :");
		lblNumberOfNights.setBounds(24, 55, 95, 14);
		frame.getContentPane().add(lblNumberOfNights);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int numbernights = Integer.parseInt(textNumber.getText());

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate = sdf.format(dp.getDate());
					model.Date date = DateFactory.generateDate(stringDate);

					ReservationService rs = new ReservationService();
					List<House> houselist = rs.getAvailableHouses(date, numbernights);
					unitsAvailable.removeAllItems();
					for (House h : houselist) {
						unitsAvailable.addItem(h);
					}

					if (houselist.size() < 1) {
						JOptionPane.showMessageDialog(null, "No bungalows available.");
					} else {
						unitsAvailable.setEnabled(true);
						//unitsAvailable = houselist.toString();
					}
				}
				catch (CompositeException compEx) {
					String fouten = "Er werden meerdere fouten gevonden:";
					for (Exception ex : compEx.getInnerExceptionList()) {
						fouten += String.format("%n%s", ex.getMessage());
					}
					JOptionPane.showMessageDialog(null, fouten);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Throwable ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});
		btnNewButton.setBounds(297, 67, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblUnitsAvailable = new JLabel("Units Available");
		lblUnitsAvailable.setBounds(26, 92, 93, 14);
		frame.getContentPane().add(lblUnitsAvailable);

		unitsAvailable = new JComboBox<House>();
		unitsAvailable.setEnabled(false);
		unitsAvailable.setEditable(true);
		unitsAvailable.setBounds(140, 89, 125, 20);
		unitsAvailable.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					try {
						selectedHouse = (House)event.getItem();
						textLastName.setEnabled(true);
						textFirstName.setEnabled(true);
					} catch (Exception ex) {
						// cast failed
						selectedHouse = null;
					}
				}
			}
		});
		frame.getContentPane().add(unitsAvailable);

		JLabel lblLastNa = new JLabel("Last Name Tennant");
		lblLastNa.setBounds(24, 137, 110, 14);
		frame.getContentPane().add(lblLastNa);

		textLastName = new JTextField();
		textLastName.setEnabled(false);
		textLastName.setBounds(140, 134, 142, 20);
		frame.getContentPane().add(textLastName);
		textLastName.setColumns(10);
		textLastName.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				enableRegisterButton();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				enableRegisterButton();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				enableRegisterButton();
			}		
		});

		JLabel lblNewLabel = new JLabel("First name tennant");
		lblNewLabel.setBounds(24, 172, 95, 14);
		frame.getContentPane().add(lblNewLabel);

		textFirstName = new JTextField();
		textFirstName.setEnabled(false);
		textFirstName.setColumns(10);
		textFirstName.setBounds(140, 169, 142, 20);
		frame.getContentPane().add(textFirstName);
		textFirstName.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				enableRegisterButton();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				enableRegisterButton();
			}
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				enableRegisterButton();	
			}		
		});
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Reservation reservation = new Reservation();
					Person person = new Person();
					ReservationService reservationService = new ReservationService();
					
					
					person.setFirstName(textFirstName.getText());
					person.setLastName(textLastName.getText());					
					reservation.setHouse((House)unitsAvailable.getSelectedItem());
					reservation.setPerson(person);
					reservation.setNumberOfDays(Integer.parseInt(textNumber.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate = sdf.format(dp.getDate());
					model.Date date = DateFactory.generateDate(stringDate);
					reservation.setStartDate(date);
					
					reservationService.CreateReservation(reservation);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		
		btnRegister.setEnabled(false);
		btnRegister.setBounds(297, 209, 89, 23);
		frame.getContentPane().add(btnRegister);

		textNumber = new JTextField();
		textNumber.setBounds(138, 52, 86, 20);
		frame.getContentPane().add(textNumber);
		textNumber.setColumns(10);

		dp = new JDateChooser();
		dp.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate = sdf.format(dp.getDate());
					model.Date date = DateFactory.generateDate(stringDate);
					
					model.Date current = DateFactory.generateDate();
					
					if (date.smallerThan(current)) {
						btnNewButton.setEnabled(false);
					} else {
						btnNewButton.setEnabled(true);
					}
				} catch (Exception e) {
					
				}
			}
		});
		dp.setDateFormatString("dd/MM/yyyy");
		dp.setBounds(140, 21, 125, 23);
		Date minDate = new Date();
		dp.setMinSelectableDate(minDate);
		frame.getContentPane().add(dp);
		Date sysTime = new Date();
		dp.setDate(sysTime);

		}
	
		public void enableRegisterButton(){
			if(textFirstName.getText().equals("") || textLastName.getText().equals("")){
				btnRegister.setEnabled(false);
			}
			else{
				btnRegister.setEnabled(true);
			}
		}
		
		private void clearWindow(){
			dp.setCalendar(null);
			textNumber.setText("");
			unitsAvailable.setSelectedIndex(-1);
			textLastName.setText("");
			textFirstName.setText("");
		}
}
