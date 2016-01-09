package common.factories;

/**
 * 
 * @author André Nóbrega & Vervoort Peter
 *
 */

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;
import common.enums.*;
import model.*;
import model.subItems.*;

public class ItemFactory {
	public static Item getItem(){
		final Random rand = new Random();
		final Item item;
		
	
		final String itemTitel = EnumItemTitels.values()[rand.nextInt(EnumItemTitels.values().length)].getTitel(); 
		final String itemType = EnumItemTypeItems.values()[rand.nextInt(EnumItemTypeItems.values().length)].getItemType();
		final Double tempDouble = rand.nextDouble() + rand.nextInt(5);
		final Double itemPrijs = (double) Math.round(tempDouble * 100) / 100;
		
		final BigDecimal itemPrijsPerDag = new BigDecimal(itemPrijs, MathContext.DECIMAL64);
		
		switch (itemType){
		case "CD":
			item = new Cd(itemTitel, itemPrijsPerDag, itemPrijs, EnumTypeCd.values()[0]);
			break;
		case "DVD":
			item = new Dvd(itemTitel, itemPrijsPerDag, itemPrijs, EnumTypeDvd.values()[0]);
			break;
		case "Game":
			item = new Game(itemTitel, itemPrijsPerDag, itemPrijs, EnumTypeGame.values()[0]);
			break;
		default:
			item = new Cd(itemTitel, itemPrijsPerDag, itemPrijs, EnumTypeCd.values()[0]);
			break;
		}
		
		return item;
	}
}
