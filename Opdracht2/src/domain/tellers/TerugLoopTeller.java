package domain.tellers;

import java.lang.reflect.Array;

import Helpers.NotImplementedException;

public class TerugLoopTeller extends Teller {

	private Boolean oplopend = false;
	
	public TerugLoopTeller() {
		super();
	}
	
	public TerugLoopTeller(Character...chars) {
		super(chars);
	}
	
	@Override
	public void updateHuidigeWaarde() {
		int huidigeWaarde = this.getHuidigeIndex();
		int maxWaarde = Array.getLength(this.getMogelijkeWaarden()) - 1;
		
		if (oplopend){
			huidigeWaarde++;
			setHuidigeIndex(huidigeWaarde);
			if (huidigeWaarde == maxWaarde){
				oplopend = false;
			}
		}
		else{
			huidigeWaarde--;
			setHuidigeIndex(huidigeWaarde);
			if (huidigeWaarde == 0){
				oplopend = true;
			}
		}
	}
}
