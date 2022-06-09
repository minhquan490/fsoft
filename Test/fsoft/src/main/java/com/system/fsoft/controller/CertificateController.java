package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.service.CertificateService;
import com.system.fsoft.service.impl.CertificateServiceImpl;

public class CertificateController {

    private CertificateService service = new CertificateServiceImpl();

    private CertificateController() {
    }

    public static CertificateController init() {
        return new CertificateController();
    }

    public void saveOrUpdate(Certificate certificate) throws SQLException {
        service.saveOrUpdate(certificate);
    }

    public void delete(Certificate certificate) throws SQLException {
        service.delete(certificate);
    }

    public List<Certificate> getCertificatesByCandidate(Candidate candidate) throws SQLException {
        return service.getCertificatesByCandidate(candidate);
    }
}