package testing;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Customer;

public class CustomerTableIOTest {

	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public void openFile(){
		try {
			input = new ObjectInputStream(Files.newInputStream(Paths.get("CustomerTableTest.ser")));
		} catch (IOException ioe) {
			System.err.println("Failed to load Customer Table.");
			ioe.printStackTrace();
			System.exit(1);
		}
	}
	
	public void closeFile(){
		try{
			if (output != null){
				output.close();
			}
		}
		catch (IOException ioe){
			System.err.println("Failed to close Customer Table.");
			System.exit(1);
		}
	}
	
	public void addCustomer(Customer customer){
		try {
			output.writeObject(customer);
		} catch (IOException e) {
			System.out.println("Error on saving customer.");
			e.printStackTrace();
		}
	}
	
	public void addCustomer(ArrayList<Customer> customerList){
		try {
			output.writeObject(customerList);
		} catch (IOException e) {
			System.err.println("Error on saving the customer list.");
			e.printStackTrace();
		}
	}
	
	/*
	public void copyToList(){
		while (true){
			try {
				Customer customer = (Customer) input.readObject();
				CustomerOverview_test.addToTempList(customer);
			} 
			catch (EOFException eof){
				System.out.println("End of file.");
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*/
}
