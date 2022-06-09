package com.system.fsoft.entity;

import java.util.Objects;

@SuppressWarnings("serial")
public class Intern extends Candidate {

	private String major;
	private int semester;
	private String universityName;

	public Intern() {
	}

	private Intern(Candidate candidate) {
		this.setFullName(candidate.getFullName());
		this.setBirthDate(candidate.getBirthDate());
		this.setPhone(candidate.getPhone());
		this.setEmail(candidate.getEmail());
		this.setCandidateType(candidate.getCandidateType());
	}

	public static Intern of(Candidate candidate) {
		return new Intern(candidate);
	}

	public String getMajor() {
		return major;
	}

	public int getSemester() {
		return semester;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(major, semester, universityName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intern other = (Intern) obj;
		return Objects.equals(major, other.major) && semester == other.semester
				&& Objects.equals(universityName, other.universityName);
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("Major: " + this.getMajor());
		System.out.println("Semester: " + this.getSemester());
		System.out.println("University Name: " + this.getUniversityName());
	}
}