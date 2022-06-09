package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.service.CandidateService;
import com.system.fsoft.service.impl.CandidateServiceImpl;

public class CandidateController {

	private static Logger log = LogManager.getLogger(CandidateController.class.getName());

	private CandidateService candidateService = new CandidateServiceImpl();

	private CandidateController() {
	}

	public static CandidateController init() {
		log.info("Candidate controller created");
		return new CandidateController();
	}

	public List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException {
		return candidateService.getAllCandidateAndTheirCertidicate();
	}

	public List<Candidate> getAll() throws SQLException {
		return candidateService.getAll();
	}
}