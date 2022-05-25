package com.system.fsoft.gui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.system.fsoft.controller.ExperienceController;
import com.system.fsoft.controller.FresherController;
import com.system.fsoft.controller.InternController;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.entity.Fresher;
import com.system.fsoft.entity.Intern;
import com.system.fsoft.utils.Validator;

public class MainPanel {

    private Scanner in = new Scanner(System.in);

    private MainPanel() {
    }

    public static MainPanel createPanel() {
        return new MainPanel();
    }

    public void run() throws SQLException {
        int choice = this.showMainMenu();
        switch (choice) {
            case 1:
                int choiceAdd = this.showMenuAdd();
                switch (choiceAdd) {
                    case 1:
                        int userChoiceAddIntern = this.addInternMenu();
                        if (userChoiceAddIntern == 1) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            InternController internController = InternController.init(this.addListIntern());
                            internController.saveAll();
                        }
                        if (userChoiceAddIntern == 2) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            InternController internController = InternController.init(this.addIntern());
                            internController.save();
                        }
                        break;
                    case 2:
                        int userChoiceAddFresher = this.addFresherMenu();
                        if (userChoiceAddFresher == 1) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            FresherController fresherController = FresherController.init(this.addListFresher());
                            fresherController.saveAll();
                        }
                        if (userChoiceAddFresher == 2) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            FresherController fresherController = FresherController.init(this.addFresher());
                            fresherController.save();
                        }
                        break;
                    case 3:
                        int userChoiceAddExperience = this.addExperienceMenu();
                        if (userChoiceAddExperience == 1) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            ExperienceController experienceController = ExperienceController
                                    .init(this.addListExperience());
                            experienceController.saveAll();
                        }
                        if (userChoiceAddExperience == 2) {
                            System.out.println(
                                    "Warning: Check infomation of Candidate who you will input. Regulatory compliance or take more time to finish work");
                            ExperienceController experienceController = ExperienceController.init(this.addExperience());
                            experienceController.save();
                        }
                    case 4:
                        this.showMainMenu();
                        break;
                    case 5:
                        this.showMenuFind();
                        break;
                    case 6:
                        this.showMenuDelete();
                        break;
                    case 7:
                        this.showMenuEdit();
                        break;
                    case 8:
                        this.showAllCandidate();
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("Input 1-9");
                        break;
                }
                break;
            case 2:
                this.showMenuFind();

                break;
            case 3:
                this.showMenuEdit();
                // TODO
                break;
            case 4:
                this.showMenuDelete();
                // TODO
                break;
            case 5:
                this.showAllCandidate();
                // TODO
                break;
            default:
                System.out.println("Try again please");
                break;
        }
    }

    private int showMainMenu() {
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION  ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Add candidate");
        System.out.println("2. Find candidate ");
        System.out.println("3. Edit candidate");
        System.out.println("4. Delete candidate");
        System.out.println("5. Print all candidate");
        System.out.println("6. Exit");
        System.out.println("------------------------------------------------");
        System.out.println("Enter your choice: ");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 6) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int showMenuAdd() {
        System.out.println("   WELCOME TO ADD CANDIDATE CENTER   ");
        System.out.println("--------------------------------");
        System.out.println("1. Add Intern");
        System.out.println("2. Add Fresher");
        System.out.println("3. Add Experience");
        System.out.println("4. Return main menu");
        System.out.println("5. Return find menu");
        System.out.println("6. Return delete menu");
        System.out.println("7. Return edit menu");
        System.out.println("8. Return to show all menu");
        System.out.println("9. Exit program");
        System.out.println("------------------------------");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 9) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int addInternMenu() {
        System.out
                .println("You can add intern with two ways (add list intern or only one intern). What do you choice ?");
        System.out.println(" 1. Add intern with list. ");
        System.out.println(" 2. Add only one intern. ");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 2) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int addFresherMenu() {
        System.out
                .println(
                        "You can add fresher with two ways (add list fresher or only one fresher). What do you choice ?");
        System.out.println(" 1. Add fresher with list. ");
        System.out.println(" 2. Add only one fresher. ");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 2) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int addExperienceMenu() {
        System.out
                .println(
                        "You can add experience with two ways (add list experience or only one experience). What do you choice ?");
        System.out.println(" 1. Add experience with list. ");
        System.out.println(" 2. Add only one experience. ");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 2) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int showMenuFind() {
        System.out.println("    WELCOME TO FIND CENTER    ");
        System.out.println("------------------------------");
        System.out.println("1. Find Intern");
        System.out.println("2. Find Fresher");
        System.out.println("3. Find Experience");
        System.out.println("4. Return main menu");
        System.out.println("5. Return add menu");
        System.out.println("6. Return delete menu");
        System.out.println("7. Return edit menu");
        System.out.println("8. Return to show all menu");
        System.out.println("9. Exit program");
        System.out.println("------------------------------");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 9) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int showMenuDelete() {
        System.out.println("    WELCOME TO DELETE CENTER    ");
        System.out.println("------------------------------");
        System.out.println("1. Delete Intern");
        System.out.println("2. Delete Fresher");
        System.out.println("3. Delete Experience");
        System.out.println("4. Return main menu");
        System.out.println("5. Return find menu");
        System.out.println("6. Return add menu");
        System.out.println("7. Return edit menu");
        System.out.println("8. Return to show all menu");
        System.out.println("9. Exit program");
        System.out.println("------------------------------");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 9) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int showMenuEdit() {
        System.out.println("    WELCOME TO EDIT CENTER    ");
        System.out.println("------------------------------");
        System.out.println("1. Edit Intern");
        System.out.println("2. Edit Fresher");
        System.out.println("3. Edit Experience");
        System.out.println("4. Return main menu");
        System.out.println("5. Return find menu");
        System.out.println("6. Return add menu");
        System.out.println("7. Return delete menu");
        System.out.println("8. Return to show all menu");
        System.out.println("9. Exit program");
        System.out.println("------------------------------");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 9) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int showAllCandidate() {
        System.out.println("    WELCOME TO METADATA CENTER    ");
        System.out.println("------------------------------");
        System.out.println("1. Show all Intern");
        System.out.println("2. Show all Fresher");
        System.out.println("3. Show all Experience");
        System.out.println("4. Return main menu");
        System.out.println("5. Return find menu");
        System.out.println("6. Return add menu");
        System.out.println("7. Return delete menu");
        System.out.println("8. Return edit menu");
        System.out.println("9. Exit program");
        System.out.println("------------------------------");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 9) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private Set<Intern> addListIntern() {
        String[] now = LocalDate.now().toString().split("-");
        Set<Intern> interns = new HashSet<>();
        while (true) {
            Intern intern = new Intern();
            System.out.println("Input intern's full name: ");
            intern.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input intern's birth day (year must be > 1900 and <" + " " + now[0] + "). Pattern: yyyy-MM-dd");
            intern.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input intern's phone (10 digits): ");
            intern.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input intern's email (must be has @fsoft.com.vn)");
            intern.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input intern's major (Do not input white space): ");
            intern.setMajor(in.nextLine().trim());
            System.out.println("Input semester (Do not input white space): ");
            intern.setSemester(Integer.valueOf(in.nextLine().trim()));
            System.out.println("Input intern's university name: ");
            intern.setUniversityName(in.nextLine().trim());
            interns.add(intern);
            System.out.println("Input next Candidate ? Y/n");
            if (in.nextLine().equalsIgnoreCase("n")) {
                return interns;
            }
        }
    }

    private Set<Fresher> addListFresher() {
        String[] now = LocalDate.now().toString().split("-");
        Set<Fresher> freshers = new HashSet<>();
        while (true) {
            Fresher fresher = new Fresher();
            System.out.println("Input fresher's full name: ");
            fresher.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input fresher's birth day (year must be > 1900 and <" + " " + now[0] + "). Pattern: yyyy-MM-dd");
            fresher.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input fresher's phone (10 digits): ");
            fresher.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input fresher's email (must be has @fsoft.com.vn)");
            fresher.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input Graduation Day: ");
            fresher.setGraduationDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input Graduation Rank: ");
            fresher.setGraduationRank(Validator.checkGraduationRank(in.nextLine().trim()));
            System.out.println("Input fresher's university name");
            fresher.setEducation(in.nextLine().trim());
            freshers.add(fresher);
            System.out.println("Input next Candidate ? Y/n");
            if (in.nextLine().equalsIgnoreCase("n")) {
                return freshers;
            }
        }
    }

    private Set<Experience> addListExperience() {
        String[] now = LocalDate.now().toString().split("-");
        Set<Experience> experiences = new HashSet<>();
        while (true) {
            Experience experience = new Experience();
            System.out.println("Input Experience's full name: ");
            experience.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input Experience's birth day (year must be > 1900 and <" + " " + now[0]
                            + "). Pattern: yyyy-MM-dd");
            experience.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input experience's phone (10 digits): ");
            experience.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input experience's email (must be has @fsoft.com.vn)");
            experience.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input exp in year: ");
            experience.setExpInYear(Integer.valueOf(in.nextLine().trim()));
            System.out.println("Input advanced skill: ");
            experience.setProSkill(Integer.valueOf(in.nextLine().trim()));
            experiences.add(experience);
            System.out.println("Input next Candidate ? Y/n");
            if (in.nextLine().equalsIgnoreCase("n")) {
                return experiences;
            }
        }
    }

    private Intern addIntern() {
        String[] now = LocalDate.now().toString().split("-");
        while (true) {
            Intern intern = new Intern();
            System.out.println("Input intern's full name: ");
            intern.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input intern's birth day (year must be > 1900 and <" + " " + now[0] + "). Pattern: yyyy-MM-dd");
            intern.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input intern's phone (10 digits): ");
            intern.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input intern's email (must be has @fsoft.com.vn)");
            intern.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input intern's major (Do not input white space): ");
            intern.setMajor(in.nextLine().trim());
            System.out.println("Input semester (Do not input white space): ");
            intern.setSemester(Integer.valueOf(in.nextLine().trim()));
            System.out.println("Input intern's university name: ");
            intern.setUniversityName(in.nextLine().trim());
            return intern;
        }
    }

    private Fresher addFresher() {
        String[] now = LocalDate.now().toString().split("-");
        while (true) {
            Fresher fresher = new Fresher();
            System.out.println("Input fresher's full name: ");
            fresher.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input fresher's birth day (year must be > 1900 and <" + " " + now[0] + "). Pattern: yyyy-MM-dd");
            fresher.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input fresher's phone (10 digits): ");
            fresher.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input fresher's email (must be has @fsoft.com.vn)");
            fresher.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input Graduation Day: ");
            fresher.setGraduationDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input Graduation Rank: ");
            fresher.setGraduationRank(Validator.checkGraduationRank(in.nextLine().trim()));
            System.out.println("Input fresher's university name");
            fresher.setEducation(in.nextLine().trim());
            return fresher;
        }
    }

    private Experience addExperience() {
        String[] now = LocalDate.now().toString().split("-");
        while (true) {
            Experience experience = new Experience();
            System.out.println("Input Experience's full name: ");
            experience.setFullName(in.nextLine().trim());
            System.out.println(
                    "Input Experience's birth day (year must be > 1900 and <" + " " + now[0]
                            + "). Pattern: yyyy-MM-dd");
            experience.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input experience's phone (10 digits): ");
            experience.setPhone(Validator.checkPhone(in.nextLine().trim()));
            System.out.println("Input experience's email (must be has @fsoft.com.vn)");
            experience.setEmail(Validator.checkInvalidEmail(in.nextLine()));
            System.out.println("Input exp in year: ");
            experience.setExpInYear(Integer.valueOf(in.nextLine().trim()));
            System.out.println("Input advanced skill: ");
            experience.setProSkill(Integer.valueOf(in.nextLine().trim()));
            return experience;
        }
    }
}