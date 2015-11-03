package dataStorage;

import java.util.ArrayList;
import java.util.List;

import application.House;

public class HouseService {

	private List<House> everyHouse = new ArrayList<House>();
	
	public List<House> getAllHouses() throws Throwable {

		if (everyHouse != null && everyHouse.size() ==0) {
			int rowNumber = 1;
			int houseNumber = 1;
			while (everyHouse.size() < 107) {
				if (houseNumber > 10) {
					rowNumber++;
					houseNumber = 1;
				}
				House house = new House((rowNumber * 100) + houseNumber);
				everyHouse.add(house);
				houseNumber++;
			}
		}
		return everyHouse;
	}
	
}
