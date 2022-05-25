package com.system.fsoft.repository;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Intern;

public interface InternRepository {
    void save(Intern intern) throws SQLException;

    void edit(Intern intern) throws SQLException;

    void delete(Intern intern) throws SQLException;

    void saveOrUpdate(Intern intern) throws SQLException;

    int countInDatabase() throws SQLException;

    Intern getByID(String idCandidate) throws SQLException;

    Intern getByName(String candidateName) throws SQLException;

    List<Intern> getAll() throws SQLException;

    List<Intern> getByMajor(String majorName) throws SQLException;

    List<Intern> getByUniversity(String universityName) throws SQLException;
}