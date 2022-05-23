package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.fsoft.config.DatabaseConfig;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.repository.CertificateRepository;

public class CertificateRepositoryImpl implements CertificateRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    private static final String INSERT_QUERY = "INSERT INTO Certificate(Certificate_ID, Certificate_Name, Certificate_Rank, Certificate_Day, Candidate_ID) VALUES (?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE Certificate SET Certificate_Name = ?, Certificate_Rank = ?, Certificate_Day = ? WHERE Certificate_ID = ?";

    private static final String DELETE_QUERY = "DELETE FROM Certificate WHERE Certificate_ID = ?";

    private static final String SELECT_QUERY = "SELECT ce.Certificate_Name, ce.Certificate_Rank, ce.Certificate_Day FROM Certificate ce WHERE ce.Certificate_ID = ?";
    private static final String SELECT_ALL_CERTIFICATES = "SELECT * FROM Certificate ce WHERE ce.Certificate_ID = ?";

    @Override
    public void saveOrUpdate(Certificate certificate) throws SQLException {
        try {
            if (this.get(certificate.getCandidateID()) != null) {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(INSERT_QUERY);
                statement.setString(1, certificate.getCertificatedID());
                statement.setString(2, certificate.getCertificatedName());
                statement.setString(3, certificate.getCertificatedRank());
                statement.setDate(4, certificate.getCertificatedDate());
                statement.setString(5, certificate.getCandidateID());
                statement.execute();
            } else {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(UPDATE_QUERY);
                statement.setString(1, certificate.getCertificatedName());
                statement.setString(2, certificate.getCertificatedRank());
                statement.setDate(3, certificate.getCertificatedDate());
                statement.setString(4, certificate.getCandidateID());
                statement.execute();
            }
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
    public void delete(Certificate certificate) throws SQLException {
        if (this.get(certificate.getCertificatedID()) != null) {
            try {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(DELETE_QUERY);
                statement.setString(1, certificate.getCertificatedID());
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
            } finally {
                if (connection != null) {
                    statement.close();
                    connection.commit();
                }
            }
        } else {
            System.out.println("Certificatie not exist");
        }
    }

    @Override
    public Certificate get(String certificateID) throws SQLException {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_QUERY);
            statement.setString(1, certificateID);
            resultSet = statement.executeQuery();
            Certificate certificate = new Certificate();
            certificate.setCertificatedName(resultSet.getString("Certificate_Name"));
            certificate.setCertificatedRank(resultSet.getString("Certificate_Rank"));
            certificate.setCertificatedDate(resultSet.getDate("Certificate_Day"));
            return certificate;
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
            return null;
        } finally {
            if (connection != null) {
                resultSet.close();
                statement.close();
                connection.commit();
            }
        }
    }

    @Override
    public List<Certificate> getAllCertificates(String candidateID) throws SQLException {
        List<Certificate> certificates = new ArrayList<>();
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_CERTIFICATES);
            statement.setString(1, candidateID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Certificate certificate = new Certificate();
                certificate.setCertificatedID(resultSet.getString("Certificate_ID"));
                certificate.setCertificatedName(resultSet.getString("Certificate_Name"));
                certificate.setCertificatedRank(resultSet.getString("Certificate_Rank"));
                certificate.setCertificatedDate(resultSet.getDate("Certificate_Day"));
                certificates.add(certificate);
            }
            return certificates;
        } catch (Exception e) {
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
            return null;
        } finally {
            if (connection != null) {
                resultSet.close();
                statement.close();
                connection.commit();
            }
        }
    }
}