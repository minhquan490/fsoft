package com.system.fsoft.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class Candidate implements Serializable {
	private String candidateID;
	private String fullName;
	private Date birthDate;
	private String phone;
	private String email;
	private int candidateType;
	private int totalCertificate;

	public Candidate() {
	}

	public Candidate(String candidateID, String fullName, Date birthDate, String phone, String email) {
		this.candidateID = candidateID;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.phone = phone;
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCandidateID() {
		return candidateID;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(int candidateType) {
		this.candidateType = candidateType;
	}

	public int getTotalCertificate() {
		return totalCertificate;
	}

	public void setTotalCertificate(int totalCertificate) {
		this.totalCertificate = totalCertificate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, candidateID, candidateType, email, fullName, phone, totalCertificate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(candidateID, other.candidateID)
				&& candidateType == other.candidateType && Objects.equals(email, other.email)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(phone, other.phone)
				&& totalCertificate == other.totalCertificate;
	}

	public void showInfo() {
		System.out.println("Candidate ID: " + this.getCandidateID());
		System.out.println("Candidate name: " + this.getFullName());
		System.out.println("Candidate email: " + this.getEmail());
		System.out.println("Candidate phone: " + this.getPhone());
		System.out.println("Candidate birth day: " + this.getBirthDate());
	}
}