package database.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DataSourceFactory;
import database.helpers.DataSource;
import database.implementations.CSVDatabase;
import database.implementations.ExcelDatabase;
import database.implementations.SQLDatabase;
import database.implementations.TextDatabase;
import database.internalInterface.DataReadWriteService;
import junit.framework.Assert;
import model.Address;
import model.Shop;

public class DataSourceFactoryTest {

	@Test
	public void testGetServiceCSV() {
		DataSourceFactory.setType(DataSource.CSV);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(CSVDatabase.class, addressRWS.getClass());
		
		
		DataReadWriteService<Shop> shopRWS = DataSourceFactory.getSource(Shop.class);
		
		assertNotNull(shopRWS);
		assertSame(CSVDatabase.class, shopRWS.getClass());
		assertNotSame(addressRWS, shopRWS);
	}
	
	@Test
	public void testGetServiceSQL() {
		DataSourceFactory.setType(DataSource.SQL);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(SQLDatabase.class, addressRWS.getClass());
	}
	
	@Test
	public void testGetServiceText() {
		DataSourceFactory.setType(DataSource.Text);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(TextDatabase.class, addressRWS.getClass());
	}
	
	@Test
	public void testGetServiceExcel() {
		DataSourceFactory.setType(DataSource.EXCEL);
		DataReadWriteService<Address> addressRWS = DataSourceFactory.getSource(Address.class);
		
		assertNotNull(addressRWS);
		assertSame(ExcelDatabase.class, addressRWS.getClass());
	}

}
