package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.panels.*;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private ButtonPanel buttonPanel;

	public MainWindow() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 200, 600 };
		gbl.rowHeights = new int[] { 0 };
		gbl.columnWeights = new double[] { Double.MIN_VALUE };
		gbl.rowWeights = new double[] { Double.MIN_VALUE };

		contentPanel = new JPanel();
		contentPanel.setLayout(gbl);
		buttonPanel = new ButtonPanel(this);
		setButtonPanel(buttonPanel);

		this.setContentPane(contentPanel);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setViewPanel(JPanel panel) {
		GridBagConstraints gbc_viewPanel = new GridBagConstraints();
		gbc_viewPanel.gridy = 0;
		gbc_viewPanel.gridx = 1;
		gbc_viewPanel.fill = GridBagConstraints.BOTH;
		contentPanel.add(panel, gbc_viewPanel);
	}

	private void setButtonPanel(ButtonPanel buttonPanel) {
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.gridy = 0;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		contentPanel.add(buttonPanel, gbc_buttonPanel);
	}
	
	public void changeViewPanel(JPanel panel) {	
		contentPanel.removeAll();
		setButtonPanel(buttonPanel);
		setViewPanel(panel);
		setVisible(true);
	}
	

}
