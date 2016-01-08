package common.enums;

public enum EnumTypeGame {
	FIRSTPERSONSHOOTER("First Person Shooter"),
	MASSIVEONLINEMULTIPLAYER("Massive Online Multiplayer"),
	ROLEPLAYINGGAME("Roleplqying Game");
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private EnumTypeGame (String type){
		this.setType(type);
	}
}
