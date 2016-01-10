package view.custom;

import javax.swing.JButton;

import common.enums.EventEnum;

/**
 * 
 * @author Huybrechts
 *
 */

public class Button extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Button(String arg0){
		super(arg0);
	}
	
	public void setActionCommand(EventEnum eventEnum){
		super.setActionCommand(eventEnum.getActionCommand());
	}
	
}
