package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.system.fsoft.entity.Fresher;
import com.system.fsoft.service.FresherService;
import com.system.fsoft.service.impl.FresherServiceImpl;

public class FresherController {

	private static Logger log = LogManager.getLogger(FresherController.class.getName());

	private Set<Fresher> freshers;
	private FresherService service = new FresherServiceImpl();
	private Fresher fresher;

	private FresherController(Set<Fresher> freshers) {
		this.freshers = freshers;
	}

	private FresherController(Fresher fresher) {
		this.fresher = fresher;
	}

	private FresherController() {
	}

	public static FresherController init() {
		log.info("Fresher controller is created");
		return new FresherController();
	}

	public static FresherController init(Set<Fresher> freshers) {
		log.info("Fresher controller with set fresher is created");
		return new FresherController(freshers);
	}

	public static FresherController init(Fresher fresher) {
		log.info("Fresher controller with single fresher is created");
		return new FresherController(fresher);
	}

	public void saveAll() throws SQLException {
		if (this.freshers == null) {
			System.out.println("Why it null ?");
		}
		for (Fresher fresher : freshers) {
			service.save(fresher);
		}
	}

	public void save() throws SQLException {
		if (this.fresher == null) {
			System.out.println("Why it null ?");
		}
		service.save(fresher);
	}

	public Fresher getFresherByName(String fresherName) throws SQLException {
		return service.getByName(fresherName);
	}

	public Fresher getByID(String fresherID) throws SQLException {
		return service.getByID(fresherID);
	}

	public void edit(Fresher fresher) throws SQLException {
		service.edit(fresher);
	}

	public void delete(Fresher fresher) throws SQLException {
		service.delete(fresher);
	}

	public List<Fresher> getFreshersByRank(String rank) throws SQLException {
		return service.getFreshersByRank(rank);
	}

	public List<Fresher> getFreshersByUniversityName(String universityName) throws SQLException {
		return service.getFreshersByUniversity(universityName);
	}

	public List<Fresher> getAll() throws SQLException {
		return service.getAll();
	}
}