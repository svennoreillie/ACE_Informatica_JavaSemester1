package view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.*;
import common.factories.*;
import controller.WinkelController;
import controller.WinkelService;
import controller.event.WindowChangedService;

import database.helpers.DataSource;
import database.*;
import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.*;
import view.panels.*;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;

public class MainWindow extends JFrame implements WindowChangedService {

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
		buttonPanel = new ButtonPanel();
		setButtonPanel(buttonPanel);
			

		this.setContentPane(contentPanel);
		this.setSize(800, 630);
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

		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		contentPanel.add(buttonPanel, gbc_buttonPanel);
	}

	public void changeViewPanel(JPanel panel) {
		contentPanel.removeAll();
		setButtonPanel(buttonPanel);
		setViewPanel(panel);
		setVisible(true);
	}

	@Override
	public void fireChanged(JPanel panel) {
		changeViewPanel(panel);
	}

}
