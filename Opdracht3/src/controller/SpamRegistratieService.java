package controller;

import model.Customer;

public interface SpamRegistratieService {

	public void activerenSpam (Customer customer);
	
	public void stopSpam (Customer customer);
	
}
