package common.factories;

import java.util.Random;

import common.enums.EnumAddressStreet;
import common.enums.EnumAddressCity;
import model.Address;

public class AddressFactory {

	public static Address getAddress(final Random rand, int id){
		final Address address = new Address();
		address.setStreet(EnumAddressStreet.values()[rand.nextInt(EnumAddressStreet.values().length)].getAddress());
		address.setNumber(Integer.toString(rand.nextInt(999)));
		address.setBox("");
		
		EnumAddressCity city = EnumAddressCity.values()[rand.nextInt(EnumAddressCity.values().length)];
		address.setCity(city.getCity());
		address.setZip(city.getZip());
		address.setCountry("Belgium");
		address.setId(id);
		
		return address;
	}
	
//	public static Address getAddress(){
//		final Address address = new Address();
////		final Random rand = new Random();
//		
//		address.setStreet(EnumAddressStreet.values()[new Random().nextInt(EnumAddressStreet.values().length)].getAddress());
//		address.setNumber(Integer.toString(new Random().nextInt(999)));
//		address.setBox("");
//		
//		EnumAddressCity city = EnumAddressCity.values()[new Random().nextInt(EnumAddressCity.values().length)];
//		address.setCity(city.getCity());
//		address.setZip(city.getZip());
//		address.setCountry("Belgium");
//		
//		return address;
//	}
	
}