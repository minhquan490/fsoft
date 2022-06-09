package com.system.fsoft.service;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;

public interface CandidateService {

    List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException;

    List<Candidate> getAll() throws SQLException;
}