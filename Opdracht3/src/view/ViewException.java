package view;


public class ViewException extends Throwable{
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
