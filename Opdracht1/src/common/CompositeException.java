package common;

import java.util.ArrayList;
import java.util.List;

public class CompositeException extends Exception {

	private static final long serialVersionUID = -7823771485686859579L;
	private List<Exception> list;
	
	public CompositeException() {
		list = new ArrayList<Exception>();
	}
	
	public CompositeException(List<Exception> exceptions) {
		this.list = exceptions;
	}
	
	public String getMessage() {
		return "Multiple exceptions occurred, check innerExceptionList for more details";
	}
	
	public List<Exception> getInnerExceptionList() {
		return list;
	}
}
