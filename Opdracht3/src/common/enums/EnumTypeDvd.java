package common.enums;

public enum EnumTypeDvd {
	FILM("Film"),
	SOFTWARE("Software");
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private EnumTypeDvd (String type){
		this.setType(type);
	}
}
