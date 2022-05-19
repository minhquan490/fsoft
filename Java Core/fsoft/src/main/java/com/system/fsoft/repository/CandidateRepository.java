package com.system.fsoft.repository;

import java.sql.SQLException;

public interface CandidateRepository extends Runnable {
    void save() throws SQLException;

    void edit() throws SQLException;
}