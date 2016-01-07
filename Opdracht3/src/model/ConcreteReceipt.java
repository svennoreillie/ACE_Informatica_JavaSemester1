package model;

public class ConcreteReceipt implements Receipt {

	@Override
	public void describe() {
		System.out.println("This is the receipt");
	}

}
