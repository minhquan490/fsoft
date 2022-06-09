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
import com.system.fsoft.entity.Fresher;
import com.system.fsoft.repository.CandidateRepository;
import com.system.fsoft.repository.FresherRepository;
import com.system.fsoft.utils.IDGenerator;

public class FresherRepositoryImpl implements FresherRepository {

	private final Logger log = LogManager.getLogger(FresherRepositoryImpl.class.getName());

	private CandidateRepository candidateRepository;

	private static final String INSERT_QUERY = "INSERT INTO Fresher(Candidate_ID, Graduation_Day, Graduation_Rank, Education) VALUES (?,?,?,?)";
	private static final String INSERT_CANDIDATE = "INSERT INTO Candidate(Candidate_ID, Full_Name, Birth_Day, Phone, Email, Candidate_Type) VALUES (?,?,?,?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM Candidate WHERE Candidate_ID = ?";
	private static final String UPDATE_QUERY = "UPDATE Fresher SET Graduation_Day = ?, Graduation_Rank = ?, Education = ? WHERE Candidate_ID = ?";
	private static final String UPDATE_CANDIDATE = "UPDATE Candidate SET Full_Name = ?, Birth_Day = ?, Phone = ?, Email = ?, Candidate_Type = ? WHERE Candidate_ID = ?";
	private static final String SELECT_TO_INSET_OR_UPDATE_QUERY = "SELECT * FROM Fresher e WHERE e.Candidate_ID = ?";
	private static final String SELECT_CANDIDATE_TO_INSERT_OR_UPDATE = "SELECT * FROM Candidate c WHERE c.Candidate_ID = ?";
	private static final String SELECT_QUERY_BY_NAME = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Graduation_Day, e.Graduation_Rank, e.Education FROM Fresher e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_Name = ?";
	private static final String SELECT_QUERY_BY_ID = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Graduation_Day, e.Graduation_Rank, e.Education FROM Fresher AS e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_ID = ?";
	private static final String SELECT_ALL = "SELECT * FROM Candidate c FULL JOIN Fresher e ON c.Candidate_ID = e.Candidate_ID ORDER BY c.Full_Name";
	private static final String SELECT_BY_RANK = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Graduation_Day, e.Graduation_Rank, e.Education FROM Candidate c LEFT JOIN Fresher e WHERE e.Graduation_Rank = ? ORDER BY c.Full_Name";
	private static final String SELECT_BY_UNIVERSITY = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Graduation_Day, e.Graduation_Rank, e.Education FROM Candidate c LEFT JOIN Fresher e WHERE e.Education = ? ORDER BY c.Full_Name";

