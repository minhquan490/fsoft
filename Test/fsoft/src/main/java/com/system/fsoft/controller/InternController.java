package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.system.fsoft.entity.Intern;
import com.system.fsoft.service.InternService;
import com.system.fsoft.service.impl.InternServiceImpl;

public class InternController {

	private Set<Intern> interns;
	private InternService service = new InternServiceImpl();
	private Intern intern;

	private InternController(Set<Intern> interns) {
		this.interns = interns;
	}

	private InternController(Intern intern) {
		this.intern = intern;
	}

	private InternController() {
	}

	public static InternController init() {
		return new InternController();
	}

	public static InternController init(Set<Intern> interns) {
		return new InternController(interns);
	}

	public static InternController init(Intern intern) {
		return new InternController(intern);
	}

	public void saveAll() throws SQLException {
		if (this.interns == null) {
			System.out.println("Why it null ?");
		}
		for (Intern intern : interns) {
			service.save(intern);
		}
	}

	public void save() throws SQLException {
		if (this.intern == null) {
			System.out.println("Why it null ?");
		}
		service.save(intern);
	}

	public void edit(Intern intern) throws SQLException {
		service.edit(intern);
	}

	public void delete(Intern intern) throws SQLException {
		service.delete(intern);
	}

	public Intern getInternByName(String name) throws SQLException {
		return service.getByName(name);
	}

	public Intern getByID(String internID) throws SQLException {
		return service.getByID(internID);
	}

	public List<Intern> getInternsByMajor(String majorName) throws SQLException {
		return service.getByMajor(majorName.trim());
	}

	public List<Intern> getInternsByUniversity(String universityName) throws SQLException {
		return service.getByUniversity(universityName);
	}

	public List<Intern> getAll() throws SQLException {
		return service.getAll();
	}
}