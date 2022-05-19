package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.system.fsoft.config.DatabaseConfig;
import com.system.fsoft.entity.Candidate;
import com.system.fsoft.repository.CandidateRepository;

public class CandidateRepositoryImpl implements CandidateRepository {

    private Candidate candidate;
    private String query;

    private Connection connection = null;
    private PreparedStatement statement = null;

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
            System.out.println("Server Internal Error");
        } finally {
            statement.close();
            connection.commit();
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
            System.out.println("Server Internal Error");
        } finally {
            statement.close();
            connection.commit();
        }
    }

    @Override
    public void run() {
        if (query.contains("UPDATE")) {
            try {
                this.edit();
            } catch (Exception e) {
                System.out.println("Server Internal Error");
            }
        }
        if (query.contains("INSERT INTO")) {
            try {
                this.save();
            } catch (Exception e) {
                System.out.println("Server Internal Error");
            }
        }
    }

}