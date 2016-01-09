package database.implementations;

/**
 * 
 * @author Sven Noreillie & Vervoort Peter
 *
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import common.*;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DatabaseText<T extends ModelBase> implements DataReadWriteService<T> {

	private final Path dbPath;

	public DatabaseText(Class<T> classType) {
		this.dbPath = Paths.get(classType.getName() + ".db");
	}
	
	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		List<T> list = new ArrayList<T>();
		try {
			ObjectInputStream stream = this.getInputStream();
			while (true) {
				list.add((T) stream.readObject());
			} 
		} catch (EOFException e) {
			//end of file read => this is expected
		} catch (ClassNotFoundException | IOException e) {
			throw new DBException("Failure reading from text db", e);
		}
		
		return list;
	}

	@Override
	public void writeDB(List<T> list, Boolean update) throws DBMissingException, DBException {
		ObjectOutputStream stream = this.getOutputStream();
		for (T entity : list) {
			try {
				stream.writeObject(entity);
			} catch (IOException e) {
				throw new DBException("Failed to write to text db", e);
			}
		}
	}
	

	protected Path getDbPath() {
		return this.dbPath;
	}

	protected ObjectInputStream getInputStream() throws EOFException, DBException, DBMissingException {
		try {
			checkDB();
			ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(this.dbPath));
			return stream;
		} catch (EOFException e) {
			throw e;
		} catch (IOException e) {
			throw new DBException(AntiMagicStrings.DBReadError, e);
		}
	}

	protected ObjectOutputStream getOutputStream() throws DBMissingException, DBException {
		try {
			checkDB();
			ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(this.dbPath));
			return stream;
		} catch (IOException e) {
			throw new DBException(AntiMagicStrings.DBWriteError, e);
		}
	}

	private void checkDB() throws DBMissingException {
		if (!Files.exists(this.dbPath)) {
			try {
				Files.createFile(this.dbPath);
			} catch (IOException e) {
				throw new DBMissingException();
			}
		}
	}

	
	
	
}
