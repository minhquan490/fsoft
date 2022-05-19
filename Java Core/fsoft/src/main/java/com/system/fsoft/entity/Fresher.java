package com.system.fsoft.entity;

import java.sql.Date;

public class Fresher extends Candidate {
    private Date graduationDate;
    private String graduationRank;
    private String education;

    public Fresher() {
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
    public Date getBirthDate() {
        return super.getBirthDate();
    }

    @Override
    public String getCandidateID() {
        return super.getCandidateID();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setBirthDate(Date birthDate) {
        super.setBirthDate(birthDate);
    }

    @Override
    public void setCandidateID(String candidateID) {
        super.setCandidateID(candidateID);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    void showInfo() {
        super.showInfo();
        System.out.println("Graduation Date: " + this.getGraduationDate());
        System.out.println("Graduation Rank: " + this.getGraduationRank());
        System.out.println("Education: " + this.getEducation());
    }
}