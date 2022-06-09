package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.system.fsoft.config.database.DataSource;
import com.system.fsoft.entity.Candidate;
import com.system.fsoft.repository.CandidateRepository;

public class CandidateRepositoryImpl implements CandidateRepository {

	private final Logger log = LogManager.getLogger(CandidateRepositoryImpl.class.getName());

	private Candidate candidate;
	private String query;

	private static final String SELECT_CANDIDATE_AND_COUNT_THEIR_CERTIFICATE = "SELECT c.Candidate_ID, c.Full_Name, c.Candidate_Type, Count(ce.Certificate_ID) AS Total_Certificate FROM Candidate c LEFT JOIN Certificate ce ON c.Candidate_ID = ce.Candidate_ID GROUP BY c.Candidate_ID, c.Full_Name, c.Candidate_Type, ce.Certificate_ID";
	private static final String SELECT_ALL = "SELECT * FROM Candidate";

	public CandidateRepositoryImpl(Candidate candidate, String query) {
		this.candidate = candidate;
		this.query = query;
	}

	public CandidateRepositoryImpl() {
	}

	@Override
	public void save() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, this.candidate.getCandidateID());
			statement.setString(2, this.candidate.getFullName());
			statement.setDate(3, this.candidate.getBirthDate());
			statement.setString(4, this.candidate.getPhone());
			statement.setString(5, this.candidate.getEmail());
			statement.setInt(6, this.candidate.getCandidateType());
			if (statement.executeUpdate() == 1) {
				log.info("Save Candidate success");
			} else {
				log.debug("Something wrong");
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Error at CandidateRepository.save()", e);
		}
	}

	@Override
	public void edit() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, this.candidate.getFullName());
			statement.setDate(2, this.candidate.getBirthDate());
			statement.setString(3, this.candidate.getPhone());
			statement.setString(4, this.candidate.getEmail());
			statement.setInt(5, this.candidate.getCandidateType());
			statement.setString(6, this.candidate.getCandidateID());
			if (statement.executeUpdate() == 1) {
				log.info("Edit Candidate success");
			} else {
				log.debug("Something wrong");
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Error at CandidateRepository.edit()", e);
		}
	}

	@Override
	public void run() {
		if (query.contains("UPDATE")) {
			try {
				this.edit();
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error("Problem at CandidateRepository.run() when execute update statement", e);
			}
		}
		if (query.contains("INSERT INTO")) {
			try {
				this.save();
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error("Problem at CandidateRepository.run() when execute insert statement", e);
			}
		}
		if (query.contains("SELECT")) {
			try {
				if (candidate.getCandidateID() != null) {
					this.editViaResulSet();
				} else {
					this.saveViaResultSet();
				}
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error("Problem at CandidateRepository.run() when execute select statement", e);
			}
		}
	}

	@Override
	public void saveViaResultSet() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSet = statement.executeQuery();) {
			resultSet.moveToInsertRow();
			statement.setString(1, candidate.getCandidateID());
			resultSet.updateString(2, candidate.getFullName());
			resultSet.updateDate(3, candidate.getBirthDate());
			resultSet.updateString(4, candidate.getPhone());
			resultSet.updateString(5, candidate.getEmail());
			resultSet.updateInt(6, candidate.getCandidateType());
			resultSet.insertRow();
			log.info("Insert success");
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Problem at CandidateRepository.saveViaResultSet()", e);
		}
	}

	@Override
	public void editViaResulSet() throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, candidate.getCandidateID());
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.updateString(2, candidate.getFullName());
				resultSet.updateDate(3, candidate.getBirthDate());
				resultSet.updateString(4, candidate.getPhone());
				resultSet.updateString(5, candidate.getEmail());
				resultSet.updateInt(6, candidate.getCandidateType());
				resultSet.updateRow();
				log.info("Edit success");
			}
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Problem at CandidateRepository.editViaResulSet()", e);
		}
	}

	@Override
	public List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_CANDIDATE_AND_COUNT_THEIR_CERTIFICATE);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Candidate candidate = new Candidate();
				candidate.setCandidateID(resultSet.getString("Candidate_ID"));
				candidate.setFullName(resultSet.getString("Full_Name"));
				candidate.setCandidateType(resultSet.getInt("Candidate_Type"));
				candidate.setTotalCertificate(resultSet.getInt("Total_Certificate"));
				candidates.add(candidate);
			}
			return candidates;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Problem at CandidateRepository.getAllCandidateAndTheirCertidicate()", e);
			return null;
		}
	}

	@Override
	public List<Candidate> getAll() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Candidate c = new Candidate();
				c.setCandidateID(resultSet.getString("Candidate_ID"));
				c.setBirthDate(resultSet.getDate("Birth_Day"));
				c.setPhone(resultSet.getString("Phone"));
				c.setEmail(resultSet.getString("Email"));
				c.setFullName(resultSet.getString("Full_Name"));
				c.setCandidateType(resultSet.getInt("Candidate_Type"));
				candidates.add(c);
			}
			return candidates;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error("Problem at CandidateRepository.getAll()", e);
			return null;
		}
	}
}