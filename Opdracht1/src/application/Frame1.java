package application;

import java.awt.EventQueue;

import com.toedter.calendar.JDateChooser;

import components.SpinnerDemo;
import model.DateFactory;

import java.util.List;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

//Geert toegevoegd 2 November
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.util.Calendar;
import java.util.Date;
//TO DO interface SpinnerModel van javax.swing

public class Frame1 extends JFrame {

	/**
	 * 
	 */
	
	//toegevoegd Geert 3 november bron stack overflow private class gebruiken om te extenden naar JPanel
		private class MyPanel extends JPanel{
			 /**
		     * Return the formatted text field used by the editor, or
		     * null if the editor doesn't descend from JSpinner.DefaultEditor.
		     */
		    public JFormattedTextField getTextField(JSpinner spinner) {
		        JComponent editor = spinner.getEditor();
		        if (editor instanceof JSpinner.DefaultEditor) {
		            return ((JSpinner.DefaultEditor)editor).getTextField();
		        } else {
		            System.err.println("Unexpected editor type: "
		                               + spinner.getEditor().getClass()
		                               + " isn't a descendant of DefaultEditor");
		            return null;
		        }
		    }
			 /**
		     * DateFormatSymbols returns an extra, empty value at the
		     * end of the array of months.  Remove it.
		     */
			//3 Nov Geert removed static to resolve error
		     protected String[] getMonthStrings() {
		        String[] months = new java.text.DateFormatSymbols().getMonths();
		        int lastIndex = months.length - 1;

		        if (months[lastIndex] == null
		           || months[lastIndex].length() <= 0) { //last item empty
		            String[] monthStrings = new String[lastIndex];
		            System.arraycopy(months, 0,
		                             monthStrings, 0, lastIndex);
		            return monthStrings;
		        } else { //last item not empty
		            return months;
		        }
		    }
			//Geert 3 november removed static to resolve error
		protected JSpinner addLabeledSpinner(Container c,
                    String label,
                    SpinnerModel model) {
				JLabel l = new JLabel(label);
				c.add(l);
				
				JSpinner spinner = new JSpinner(model);
				l.setLabelFor(spinner);
				c.add(spinner);
				
				return spinner;
				}
		  /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event dispatch thread.
	     */
			//Geert 3 november removed static to resolve error
		    private void createAndShowGUI() {
		        //Create and set up the window.
		        JFrame frame = new JFrame("SpinnerDemo");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        //Add content to the window.
		        frame.add(new SpinnerDemo(false));

		        //Display the window.
		        frame.pack();
		        frame.setVisible(true);
		    }
		    //Geert 3november removed static
		    public void main(String[] args) {
		        //Schedule a job for the event dispatch thread:
		        //creating and showing this application's GUI.
		        SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                //Turn off metal's use of bold fonts
			        UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
		            }
		        });
		    }
			
		}
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JDateChooser dp = new JDateChooser();
	private JComboBox<House> unitsAvailable = new JComboBox<House>();

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
		//Geert toegevoegd 2 november; hieronder code voor Jspinner?
		/*
		 * 
		 */
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int numbernights = Integer.parseInt(textField_2.getText());

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate = sdf.format(dp.getDate());
					model.Date date = DateFactory.generateDate(stringDate);

					ReservationService rs = new ReservationService();
					List<House> houselist = rs.getAvailableHouses(date, numbernights);
					for (House h : houselist) {
						unitsAvailable.addItem(h);
					}

					if (houselist.size() < 1) {
						JOptionPane.showMessageDialog(null, "No bungalows available.");
					} else {
						unitsAvailable.setEnabled(true);
					}
				}

				catch (NumberFormatException e1) {
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
						textField.setEnabled(true);
						textField_1.setEnabled(true);
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

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(140, 134, 142, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("First name tennant");
		lblNewLabel.setBounds(24, 172, 95, 14);
		frame.getContentPane().add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(140, 169, 142, 20);
		frame.getContentPane().add(textField_1);

		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {/*
					int numbernights = Integer.parseInt(textField_2.getText());

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate = sdf.format(dp.getDate());
					model.Date date = DateFactory.generateDate(stringDate);

					ReservationService rs = new ReservationService();
					List<House> houselist = rs.getAvailableHouses(date, numbernights);
					for (House h : houselist) {
						unitsAvailable.addItem(h);
					}

					if (houselist.size() < 1) {
						JOptionPane.showMessageDialog(null, "No bungalows available.");
					} else {
						unitsAvailable.setEnabled(true);
					}*/
				}

				catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Throwable ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(297, 209, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		textField_2 = new JTextField();
		textField_2.setBounds(138, 52, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		dp = new JDateChooser();
		dp.setDateFormatString("dd/MM/yyyy");
		dp.setBounds(140, 21, 125, 23);
		frame.getContentPane().add(dp);
		Date sysTime = new Date();
		dp.setDate(sysTime);

	}
}
