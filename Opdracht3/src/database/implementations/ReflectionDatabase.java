package database.implementations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import database.DataService;
import database.DataStrategy;
import database.helpers.ReflectionPropertyHelper;
import database.internalInterface.DataReadWriteService;
import model.Address;
import model.Customer;
import model.Item;
import model.ModelBase;
import model.Person;
import model.Shop;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public class ReflectionDatabase<T extends ModelBase> {

	protected final Class<T> classType;
	private List<ReflectionPropertyHelper> properties;

	public ReflectionDatabase(Class<T> classType) {
		this.classType = classType;
	}

	protected List<ReflectionPropertyHelper> getFields() {
		if (properties == null) {
	
			properties = new ArrayList<ReflectionPropertyHelper>();
	
			// iterate all methods found in T
			for (Method m : this.classType.getMethods()) {
				if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
					// if the name starts with get and has no parameters we
					// assume it is a getter
					ReflectionPropertyHelper helper = new ReflectionPropertyHelper();
					try {
						String getName = m.getName();
						helper.setName(getName.substring(3, getName.length()));
						helper.setGetter(m);
						helper.setPropertyType(m.getReturnType());
	
						// look for a setter
						if (helper.getName().equals("Class"))
							continue;
						Method setter = this.classType.getMethod("set" + helper.getName(), helper.getPropertyType());
						helper.setSetter(setter);
	
						// add to list
						properties.add(helper);
					} catch (Exception e) {
						System.out.println(e.toString());
						// setter not available, skip property
					}
				}
			}
			properties = properties.stream().sorted().collect(Collectors.toList());
		}
	
		return properties;
	}
	
	protected DataService<? extends ModelBase> GetDedicatedDataService(String classTypeString) {
		// ugly helper to instantiate a new DataService of the correct type
		// might be a better solution for it (without converting all of it to
		// C#)
		switch (classTypeString) {
		case "model.Person":
			return DataStrategy.getDataService(Person.class);
		case "model.Address":
			return DataStrategy.getDataService(Address.class);
		case "model.Customer":
			return DataStrategy.getDataService(Customer.class);
		case "model.Item":
			return DataStrategy.getDataService(Item.class);
		case "model.Shop":
			return DataStrategy.getDataService(Shop.class);
		case "model.Uitlening":
			return DataStrategy.getDataService(Uitlening.class);
		case "model.subItems.Cd":
			return DataStrategy.getDataService(Cd.class);
		case "model.subItems.Dvd":
			return DataStrategy.getDataService(Dvd.class);
		case "model.subItems.Game":
			return DataStrategy.getDataService(Game.class);
		default:
			return null;
		}
	}

}