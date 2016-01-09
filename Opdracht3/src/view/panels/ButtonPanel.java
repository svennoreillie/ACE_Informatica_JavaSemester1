package view.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import common.enums.EventEnum;
import controller.WindowController;
import controller.event.MainWindowChangedFiringSource;
import view.custom.Button;

/**
 * 
 * @author Huybrechts
 *
 */

public class ButtonPanel extends JPanel {
	
	private class ButtonPanelKeyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	if (e.isAltDown()) {
            		dropDbTables.setVisible(true);
            	}
            } else {
            	dropDbTables.setVisible(false);
            }

            return false;
        }
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Button itemManagement;
	Button yellowButton;
	Button redButton;
	Button rentalStatusButton;
	Button customerOverviewButton;
	Button returnItemButton;
	Button dropDbTables;

	public ButtonPanel() {
		super();
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 50,100,50 };
		int numberOfRows=14;
		int[] heights=new int[numberOfRows];
		int windowHeight=580;
		for(int i=0;i<numberOfRows;i++){
			heights[i]=windowHeight/numberOfRows;
		}
		gbl.rowHeights = heights;
		setLayout(gbl);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		itemManagement = new Button("Item Management");
		itemManagement.addActionListener(listener);
		itemManagement.setActionCommand(EventEnum.ITEMMANAGEMENT);
		add(itemManagement,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		customerOverviewButton = new Button("Customer overview");
		customerOverviewButton.addActionListener(listener);
		customerOverviewButton.setActionCommand(EventEnum.CUSTOMEROVERVIEWBUTTONEVENT);
		add(customerOverviewButton,gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		customerOverviewButton = new Button("Rent");
		customerOverviewButton.addActionListener(listener);
		customerOverviewButton.setActionCommand(EventEnum.RENTBUTTON1);
		add(customerOverviewButton,gbc);
		
		
	
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		returnItemButton = new Button("Returns");
		returnItemButton.addActionListener(listener);
		returnItemButton.setActionCommand(EventEnum.RETURNITEMBUTTONEVENT);
		add(returnItemButton,gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		dropDbTables = new Button("DROP SQL Tables");
		dropDbTables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				WindowController controller = new WindowController();
				controller.dropDB();
			}
		});
		dropDbTables.setVisible(false);
		add(dropDbTables,gbc);
		
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new ButtonPanelKeyDispatcher());
	}
}
