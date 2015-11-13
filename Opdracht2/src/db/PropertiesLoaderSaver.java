package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import domain.tellers.TellerType;

public class PropertiesLoaderSaver {
	
	private static Properties properties;
	private static File propertiesFile;
	private static String fileName="lockProperties.xml";
	
	private static List<TellerType> tellerTypes;
	private static List<Character[]> tellerWaardes;
	private static String deblokeercode;
	private static String code;
	
	public static void setTellerTypes(List<TellerType> tellerTypes) throws dbException{
		if(tellerTypes.size()>5){
			throw new dbException("Er mogen max 5 tellers zijn.");
		}
		
		PropertiesLoaderSaver.tellerTypes=tellerTypes;	
	}
	
	public static void setTellerWaardes(List<Character[]> tellerWaardes) throws dbException{
		if(tellerWaardes.size()>5){
			throw new dbException("Er mogen max 5 tellers zijn.");
		}
		
		PropertiesLoaderSaver.tellerWaardes = tellerWaardes;
	}
	
	public static void setDeblokeercode(String deblokeercode) throws dbException{
		if(deblokeercode==null || deblokeercode.equals("")){
			throw new dbException("De deblokeercode mag niet leeg zijn");
		}
		
		PropertiesLoaderSaver.deblokeercode=deblokeercode;
	}
	
	public static void setCode(String code) throws dbException{
		if(code == null || code.equals("")){
			throw new dbException("De code mag niet leeg zijn");
		}
		
		if(code.length()>tellerTypes.size()){
			throw new dbException("De code is langer dan het aantal tellers. Check of de tellers eerst geset zijn!");
		}
		
		for(int i=0 ; i<code.length() ; i++){
			boolean komtVoor = false;
			
			for(Character c : tellerWaardes.get(i)){
				if(c == code.charAt(i)){
					komtVoor = true;
					break;
				}
			}
			
			if(!komtVoor){
				throw new dbException("De "+(i+1)+"e letter van de code komt niet voor in de waardes van de teller. Check of de tellerWaardes eerst geset zijn!");
			}
		}
		
		PropertiesLoaderSaver.code = code;
	}

	public static List<TellerType> getTellerTypes(){
		return tellerTypes;
	}
	
	public static List<Character[]> getTellerWaardes(){
		return tellerWaardes;
	}
	
	public static String getCode(){
		return code;
	}
	
	public static String getDeblokeercode(){
		return deblokeercode;
	}
	
	public static void saveproperties() throws dbException{
		
		
		//Checkt of de properties file al bestaad. indien dit niet het geval is, probeert hij deze te creëren.
		if(propertiesFile==null){
			propertiesFile = new File(fileName);
			if(!propertiesFile.exists()){
				try {
					propertiesFile.createNewFile();
				} catch (IOException e) {
					throw new dbException("Properties file could not be found and created");
				}
			}
		}
		
		if(properties==null){
			properties = new Properties();
		}
		
		
		
		
		Map<TellerType, String> tellerTypeMap = new HashMap<TellerType, String>();
		for(TellerType t : TellerType.values()){
			tellerTypeMap.put(t, t.getOmschrijving());
		}
		
		
		for(int i=0;i<5;i++){
			String waardes="";
			String tellerType="";
					
			try{
				tellerType=tellerTypeMap.get(getTellerTypes().get(i));
				for(Character c : getTellerWaardes().get(i)){
					waardes+=c.toString();
				}
			}catch(Exception e){
				tellerType="null";
			}
			
			
			
			properties.put("teller"+i+"Type", tellerType);
			properties.put("teller"+i+"Waardes",waardes);
		}
		
		properties.put("code", getCode());
		properties.put("deblokeercode", getDeblokeercode());
		
		
		
		try {
			FileOutputStream out = new FileOutputStream(propertiesFile);
			properties.storeToXML(out, "");
			out.close();
		} catch (IOException e) {
			throw new dbException("saving properties failed");
		}
		
		
	}
	
	public static void loadProperties() throws dbException{
		
		//Checkt of de properties file al bestaad. indien dit niet het geval is, zijn er geen properties om in te lezen.
		if(propertiesFile == null){
			propertiesFile = new File(fileName);
			
			if(!propertiesFile.exists()){
				throw new dbException("There are no properties to be loaded yet");
			}
		}
		
		if(properties==null){
			properties = new Properties();
		}
		
		//properties inladen
		try {
			FileInputStream in = new FileInputStream(propertiesFile);
			properties.loadFromXML(in);
			in.close();
		} catch (IOException e) {
			throw new dbException("Loading properties failed");
		}
		
		Map<String, TellerType> tellerTypeMap = new HashMap<String, TellerType>();
		for(TellerType t : TellerType.values()){
			tellerTypeMap.put(t.getOmschrijving(), t);
		}
		
		List<Character[]> tellerWaardes = new ArrayList<Character[]>();
		List<TellerType> tellerTypes = new ArrayList<TellerType>();
		
		for(int i=0;i<5;i++){
			String typeStr = properties.getProperty("teller"+i+"Type");
			String waardesStr = properties.getProperty("teller"+i+"Waardes");
			
			TellerType type=null;
			Character[] waardes = new Character[waardesStr.length()];
			
			try{
				type = tellerTypeMap.get(typeStr);
			}catch(Exception e){
				type =null;
			}
			
			for(int j=0;j<waardesStr.length();j++){
				waardes[j]=waardesStr.charAt(j);
			}
			
			tellerTypes.add(type);
			tellerWaardes.add(waardes);
		}
		
		setTellerTypes(tellerTypes);
		setTellerWaardes(tellerWaardes);
		setCode(properties.getProperty("code"));
		setDeblokeercode(properties.getProperty("deblokeercode"));

	}
	
	
}
