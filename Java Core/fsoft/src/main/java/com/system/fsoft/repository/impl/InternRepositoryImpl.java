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
import com.system.fsoft.entity.Intern;
import com.system.fsoft.repository.CandidateRepository;
import com.system.fsoft.repository.InternRepository;
import com.system.fsoft.utils.IDGenerator;

public class InternRepositoryImpl implements InternRepository {

	private final Logger log = LogManager.getLogger(InternRepositoryImpl.class.getName());

	private CandidateRepository candidateRepository;

	private static final String INSERT_QUERY = "INSERT INTO Intern(Candidate_ID, Major, Semester, Education) VALUES (?,?,?,?)";
	private static final String INSERT_CANDIDATE = "INSERT INTO Candidate(Candidate_ID, Full_Name, Birth_Day, Phone, Email, Candidate_Type) VALUES (?,?,?,?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM Candidate WHERE Candidate_ID = ?";
	private static final String UPDATE_QUERY = "UPDATE Intern SET Major = ?, Semester = ?, Education = ? WHERE Candidate_ID = ?";
	private static final String UPDATE_CANDIDATE = "UPDATE Candidate SET Full_Name = ?, Birth_Day = ?, Phone = ?, Email = ?, Candidate_Type = ? WHERE Candidate_ID = ?";
	private static final String SELECT_TO_INSET_OR_UPDATE_QUERY = "SELECT * FROM Intern e WHERE e.Candidate_ID = ?";
	private static final String SELECT_CANDIDATE_TO_INSERT_OR_UPDATE = "SELECT * FROM Candidate c WHERE c.Candidate_ID = ?";
	private static final String SELECT_QUERY_BY_NAME = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Major, e.Semester, e.Education FROM Intern e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_Name = ?";
	private static final String SELECT_QUERY_BY_ID = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Major, e.Semester, e.Education FROM Intern e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_ID = ?";
	private static final String SELECT_ALL = "SELECT * FROM Candidate c FULL JOIN Intern e ON c.Candidate_ID = e.Candidate_ID ORDER BY c.Full_Name";
	private static final String SELECT_BY_MAJOR = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Major, e.Semester, e.Education FROM Candidate c LEFT JOIN Intern e WHERE e.Major = ? ORDER BY c.Full_Name";
	private static final String SELECT_BY_UNIVERSITY = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Major, e.Semester, e.Education FROM Candidate c LEFT JOIN Intern e WHERE e.Education = ? ORDER BY c.Full_Name";

