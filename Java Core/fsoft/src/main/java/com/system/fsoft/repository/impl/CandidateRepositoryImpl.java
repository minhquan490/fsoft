package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.fsoft.config.DatabaseConfig;
import com.system.fsoft.entity.Candidate;
import com.system.fsoft.repository.CandidateRepository;

public class CandidateRepositoryImpl implements CandidateRepository {

    private Candidate candidate;
    private String query;

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public CandidateRepositoryImpl(Candidate candidate, String query) {
        this.candidate = candidate;
        this.query = query;
    }

    @Override
    public void save() throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, this.candidate.getCandidateID());
            statement.setString(2, this.candidate.getFullName());
            statement.setDate(3, this.candidate.getBirthDate());
            statement.setString(4, this.candidate.getPhone());
            statement.setString(5, this.candidate.getEmail());
            statement.setInt(6, this.candidate.getCandidateType());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
        } finally {
            if (connection != null) {
                statement.close();
                connection.commit();
            }
        }
    }

    @Override
    public void edit() throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, this.candidate.getFullName());
            statement.setDate(2, this.candidate.getBirthDate());
            statement.setString(3, this.candidate.getPhone());
            statement.setString(4, this.candidate.getEmail());
            statement.setInt(5, this.candidate.getCandidateType());
            statement.setString(6, this.candidate.getCandidateID());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
        } finally {
            if (connection != null) {
                statement.close();
                connection.commit();
            }
        }
    }

    @Override
    public void run() {
        if (query.contains("UPDATE")) {
            try {
                this.edit();
            } catch (Exception e) {
                System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
            }
        }
        if (query.contains("INSERT INTO")) {
            try {
                this.save();
            } catch (Exception e) {
                System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
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
            }
        }
    }

    @Override
    public void saveViaResultSet() throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, candidate.getCandidateID());
            resultSet = statement.executeQuery();
            resultSet.moveToInsertRow();
            resultSet.updateString(2, candidate.getFullName());
            resultSet.updateDate(3, candidate.getBirthDate());
            resultSet.updateString(4, candidate.getPhone());
            resultSet.updateString(5, candidate.getEmail());
            resultSet.updateInt(6, candidate.getCandidateType());
            resultSet.insertRow();
            System.out.println("Insert success");
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
        } finally {
            if (connection != null) {
                resultSet.close();
                statement.close();
                connection.commit();
            }
        }
    }

    @Override
    public void editViaResulSet() throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, candidate.getCandidateID());
            resultSet = statement.executeQuery();
            resultSet.updateString(2, candidate.getFullName());
            resultSet.updateDate(3, candidate.getBirthDate());
            resultSet.updateString(4, candidate.getPhone());
            resultSet.updateString(5, candidate.getEmail());
            resultSet.updateInt(6, candidate.getCandidateType());
            resultSet.updateRow();
            System.out.println("Update success");
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
        } finally {
            if (connection != null) {
                resultSet.close();
                statement.close();
                connection.commit();
            }
        }
    }

}