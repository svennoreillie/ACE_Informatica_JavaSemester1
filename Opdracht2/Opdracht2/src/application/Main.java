package application;
import domain.*;
import domain.tellers.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import controller.SlotController;

public class Main
{
   public static void main( String[] args ){ 
      
      JFrame application = new JFrame( "Knack die code" );
      
      Teller t1= new CyclischeTeller('a','b','c');
      Teller t2=new TerugLoopTeller();
      Teller t3=new CyclischeTeller('*','+','@');
      Object[]w=new Character[2];
      w[0]='z'; w[1]= 'h';
      Object[] passed = {w};
      
      /*
       The newInstance() method of the constructor class takes 
       an array of objects. Each item in the array is an argument 
       of the constructor you are invoking. Your class's constructor
       takes an object array so you need to have an object array 
       inside the array you pass to the new instance method     
       */
    		  
      Teller t4 = TellerTypeFactory.createTellerType(TellerType.CYCLISCH.getKlasseNaam(),passed);
      ArrayList<Teller>tellers =new ArrayList<Teller>();
      tellers.add(t1); tellers.add(t2); tellers.add(t3);tellers.add(t4);
      Slot slot =new Slot(tellers,"a4+z");
      new SlotController(slot,application);


      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.setSize( 600, 400 ); // set frame size
      application.setVisible( true ); // display frame
   } 
}



