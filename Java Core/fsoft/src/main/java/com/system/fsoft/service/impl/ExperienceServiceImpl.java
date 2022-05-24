package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Experience;
import com.system.fsoft.exception.CandidateNotFoundException;
import com.system.fsoft.repository.ExperienceRepository;
import com.system.fsoft.repository.impl.ExperienceRepositoryImpl;
import com.system.fsoft.service.ExperienceService;

public class ExperienceServiceImpl implements ExperienceService {

    private ExperienceRepository repository = new ExperienceRepositoryImpl();

    @Override
    public void save(Experience experience) throws SQLException {
        repository.save(experience);
    }

    @Override
    public void edit(Experience experience) throws SQLException {
        Experience oldExperience = this.getByID(experience.getCandidateID());
        if (oldExperience == null) {
            throw new CandidateNotFoundException(
                    "Candidate is not exist or deleted, reload Candidate set then try again");
        }
        oldExperience.setFullName(experience.getFullName());
        oldExperience.setBirthDate(experience.getBirthDate());
        oldExperience.setPhone(experience.getPhone());
        oldExperience.setEmail(experience.getEmail());
        oldExperience.setCandidateType(experience.getCandidateType());
        oldExperience.setExpInYear(experience.getExpInYear());
        oldExperience.setProSkill(experience.getProSkill());
        repository.edit(experience);
    }

    @Override
    public void delete(Experience experience) throws SQLException {
        repository.delete(experience);
    }

    @Override
    public void saveOrUpdate(Experience experience) throws SQLException {
        Experience oldExperience = this.getByID(experience.getCandidateID());
        if (oldExperience == null) {
            throw new CandidateNotFoundException(
                    "Candidate is not exist or deleted, reload Candidate set then try again");
        }
        oldExperience.setFullName(experience.getFullName());
        oldExperience.setBirthDate(experience.getBirthDate());
        oldExperience.setPhone(experience.getPhone());
        oldExperience.setEmail(experience.getEmail());
        oldExperience.setCandidateType(experience.getCandidateType());
        oldExperience.setExpInYear(experience.getExpInYear());
        oldExperience.setProSkill(experience.getProSkill());
        repository.saveOrUpdate(experience);
    }

    @Override
    public Experience getByID(String idCandidate) throws SQLException {
        return repository.getByID(idCandidate);
    }

    @Override
    public Experience getByName(String candidateName) throws SQLException {
        return repository.getByName(candidateName);
    }

    @Override
    public List<Experience> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public List<Experience> getByExperience(int expInYear) throws SQLException {
        return repository.getByExperience(expInYear);
    }

    @Override
    public List<Experience> getByAdvancedSkills(int proSkill) throws SQLException {
        return repository.getByAdvancedSkills(proSkill);
    }
}