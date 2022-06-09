package com.system.fsoft.repository;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;

public interface CandidateRepository extends Runnable {
    void save() throws SQLException;

    void edit() throws SQLException;

    void saveViaResultSet() throws SQLException;

    void editViaResulSet() throws SQLException;

    List<Candidate> getAllCandidateAndTheirCertidicate() throws SQLException;

    List<Candidate> getAll() throws SQLException;
}