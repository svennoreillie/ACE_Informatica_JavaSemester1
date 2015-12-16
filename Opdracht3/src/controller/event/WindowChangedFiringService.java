package controller.event;

import javax.swing.JPanel;

public interface WindowChangedFiringService {
	public void addListener(WindowChangedService listener);
	public void removeListener(WindowChangedService listener);
	public void fireChanged(JPanel panel);
}
