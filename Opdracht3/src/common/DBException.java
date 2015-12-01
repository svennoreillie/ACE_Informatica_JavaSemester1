/**
 * @Autor: Sven Noreillie, Peter Vervoort
 * @Team: Team13
 * @Date: 26/10/2015
 * @Project: KroegenTocht
 * @Purpose: Algemene Database exception, voorziet optioneel bericht en interne fout
 */

package common;

public class DBException extends Exception {

	private static final long serialVersionUID = 7677183055324217639L;
	private String message;
	private Exception innerException;
	
	public DBException() {
		
	}
	
	public DBException(String message) {
		this.message = message;
	}
	
	public DBException(String message, Exception innerException) {
		this.message = message;
		this.innerException = innerException;
	}
	
	public String getMessage() {
		return this.message;
	}
}
