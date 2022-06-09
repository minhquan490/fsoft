package com.system.fsoft.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class Certificate implements Serializable {

	private String certificatedID;
	private String certificatedName;
	private String certificatedRank;
	private Date certificatedDate;
	private Candidate candidate;

	public Certificate() {
	}

	public Certificate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Date getCertificatedDate() {
		return certificatedDate;
	}

	public String getCertificatedID() {
		return certificatedID;
	}

	public String getCertificatedName() {
		return certificatedName;
	}

	public String getCertificatedRank() {
		return certificatedRank;
	}

	public void setCertificatedDate(Date certificatedDate) {
		this.certificatedDate = certificatedDate;
	}

	public void setCertificatedID(String certificatedID) {
		this.certificatedID = certificatedID;
	}

	public void setCertificatedName(String certificatedName) {
		this.certificatedName = certificatedName;
	}

	public void setCertificatedRank(String certificatedRank) {
		this.certificatedRank = certificatedRank;
	}

	public String getCandidateID() {
		return candidate.getCandidateID();
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidate, certificatedDate, certificatedID, certificatedName, certificatedRank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Certificate other = (Certificate) obj;
		return Objects.equals(candidate, other.candidate) && Objects.equals(certificatedDate, other.certificatedDate)
				&& Objects.equals(certificatedID, other.certificatedID)
				&& Objects.equals(certificatedName, other.certificatedName)
				&& Objects.equals(certificatedRank, other.certificatedRank);
	}
}