package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame{

	JPanel contentPanel;
	JPanel buttonPanel;
	JPanel viewPanel;
	
	JButton greenButton;
	JButton blueButton;
	JButton yellowButton;
	
	
	public MainWindow(){
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		viewPanel = new JPanel();
		viewPanel.setBackground(Color.GRAY);		
		
		greenButton=new JButton("Green Button");
		greenButton.addActionListener(new GreenButtonListener());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(greenButton);
		yellowButton=new JButton("Yellow Button");
		yellowButton.addActionListener(new YellowButtonListener());
		blueButton=new JButton("Blue Button");
		blueButton.addActionListener(new BlueButtonListener());
		buttonPanel.add(blueButton);
		buttonPanel.add(yellowButton);
		
		this.setContentPane(contentPanel);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.gridy = 0;
		gbc_buttonPanel.gridx = 0;
		contentPanel.add(buttonPanel, gbc_buttonPanel);
		contentPanel.add(viewPanel);
		
		GridBagLayout gbl_viewPanel = new GridBagLayout();
		gbl_viewPanel.columnWidths = new int[] {200, 600};
		gbl_viewPanel.rowHeights = new int[]{0};
		gbl_viewPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_viewPanel.rowWeights = new double[]{Double.MIN_VALUE};
		contentPanel.setLayout(gbl_viewPanel);
		
		
		this.setVisible(true);
	}
	
	class GreenButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Green Button");			
		}
		
	}
	
	class YellowButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Yellow Button");
			
		}

	}
	
	class BlueButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Blue Button");			
		}
		
	}
	
}
