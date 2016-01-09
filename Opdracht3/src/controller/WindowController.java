package controller;

import database.DatabaseMaintenance;

public class WindowController {
	public void dropDB() {
		DatabaseMaintenance maintenance = new DatabaseMaintenance();
		maintenance.dropAllDBs();
	}
}
