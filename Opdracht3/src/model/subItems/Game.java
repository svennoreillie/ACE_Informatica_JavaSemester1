package model.subItems;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.math.BigDecimal;
import common.enums.EnumTypeGame;

public class Game extends model.Item {

	private static final long serialVersionUID = 6019194465189255343L;
	private EnumTypeGame gameType;
	
	public EnumTypeGame getGameType() {
		return gameType;
	}

	public void setGameType(EnumTypeGame newGameTtype) {
		this.gameType = newGameTtype;
	}
	public Game(String titel, BigDecimal verhuurPrijsInEuro,Double verhuurPrijsPerDag , EnumTypeGame gameType) {
		super(titel, verhuurPrijsInEuro, verhuurPrijsPerDag);
		setGameType(gameType);
	}
	
	public Game() {
		super();
		setGameType(EnumTypeGame.values()[0]);
	}

	@Override
	public String toString() {
		return "Games [gameType=" + gameType + ", " + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (gameType != other.gameType)
			return false;
		return true;
	}
	
}
