package common.enums;

/**
 * 
 * @author André Nóbrega
 *
 */
public enum EnumPersonFirstName {
	JAN("Jan"),
	PIET("Piet"),
	JORIS("Joris"),
	CORNEEL("Corneel"),
	ANDRE("Andre"),
	PIETER("Pieter"),
	PETER("Peter"),
	BART("Bart"),
	SVEN("Sven"),
	GEERT("Geert"),
	LEO("Leo"),
	AN("An"),
	MARIE("Marie"),
	SYLVIE("Sylvie"),
	HELGA("Helga"),
	LYNDA("Lynda"),
	JESSICA("Jessica"),
	HIERONIMUS("Hieronimus");
	
	private String name;
	
	private EnumPersonFirstName(String name){
		setFirstName(name);
	}
	
	public String getFirstName(){
		return name;
	}
	public void setFirstName(String name){
		this.name = name;
	}
}
