/**
 * @Autor: Sven Noreillie, Peter Vervoort
 * @Team: Team13
 * @Date: 31/10/2015
 * @Project: KroegenTocht
 * @Purpose: Service die Input en output stream creert, enige reden van bestaan is zodat dit
 * 			gemocked zou kunnen worden in unittests
 */

package database.implementations;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import common.*;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class TextDatabase<T extends ModelBase> implements DataReadWriteService<T> {

	private final Path dbPath;

	public TextDatabase() {
		this.dbPath = Paths.get(getGenericClass().getName() + ".db");
	}
	
	protected Class<T> getGenericClass() {
		Class<? extends TextDatabase> classType = this.getClass();
		ParameterizedType type = (ParameterizedType)classType.getGenericSuperclass();
		Class<T> genericClass = (Class<T>)type.getActualTypeArguments()[0];
		return genericClass;
	}

	public void Show() {

	}

	public Path getDbPath() {
		return this.dbPath;
	}

	public ObjectInputStream getInputStream() throws EOFException, DBException, DBMissingException {
		try {
			checkDB();
			ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(this.dbPath));
			return stream;
		} catch (EOFException e) {
			throw e;
		} catch (IOException e) {
			throw new DBException(MagicStrings.DBReadError, e);
		}
	}

	public ObjectOutputStream getOutputStream() throws DBMissingException, DBException {
		try {
			checkDB();
			ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(this.dbPath));
			return stream;
		} catch (IOException e) {
			throw new DBException(MagicStrings.DBWriteError, e);
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

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeDB(List<T> list) throws DBMissingException, DBException {
		// TODO Auto-generated method stub
		
	}
	
	
}
