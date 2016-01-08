package common.enums;

public enum EventEnum {
	HUURSTATUSBUTTONEVENT("huurstatusButtonEvent"),
	CUSTOMEROVERVIEWBUTTONEVENT("customerOverviewButtonEvent"),
	RENTBUTTON1("rentButtonEvent1"),
	RENTBUTTON2("rentButtonEvent2"),
	BLUEBUTTONEVENT("blueButtonEvent"),
	YELLOWBUTTONEVENT("yellowButtonEvent"),
	REDBUTTONEVENT("redButtonEvent");
	
	
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