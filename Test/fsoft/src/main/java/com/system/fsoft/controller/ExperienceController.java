package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.system.fsoft.entity.Experience;
import com.system.fsoft.service.ExperienceService;
import com.system.fsoft.service.impl.ExperienceServiceImpl;

public class ExperienceController {
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

	public static ExperienceController init(Set<Experience> experiences) {
		return new ExperienceController(experiences);
	}

	public static ExperienceController init(Experience experience) {
		return new ExperienceController(experience);
	}

	public void saveAll() throws SQLException {
		for (Experience experience : experiences) {
			service.save(experience);
		}
	}

	public void save() throws SQLException {
		if (this.experience == null) {
			System.out.println("Why it null ?");
		}
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

	public Experience getByID(String experienceID) throws SQLException {
		return service.getByID(experienceID);
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