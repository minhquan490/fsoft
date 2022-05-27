package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.system.fsoft.entity.Experience;
import com.system.fsoft.service.ExperienceService;
import com.system.fsoft.service.impl.ExperienceServiceImpl;

public class ExperienceController {
    private String patternID = "EXP-";
    private Set<Experience> experiences;
    private ExperienceService service = new ExperienceServiceImpl();
    private Experience experience;

    private ExperienceController(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    private ExperienceController(Experience experience) {
        this.experience = experience;
    }

    private ExperienceController() {
    }

    public static ExperienceController init() {
        return new ExperienceController();
    }

    public static ExperienceController init(Set<Experience> Experiences) {
        return new ExperienceController(Experiences);
    }

    public static ExperienceController init(Experience Experience) {
        return new ExperienceController(Experience);
    }

    public void saveAll() throws SQLException {
        int totalExperience = service.countInDatabase();
        if (totalExperience == 0) {
            throw new SQLException("Why it equal 0 ?");
        }
        if (this.experiences == null) {
            System.out.println("Why it null ?");
        }
        for (Experience experience : experiences) {
            experience.setCandidateID(this.patternID + String.valueOf(totalExperience));
            service.save(experience);
            totalExperience++;
        }
    }

    public void save() throws SQLException {
        int totalExperience = service.countInDatabase();
        if (totalExperience == 0) {
            throw new SQLException("Why it equal 0 ?");
        }
        if (this.experience == null) {
            System.out.println("Why it null ?");
        }
        experience.setCandidateID(this.patternID + String.valueOf(totalExperience));
        service.save(experience);
    }

    public void edit(Experience experience) throws SQLException {
        service.edit(experience);
    }

    public void delete(Experience experience) throws SQLException {
        service.delete(experience);
    }

    public Experience getExperienceByName(String name) throws SQLException {
        return service.getByName(name);
    }

    public List<Experience> getByExperience(Float expInYear) throws SQLException {
        return service.getByExperience(expInYear);
    }

    public List<Experience> getBySkill(int proSkill) throws SQLException {
        return service.getByAdvancedSkills(proSkill);
    }

    public List<Experience> getAll() throws SQLException {
        return service.getAll();
    }

}