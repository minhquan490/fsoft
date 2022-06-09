package com.system.fsoft.repository.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.fsoft.config.database.DataSource;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.repository.CandidateRepository;
import com.system.fsoft.repository.ExperienceRepository;
import com.system.fsoft.utils.IDGenerator;

public class ExperienceRepositoryImpl implements ExperienceRepository {

	private CandidateRepository candidateRepository;

	private static final String INSERT_QUERY = "INSERT INTO Experience(Candidate_ID, Exp_In_Year, Pro_Skill) VALUES (?,?,?)";
	private static final String INSERT_CANDIDATE = "INSERT INTO Candidate(Candidate_ID, Full_Name, Birth_Day, Phone, Email, Candidate_Type) VALUES (?,?,?,?,?,?)";

	private static final String DELETE_QUERY = "DELETE FROM Candidate WHERE Candidate_ID = ?";

	private static final String UPDATE_QUERY = "UPDATE Experience SET Exp_In_Year = ?, Pro_Skill = ? WHERE Candidate_ID = ?";
	private static final String UPDATE_CANDIDATE = "UPDATE Candidate SET Full_Name = ?, Birth_Day = ?, Phone = ?, Email = ?, Candidate_Type = ? WHERE Candidate_ID = ?";

	private static final String SELECT_TO_INSET_OR_UPDATE_QUERY = "SELECT * FROM Experience e WHERE e.Candidate_ID = ?";
	private static final String SELECT_CANDIDATE_TO_INSERT_OR_UPDATE = "SELECT * FROM Candidate c WHERE c.Candidate_ID = ?";

	private static final String SELECT_QUERY_BY_NAME = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Exp_In_Year, e.Pro_Skill FROM Experience e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_Name = ?";
	private static final String SELECT_QUERY_BY_ID = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Exp_In_Year, e.Pro_Skill FROM Experience e"
			+ " RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + " WHERE c.Candidate_ID = ?";
	private static final String SELECT_ALL = "SELECT * FROM Experience e LEFT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID ORDER BY c.Full_Name";
	private static final String SELECT_BY_EXP = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Pro_Skill FROM Candidate c LEFT JOIN Experience e WHERE e.Exp_In_Year = ? ORDER BY c.Full_Name";
	private static final String SELECT_BY_SKILL = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, c.Candidate_Type, e.Exp_In_Year FROM Candidate c LEFT JOIN Experience e WHERE e.Pro_Skill = ? ORDER BY c.Full_Name";

	public ExperienceRepositoryImpl() {
	}

	@Override
	public void save(Experience experience) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {
			experience.setCandidateID(IDGenerator.scan(experience).toString());
			candidateRepository = new CandidateRepositoryImpl(experience, INSERT_CANDIDATE);
			candidateRepository.run();
			statement.setString(1, experience.getCandidateID());
			statement.setBigDecimal(2, new BigDecimal(String.valueOf(experience.getExpInYear())));
			statement.setInt(3, experience.getProSkill());
			Thread.sleep(200);
			statement.executeUpdate();
			System.out.println("Insert success");
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Experience experience) throws SQLException {
		if (this.getByID(experience.getCandidateID()) != null) {
			candidateRepository = new CandidateRepositoryImpl(experience, UPDATE_CANDIDATE);
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
				candidateRepository.run();
				statement.setBigDecimal(1, new BigDecimal(String.valueOf(experience.getExpInYear())));
				statement.setInt(2, experience.getProSkill());
				statement.setString(3, experience.getCandidateID());
				Thread.sleep(200);
				statement.executeUpdate();
				System.out.println("Update success");
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveOrUpdate(Experience experience) throws SQLException {

		if (experience.getCandidateID() != null) {
			candidateRepository = new CandidateRepositoryImpl(experience, SELECT_CANDIDATE_TO_INSERT_OR_UPDATE);
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(SELECT_TO_INSET_OR_UPDATE_QUERY,
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				candidateRepository.run();
				statement.setString(1, experience.getCandidateID());
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.updateBigDecimal(2, new BigDecimal(String.valueOf(experience.getExpInYear())));
					resultSet.updateInt(3, experience.getProSkill());
					Thread.sleep(200);
					resultSet.updateRow();
					System.out.println("Update success");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Save failure");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Save failure");
			}

		} else {
			experience.setCandidateID(IDGenerator.scan(experience).toString());
			String selectInsertQuery = SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.substring(0,
					SELECT_CANDIDATE_TO_INSERT_OR_UPDATE.lastIndexOf("W"));
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectInsertQuery,
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
				candidateRepository = new CandidateRepositoryImpl(experience, selectInsertQuery);
				candidateRepository.run();
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.moveToInsertRow();
					statement.setString(1, experience.getCandidateID());
					resultSet.updateBigDecimal(2, new BigDecimal(String.valueOf(experience.getExpInYear())));
					resultSet.updateInt(3, experience.getProSkill());
					Thread.sleep(200);
					resultSet.insertRow();
					System.out.println("Insert success");
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}

		}
	}

