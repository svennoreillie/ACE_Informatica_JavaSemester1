package testing;

import static testing.ThrowableCaptor.thrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DataStorageTest_17102015ITest {

	private static final String CONTENT = "content";

	  @Rule
	  public TemporaryFolder temporaryFolder = new TemporaryFolder();
	  
	  private DataStorageTest_17102015 storage;
	  private File storageLocation;
	  
	  @Before
	  public void setUp() throws IOException {
	    storageLocation = temporaryFolder.newFile();
	    storage = new DataStorageTest_17102015( );
	  }
	  
	  @Test
	  public void store() throws IOException {
	    File file = new File( CONTENT );
	    
	    storage.store( file );
	    
	    assertEquals( CONTENT, readStoredContent() );
	  }
	  
	  @Test
	  public void storeIfStorageLocationDoesNotExist() throws IOException {
	    storageLocation.delete();
	    
	    Throwable actual = thrownBy( () -> storage.store( new File( CONTENT ) ) );
	    
	    assertTrue( actual instanceof IllegalStateException );
	    assertTrue( actual.getCause() instanceof IOException );
	  }

	  @Test
	  public void read() throws IOException {
	    writeContentToStore( CONTENT );
	    
	    File file = storage.read();
	    
	    assertEquals( CONTENT, file.toString() );
	  }

	  @Test
	  public void readIfStorageLocationDoesNotExist() {
	    storageLocation.delete();

	    Throwable actual = thrownBy( () -> storage.read() );

	    assertTrue( actual instanceof IllegalStateException );
	    assertTrue( actual.getCause() instanceof IOException );
	  }
	  
	  @Test( expected = IllegalArgumentException.class )
	  public void constructWithNullAsStorageLocation() {
	    new DataStorageTest_17102015( ); 
	  }
	  
	  private String readStoredContent() throws IOException {
	    byte[] bytes = Files.readAllBytes( storageLocation.toPath() );
	    return new String( bytes, StandardCharsets.UTF_8 );
	  }

	  private Path writeContentToStore( String content )
		 throws IOException
	  {
	    byte[] bytes = content.getBytes( StandardCharsets.UTF_8 );
	    return Files.write( storageLocation.toPath(), bytes );
	  }	
	
}
