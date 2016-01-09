package controller.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import common.enums.EventEnum;
import view.panels.AddItemPanel;
import view.panels.CustomerOverview;
import view.panels.ItemManagementPanel;
import view.panels.UitleningStap1Panel;
import view.panels.UitleningStap2Panel;

public class MainWindowChangedFiringSource implements WindowChangedFiringService,ActionListener{

	private List<WindowChangedService> listeners;
	private static MainWindowChangedFiringSource instance;
	
	private MainWindowChangedFiringSource() {
		listeners = new ArrayList<WindowChangedService>();
	}
	
	public static MainWindowChangedFiringSource getInstance(){
	 if(instance==null){
		 instance = new MainWindowChangedFiringSource();
	 }
	 return instance;
	}
	
	@Override
	public void addListener(WindowChangedService listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(WindowChangedService listener) {
		listeners.remove(listener);
	}

	@Override
	public void fireChanged(JPanel panel) {
		for(WindowChangedService listener : listeners){
			listener.fireChanged(panel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		EventEnum eventEnum = null;
		
		for(EventEnum event : EventEnum.values()){
			if(event.toString().equals(arg0.getActionCommand())){
				eventEnum = event;
				break;
			}
		}
		
		switch (eventEnum) {
		case ITEMMANAGEMENT:
			fireChanged(new ItemManagementPanel());
			break;
		case CUSTOMEROVERVIEWBUTTONEVENT:
			fireChanged(new CustomerOverview());
			break;
		case ADDITEMBUTTONEVENT:
			fireChanged(new AddItemPanel());
			break;
		case RENTBUTTON1:
			fireChanged(new UitleningStap1Panel());
			break;
		case RENTBUTTON2:
			fireChanged(new UitleningStap2Panel());
			break;
		default:
			break;
		}
	}
}
