package com.system.fsoft.repository;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Fresher;

public interface FresherRepository {
    void save(Fresher fresher) throws SQLException;

    void edit(Fresher fresher) throws SQLException;

    void delete(String candidateName) throws SQLException;

    void saveOrUpdate(Fresher fresher) throws SQLException;

    int countInDatabase() throws SQLException;

    Fresher getByID(String idCandidate) throws SQLException;

    Fresher getByName(String candidateName) throws SQLException;

    List<Fresher> getAll() throws SQLException;

    List<Fresher> getFreshersByRank(String rank) throws SQLException;

    List<Fresher> getFreshersByUniversity(String universityName) throws SQLException;
}