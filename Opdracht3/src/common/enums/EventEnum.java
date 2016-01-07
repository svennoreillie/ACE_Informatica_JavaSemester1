package common.enums;

public enum EventEnum {
	HUURSTATUSBUTTONEVENT("huurstatusButtonEvent"),
	CUSTOMEROVERVIEWBUTTONEVENT("customerOverviewButtonEvent"),
	BLUEBUTTONEVENT("blueButtonEvent"),
	YELLOWBUTTONEVENT("yellowButtonEvent"),
	REDBUTTONEVENT("redButtonEvent"), 
	ADDITEMBUTTONEVENT("addItemButtonEvent");
	
	
	private String actionCommand;
	
	private EventEnum(String actionCommand){
		this.actionCommand = actionCommand;
	}
	
	public String getActionCommand(){
		return actionCommand;
	}
	
	public String toString(){
		return actionCommand;
	}
}
