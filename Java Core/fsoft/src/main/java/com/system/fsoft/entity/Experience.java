package com.system.fsoft.entity;

import java.sql.Date;

public class Experience extends Candidate {
    private int expInYear;
    private int proSkill;

    public Experience() {
    }

    public int getExpInYear() {
        return expInYear;
    }

    public int getProSkill() {
        return proSkill;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public void setProSkill(int proSkill) {
        this.proSkill = proSkill;
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
        System.out.println("Experience in year: " + this.getExpInYear());
        System.out.println("Advanced skills: " + this.getProSkill());
    }
}