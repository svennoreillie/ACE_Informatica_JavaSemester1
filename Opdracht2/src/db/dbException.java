package db;

public class dbException extends Throwable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4959721177580426545L;

	public dbException(){
		super();
	}
	
	public dbException(String message){
		super(message);
	}
	
	public dbException(Throwable cause){
		super(cause);
	}
	
	public dbException(String message , Throwable cause){
		super(message, cause);
	}
	
}
