import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Winkel {
	
	private ArrayList<String> itemTitles;
	private ArrayList<String> itemTypes;
	private ArrayList<String> itemIds;
	
	public Winkel()
	{
		itemTitles = new ArrayList<String>();
		itemTypes = new ArrayList<String>();
		itemIds = new ArrayList<String>();
	}

	public double getPrice(int itemidx, int days) {
		double price = 0;
		if(itemTypes.get(itemidx).equals("M")){ 
			price = 5;
			int daysLeft = days - 3;
			if (daysLeft > 0) {
				price += (daysLeft * 2);
			}
		} else if(itemTypes.get(itemidx) == "G"){ 
			price = days * 3;
		}
		return price;
	}		

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Winkel winkel = new Winkel();

		String menu = "1. Add item\n2. Show item\n3. Show rental price\n\n0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			choice = Integer.parseInt(choiceString);
			if (choice == 1) {
				addItem(winkel);
			} else if (choice == 2) {
				showItem(winkel);
			} else if (choice == 3){
				showPrice(winkel);
			}
		}
	}
	
	public static void addItem(Winkel winkel) {
		String title = JOptionPane.showInputDialog("Enter the title:");
		String id = JOptionPane.showInputDialog("Enter the id:");
		String type = JOptionPane.showInputDialog("Enter the type (M for movie/G for game):");
		
		winkel.itemTitles.add(title);
		winkel.itemIds.add(id);
		winkel.itemTypes.add(type);
	}
	
	public static void showItem(Winkel winkel){
		String id = JOptionPane.showInputDialog("Enter the id:");
		int idx = -1;
		boolean gevonden = false;
		for(int i = 0; i < winkel.itemIds.size() && !gevonden; i++)
		{
			if(winkel.itemIds.get(i).equals(id))
			{
				idx = i;
				gevonden = true;
			}
		}
		if(gevonden)
		{
			JOptionPane.showMessageDialog(null, winkel.itemTitles.get(idx));
		}		
	}

	public static void showPrice(Winkel winkel){
		String id = JOptionPane.showInputDialog("Enter the id:");
		int idx = -1;
		boolean gevonden = false;
		for(int i = 0; i < winkel.itemIds.size() && !gevonden; i++){
			if(winkel.itemIds.get(i).equals(id)){
				idx = i;
				gevonden = true;
			}
		}
		if(gevonden){
			String daysString = JOptionPane.showInputDialog("Enter the number of days:");
			int days = Integer.parseInt(daysString);
			JOptionPane.showMessageDialog(null, winkel.getPrice(idx,days));
		}
	}
}