package common.enums;

public enum EnumPersonLastName {
	JANSENS("Jansens"),
	JANSSENS("Janssens"),
	SMET("Smet"),
	VANHIER("Vanhier"),
	VANGINDER("Vanginder"),
	VANBOVEN("Vanboven"),
	VANONDER("Vanonder"),
	HALDIS("Haldis"),
	UYTEBROEK("Uytebroek");
	
	private String lastName;
	
	private EnumPersonLastName(String name){
		setLastName(name);
	}
	
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String name){
		this.lastName = name;
	}
}
