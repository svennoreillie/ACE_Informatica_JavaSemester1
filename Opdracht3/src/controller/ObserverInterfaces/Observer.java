package controller.ObserverInterfaces;

/**
 * 
 * @author Vervoort Peter
 *
 */

import common.DBException;
import common.DBMissingException;

public interface Observer {
	public void update(String title) throws DBMissingException, DBException;
}
