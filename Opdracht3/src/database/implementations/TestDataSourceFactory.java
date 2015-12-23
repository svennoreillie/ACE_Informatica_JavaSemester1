package database.implementations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.helpers.DataSource;
import database.internalInterface.DataReadWriteService;
import model.Address;
import model.Shop;

public class TestDataSourceFactory {

	
	@Test
	public void testGetServiceSQL() {
		DataSourceFactory.setType(DataSource.SQL);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(DatabaseSQL.class, addressRWS.getClass());
	}
	
	@Test
	public void testGetServiceText() {
		DataSourceFactory.setType(DataSource.Text);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(DatabaseText.class, addressRWS.getClass());
	}
	
	@Test
	public void testGetServiceExcel() {
		DataSourceFactory.setType(DataSource.EXCEL);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(DatabaseExcel.class, addressRWS.getClass());
	}

}
