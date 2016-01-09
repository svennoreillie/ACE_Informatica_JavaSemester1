package controller;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.util.List;
import model.Item;

public interface WinkelService<T extends Item> {
	
	public void AddItemToStore(T entity);
	
	public void RemoveItemToStore (T entity);
	
	public List<T> getAllSortedByName();
	
	public List<T> searchByStringPart(String searchString); 
	
}