	@Override
	public void save(Fresher fresher) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {
			fresher.setCandidateID(IDGenerator.scan(fresher).toString());
			candidateRepository = new CandidateRepositoryImpl(fresher, INSERT_CANDIDATE);
			candidateRepository.run();
			statement.setString(1, fresher.getCandidateID());
			statement.setDate(2, fresher.getGraduationDate());
			statement.setString(3, fresher.getGraduationRank());
			statement.setString(4, fresher.getEducation());
			Thread.sleep(200);
			if (statement.executeUpdate() == 1) {
				log.info("Save success");
			} else {
				log.debug("Something wrong when calling FresherRepository.save()");
			}
		} catch (SQLException | InterruptedException e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.save() " + e.getMessage(), e);
		}
	}

	@Override
	public void edit(Fresher fresher) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			candidateRepository = new CandidateRepositoryImpl(fresher, UPDATE_CANDIDATE);
			candidateRepository.run();
			statement.setString(1, fresher.getGraduationRank());
			statement.setString(2, fresher.getEducation());
			statement.setString(3, fresher.getCandidateID());
			statement.setDate(4, fresher.getGraduationDate());
			Thread.sleep(200);
			if (statement.executeUpdate() == 1) {
				log.info("Edit success");
			} else {
				log.debug("Something wrong when calling FresherRepository.edit()");
			}
		} catch (SQLException | InterruptedException e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.edit() " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Fresher fresher) throws SQLException {
		if (this.getByID(fresher.getCandidateID()) != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
				statement.setString(1, fresher.getCandidateID());
				if (statement.executeUpdate() == 1) {
					log.info("Delete success");
				}
			} catch (SQLException e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error(() -> "Problem at FresherRepository.delete() " + e.getMessage(), e);
			}
		} else {
			System.out.println("Candidate not exist");
		}
	}

	@Override
	public void saveOrUpdate(Fresher fresher) throws SQLException {
		if (fresher.getCandidateID() != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(SELECT_TO_INSET_OR_UPDATE_QUERY,
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				candidateRepository = new CandidateRepositoryImpl(fresher, SELECT_CANDIDATE_TO_INSERT_OR_UPDATE);
				candidateRepository.run();
				statement.setString(1, fresher.getCandidateID());
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.updateDate(2, fresher.getGraduationDate());
					resultSet.updateString(3, fresher.getGraduationRank());
					resultSet.updateString(4, fresher.getEducation());
					Thread.sleep(200);
					resultSet.updateRow();
					log.info("Update success");
				}
			} catch (SQLException | InterruptedException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				}
				log.error(() -> "Problem at FresherRepository.saveOrUpdate() " + e.getMessage(), e);
			}
		} else {
			String selectInsertQuery = SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.substring(0,
					SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.lastIndexOf("W") - 1);
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectInsertQuery,
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				fresher.setCandidateID(IDGenerator.scan(fresher).toString());
				candidateRepository = new CandidateRepositoryImpl(fresher, selectInsertQuery);
				candidateRepository.run();
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.moveToInsertRow();
					statement.setString(1, fresher.getCandidateID());
					resultSet.updateDate(2, fresher.getGraduationDate());
					resultSet.updateString(3, fresher.getGraduationRank());
					resultSet.updateString(4, fresher.getEducation());
					Thread.sleep(200);
					resultSet.insertRow();
					log.info("Insert success");
				}
			} catch (SQLException | InterruptedException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				}
				log.error(() -> "Problem at FresherRepository.saveOrUpdate() " + e.getMessage(), e);
			}
		}
	}

	@Override
	public Fresher getByID(String idCandidate) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_ID);) {
			statement.setString(1, idCandidate);
			try (ResultSet resultSet = statement.executeQuery();) {
				Fresher fresher = new Fresher();
				while (resultSet.next()) {
					fresher.setCandidateID(resultSet.getString("Candidate_ID"));
					fresher.setFullName(resultSet.getString("Full_Name"));
					fresher.setBirthDate(resultSet.getDate("Birth_Day"));
					fresher.setPhone(resultSet.getString("Phone"));
					fresher.setEmail(resultSet.getString("Email"));
					fresher.setCandidateType(resultSet.getInt("Candidate_Type"));
					fresher.setGraduationDate(resultSet.getDate("Graduation_Day"));
					fresher.setGraduationRank(resultSet.getString("Graduation_Rank"));
					fresher.setEducation(resultSet.getString("Education"));
				}
				return fresher;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.getByID() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Fresher getByName(String candidateName) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_NAME);) {
			statement.setString(1, candidateName);
			try (ResultSet resultSet = statement.executeQuery();) {
				Fresher fresher = new Fresher();
				while (resultSet.next()) {
					fresher.setCandidateID(resultSet.getString("Candidate_ID"));
					fresher.setFullName(resultSet.getString("Full_Name"));
					fresher.setBirthDate(resultSet.getDate("Birth_Day"));
					fresher.setPhone(resultSet.getString("Phone"));
					fresher.setEmail(resultSet.getString("Email"));
					fresher.setCandidateType(resultSet.getInt("Candidate_Type"));
					fresher.setGraduationDate(resultSet.getDate("Graduation_Day"));
					fresher.setGraduationRank(resultSet.getString("Graduation_Rank"));
					fresher.setEducation(resultSet.getString("Education"));
				}
				return fresher;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.getByName() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Fresher> getAll() throws SQLException {
		List<Fresher> freshers = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Fresher fresher = new Fresher();
				fresher.setCandidateID(resultSet.getString("Candidate_ID"));
				fresher.setFullName(resultSet.getString("Full_Name"));
				fresher.setBirthDate(resultSet.getDate("Birth_Day"));
				fresher.setPhone(resultSet.getString("Phone"));
				fresher.setEmail(resultSet.getString("Email"));
				fresher.setCandidateType(resultSet.getInt("Candidate_Type"));
				fresher.setGraduationDate(resultSet.getDate("Graduation_Day"));
				fresher.setGraduationRank(resultSet.getString("Graduation_Rank"));
				fresher.setEducation(resultSet.getString("Education"));
				freshers.add(fresher);
			}
			return freshers;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.getAll() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Fresher> getFreshersByRank(String rank) throws SQLException {
		List<Fresher> freshers = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_RANK);) {
			statement.setString(1, rank);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Fresher fresher = new Fresher();
					fresher.setCandidateID(resultSet.getString("Candidate_ID"));
					fresher.setFullName(resultSet.getString("Full_Name"));
					fresher.setBirthDate(resultSet.getDate("Birth_Day"));
					fresher.setPhone(resultSet.getString("Phone"));
					fresher.setEmail(resultSet.getString("Email"));
					fresher.setCandidateType(resultSet.getInt("Candidate_Type"));
					fresher.setGraduationDate(resultSet.getDate("Graduation_Day"));
					fresher.setGraduationRank(resultSet.getString("Graduation_Rank"));
					fresher.setEducation(resultSet.getString("Education"));
					freshers.add(fresher);
				}
				return freshers;
			}
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.getFreshersByRank() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Fresher> getFreshersByUniversity(String universityName) throws SQLException {
		List<Fresher> freshers = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_UNIVERSITY);) {
			statement.setString(1, universityName);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Fresher fresher = new Fresher();
					fresher.setCandidateID(resultSet.getString("Candidate_ID"));
					fresher.setFullName(resultSet.getString("Full_Name"));
					fresher.setBirthDate(resultSet.getDate("Birth_Day"));
					fresher.setPhone(resultSet.getString("Phone"));
					fresher.setEmail(resultSet.getString("Email"));
					fresher.setCandidateType(resultSet.getInt("Candidate_Type"));
					fresher.setGraduationDate(resultSet.getDate("Graduation_Day"));
					fresher.setGraduationRank(resultSet.getString("Graduation_Rank"));
					fresher.setEducation(resultSet.getString("Education"));
					freshers.add(fresher);
				}
				return freshers;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at FresherRepository.getFreshersByUniversity() " + e.getMessage(), e);
			return null;
		}
	}
}