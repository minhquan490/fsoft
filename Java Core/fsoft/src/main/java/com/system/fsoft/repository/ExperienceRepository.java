package com.system.fsoft.repository;

import java.util.List;

import com.system.fsoft.entity.Experience;

public interface ExperienceRepository {
    void save(Experience experience);

    void edit(Experience experience);

    void delete(Experience experience);

    Experience getByID(String idCandidate);

    Experience getByName(String candidateName);

    List<Experience> getAll();

    List<Experience> getByExperience(int expInYear);

    List<Experience> getByAdvancedSkills(int proSkill);
}