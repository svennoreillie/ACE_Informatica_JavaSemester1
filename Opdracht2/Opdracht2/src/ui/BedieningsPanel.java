package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BedieningsPanel extends JPanel{
	private JPanel logPanel;
	private JPanel knoppenPanel;
	private KluisPanel kluisPanel;
	private JButton controleButton;
	private JButton [] slotButtons;
	private JTextArea log;
   
   public BedieningsPanel(int aantalKnoppen){
      setLayout(new GridLayout(2,1));
      
      logPanel= new JPanel();
      logPanel.setLayout(new GridLayout(1,2));
      logPanel.setBackground(Color.GRAY); 
      kluisPanel = new KluisPanel();
      logPanel.add(kluisPanel);
      log = new JTextArea();
      log.setEditable(false);
      logPanel.add(new JScrollPane(log));      
      
      knoppenPanel = new JPanel();
      knoppenPanel.setLayout(new GridLayout(1,aantalKnoppen+1));      
      controleButton = new JButton("OPEN SLOT");      
      knoppenPanel.add(controleButton);
      slotButtons=new JButton[aantalKnoppen];
      for (int i = 0;i < aantalKnoppen;i++){
    	  slotButtons[i] = new JButton("?");
    	  slotButtons[i].setActionCommand(i+"");
       	  knoppenPanel.add(slotButtons[i]);    	  
      } 
      
      this.add(logPanel); this.add(knoppenPanel);
   } 
   
   public void setSysmboolForSlotButton(int index, char symbool){
	   slotButtons[index].setText(symbool+"");
   }
   
   public void setTekstForControleButton(String tekst){
	   controleButton.setText(tekst);
   }

   public void setSlotButtonsListener(ActionListener listenerForSlotButtons){
	  for (int i = 0;i <slotButtons.length ;i++){
		  slotButtons[i].addActionListener(listenerForSlotButtons);		 
	  }
   }  
	  
	public void setControleButtonListener(ActionListener listenerForControleButton){
		  controleButton.addActionListener(listenerForControleButton);			 
	}
	
	public void setSlotButtonsActief (boolean actief){
		for (int i = 0;i <slotButtons.length ;i++){
			  slotButtons[i].setEnabled(actief);		 
		  }
	}
	
	public void openKluis(){
		 kluisPanel.setDicht(false);
         kluisPanel.repaint();
	}
	
	public void sluitKluis(){
		kluisPanel.setDicht(true);
        kluisPanel.repaint();
	}
	
	public void updateLogTekst(String lijn){
		String logTekst = lijn+"\n"+log.getText();
		log.setText(logTekst);
	}
  } 

