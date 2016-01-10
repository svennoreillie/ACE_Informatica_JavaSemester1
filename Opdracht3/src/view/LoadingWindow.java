/**
 * @Autor: Sven Noreillie
 * 
 */

package view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.*;
import common.factories.*;
import controller.WinkelController;
import controller.WinkelService;
import controller.event.MainWindowChangedFiringSource;
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
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class LoadingWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar = null;
	private JLabel lblLoading = null;

	public LoadingWindow() {

		this.setLocationRelativeTo(null);
		this.setTitle("Loading database");
		this.setSize(400, 80);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		
		getContentPane().add(progressBar, BorderLayout.CENTER);
		
		JLabel label = new JLabel("      ");
		getContentPane().add(label, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel("    ");
		getContentPane().add(label_1, BorderLayout.EAST);
		
		lblLoading = new JLabel("Loading", SwingConstants.CENTER);
		getContentPane().add(lblLoading, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private void updateProgress(int step, int maxStep, String text) {
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	if (maxStep == 0)  return;
        		float progress = (float)step / maxStep;
        		progressBar.setValue((int)(progress * 100));
            }
        });
		
		lblLoading.setText(text);
	}
	
	public void start() {
		fillDB();
		
		MainWindow mainWindow = new MainWindow();
		MainWindowChangedFiringSource.getInstance().addListener(mainWindow);
		MainWindowChangedFiringSource.getInstance().fireChanged(new CustomerOverview());
		
	}
	
	private void fillDB() {
		WinkelController<Cd> winkelControllerCd = new WinkelController<Cd>(Cd.class);
		updateProgress(1, 30, "Cd Controller created");
		WinkelController<Dvd> winkelControllerDvd = new WinkelController<Dvd>(Dvd.class);
		updateProgress(2, 30, "Dvd Controller created");
		WinkelController<Game> winkelControllerGame = new WinkelController<Game>(Game.class);
		updateProgress(3, 30, "Game Controller created");
		
		DataService<Customer> dataBaseCustomer = DataStrategy.getDataService(Customer.class);
		updateProgress(4, 30, "Customer database created");
		DataService<Uitlening> dataBaseUitlening = DataStrategy.getDataService(Uitlening.class);
		updateProgress(5, 30, "Uitlening database created");
		
		DataService<Cd> dataBaseCd = DataStrategy.getDataService(Cd.class);
		updateProgress(6, 30, "Cd database created");
		DataService<Game> dataBaseGame = DataStrategy.getDataService(Game.class);
		updateProgress(7, 30, "Game database created");
		DataService<Dvd> dataBaseDvd = DataStrategy.getDataService(Dvd.class);
		updateProgress(8, 30, "Dvd database created");
		
		try {
			if (winkelControllerCd.getAllSortedByName().isEmpty() && winkelControllerDvd.getAllSortedByName().isEmpty()
					&& winkelControllerGame.getAllSortedByName().isEmpty()) {
				List<Item> itemList = new ArrayList<Item>();
				for (int i = 0; i < 80; i++) {
					itemList.add(ItemFactory.getItem());
				}
				List<Cd> cdList = new ArrayList<Cd>();
				List<Game> gameList = new ArrayList<Game>();
				List<Dvd> dvdList = new ArrayList<Dvd>();
				
				for (Item item : itemList) {

					switch (item.getClass().toString()) {
					case "class model.subItems.Cd":
						cdList.add((Cd)item);
						break;
					case "class model.subItems.Dvd":
						dvdList.add((Dvd)item);
						break;
					case "class model.subItems.Game":
						gameList.add((Game)item);
						break;
					}
				}
				
				dataBaseCd.addAll(cdList);
				updateProgress(12, 30, "Cd database filled");
				dataBaseGame.addAll(gameList);
				updateProgress(16, 30, "Game database filled");
				dataBaseDvd.addAll(dvdList);
				updateProgress(20, 30, "Dvd database filled");
				
				if (dataBaseCustomer.getAll().isEmpty()) {
					List<Customer> custList = new ArrayList<Customer>();
					for (int i = 0; i < 20; i++) {
						custList.add(CustomerFactory.getCustomer());
					}
					dataBaseCustomer.addAll(custList);
					updateProgress(25, 40, "Customer database filled");
				}
				if (dataBaseUitlening.getAll().isEmpty()) {
					List<Uitlening> uitleningList = new ArrayList<Uitlening>();
					for (int i = 0; i < 30; i++) {
						uitleningList.add(UitleningFactory.getUitlening());
					}
					dataBaseUitlening.addAll(uitleningList);
					updateProgress(30, 30, "Uitlening database filled");
				}
			}
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.FactoriesDidntWork);
			e.printStackTrace();
		}
	}

}
