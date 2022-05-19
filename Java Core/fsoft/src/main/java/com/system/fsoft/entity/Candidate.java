package com.system.fsoft.entity;

import java.io.Serializable;
import java.sql.Date;

public class Candidate implements Serializable {
    private String candidateID;
    private String fullName;
    private Date birthDate;
    private String phone;
    private String email;
    private int candidateType;
    private int candidateCount;

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

    public int getCandidateCount() {
        return candidateCount;
    }

    public void setCandidateCount(int candidateCount) {
        this.candidateCount = candidateCount;
    }

    public int getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(int candidateType) {
        this.candidateType = candidateType;
    }

    void showInfo() {
        System.out.println("Candidate ID: " + this.getCandidateID());
        System.out.println("Candidate name: " + this.getEmail());
        System.out.println("Candidate email: " + this.getEmail());
        System.out.println("Candidate phone: " + this.getPhone());
        System.out.println("Candidate birth day: " + this.getBirthDate());
    }
}