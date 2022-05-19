package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.fsoft.config.DatabaseConfig;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.repository.CandidateRepository;
import com.system.fsoft.repository.ExperienceRepository;

public class ExperienceRepositoryImpl implements ExperienceRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private CandidateRepository candidateRepository;

    private static final String INSERT_QUERY = "INSERT INTO Experience(Candidate_ID, Exp_In_Year, Pro_Skill) VALUES (?,?,?)";
    private static final String INSERT_CANDIDATE = "INSERT INTO Candidate(Candidate_ID, Full_Name, Birth_Day, Phone, Email, Candidate_Type) VALUES (?,?,?,?,?,?)";

    private static final String DELETE_QUERY = "DELETE FROM Candidate c WHERE c.Full_Name = ?";

    private static final String UPDATE_QUERY = "UPDATE Experience SET Exp_In_Year = ?, Pro_Skill = ? WHERE Candidate_ID = ?";
    private static final String UPDATE_CANDIDATE = "UPDATE Candidate SET Full_Name = ?, Birth_Day = ?, Phone = ?, Email = ? WHERE Candidate_ID = ?";

    private static final String SELECT_QUERY_BY_NAME = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, e.Exp_In_Year, e.Pro_Skill FROM Experience e"
            + "RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + "WHERE c.Candidate_Name = ?";
    private static final String SELECT_QUERY_BY_ID = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, e.Exp_In_Year, e.Pro_Skill FROM Experience e"
            + "RIGHT JOIN Candidate c ON c.Candidate_ID = e.Candidate_ID" + "WHERE c.Candidate_ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Candidate c FULL JOIN Experience e ON c.Candidate_ID = e.Candidate_ID ORDER BY c.Full_Name";
    private static final String SELECT_BY_EXP = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, e.Pro_Skill FROM Candidate c LEFT JOIN Experience e WHERE e.Exp_In_Year = ? ORDER BY c.Full_Name";
    private static final String SELECT_BY_SKILL = "SELECT c.Candidate_ID, c.Full_Name, c.Birth_Day, c.Phone, c.Email, e.Exp_In_Year FROM Candidate c LEFT JOIN Experience e WHERE e.Pro_Skill = ? ORDER BY c.Full_Name";

    public ExperienceRepositoryImpl() {
    }

    @Override
    public void save(Experience experience) throws SQLException {
        try {
            candidateRepository = new CandidateRepositoryImpl(experience, INSERT_CANDIDATE);
            connection = DatabaseConfig.getConnection();
            candidateRepository.run();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, experience.getCandidateID());
            statement.setInt(2, experience.getExpInYear());
            statement.setInt(3, experience.getProSkill());
            Thread.sleep(200);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Server Internal Error");
        } finally {
            statement.close();
            connection.commit();
        }
    }

    @Override
    public void edit(Experience experience) throws SQLException {
        if (this.getByID(experience.getCandidateID()) != null) {
            candidateRepository = new CandidateRepositoryImpl(experience, UPDATE_CANDIDATE);
            try {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(UPDATE_QUERY);
                candidateRepository.run();
                statement.setInt(1, experience.getExpInYear());
                statement.setInt(2, experience.getProSkill());
                statement.setString(3, experience.getCandidateID());
                Thread.sleep(200);
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Server Internal Error");
            } finally {
                statement.close();
                connection.commit();
            }
        }
    }

    @Override
    public void delete(String candidateName) throws SQLException {
        Experience experience = this.getByName(candidateName);
        if (experience.getFullName() != null) {
            try {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(DELETE_QUERY);
                statement.setString(1, candidateName);
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Server Internal Error");
            } finally {
                statement.close();
                connection.commit();
            }
        } else {
            System.out.println("Candidate not exist");
        }
    }

    @Override
    public Experience getByID(String idCandidate) throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_QUERY_BY_ID);
            statement.setString(1, idCandidate);
            resultSet = statement.executeQuery();
            Experience experience = new Experience();
            experience.setCandidateID(resultSet.getString("Candidate_ID"));
            experience.setFullName(resultSet.getString("Full_Name"));
            experience.setBirthDate(resultSet.getDate("Birth_Day"));
            experience.setPhone(resultSet.getString("Phone"));
            experience.setEmail(resultSet.getString("Email"));
            experience.setCandidateType(resultSet.getInt("Candidate_Type"));
            experience.setExpInYear(resultSet.getInt("Exp_In_Year"));
            experience.setProSkill(resultSet.getInt("Pro_Skill"));
            return experience;
        } catch (Exception e) {
            System.out.println("Server Internal Error");
            return null;
        } finally {
            resultSet.close();
            statement.close();
            connection.commit();
        }
    }

    @Override
    public Experience getByName(String candidateName) throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_QUERY_BY_NAME);
            statement.setString(1, candidateName);
            resultSet = statement.executeQuery();
            Experience experience = new Experience();
            experience.setCandidateID(resultSet.getString("Candidate_ID"));
            experience.setFullName(resultSet.getString("Full_Name"));
            experience.setBirthDate(resultSet.getDate("Birth_Day"));
            experience.setPhone(resultSet.getString("Phone"));
            experience.setEmail(resultSet.getString("Email"));
            experience.setCandidateType(resultSet.getInt("Candidate_Type"));
            experience.setExpInYear(resultSet.getInt("Exp_In_Year"));
            experience.setProSkill(resultSet.getInt("Pro_Skill"));
            return experience;
        } catch (Exception e) {
            System.out.println("Server Internal Error");
            return null;
        } finally {
            resultSet.close();
            statement.close();
            connection.commit();
        }
    }

    @Override
    public List<Experience> getAll() throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setCandidateID(resultSet.getString("Candidate_ID"));
                experience.setFullName(resultSet.getString("Full_Name"));
                experience.setBirthDate(resultSet.getDate("Birth_Day"));
                experience.setPhone(resultSet.getString("Phone"));
                experience.setEmail(resultSet.getString("Email"));
                experience.setCandidateType(resultSet.getInt("Candidate_Type"));
                experience.setExpInYear(resultSet.getInt("Exp_In_Year"));
                experience.setProSkill(resultSet.getInt("Pro_Skill"));
                experiences.add(experience);
            }
            return experiences;
        } catch (Exception e) {
            System.out.println("Server Internal Error");
            return null;
        } finally {
            resultSet.close();
            statement.close();
            connection.commit();
        }
    }

    @Override
    public List<Experience> getByExperience(int expInYear) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_BY_EXP);
            statement.setInt(1, expInYear);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setCandidateID(resultSet.getString("Candidate_ID"));
                experience.setFullName(resultSet.getString("Full_Name"));
                experience.setBirthDate(resultSet.getDate("Birth_Day"));
                experience.setPhone(resultSet.getString("Phone"));
                experience.setEmail(resultSet.getString("Email"));
                experience.setCandidateType(resultSet.getInt("Candidate_Type"));
                experience.setExpInYear(resultSet.getInt("Exp_In_Year"));
                experience.setProSkill(resultSet.getInt("Pro_Skill"));
                experiences.add(experience);
            }
            return experiences;
        } catch (Exception e) {
            System.out.println("Server Internal Error");
            return null;
        } finally {
            resultSet.close();
            statement.close();
            connection.commit();
        }
    }

    @Override
    public List<Experience> getByAdvancedSkills(int proSkill) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_BY_SKILL);
            statement.setInt(1, proSkill);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setCandidateID(resultSet.getString("Candidate_ID"));
                experience.setFullName(resultSet.getString("Full_Name"));
                experience.setBirthDate(resultSet.getDate("Birth_Day"));
                experience.setPhone(resultSet.getString("Phone"));
                experience.setEmail(resultSet.getString("Email"));
                experience.setCandidateType(resultSet.getInt("Candidate_Type"));
                experience.setExpInYear(resultSet.getInt("Exp_In_Year"));
                experience.setProSkill(resultSet.getInt("Pro_Skill"));
                experiences.add(experience);
            }
            return experiences;
        } catch (Exception e) {
            System.out.println("Server Internal Error");
            return null;
        } finally {
            resultSet.close();
            statement.close();
            connection.commit();
        }
    }
}