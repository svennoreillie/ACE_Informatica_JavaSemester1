package view.panels;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

public class RentalStatusPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RentalStatusPanel(){
		super();
		
		setBorder(BorderFactory.createTitledBorder("Huur status"));
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {300, 300};
		gbl.rowHeights = new int[]{300,300};
		
		this.setSize(600,600);
		this.setLayout(gbl);
	}
}
