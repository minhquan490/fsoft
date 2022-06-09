package com.system.fsoft.entity;

import java.util.Objects;

@SuppressWarnings("serial")
public class Experience extends Candidate {
	private float expInYear;
	private int proSkill;

	public Experience() {
	}

	private Experience(Candidate candidate) {
		this.setFullName(candidate.getFullName());
		this.setBirthDate(candidate.getBirthDate());
		this.setPhone(candidate.getPhone());
		this.setEmail(candidate.getEmail());
		this.setCandidateType(candidate.getCandidateType());
	}

	public static Experience of(Candidate candidate) {
		return new Experience(candidate);
	}

	public float getExpInYear() {
		return expInYear;
	}

	public int getProSkill() {
		return proSkill;
	}

	public void setExpInYear(float f) {
		this.expInYear = f;
	}

	public void setProSkill(int proSkill) {
		this.proSkill = proSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(expInYear, proSkill);
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
		Experience other = (Experience) obj;
		return Float.floatToIntBits(expInYear) == Float.floatToIntBits(other.expInYear) && proSkill == other.proSkill;
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("Experience in year: " + this.getExpInYear());
		System.out.println("Advanced skills: " + this.getProSkill());
	}
}