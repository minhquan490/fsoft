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
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.repository.CertificateRepository;
import com.system.fsoft.utils.IDGenerator;

public class CertificateRepositoryImpl implements CertificateRepository {

	private final Logger log = LogManager.getLogger(CertificateRepository.class.getName());

	private static final String INSERT_QUERY = "INSERT INTO Certificate(Certificate_ID, Certificate_Name, Certificate_Rank, Certificate_Date, Candidate_ID) VALUES (?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE Certificate SET Certificate_Name = ?, Certificate_Rank = ?, Certificate_Date = ? WHERE Certificate_ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM Certificate WHERE Certificate_ID = ?";
	private static final String SELECT_QUERY = "SELECT ce.Certificate_Name, ce.Certificate_Rank, ce.Certificate_Date FROM Certificate ce WHERE ce.Certificate_ID = ?";
	private static final String SELECT_ALL_CERTIFICATES = "SELECT * FROM Certificate ce WHERE ce.Candidate_ID = ?";

	@Override
	public void saveOrUpdate(Certificate certificate) throws SQLException {
		if (certificate.getCertificatedID() != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
				statement.setString(1, certificate.getCertificatedName());
				statement.setString(2, certificate.getCertificatedRank());
				statement.setDate(3, certificate.getCertificatedDate());
				statement.setString(4, certificate.getCandidateID());
				if (statement.executeUpdate() == 1) {
					log.info("update success");
				} else {
					log.debug("Something wrong at CertificateRepository.saveOrUpdate()");
				}
			} catch (SQLException e) {
				log.error(() -> "Problem at CertificateRepository.saveOrUpdate(): " + e.getMessage(), e);
			}
		} else {
			certificate.setCertificatedID(IDGenerator.scan(certificate).toString());
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {
				statement.setString(1, certificate.getCertificatedID());
				statement.setString(2, certificate.getCertificatedName());
				statement.setString(3, certificate.getCertificatedRank());
				statement.setDate(4, certificate.getCertificatedDate());
				statement.setString(5, certificate.getCandidateID());
				if (statement.executeUpdate() == 1) {
					log.info("Save success");
				} else {
					log.debug("Something wrong at CertificateRepository.saveOrUpdate()");
				}
			} catch (Exception e) {
				log.error(() -> "Problem at CertificateRepository.saveOrUpdate(): " + e.getMessage(), e);
			}
		}
	}

	@Override
	public void delete(Certificate certificate) throws SQLException {
		if (this.get(certificate.getCertificatedID()) != null) {
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
				statement.setString(1, certificate.getCertificatedID());
				if (statement.executeUpdate() == 1) {
					log.info("Delete success");
				} else {
					log.debug("Something wrong at CertificateRepository.delete()");
				}
			} catch (Exception e) {
				System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
				log.error(() -> "Problem at CertificateRepository.delete(): " + e.getMessage(), e);
			}
		} else {
			System.out.println("Certificatie not exist");
		}
	}

	@Override
	public Certificate get(String certificateID) throws SQLException {
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);) {
			statement.setString(1, certificateID);
			try (ResultSet resultSet = statement.executeQuery();) {
				Certificate certificate = new Certificate();
				while (resultSet.next()) {
					certificate.setCertificatedName(resultSet.getString("Certificate_Name"));
					certificate.setCertificatedRank(resultSet.getString("Certificate_Rank"));
					certificate.setCertificatedDate(resultSet.getDate("Certificate_Date"));
				}
				return certificate;
			}
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at CertificateRepository.get(): " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Certificate> getCertificatesByCandidate(Candidate candidate) throws SQLException {
		List<Certificate> certificates = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CERTIFICATES);) {
			statement.setString(1, candidate.getCandidateID());
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Certificate certificate = new Certificate();
					certificate.setCertificatedID(resultSet.getString("Certificate_ID"));
					certificate.setCertificatedName(resultSet.getString("Certificate_Name"));
					certificate.setCertificatedRank(resultSet.getString("Certificate_Rank"));
					certificate.setCertificatedDate(resultSet.getDate("Certificate_Date"));
					certificates.add(certificate);
				}
			}
			return certificates;
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			log.error(() -> "Problem at CertificateRepository.getCertificatesByCandidate(): " + e.getMessage(), e);
			return null;
		}
	}
}