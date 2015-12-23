package controller;

import model.Customer;

public class SpamRegistratieController implements SpamRegistratieService {

	@Override
	public void activerenSpam(Customer customer) {
		customer.setSpam(true);
		
	}

	@Override
	public void stopSpam(Customer customer) {
		customer.setSpam(false);
	}

}
