package dataStorage;

public interface dataStorage {

	/**
	 * Gets the data from the data file
	 * 
	 */
	public StringBuffer getData();
	
	/**
	 * Sets the data in to the data file
	 * 
	 */
	public void setData(StringBuffer data);
	
	
	/**
	 * Checks if the format of the data is correct
	 * 
	 */
	public void dataFormatCheck (StringBuffer data);
	
}
