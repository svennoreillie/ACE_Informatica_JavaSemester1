package common.factories;

import java.util.Random;

import model.Person;
import common.enums.EnumPersonFirstName;
import common.enums.EnumPersonLastName;

public class PersonFactory {
	public static Person getPerson(final Random rand, int id){
		final Person person = new Person();
		
		person.setFirstName(EnumPersonFirstName.values()[rand.nextInt(EnumPersonFirstName.values().length)].getFirstName());
		person.setLastName(EnumPersonLastName.values()[rand.nextInt(EnumPersonLastName.values().length)].getLastName());
		person.setId(id);
		
		return person;
	}
	
//	public static Person getPerson(){
//		final Person person = new Person();
////		final Random rand = new Random();
//		
//		person.setFirstName(EnumPersonFirstName.values()[new Random().nextInt(EnumPersonFirstName.values().length)].getFirstName());
//		person.setLastName(EnumPersonLastName.values()[new Random().nextInt(EnumPersonLastName.values().length)].getLastName());
//		
//		return person;
//	}
}
