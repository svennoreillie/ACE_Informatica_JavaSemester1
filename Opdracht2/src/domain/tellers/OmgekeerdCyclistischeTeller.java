package domain.tellers;

import java.lang.reflect.Array;

public class OmgekeerdCyclistischeTeller extends Teller {

	public OmgekeerdCyclistischeTeller() {
		super();
	}
	
	public OmgekeerdCyclistischeTeller(Character...chars) {
		super(chars);
	}
	
	@Override
	public void updateHuidigeWaarde() {
		int huidigeIndex = this.getHuidigeIndex();
		if (huidigeIndex == 0) {
			int maximumIndex = Array.getLength(this.getMogelijkeWaarden()) - 1;
			this.setHuidigeIndex(maximumIndex);
		} else {
			this.setHuidigeIndex(huidigeIndex - 1);
		}
	}

}
