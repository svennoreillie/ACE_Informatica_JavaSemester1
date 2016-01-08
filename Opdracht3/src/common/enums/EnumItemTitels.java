package common.enums;

public enum EnumItemTitels {
	FARGO("Fargo"),
	DJANGO("Django"),
	STARWARS("Star Wars"),
	POLICEACADEMY("Police Academy"),
	FIGHTCLUB("Fight Club"),
	HARRYPOTTER("Harry Potter");
	
	private String titel;
	
	private EnumItemTitels(String titel){
		setTitel(titel);
	}

	private void setTitel(String titelToSet) {
		this.titel = titelToSet;
	}
	
	public String getTitel(){
		return this.titel;
	}
}