	@Override
	public void delete(Experience experience) throws SQLException {
		if (this.getByID(experience.getCandidateID()) != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
				statement.setString(1, experience.getCandidateID());
				if (statement.executeUpdate() == 1) {
					System.out.println("Delete success");
				}
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				e.printStackTrace();
			}
		} else {
			System.out.println("Candidate not exist");
		}
	}

	@Override
	public Experience getByID(String idCandidate) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_ID);) {
			statement.setString(1, idCandidate);
			try (ResultSet resultSet = statement.executeQuery();) {
				Experience experience = new Experience();
				while (resultSet.next()) {
					experience.setCandidateID(resultSet.getString("Candidate_ID"));
					experience.setFullName(resultSet.getString("Full_Name"));
					experience.setBirthDate(resultSet.getDate("Birth_Day"));
					experience.setPhone(resultSet.getString("Phone"));
					experience.setEmail(resultSet.getString("Email"));
					experience.setCandidateType(resultSet.getInt("Candidate_Type"));
					experience.setExpInYear(Float.valueOf(resultSet.getBigDecimal("Exp_In_Year").toString()));
					experience.setProSkill(resultSet.getInt("Pro_Skill"));
				}
				return experience;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Experience getByName(String candidateName) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_NAME);) {
			statement.setString(1, candidateName);
			try (ResultSet resultSet = statement.executeQuery();) {
				Experience experience = new Experience();
				while (resultSet.next()) {
					experience.setCandidateID(resultSet.getString("Candidate_ID"));
					experience.setFullName(resultSet.getString("Full_Name"));
					experience.setBirthDate(resultSet.getDate("Birth_Day"));
					experience.setPhone(resultSet.getString("Phone"));
					experience.setEmail(resultSet.getString("Email"));
					experience.setCandidateType(resultSet.getInt("Candidate_Type"));
					experience.setExpInYear(Float.valueOf(resultSet.getBigDecimal("Exp_In_Year").toString()));
					experience.setProSkill(resultSet.getInt("Pro_Skill"));
				}
				return experience;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Experience> getAll() throws SQLException {
		List<Experience> experiences = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Experience experience = new Experience();
				experience.setCandidateID(resultSet.getString("Candidate_ID"));
				experience.setFullName(resultSet.getString("Full_Name"));
				experience.setBirthDate(resultSet.getDate("Birth_Day"));
				experience.setPhone(resultSet.getString("Phone"));
				experience.setEmail(resultSet.getString("Email"));
				experience.setCandidateType(resultSet.getInt("Candidate_Type"));
				experience.setExpInYear(Float.valueOf(resultSet.getBigDecimal("Exp_In_Year").toString()));
				experience.setProSkill(resultSet.getInt("Pro_Skill"));
				experiences.add(experience);
			}
			return experiences;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Experience> getByExperience(float expInYear) throws SQLException {
		List<Experience> experiences = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_EXP);) {
			statement.setBigDecimal(1, new BigDecimal(String.valueOf(expInYear)));
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Experience experience = new Experience();
					experience.setCandidateID(resultSet.getString("Candidate_ID"));
					experience.setFullName(resultSet.getString("Full_Name"));
					experience.setBirthDate(resultSet.getDate("Birth_Day"));
					experience.setPhone(resultSet.getString("Phone"));
					experience.setEmail(resultSet.getString("Email"));
					experience.setCandidateType(resultSet.getInt("Candidate_Type"));
					experience.setExpInYear(Float.valueOf(resultSet.getBigDecimal("Exp_In_Year").toString()));
					experience.setProSkill(resultSet.getInt("Pro_Skill"));
					experiences.add(experience);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return experiences;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Experience> getByAdvancedSkills(int proSkill) throws SQLException {
		List<Experience> experiences = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_SKILL);) {
			statement.setInt(1, proSkill);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Experience experience = new Experience();
					experience.setCandidateID(resultSet.getString("Candidate_ID"));
					experience.setFullName(resultSet.getString("Full_Name"));
					experience.setBirthDate(resultSet.getDate("Birth_Day"));
					experience.setPhone(resultSet.getString("Phone"));
					experience.setEmail(resultSet.getString("Email"));
					experience.setCandidateType(resultSet.getInt("Candidate_Type"));
					experience.setExpInYear(Float.valueOf(resultSet.getBigDecimal("Exp_In_Year").toString()));
					experience.setProSkill(resultSet.getInt("Pro_Skill"));
					experiences.add(experience);
				}
			} catch (Exception e) {
				return null;
			}
			return experiences;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
			return null;
		}
	}
}