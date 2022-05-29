package com.system.fsoft.service;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.entity.Certificate;

public interface CertificateService {
    void saveOrUpdate(Certificate certificate) throws SQLException;

    void delete(Certificate certificate) throws SQLException;

    Certificate get(String certificateID) throws SQLException;

    List<Certificate> getCertificatesByCandidate(Candidate candidate) throws SQLException;
}