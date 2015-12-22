package database.helpers;

import java.lang.reflect.Method;

public class ReflectionPropertyHelper {
	
	private String name;
	private Method getter;
	private Method setter;
	private Class propertyType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Method getGetter() {
		return getter;
	}
	public void setGetter(Method getter) {
		this.getter = getter;
	}
	public Method getSetter() {
		return setter;
	}
	public void setSetter(Method setter) {
		this.setter = setter;
	}
	public Class getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(Class propertyType) {
		this.propertyType = propertyType;
	}
	
	
}
