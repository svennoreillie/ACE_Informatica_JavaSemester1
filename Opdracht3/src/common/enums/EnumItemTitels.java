package common.enums;

/**
 * 
 * @author Andr� N�brega
 *
 */
public enum EnumItemTitels {
	FARGO("Fargo"),
	DJANGO("Django"),
	STARWARS("Star Wars"),
	POLICEACADEMY("Police Academy"),
	FIGHTCLUB("Fight Club"),
	HARRYPOTTER("Harry Potter"),
	FASTFURIOUS7("Fast and Furious 7"),
	PULPFICTION("Pulp fiction"),
	FORESTGUMP("Forest Gump"),
	TOPGUN("Top Gun"),
	HOTSHOTS("Hot Shots"),
	THENAKEDGUN("The Naked Gun"),
	BONDSPECTRE("James Bond Spectre"),
	BONDGOLFINGER("James Bond Goldfinger"),
	BONDSKYFALL("James Bond Skyfall"),
	BONDCASINOROYAL("James Bond Casino Royal"),
	BONDDIEANOTHERDAY("James Bond Die Another Day"),
	GTAV("GTA V"),
	ROCKETLEAGUE("RocketLeague"),
	HEARTSTONE("HeartStone"),
	CSGO("Counter Strike: Global Offensive"),
	PROJECTCARS("Project Cars"),
	SIMCITY("Sim City"),
	PLANTSVSZOMBIES("Plants vs Zombies: Garden Warfare"),
	INTERSTELLAR("Interstellar");
	
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
