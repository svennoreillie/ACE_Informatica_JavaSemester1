package application;

import java.util.ArrayList;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import controller.event.MainWindowChangedFiringSource;
import model.Item;
import model.Shop;
import view.MainWindow;
import view.panels.CustomerOverview;
import view.panels.testpanels.BluePanel;

public class App {
	public static void main(String[] args) throws DBMissingException, DBException{
		/*Shop shop = new Shop();
		List<Item> items = new ArrayList<Item>();
		
		for(int i = 0; i<200;i++){
			Item item;
			
			if(i<100){
				item=
				item.setId(i);
				item.setType(ItemType.FILM);
				item.setTitel("Film " + i);
				item.setVerhuurPrijs(new BigDecimal(3));
			}else if(i<200){
				item.setId(i);
				item.setType(ItemType.GAME);
				item.setTitel("Game " + (i-100));
				item.setVerhuurPrijs(new BigDecimal(3));
			}
			
			//items.add(item);
		}
		
		shop.setItems(items);*/
		MainWindow mainWindow = new MainWindow();
		MainWindowChangedFiringSource.getInstance().addListener(mainWindow);
		MainWindowChangedFiringSource.getInstance().fireChanged(new CustomerOverview());
	}
}
