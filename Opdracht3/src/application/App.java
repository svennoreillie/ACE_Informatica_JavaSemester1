package application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import controller.ReceiptController;
import controller.event.MainWindowChangedFiringSource;
import model.BodyDecorator;
import model.ConcreteReceipt;
import model.FooterDecorator;
import model.HeaderDecorator;
import model.Item;
import model.Receipt;
import model.Shop;
import model.subItems.Cd;
import model.subItems.Dvd;
import view.MainWindow;
import view.panels.CustomerOverview;
import view.panels.testpanels.BluePanel;

public class App {
	public static void main(String[] args){
		
		/*List<Item> items = new ArrayList<>();
		
		items.add(new Cd("testCd", new BigDecimal(3.0), 3.0, EnumTypeCd.SOFTWARE));
		items.add(new Dvd("testDvd", new BigDecimal(3.0), 3.0, EnumTypeDvd.FILM));
		
		ConcreteReceipt CReceipt = new ConcreteReceipt();
		CReceipt.setItems(items);
		
		Receipt receipt = CReceipt;
		
		ReceiptController.printReceipt(receipt);*/

		MainWindow mainWindow = new MainWindow();
		MainWindowChangedFiringSource.getInstance().addListener(mainWindow);
		MainWindowChangedFiringSource.getInstance().fireChanged(new CustomerOverview());

	}
}
