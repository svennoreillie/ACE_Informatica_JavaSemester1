package domain.tellers;

import java.lang.reflect.Array;

import Helpers.NotImplementedException;

public class CyclischeTeller extends Teller {

	public CyclischeTeller() {
		super();
	}
	
	public CyclischeTeller(Character...chars) {
		super(chars);
	}
	
	@Override
	public void updateHuidigeWaarde() {
		int huidigeIndex = this.getHuidigeIndex();
		int maximumIndex = Array.getLength(this.getMogelijkeWaarden()) - 1;
		if (huidigeIndex == maximumIndex) {
			this.setHuidigeIndex(0);
		} else {
			this.setHuidigeIndex(huidigeIndex + 1);
		}
	}

}
