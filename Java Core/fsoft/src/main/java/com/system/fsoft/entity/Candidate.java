package com.system.fsoft.entity;

import java.io.Serializable;
import java.sql.Date;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((candidateID == null) ? 0 : candidateID.hashCode());
		result = prime * result + candidateType;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + totalCertificate;
		return result;
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
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (candidateID == null) {
			if (other.candidateID != null)
				return false;
		} else if (!candidateID.equals(other.candidateID))
			return false;
		if (candidateType != other.candidateType)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (totalCertificate != other.totalCertificate)
			return false;
		return true;
	}

	void showInfo() {
		System.out.println("Candidate ID: " + this.getCandidateID());
		System.out.println("Candidate name: " + this.getEmail());
		System.out.println("Candidate email: " + this.getEmail());
		System.out.println("Candidate phone: " + this.getPhone());
		System.out.println("Candidate birth day: " + this.getBirthDate());
	}
}