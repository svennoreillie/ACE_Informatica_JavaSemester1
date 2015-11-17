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
	MainWindow mainWindow;

	public ButtonPanel(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		blueButton = new JButton("Blue");
		blueButton.addActionListener(new blueButtonListener());
		yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(new yellowButtonListener());
		redButton = new JButton("Red");
		redButton.addActionListener(new redButtonListener());

		add(blueButton);
		add(yellowButton);
		add(redButton);
	}

	class blueButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Blue button");
			mainWindow.setViewPanel(new BluePanel());
		}
	}

	class redButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Red button");
			mainWindow.setViewPanel(new RedPanel());
		}
	}

	class yellowButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Yellow button");
			mainWindow.setViewPanel(new YellowPanel());
		}
	}

}
