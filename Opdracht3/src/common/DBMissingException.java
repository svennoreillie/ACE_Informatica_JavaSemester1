
/**
 * 
 * @author Sven Noreillie & Vervoort Peter
 *
 */
package common;

public class DBMissingException extends Exception {

	private static final long serialVersionUID = 7677183055324217639L;
	private String message;
	
	public DBMissingException() {
		
	}
	
	public DBMissingException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
