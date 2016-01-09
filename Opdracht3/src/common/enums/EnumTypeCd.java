package common.enums;

/**
 * 
 * @author Vervoort Peter
 *
 */

public enum EnumTypeCd {
	MUZIEK("Muziek"),
	GAMES("Games"),
	SOFTWARE("Software");
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private EnumTypeCd (String type){
		this.setType(type);
	}
}
