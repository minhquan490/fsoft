package com.system.fsoft.config.database;

public class DatabaseConfiguration {
	public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SERVER_ADDRESS = "localhost";
	public static final String DB_NAME = "Candidate_Management";
	public static final String SERVER_PORT = "1433";
	public static final String USERNAME = "sa";
	public static final String PASSWORD = "Quan3008";
	public static final int DB_MIN_CONNECTIONS = 2;
	public static final int DB_MAX_CONNECTIONS = 10;
	public static final String URL = "jdbc:sqlserver://" + SERVER_ADDRESS + ":" + SERVER_PORT + ";" + "database="
			+ DB_NAME + ";" + "trustServerCertificate=true;";

	private DatabaseConfiguration() {
		super();
	}
}
