package controller;

import common.DBException;
import common.DBMissingException;
import model.Customer;

public interface SpamRegistratieService {

	public void activerenSpam (Customer customer) throws DBMissingException, DBException;
	
	public void stopSpam (Customer customer) throws DBMissingException, DBException;
}
