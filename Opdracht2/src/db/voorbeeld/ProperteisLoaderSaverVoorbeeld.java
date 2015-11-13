package db.voorbeeld;

import java.util.ArrayList;
import java.util.List;

import db.PropertiesLoaderSaver;
import db.dbException;
import domain.tellers.TellerType;

public class ProperteisLoaderSaverVoorbeeld {

	public static void main(String[] args){
		
		//Voorbereiding
		List<TellerType> tellerTypes=new ArrayList<TellerType>();
		tellerTypes.add(TellerType.CYCLISCH);
		List<Character[]> tellerWaardes=new ArrayList<Character[]>();
		Character[] waardes={'a','b','c'};
		tellerWaardes.add(waardes);
		String code = "a";
		String deblokeercode="test";
		
		//Properties opslagen (In deze volgorde!!!)
		try {
			PropertiesLoaderSaver.setTellerTypes(tellerTypes);
			PropertiesLoaderSaver.setTellerWaardes(tellerWaardes);
			PropertiesLoaderSaver.setCode(code);
			PropertiesLoaderSaver.setDeblokeercode(deblokeercode);
			PropertiesLoaderSaver.saveproperties();
		} catch (dbException e) {
			e.printStackTrace();
		}
		
		
		//Properties laden
		try {
			PropertiesLoaderSaver.loadProperties();
			tellerTypes = PropertiesLoaderSaver.getTellerTypes();
			tellerWaardes = PropertiesLoaderSaver.getTellerWaardes();
			code = PropertiesLoaderSaver.getCode();
			deblokeercode = PropertiesLoaderSaver.getDeblokeercode();
		} catch (dbException e) {
			e.printStackTrace();
		}
		
		
		
		//verwerken van gegevens
		for(TellerType t : tellerTypes){
			try {
				System.out.println(t.getOmschrijving());
			} catch (Exception e) {
				System.out.println("null");
			}	
		}
		
		for(Character[] c : tellerWaardes){
			String str="";
			for(Character ch : c){
				str+=ch.toString();
			}
			
			if(str.equals("")){
				str="_____";
			}
			
			System.out.println(str);
		}
		
		System.out.println(code);
		System.out.println(deblokeercode);
	}

}
