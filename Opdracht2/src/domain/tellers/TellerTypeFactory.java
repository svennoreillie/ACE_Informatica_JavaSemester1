package domain.tellers;

import java.lang.reflect.Constructor;


public class TellerTypeFactory {
	public static Teller createTellerType(
                                      String type, Object... args){
		Teller instance = null;
		Class <?> [] arg = new Class[args.length];
		int tel = 0;
		for (Object object:args){
			arg[tel++] = object.getClass();
		}
		try{
			Class<?> clazz = Class.forName(type);
			Constructor<?> constructor = clazz.getConstructor(arg); 
			instance = (Teller)constructor.newInstance(args);
		}
		catch (Exception ex){System.out.println(ex.getMessage());}
		return instance;
	}	
	
	public static Teller createTellerType(String type){
		Teller instance = null;
		try{
			Class<?> clazz = Class.forName(type);
			instance = (Teller)clazz.newInstance();
		}
		catch (Exception ex){System.out.println(ex.getMessage());}
		return instance;
	}
}

