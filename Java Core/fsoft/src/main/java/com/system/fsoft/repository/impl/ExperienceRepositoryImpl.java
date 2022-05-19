package com.system.fsoft.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.system.fsoft.config.DatabaseConfig;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.repository.ExperienceRepository;

public class ExperienceRepositoryImpl implements ExperienceRepository {

    protected Connection connection = null;
    protected PreparedStatement statement = null;
    protected ResultSet resultSet = null;

    private static final String INSERT_QUERY = "";

    @Override
    public void save(Experience experience) {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, experience.getCandidateID());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void edit(Experience experience) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Experience experience) {
        // TODO Auto-generated method stub

    }

    @Override
    public Experience getByID(String idCandidate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Experience getByName(String candidateName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Experience> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Experience> getByExperience(int expInYear) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Experience> getByAdvancedSkills(int proSkill) {
        // TODO Auto-generated method stub
        return null;
    }
}