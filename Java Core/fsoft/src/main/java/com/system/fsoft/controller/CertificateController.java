package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.service.CertificateService;
import com.system.fsoft.service.impl.CertificateServiceImpl;

public class CertificateController {

	private static Logger log = LogManager.getLogger(CertificateController.class.getName());

	private CertificateService service = new CertificateServiceImpl();

	private CertificateController() {
	}

	public static CertificateController init() {
		log.info("Certificate controller created");
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