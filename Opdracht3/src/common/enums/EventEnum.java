package common.enums;

public enum EventEnum {
	CUSTOMEROVERVIEWBUTTONEVENT("customerOverviewButtonEvent"),
	RENTBUTTON1("rentButtonEvent1"),
	RENTBUTTON2("rentButtonEvent2"),
	ITEMMANAGEMENT("itemManagement"),
	RETURNITEMBUTTONEVENT("returnItemButtonEvent");
	
	
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