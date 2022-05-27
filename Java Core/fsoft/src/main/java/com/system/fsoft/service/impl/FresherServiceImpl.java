package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Fresher;
import com.system.fsoft.exception.CandidateNotFoundException;
import com.system.fsoft.repository.FresherRepository;
import com.system.fsoft.repository.impl.FresherRepositoryImpl;
import com.system.fsoft.service.FresherService;

public class FresherServiceImpl implements FresherService {

    private FresherRepository repository = new FresherRepositoryImpl();

    @Override
    public void save(Fresher fresher) throws SQLException {
        repository.save(fresher);
    }

    @Override
    public void edit(Fresher fresher) throws SQLException {
        Fresher oldFresher = this.getByID(fresher.getCandidateID());
        if (oldFresher == null) {
            throw new CandidateNotFoundException(
                    "andidate is not exist or deleted, reload Candidate set then try again");
        }
        oldFresher.setFullName(fresher.getFullName());
        oldFresher.setBirthDate(fresher.getBirthDate());
        oldFresher.setPhone(fresher.getPhone());
        oldFresher.setEmail(fresher.getEmail());
        oldFresher.setCandidateType(fresher.getCandidateType());
        oldFresher.setGraduationDate(fresher.getGraduationDate());
        oldFresher.setGraduationRank(fresher.getGraduationRank());
        oldFresher.setEducation(fresher.getEducation());
        repository.edit(fresher);
    }

    @Override
    public void delete(Fresher fresher) throws SQLException {
        repository.delete(fresher);
    }

    @Override
    public void saveOrUpdate(Fresher fresher) throws SQLException {
        Fresher oldFresher = this.getByID(fresher.getCandidateID());
        if (oldFresher == null) {
            throw new CandidateNotFoundException(
                    "andidate is not exist or deleted, reload Candidate set then try again");
        }
        oldFresher.setFullName(fresher.getFullName());
        oldFresher.setBirthDate(fresher.getBirthDate());
        oldFresher.setPhone(fresher.getPhone());
        oldFresher.setEmail(fresher.getEmail());
        oldFresher.setCandidateType(fresher.getCandidateType());
        oldFresher.setGraduationDate(fresher.getGraduationDate());
        oldFresher.setGraduationRank(fresher.getGraduationRank());
        oldFresher.setEducation(fresher.getEducation());
        repository.saveOrUpdate(fresher);
    }

    @Override
    public Fresher getByID(String idCandidate) throws SQLException {
        return repository.getByID(idCandidate);
    }

    @Override
    public Fresher getByName(String candidateName) throws SQLException {
        return repository.getByName(candidateName);
    }

    @Override
    public List<Fresher> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public List<Fresher> getFreshersByRank(String rank) throws SQLException {
        return repository.getFreshersByRank(rank);
    }

    @Override
    public List<Fresher> getFreshersByUniversity(String universityName) throws SQLException {
        return repository.getFreshersByUniversity(universityName);
    }

    @Override
    public int countInDatabase() throws SQLException {
        return repository.countInDatabase();
    }
}