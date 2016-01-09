package common.enums;

/**
 * 
 * @author Vervoort Peter
 *
 */

public enum EnumItemTypeItems {
	CD("CD"),
	DVD("DVD"),
	GAME("Game");
	
	private String itemType;
	
	private EnumItemTypeItems(String itemType){
		setItemType(itemType);
	}

	private void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemType(){
		return this.itemType;
	}
}
