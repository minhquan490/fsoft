package com.system.fsoft.config.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSource {
	private static BasicDataSource basicDataSource = new BasicDataSource();
	private static Logger log = LogManager.getLogger(DataSource.class.getName());

	private DataSource() {
		super();
	}

	static {
		basicDataSource.setDriverClassName(DatabaseConfiguration.DB_DRIVER);
		basicDataSource.setUrl(DatabaseConfiguration.URL);
		basicDataSource.setUsername(DatabaseConfiguration.USERNAME);
		basicDataSource.setPassword(DatabaseConfiguration.PASSWORD);
		basicDataSource.setMinIdle(DatabaseConfiguration.DB_MIN_CONNECTIONS);
		basicDataSource.setInitialSize(DatabaseConfiguration.DB_MIN_CONNECTIONS);
		basicDataSource.setMaxIdle(DatabaseConfiguration.DB_MAX_CONNECTIONS);
		basicDataSource.setMaxOpenPreparedStatements(100);
	}

	public static Connection getConnection() throws SQLException {
		log.info("Connection created");
		return basicDataSource.getConnection();
	}
}