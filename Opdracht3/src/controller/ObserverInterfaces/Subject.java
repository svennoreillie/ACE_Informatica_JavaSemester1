package controller.ObserverInterfaces;

/**
 * 
 * @author Vervoort Peter
 *
 */

public interface Subject {
	public void addObserver (Observer o);
	public void removeObserver (Observer o);
}
