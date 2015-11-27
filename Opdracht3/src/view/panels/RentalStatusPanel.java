package view.panels;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class RentalStatusPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RentalStatusPanel(){
		super();
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowWeights = new double[]{0.0, 1.0};
		gbl.columnWeights = new double[]{0.0, 1.0};
		gbl.columnWidths = new int[] {5, 598};
		gbl.rowHeights = new int[] {50,550};
		
		setLayout(gbl);
		
		this.setSize(600,600);
		
		JLabel HuurStatusLabel = new JLabel("Huur Status");
		HuurStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_HuurStatusLabel = new GridBagConstraints();
		gbc_HuurStatusLabel.anchor = GridBagConstraints.WEST;
		gbc_HuurStatusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_HuurStatusLabel.gridx = 1;
		gbc_HuurStatusLabel.gridy = 0;
		add(HuurStatusLabel, gbc_HuurStatusLabel);
		
		JPanel ItemsPanel = new JPanel();
		
		GridBagConstraints gbc_ItemsPanel = new GridBagConstraints();
		gbc_ItemsPanel.fill = GridBagConstraints.BOTH;
		gbc_ItemsPanel.gridx = 1;
		gbc_ItemsPanel.gridy = 1;
		add(ItemsPanel, gbc_ItemsPanel);
		
		
	}
}
