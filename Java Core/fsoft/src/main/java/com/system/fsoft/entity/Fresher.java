package com.system.fsoft.entity;

import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class Fresher extends Candidate {
	private Date graduationDate;
	private String graduationRank;
	private String education;

	public Fresher() {
	}

	private Fresher(Candidate candidate) {
		this.setFullName(candidate.getFullName());
		this.setBirthDate(candidate.getBirthDate());
		this.setPhone(candidate.getPhone());
		this.setEmail(candidate.getEmail());
		this.setCandidateType(candidate.getCandidateType());
	}

	public static Fresher of(Candidate candidate) {
		return new Fresher(candidate);
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public String getGraduationRank() {
		return graduationRank;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(education, graduationDate, graduationRank);
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
		Fresher other = (Fresher) obj;
		return Objects.equals(education, other.education) && Objects.equals(graduationDate, other.graduationDate)
				&& Objects.equals(graduationRank, other.graduationRank);
	}

	@Override
	void showInfo() {
		super.showInfo();
		System.out.println("Graduation Date: " + this.getGraduationDate());
		System.out.println("Graduation Rank: " + this.getGraduationRank());
		System.out.println("Education: " + this.getEducation());
	}
}