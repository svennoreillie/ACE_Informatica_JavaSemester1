package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfStreet;
	private JTextField tfNumber;
	private JTextField tfBox;
	private JTextField tfZip;
	private JTextField tfCity;
	private JTextField tfCountry;
	private JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerWindow frame = new CustomerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(85, 11, 115, 20);
		contentPane.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 14, 65, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(234, 14, 65, 14);
		contentPane.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(309, 11, 115, 20);
		contentPane.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 45, 46, 14);
		contentPane.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(85, 42, 115, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 414, 2);
		contentPane.add(separator);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdress.setBounds(10, 93, 46, 14);
		contentPane.add(lblAdress);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 118, 46, 14);
		contentPane.add(lblStreet);
		
		tfStreet = new JTextField();
		tfStreet.setBounds(54, 115, 170, 20);
		contentPane.add(tfStreet);
		tfStreet.setColumns(10);
		
		JLabel lblNr = new JLabel("Nr.");
		lblNr.setBounds(234, 118, 30, 14);
		contentPane.add(lblNr);
		
		tfNumber = new JTextField();
		tfNumber.setBounds(260, 115, 50, 20);
		contentPane.add(tfNumber);
		tfNumber.setColumns(10);
		
		JLabel lblBox = new JLabel("Box");
		lblBox.setBounds(320, 118, 30, 14);
		contentPane.add(lblBox);
		
		tfBox = new JTextField();
		tfBox.setBounds(360, 115, 50, 20);
		contentPane.add(tfBox);
		tfBox.setColumns(10);
		
		tfZip = new JTextField();
		tfZip.setBounds(54, 146, 70, 20);
		contentPane.add(tfZip);
		tfZip.setColumns(10);
		
		JLabel lblZip = new JLabel("ZIP");
		lblZip.setBounds(10, 149, 46, 14);
		contentPane.add(lblZip);
		
		tfCity = new JTextField();
		tfCity.setBounds(176, 146, 86, 20);
		contentPane.add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(150, 149, 30, 14);
		contentPane.add(lblCity);
		
		tfCountry = new JTextField();
		tfCountry.setBounds(338, 146, 86, 20);
		contentPane.add(tfCountry);
		tfCountry.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(282, 149, 46, 14);
		contentPane.add(lblCountry);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfFirstName.setText("");
				tfLastName.setText("");
				tfEmail.setText("");
				tfStreet.setText("");
				tfNumber.setText("");
				tfBox.setText("");
				tfZip.setText("");
				tfCity.setText("");
				tfCountry.setText("");
			}
		});
		btnClear.setBounds(335, 228, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnRegister.setEnabled(false);
		btnRegister.setBounds(239, 228, 89, 23);
		contentPane.add(btnRegister);

	}
	
	/**
	 * Enables the Registration button
	 * If any text fields (aside from Box and E-mail) are empty, the button will remain disabled.
	 */
	public void enableRegisterButton(){
		if (tfFirstName.getText().equals("") || tfLastName.getText().equals("")
		|| tfStreet.getText().equals("") || tfNumber.getText().equals("")
		|| tfZip.getText().equals("") || tfCity.getText().equals("") || tfCountry.getText().equals("")){
			btnRegister.setEnabled(false);
		}
		else{
			btnRegister.setEnabled(true);
		}
	}
	
}
