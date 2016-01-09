package application;



import javax.swing.JOptionPane;
import common.DBException;
import common.DBMissingException;
import database.helpers.DataSource;
import database.implementations.DataSourceFactory;
import view.LoadingWindow;

public class App {
	
	public static void main(String[] args) throws DBMissingException, DBException{
		
		int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                "Kies een database type", //Object message,
                "Db?", //String title
                JOptionPane.YES_NO_OPTION, //int optionType
                JOptionPane.INFORMATION_MESSAGE, //int messageType
                null, //Icon icon,
                DataSource.values(), //Object[] options,
                null);//Object initialValue 
		if (choice == -1) return;
		DataSourceFactory.setType(DataSource.values()[choice]);

		LoadingWindow bootscreen = new LoadingWindow();
		bootscreen.start();
	}
	
	
	


}
