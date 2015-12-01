/**
 * @Autor: Sven Noreillie, Peter Vervoort
 * @Team: Team13
 * @Date: 26/10/2015
 * @Project: KroegenTocht
 * @Purpose: Exception die aangeeft dat geen database file gevonden of gemaakt kan worden 
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
