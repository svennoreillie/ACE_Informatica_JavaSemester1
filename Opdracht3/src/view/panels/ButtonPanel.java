package view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.MainWindow;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton blueButton;
	JButton yellowButton;
	JButton redButton;
	
	BluePanel bluePanel;
	YellowPanel yellowPanel;
	RedPanel redPanel;
	
	MainWindow mainWindow;

	public ButtonPanel(MainWindow mainWindow) {
		super();
		
		bluePanel=new BluePanel();
		yellowPanel=new YellowPanel();
		redPanel=new RedPanel();

		this.mainWindow = mainWindow;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		blueButton = new JButton("Blue");
		blueButton.addActionListener(new blueButtonListener());
		add(blueButton);
		
		yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(new yellowButtonListener());
		add(yellowButton);
		
		redButton = new JButton("Red");
		redButton.addActionListener(new redButtonListener());
		add(redButton);
		
		
	}

	class blueButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(bluePanel);
			bluePanel.repaint();
		}
	}

	class redButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(redPanel);
			redPanel.repaint();
		}
	}

	class yellowButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.changeViewPanel(yellowPanel);
			yellowPanel.repaint();
		}
	}

}
