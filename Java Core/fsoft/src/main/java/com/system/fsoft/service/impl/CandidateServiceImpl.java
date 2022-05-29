package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.repository.CandidateRepository;
import com.system.fsoft.repository.impl.CandidateRepositoryImpl;
import com.system.fsoft.service.CandidateService;

public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository = new CandidateRepositoryImpl();

    @Override
    public List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException {
        return candidateRepository.getAllCandidateAndTheirCertidicate();
    }

}