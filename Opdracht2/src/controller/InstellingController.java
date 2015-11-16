package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import db.PropertiesLoaderSaver;
import db.dbException;
import ui.InstellingenPanel;

public class InstellingController{
	
	InstellingenPanel instellingenPanel;
	
	public InstellingController(JFrame venster){
		instellingenPanel = new InstellingenPanel();
		instellingenPanel.setOkButtonListener(new OkButtonListener());
		instellingenPanel.setCancelButtonListener(new CancelButtonListener());
	}
	
	
	class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				PropertiesLoaderSaver.setTellerTypes(instellingenPanel.getTellerTypes());
				PropertiesLoaderSaver.setTellerWaardes(instellingenPanel.getTellerWaardes());
				PropertiesLoaderSaver.setCode(instellingenPanel.getCode());
				
				//TODO view veranderen naar Kluis
			} catch (dbException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	
	class CancelButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO view veranderen naar Kluis
		}
		
	}
}
