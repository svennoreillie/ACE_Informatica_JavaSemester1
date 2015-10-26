package ui;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class KluisPanel extends JPanel implements ActionListener{
   private boolean isDicht = true;
   private int innerStraal;
   private Timer timer;
   private int teller = 0;
   
   public KluisPanel(){
	   timer = new Timer(200, this);
	   timer.setInitialDelay(500);      
   } 
   
   public void setDicht(boolean dicht){
	   isDicht = dicht;
	   if (!isDicht){
		   teller=0;		   
		   innerStraal = 20;
		   timer.start();
	   }	   
   }
  
   public void paintComponent( Graphics g){
      super.paintComponent( g ); 
      int straal = this.getHeight();
      g.setColor(Color.GREEN);
      g.fillOval( this.getWidth()/2-straal/2,this.getHeight()/2-straal/2,straal,straal);
      if (!isDicht){  	   
    	  g.setColor(Color.GRAY);
    	  g.fillOval( this.getWidth()/2-innerStraal/2,this.getHeight()/2-innerStraal/2,innerStraal,innerStraal);  
    	  if (teller <10){
    		  innerStraal+=10;
    		  teller++;
    	  }	  
    	  if (teller == 10){
    		  timer.stop();
    	  }			
	  }    	
   } 

   @Override
   public void actionPerformed(ActionEvent arg0) {
	   repaint();	
   }
} 