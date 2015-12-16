package view;


public class ViewException extends Throwable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewException(){
		super();
	}
	
	public ViewException(String msg){
		super(msg);
	}
	
	public ViewException(Throwable throwable){
		super(throwable);
	}
	
	public ViewException(String msg,Throwable throwable){
		super(msg,throwable);
	}
}
