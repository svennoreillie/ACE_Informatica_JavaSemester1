<<<<<<< HEAD
package controller;




import model.BodyDecorator;
import model.FooterDecorator;
import model.HeaderDecorator;
import model.Receipt;

/**
 * 
 * @author Huybrechts
 *
 */

public class ReceiptController {
	
	public static void printReceipt(Receipt receipt){
		receipt = new HeaderDecorator(receipt);
		receipt = new BodyDecorator(receipt);
		receipt = new FooterDecorator(receipt);
		System.out.println(receipt.getDescription());
	}
	
	
}
=======
package controller;

import model.BodyDecorator;
import model.FooterDecorator;
import model.HeaderDecorator;
import model.Receipt;
public class ReceiptController {
	
	public static void printReceipt(Receipt receipt){
		receipt = new HeaderDecorator(receipt);
		receipt = new BodyDecorator(receipt);
		receipt = new FooterDecorator(receipt);
		System.out.println(receipt.getDescription());
	}
	
	
}
>>>>>>> refs/remotes/svennoreillie/development
