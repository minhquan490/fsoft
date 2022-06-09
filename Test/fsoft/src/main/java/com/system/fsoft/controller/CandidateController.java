package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.service.CandidateService;
import com.system.fsoft.service.impl.CandidateServiceImpl;

public class CandidateController {

    private CandidateService candidateService = new CandidateServiceImpl();

    private CandidateController() {
    }

    public static CandidateController init() {
        return new CandidateController();
    }

    public List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException {
        return candidateService.getAllCandidateAndTheirCertidicate();
    }

    public List<Candidate> getAll() throws SQLException {
        return candidateService.getAll();
    }
}