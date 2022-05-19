package com.system.fsoft.entity;

import java.io.Serializable;
import java.sql.Date;

public class Certificate implements Serializable {
    private String certificatedID;
    private String certificatedName;
    private String certificatedRank;
    private Date certificatedDate;
    private Candidate candidate;

    public Certificate() {
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

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getCandidateID() {
        return candidate.getCandidateID();
    }
}