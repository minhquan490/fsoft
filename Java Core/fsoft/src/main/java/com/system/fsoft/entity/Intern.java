package com.system.fsoft.entity;

import java.sql.Date;

public class Intern extends Candidate {

    private String major;
    private int semester;
    private String universityName;

    Intern() {
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
        System.out.println("Major: " + this.getMajor());
        System.out.println("Semester: " + this.getSemester());
        System.out.println("University Name: " + this.getUniversityName());
    }
}