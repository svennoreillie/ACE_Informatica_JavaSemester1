package common.factories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.joda.time.DateTime;
import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.*;



public class UitleningFactory {

	
	
	public static Uitlening getUitlening () throws DBMissingException, DBException{
			Random rand = new Random();
			final Uitlening uitlening = new Uitlening();
		
			try{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1*rand.nextInt(31));
			DateTime dateTime = new DateTime(calendar);
			uitlening.setBeginVerhuurDatum(dateTime);
			
			uitlening.setVerhuurPeriodeInDagen(rand.nextInt(7));
			
			DataService <Cd> dataBaseCd = DataStrategy.getDataService(Cd.class);
			DataService <Dvd> dataBaseDvd = DataStrategy.getDataService(Dvd.class);
			DataService <Game> dataBaseGame = DataStrategy.getDataService(Game.class);
			List<Item>allItems = new ArrayList<Item>();
			allItems.addAll(dataBaseCd.getAll());
			allItems.addAll(dataBaseDvd.getAll());
			allItems.addAll(dataBaseGame.getAll());
//			List<Item>nietUitgeleendeItems = new ArrayList<>();
//			for (Item item : allItems) {
//				if(!item.getisUitgeleend()){
//				nietUitgeleendeItems.add(item);
//				}
//			}
			uitlening.setUitgeleendItem(allItems.get(rand.nextInt(allItems.size())));
			
			DataService <Customer> dataBaseCustomer = DataStrategy.getDataService(Customer.class);
			List<Customer> allCustomer = dataBaseCustomer.getAll();
			uitlening.setKlantDieUitleent(allCustomer.get(rand.nextInt(allCustomer.size())));
			//uitlening.getUitgeleendItem().setisUitgeleend(true);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return uitlening;
	}
	
	
	
	public static List<Uitlening> getUitleningen(int count) throws DBMissingException, DBException {
		List<Uitlening> uitleningList = new ArrayList<Uitlening>();
		int sameIdLoop = 0;
		for (int i = 0; i < count; i++) {
			Uitlening uitlening = getUitlening();
			
			if (uitleningList.stream().anyMatch(u -> u.getUitgeleendItem().equals(uitlening.getUitgeleendItem()))) {
				//Item already in use
				//repeat this iteration
				i--;
				//increase
				sameIdLoop++;
				if (sameIdLoop > 25) {
					//assuming not enough items exist anymore => create more items first
					break;
				}
			} else {
				sameIdLoop = 0;
				uitleningList.add(uitlening);
			}
		}
		return uitleningList;
	}
}
