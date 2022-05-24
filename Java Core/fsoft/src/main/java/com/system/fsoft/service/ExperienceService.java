package com.system.fsoft.service;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Experience;

public interface ExperienceService {
    void save(Experience experience) throws SQLException;

    void edit(Experience experience) throws SQLException;

    void delete(Experience experience) throws SQLException;

    void saveOrUpdate(Experience experience) throws SQLException;

    Experience getByID(String idCandidate) throws SQLException;

    Experience getByName(String candidateName) throws SQLException;

    List<Experience> getAll() throws SQLException;

    List<Experience> getByExperience(int expInYear) throws SQLException;

    List<Experience> getByAdvancedSkills(int proSkill) throws SQLException;
}