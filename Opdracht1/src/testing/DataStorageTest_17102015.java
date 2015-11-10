package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import dataStorage.DataStorage;
import common.MagicStrings;
//inspiration from Frank Appels's website
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.write;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import java.io.IOException;
import java.nio.file.Files;

public class DataStorageTest_17102015 implements SessionStorage{
	@SuppressWarnings("unused")
	private String fullList = new String();
	@SuppressWarnings("unused")
	private File ctorFileI;
	
	
	private DataStorage ds;
	private File storageLocation;
	private final String reservation = new String();
	private final DataStorage storage = new DataStorage();
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception{
		//voorbeeld ctorDateIII = new DateInt (1,1,2000);
		this.ctorFileI = new File ("bestanden\\werknemers.txt");	
		this.ds = new DataStorage();
		//String s = new String("res");
	}
	/*
	 //Geert 10 Nov JUnit code voorbeeld, nu in commentaar gezet
	//hieronder code van JUnit testing boek aan te passen naar deze klasse
	//@Test
	@Test
	 public void testDataStorageTest_17102015( File storageLocation ) {
		    checkStorageLocation( storageLocation );
		    
		    this.storageLocation = storageLocation;
		  }

		  @Override
		  public void store( File file ) {
		    writeToFile( file.toString().getBytes( UTF_8 ) );
		  }

		  @Override
		  public File read() {
		    return new File( new String( readFromFile(), UTF_8 ) );
		  }
		  
		  private void writeToFile( byte[] bytes ) {
		    try {
		      write( storageLocation.toPath(), bytes, TRUNCATE_EXISTING, WRITE );
		    } catch( IOException cause ) {
		      throw new IllegalStateException( cause );
		    }
		  }
		  
		  private byte[] readFromFile() {
		    try {
		      return Files.readAllBytes( storageLocation.toPath() );
		    } catch( IOException cause ) {
		      throw new IllegalStateException( cause );
		    }
		  }
		  
		  private void checkStorageLocation( File storageLocation ) {
		    if( storageLocation == null ) {
		      throw new IllegalArgumentException( "Argument 'storageLocation' must not be null." );
		    }
		  }
		  //tot hier de aan te passen code van het JUnit boek
		
		  
		  
		  //testen op JUnit boek code nodig hierboven?
		  
		  //tot hier testen op JUNit code boek
		*/  
	@Test
	public void testGetDataFileNotFound() {
		MagicStrings s = new MagicStrings();
		//arrange
	    thrown.expectMessage(s.getFileNotfound());
		//act
		
	}

	@Test
	public void testGetData() {
		fail("Not yet implemented");
	} 
	
	@Test
	public void testGetReservationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetData() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetReservationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReservationWorks() throws Throwable {
		//String s = fullList();
		//assertEquals("fullList","reservation");
		fail("Not implemented yet");
	}
	@Test
	public void testAddReservationReservationNotSameAsFullList() throws Throwable {
		//String s = "res";
		this.fullList = storage.getData();
		this.fullList += ";";
		this.fullList += reservation;
		assertEquals("fullList","s");
	}
	@Test
	public void testDataFormatCheckWrongFormatNoName() throws Throwable {
		assertFalse(ds.dataFormatCheck("111,5/11/2015,4>"));
	}
	//testen op juiste formaat
	//111,5/11/2015,4>Adams,Chloe
	@Test
	public void testDataFormatCheck() throws Throwable {
		assertTrue(ds.dataFormatCheck("111,5/11/2015,4>Adams,Chloe"));
	}
	//testen op fout formaat
	@Test
	public void testDataFormatCheckWrongFormat() throws Throwable {
		assertFalse(ds.dataFormatCheck("111,5/11/2015,4>Adams"));
	}
	@Override
	public File read() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void store(File file) {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void store(testing.File file) {
		// TODO Auto-generated method stub
		
	}*/
	/*@Override
	public testing.File read() {
		// TODO Auto-generated method stub
		return null;
		*/
	}


