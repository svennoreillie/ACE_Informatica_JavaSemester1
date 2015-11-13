package application;

import domain.*;
import domain.tellers.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import controller.SlotController;
import db.PropertiesLoaderSaver;
import db.dbException;

public class Launcher{

   public static void main( String[] args ) throws dbException{
      JFrame bedieningVenster = new JFrame( "Knack die code" );
      ArrayList<Teller>tellers =new ArrayList<Teller>();
      
      /*
      Teller t1= new CyclischeTeller('a','b','c');
      Teller t2=new TerugLoopTeller();
      Teller t3=new CyclischeTeller('*','+','@');  
      
      tellers.add(t1); tellers.add(t2); tellers.add(t3);
      String geheimeCode = "a4+";
      */
      
      PropertiesLoaderSaver.loadProperties();
      String geheimeCode=PropertiesLoaderSaver.getCode();
      List<TellerType> tellerTypes = PropertiesLoaderSaver.getTellerTypes();
      List<Character[]> tellerWaardes = PropertiesLoaderSaver.getTellerWaardes();
      
      for(int i=0;i<tellerTypes.size();i++){
    	  TellerType tT = tellerTypes.get(i);
    	  Character[] tW = tellerWaardes.get(i);
    	  Object[] passed = {tW};
    	  Teller t = TellerTypeFactory.createTellerType(tT.getKlasseNaam(), passed);
    	  tellers.add(t);
      }

      Slot slot =new Slot(tellers,geheimeCode);
      
      new SlotController(slot,bedieningVenster);

      bedieningVenster.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      bedieningVenster.setSize( 600, 400 ); 
      bedieningVenster.setVisible( true ); 
   } 
} 
