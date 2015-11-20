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
	public Games(String titel, BigDecimal verhuurPrijsInEuro, Date beginVerhuurDatum, int verhuurPeriodeInDagen,
			Double verhuurPrijsPerDag, EnumTypeGame gameType) {
		super(titel, verhuurPrijsInEuro, beginVerhuurDatum, verhuurPeriodeInDagen, verhuurPrijsPerDag);
		this.setGameType(gameType);
	}
}
