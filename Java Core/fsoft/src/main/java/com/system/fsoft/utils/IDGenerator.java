package com.system.fsoft.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.fsoft.config.database.DataSource;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.entity.Fresher;
import com.system.fsoft.entity.Intern;
import com.system.fsoft.exception.SystemInterruptedException;

public class IDGenerator {

	private static final String CERTIFICATE_ID_PATTERN = "CER-";
	private static final String CANDIDATE_EXPERIENCE_ID_PATTERN = "EXP-";
	private static final String CANDIDATE_FRESHER_ID_PATTERN = "FRS-";
	private static final String CANDIDATE_INTERN_ID_PATTERN = "ITN-";

	private static final String QUERY_EXPERIENCE = "SELECT e.Candidate_ID FROM Experience e";
	private static final String QUERY_FRESHER = "SELECT f.Candidate_ID FROM Fresher f";
	private static final String QUERY_INTERN = "SELECT i.Candidate_ID FROM Candidate i";
	private static final String QUERY_CERTIFICATE = "SELECT ce.Certificate_ID FROM Certificate ce";

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private Object field;

	private IDGenerator(Object field) {
		this.field = field;
	}

	public static IDGenerator scan(Object field) {
		return new IDGenerator(field);
	}

	protected int lastID() {
		if (field instanceof Experience) {
			try {
				connection = DataSource.getConnection();
				preparedStatement = connection.prepareStatement(QUERY_EXPERIENCE, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.last()) {
					String id = resultSet.getString(1);
					return Integer.parseInt(id.substring(id.lastIndexOf("-") + 1)) + 1;
				} else {
					return 1;
				}
			} catch (SQLException e) {
				throw new SystemInterruptedException("System has problem, please try again", e);
			}
		}
		if (field instanceof Fresher) {
			try {
				connection = DataSource.getConnection();
				preparedStatement = connection.prepareStatement(QUERY_FRESHER, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.last()) {
					String id = resultSet.getString(1);
					return Integer.parseInt(id.substring(id.lastIndexOf("-") + 1)) + 1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				if (e instanceof NullPointerException) {
					return 1;
				} else {
					throw new SystemInterruptedException("System has problem, please try again", e);
				}
			}
		}
		if (field instanceof Intern) {
			try {
				connection = DataSource.getConnection();
				preparedStatement = connection.prepareStatement(QUERY_INTERN, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.last()) {
					String id = resultSet.getString(1);
					return Integer.parseInt(id.substring(id.lastIndexOf("-") + 1)) + 1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				if (e instanceof NullPointerException) {
					return 1;
				} else {
					throw new SystemInterruptedException("System has problem, please try again", e);
				}
			}
		}
		if (field instanceof Certificate) {
			try {
				connection = DataSource.getConnection();
				preparedStatement = connection.prepareStatement(QUERY_CERTIFICATE, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.last()) {
					String id = resultSet.getString(1);
					return Integer.parseInt(id.substring(id.lastIndexOf("-") + 1)) + 1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				if (e instanceof NullPointerException) {
					return 1;
				} else {
					throw new SystemInterruptedException("System has problem, please try again", e);
				}
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder pattern = new StringBuilder();
		int lastID = this.lastID();
		if (lastID == -1) {
			throw new SystemInterruptedException("System has problem, please try again", null);
		}
		if (field instanceof Experience) {
			pattern.delete(0, pattern.length());
			pattern.append(CANDIDATE_EXPERIENCE_ID_PATTERN);
			pattern.append(lastID == 1 ? lastID : lastID + 1);
			return pattern.toString();
		}
		if (field instanceof Fresher) {
			pattern.delete(0, pattern.length());
			pattern.append(CANDIDATE_FRESHER_ID_PATTERN);
			pattern.append(lastID == 1 ? lastID : lastID + 1);
			return pattern.toString();
		}
		if (field instanceof Intern) {
			pattern.delete(0, pattern.length());
			pattern.append(CANDIDATE_INTERN_ID_PATTERN);
			pattern.append(lastID == 1 ? lastID : lastID + 1);
			return pattern.toString();
		}
		if (field instanceof Certificate) {
			pattern.delete(0, pattern.length());
			pattern.append(CERTIFICATE_ID_PATTERN);
			pattern.append(lastID == 1 ? lastID : lastID + 1);
			return pattern.toString();
		}
		return "";
	}
}