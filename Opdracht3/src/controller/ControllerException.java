package controller;

public class ControllerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ControllerException(){
		super();
	}
	
	public ControllerException(String msg){
		super(msg);
	}
	
	public ControllerException(Throwable e){
		super(e);
	}
	
	public ControllerException(String msg,Throwable e){
		super(msg,e);
	}
	
}