	@Override
	public void save(Intern intern) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {
			intern.setCandidateID(IDGenerator.scan(intern).toString());
			candidateRepository = new CandidateRepositoryImpl(intern, INSERT_CANDIDATE);
			candidateRepository.run();
			statement.setString(1, intern.getCandidateID());
			statement.setString(2, intern.getMajor());
			statement.setInt(3, intern.getSemester());
			statement.setString(4, intern.getUniversityName());
			Thread.sleep(200);
			if (statement.executeUpdate() == 1) {
				log.info("Save success");
			} else {
				log.debug("Something wrong when calling InternRepository.save()");
			}
		} catch (SQLException | InterruptedException e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.save() " + e.getMessage(), e);
		}
	}

	@Override
	public void edit(Intern intern) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			candidateRepository = new CandidateRepositoryImpl(intern, UPDATE_CANDIDATE);
			candidateRepository.run();
			statement.setString(1, intern.getCandidateID());
			statement.setString(2, intern.getMajor());
			statement.setInt(3, intern.getSemester());
			statement.setString(4, intern.getUniversityName());
			Thread.sleep(200);
			if (statement.executeUpdate() == 1) {
				log.info("Edit success");
			} else {
				log.debug("Something wrong when calling InternRepository.edit()");
			}
		} catch (SQLException | InterruptedException e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.edit() " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Intern intern) throws SQLException {
		if (this.getByID(intern.getCandidateID()) != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
				statement.setString(1, intern.getCandidateID());
				if (statement.executeUpdate() == 1) {
					log.info("Delete success");
				} else {
					log.debug("Something wrong when calling InternRepository.delete()");
				}
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error(() -> "Problem at InternRepository.edit() " + e.getMessage(), e);
			}
		} else {
			System.out.println("Candidate not exist");
		}
	}

	@Override
	public void saveOrUpdate(Intern intern) throws SQLException {
		if (intern.getCandidateID() != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(SELECT_TO_INSET_OR_UPDATE_QUERY,
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				candidateRepository = new CandidateRepositoryImpl(intern, SELECT_CANDIDATE_TO_INSERT_OR_UPDATE);
				candidateRepository.run();
				statement.setString(1, intern.getCandidateID());
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.updateString(2, intern.getMajor());
					resultSet.updateInt(3, intern.getSemester());
					resultSet.updateString(4, intern.getUniversityName());
					Thread.sleep(200);
					resultSet.updateRow();
					log.info("Update success");
				}
			} catch (SQLException | InterruptedException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				}
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error(() -> "Problem at InternRepository.saveOrUpdate() " + e.getMessage(), e);
			}

		} else {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(
							SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.substring(0,
									SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.lastIndexOf("W") - 1),
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				intern.setCandidateID(IDGenerator.scan(intern).toString());
				candidateRepository = new CandidateRepositoryImpl(intern, SELECT_CANDIDATE_TO_INSERT_OR_UPDATE
						.substring(0, SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.lastIndexOf("W") - 1));
				candidateRepository.run();
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.moveToInsertRow();
					resultSet.updateString(1, intern.getCandidateID());
					resultSet.updateString(2, intern.getMajor());
					resultSet.updateInt(3, intern.getSemester());
					resultSet.updateString(4, intern.getUniversityName());
					Thread.sleep(200);
					resultSet.insertRow();
					log.info("Insert success");
				}
			} catch (SQLException | InterruptedException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				}
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error(() -> "Problem at InternRepository.saveOrUpdate() " + e.getMessage(), e);
			}
		}
	}

	@Override
	public Intern getByID(String idCandidate) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_ID);) {
			statement.setString(1, idCandidate);
			try (ResultSet resultSet = statement.executeQuery();) {
				Intern intern = new Intern();
				while (resultSet.next()) {
					intern.setCandidateID(resultSet.getString("Candidate_ID"));
					intern.setFullName(resultSet.getString("Full_Name"));
					intern.setBirthDate(resultSet.getDate("Birth_Day"));
					intern.setPhone(resultSet.getString("Phone"));
					intern.setEmail(resultSet.getString("Email"));
					intern.setCandidateType(resultSet.getInt("Candidate_Type"));
					intern.setMajor(resultSet.getString("Major"));
					intern.setSemester(resultSet.getInt("Semester"));
					intern.setUniversityName(resultSet.getString("Education"));
				}
				return intern;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.getByID() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Intern getByName(String candidateName) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_NAME);) {
			statement.setString(1, candidateName);
			try (ResultSet resultSet = statement.executeQuery();) {
				Intern intern = new Intern();
				while (resultSet.next()) {
					intern.setCandidateID(resultSet.getString("Candidate_ID"));
					intern.setFullName(resultSet.getString("Full_Name"));
					intern.setBirthDate(resultSet.getDate("Birth_Day"));
					intern.setPhone(resultSet.getString("Phone"));
					intern.setEmail(resultSet.getString("Email"));
					intern.setCandidateType(resultSet.getInt("Candidate_Type"));
					intern.setMajor(resultSet.getString("Major"));
					intern.setSemester(resultSet.getInt("Semester"));
					intern.setUniversityName(resultSet.getString("Education"));
				}
				return intern;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.getByName() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Intern> getAll() throws SQLException {
		List<Intern> interns = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Intern intern = new Intern();
				intern.setCandidateID(resultSet.getString("Candidate_ID"));
				intern.setFullName(resultSet.getString("Full_Name"));
				intern.setBirthDate(resultSet.getDate("Birth_Day"));
				intern.setPhone(resultSet.getString("Phone"));
				intern.setEmail(resultSet.getString("Email"));
				intern.setCandidateType(resultSet.getInt("Candidate_Type"));
				intern.setMajor(resultSet.getString("Major"));
				intern.setSemester(resultSet.getInt("Semester"));
				intern.setUniversityName(resultSet.getString("Education"));
				interns.add(intern);
			}
			return interns;
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.getAll() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Intern> getByMajor(String majorName) throws SQLException {
		List<Intern> interns = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_MAJOR);) {
			statement.setString(1, majorName);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Intern intern = new Intern();
					intern.setCandidateID(resultSet.getString("Candidate_ID"));
					intern.setFullName(resultSet.getString("Full_Name"));
					intern.setBirthDate(resultSet.getDate("Birth_Day"));
					intern.setPhone(resultSet.getString("Phone"));
					intern.setEmail(resultSet.getString("Email"));
					intern.setCandidateType(resultSet.getInt("Candidate_Type"));
					intern.setMajor(resultSet.getString("Major"));
					intern.setSemester(resultSet.getInt("Semester"));
					intern.setUniversityName(resultSet.getString("Education"));
					interns.add(intern);
				}
			}
			return interns;
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.getByMajor() " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Intern> getByUniversity(String universityName) throws SQLException {
		List<Intern> interns = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_UNIVERSITY);) {

			statement.setString(1, universityName);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Intern intern = new Intern();
					intern.setCandidateID(resultSet.getString("Candidate_ID"));
					intern.setFullName(resultSet.getString("Full_Name"));
					intern.setBirthDate(resultSet.getDate("Birth_Day"));
					intern.setPhone(resultSet.getString("Phone"));
					intern.setEmail(resultSet.getString("Email"));
					intern.setCandidateType(resultSet.getInt("Candidate_Type"));
					intern.setMajor(resultSet.getString("Major"));
					intern.setSemester(resultSet.getInt("Semester"));
					intern.setUniversityName(resultSet.getString("Education"));
					interns.add(intern);
				}
			}
			return interns;
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at InternRepository.getByMajor() " + e.getMessage(), e);
			return null;
		}
	}
}