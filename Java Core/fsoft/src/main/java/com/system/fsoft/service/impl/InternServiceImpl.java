package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Intern;
import com.system.fsoft.exception.CandidateNotFoundException;
import com.system.fsoft.repository.InternRepository;
import com.system.fsoft.repository.impl.InternRepositoryImpl;
import com.system.fsoft.service.InternService;

public class InternServiceImpl implements InternService {

    private InternRepository repository = new InternRepositoryImpl();

    @Override
    public void save(Intern intern) throws SQLException {
        repository.save(intern);
    }

    @Override
    public void edit(Intern intern) throws SQLException {
        Intern oldIntern = this.getByID(intern.getCandidateID());
        if (oldIntern == null) {
            throw new CandidateNotFoundException(
                    "Candidate is not exist or deleted, reload Candidate set then try again");
        }
        oldIntern.setFullName(intern.getFullName());
        oldIntern.setBirthDate(intern.getBirthDate());
        oldIntern.setEmail(intern.getEmail());
        oldIntern.setPhone(intern.getPhone());
        oldIntern.setCandidateType(intern.getCandidateType());
        oldIntern.setSemester(intern.getSemester());
        oldIntern.setUniversityName(intern.getUniversityName());
        oldIntern.setMajor(intern.getMajor());
        repository.edit(oldIntern);
    }

    @Override
    public void delete(Intern intern) throws SQLException {
        repository.delete(intern);
    }

    @Override
    public void saveOrUpdate(Intern intern) throws SQLException {
        Intern oldIntern = this.getByID(intern.getCandidateID());
        if (oldIntern == null) {
            throw new CandidateNotFoundException(
                    "Candidate is not exist or deleted, reload Candidate set then try again");
        }
        oldIntern.setFullName(intern.getFullName());
        oldIntern.setBirthDate(intern.getBirthDate());
        oldIntern.setEmail(intern.getEmail());
        oldIntern.setPhone(intern.getPhone());
        oldIntern.setCandidateType(intern.getCandidateType());
        oldIntern.setSemester(intern.getSemester());
        oldIntern.setUniversityName(intern.getUniversityName());
        oldIntern.setMajor(intern.getMajor());
        repository.saveOrUpdate(intern);
    }

    @Override
    public Intern getByID(String idCandidate) throws SQLException {
        return repository.getByID(idCandidate);
    }

    @Override
    public Intern getByName(String candidateName) throws SQLException {
        return repository.getByName(candidateName);
    }

    @Override
    public List<Intern> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public List<Intern> getByMajor(String majorName) throws SQLException {
        return repository.getByMajor(majorName);
    }

    @Override
    public List<Intern> getByUniversity(String universityName) throws SQLException {
        return repository.getByUniversity(universityName);
    }
}