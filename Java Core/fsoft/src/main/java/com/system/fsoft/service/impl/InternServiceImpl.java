package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Intern;
import com.system.fsoft.repository.InternRepository;
import com.system.fsoft.repository.impl.InternRepositoryImpl;
import com.system.fsoft.service.InternService;

public class InternServiceImpl implements InternService {

    private InternRepository repository = new InternRepositoryImpl();

    @Override
    public void save(Intern intern) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void edit(Intern intern) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String candidateName) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveOrUpdate(Intern intern) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Intern getByID(String idCandidate) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Intern getByName(String candidateName) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Intern> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Intern> getByMajor(String majorName) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Intern> getByUniversity(String universityName) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}