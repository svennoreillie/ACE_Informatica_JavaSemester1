package controller.ObserverInterfaces;

import common.DBException;
import common.DBMissingException;

public interface Observer {
	public void update(String title) throws DBMissingException, DBException;
}
