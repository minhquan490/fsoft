package com.system.fsoft.repository;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Certificate;

public interface CertificateRepository {
    void saveOrUpdate(Certificate certificate) throws SQLException;

    void delete(Certificate certificate) throws SQLException;

    Certificate get(String certificateID) throws SQLException;

    List<Certificate> getAllCertificates(String candidateID) throws SQLException;
}