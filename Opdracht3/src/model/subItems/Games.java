package model.subItems;

import java.math.BigDecimal;
import java.util.Date;

import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import model.Item;

public class Games extends model.Item {

	private EnumTypeGame gameType;
	
	public EnumTypeGame getDvdType() {
		return gameType;
	}

	public void setGameType(EnumTypeGame newGameTtype) {
		this.gameType = newGameTtype;
	}
	public Games(String titel, BigDecimal verhuurPrijsInEuro,Double verhuurPrijsPerDag , EnumTypeGame gameType) {
		super(titel, verhuurPrijsInEuro, verhuurPrijsPerDag);
		setGameType(gameType);
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
		Games other = (Games) obj;
		if (gameType != other.gameType)
			return false;
		return true;
	}
	
}
