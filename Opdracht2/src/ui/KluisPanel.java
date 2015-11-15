package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ui.CGBouncingBallSwingTimer.DrawCanvas;


//TO DO zou JFrame moeten implementeren om animation toe te laten in volle glorie
public class KluisPanel extends JPanel implements ActionListener{
	   private boolean isDicht = true;
	   //private int innerStraal;
	   private int xpoints[] = {25, 145, 25, 145, 25};
	   private int ypoints[] = {25, 25, 145, 145, 25};
	   private int npoints = 5;
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
			   //innerStraal = 20;
			   int xpoints[] = {15, 120, 15, 120, 15};
			   int ypoints[] = {15, 15, 120, 120, 15};
			   int npoints = 10;
			   timer.start();
		   }	   
	   }
	  
	   public void paintComponent( Graphics g){
	      super.paintComponent( g ); 
	      //int straal = this.getHeight();
	      g.setColor(Color.BLUE);
	      //Geert 14 Nov andere figuur dan ovaal
	      /*int xpoints[] = {25, 145, 25, 145, 25};
	      int ypoints[] = {25, 25, 145, 145, 25};
	      int npoints = 5;*/
	      
	      g.fillPolygon(xpoints, ypoints, npoints);
	      if (!isDicht){  	   
	    	  g.setColor(Color.GRAY);
	    	  g.fillPolygon(xpoints, ypoints, npoints);  
	    	  if (teller <10){
	    		  //innerStraal+=10;
	    		  int xpoints[] = {5, 60, 5, 60, 5};
	    		  int ypoints[] = {5, 5, 60, 60, 5};
	    		  int npoints = 10;
	    		  teller++;
	    		 /* xpoints[]++;
	    		  ypoints[]++;*/
	    		  npoints++;
	    	  }	  
	    	  if (teller == 10){
	    		  timer.stop();
	    	  	}
	      }
	      /*g.fillOval( this.getWidth()/2-straal/2,this.getHeight()/2-straal/2,straal,straal);
	      if (!isDicht){  	   
	    	  g.setColor(Color.GRAY);
	    	  g.fillOval( this.getWidth()/2-innerStraal/2,this.getHeight()/2-innerStraal/2,innerStraal,innerStraal);  
	    	  if (teller <10){
	    		  innerStraal+=10;
	    		  teller++;
	    	  }	  
	    	  if (teller == 10){
	    		  timer.stop();
	    	  }		*/	
		  }    	
	   

	   @Override
	   public void actionPerformed(ActionEvent arg0) {
		   repaint();	
	   }
	} 