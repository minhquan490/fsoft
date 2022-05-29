package com.system.fsoft.gui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.system.fsoft.controller.CandidateController;
import com.system.fsoft.controller.CertificateController;
import com.system.fsoft.controller.ExperienceController;
import com.system.fsoft.controller.FresherController;
import com.system.fsoft.controller.InternController;
import com.system.fsoft.entity.Candidate;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.entity.Experience;
import com.system.fsoft.entity.Fresher;
import com.system.fsoft.entity.Intern;
import com.system.fsoft.exception.SystemInterruptedException;
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
                        if (userChoiceAddIntern == 3) {
                            this.showMenuAdd();
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
                        if (userChoiceAddFresher == 3) {
                            this.showMenuAdd();
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
                        if (userChoiceAddExperience == 3) {
                            this.showMenuAdd();
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
                int choiceFind = this.showMenuFind();
                StringBuilder optionFind = new StringBuilder("find");
                switch (choiceFind) {
                    case 1:
                        int choiceFindIntern = this.findInternMenu();
                        if (choiceFindIntern == 1) {
                            InternController internController = InternController.init();
                            System.out.println("Input intern's name: ");
                            Intern intern = internController.getInternByName(in.nextLine().trim());
                            if (intern == null) {
                                System.out.println("Intern doesn't exist");
                            } else {
                                this.showInterns((Intern[]) Arrays.asList(intern).toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindIntern == 2) {
                            InternController internController = InternController.init();
                            System.out.println("Enter intern's major: ");
                            List<Intern> interns = internController.getInternsByMajor(in.nextLine());
                            if (interns.isEmpty()) {
                                System.out.println("No intern found");
                            } else {
                                this.showInterns((Intern[]) interns.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindIntern == 3) {
                            InternController internController = InternController.init();
                            System.out.println("Enter intern's university name");
                            List<Intern> interns = internController.getInternsByUniversity(in.nextLine());
                            if (interns.isEmpty()) {
                                System.out.println("No intern found");
                            } else {
                                this.showInterns((Intern[]) interns.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindIntern == 4) {
                            this.showMenuFind();
                        }
                        break;
                    case 2:
                        int choiceFindFresher = this.findFresherMenu();
                        if (choiceFindFresher == 1) {
                            FresherController fresherController = FresherController.init();
                            System.out.println("Enter fresher's name: ");
                            Fresher fresher = fresherController.getFresherByName(in.nextLine().trim());
                            if (fresher == null) {
                                System.out.println("Fresher doesn't exit");
                            } else {
                                this.showFreshers((Fresher[]) Arrays.asList(fresher).toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindFresher == 2) {
                            FresherController fresherController = FresherController.init();
                            System.out.println("Enter fresher's rank to find: ");
                            List<Fresher> freshers = fresherController
                                    .getFreshersByRank(in.nextLine().trim());
                            if (freshers.isEmpty()) {
                                System.out.println("No fresher found");
                            } else {
                                this.showFreshers((Fresher[]) freshers.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindFresher == 3) {
                            FresherController fresherController = FresherController.init();
                            System.out.println("Enter fresher's university: ");
                            List<Fresher> freshers = fresherController
                                    .getFreshersByRank(in.nextLine().trim());
                            if (freshers.isEmpty()) {
                                System.out.println("No fresher have university:  " + in.nextLine());
                            } else {
                                this.showFreshers((Fresher[]) freshers.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindFresher == 4) {
                            this.showMenuFind();
                        }
                        break;
                    case 3:
                        int choiceFindExperience = this.findExperienceMenu();
                        if (choiceFindExperience == 1) {
                            ExperienceController experienceController = ExperienceController.init();
                            System.out.println("Enter experience's name: ");
                            Experience experience = experienceController.getExperienceByName(in.nextLine().trim());
                            if (experience == null) {
                                System.out.println("Experience doesn't exit: ");
                            } else {
                                this.showExperiences((Experience[]) Arrays.asList(experience).toArray(),
                                        optionFind.toString());
                            }
                        }
                        if (choiceFindExperience == 2) {
                            ExperienceController experienceController = ExperienceController.init();
                            System.out.println("Enter exp in year to show experiences: ");
                            List<Experience> experiences = experienceController
                                    .getByExperience(Float.valueOf(in.nextLine().trim()));
                            if (experiences.isEmpty()) {
                                System.out.println("No candidate has year in work it you input");
                            } else {
                                this.showExperiences((Experience[]) experiences.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindExperience == 3) {
                            ExperienceController experienceController = ExperienceController.init();
                            System.out.println("Enter advance skill to show experiences: ");
                            List<Experience> experiences = experienceController
                                    .getBySkill(Integer.valueOf(in.nextLine().trim()));
                            if (experiences.isEmpty()) {
                                System.out.println("No candidate has year in work it you input");
                            } else {
                                this.showExperiences((Experience[]) experiences.toArray(), optionFind.toString());
                            }
                        }
                        if (choiceFindExperience == 4) {
                            this.showMenuFind();
                        }
                        break;
                    case 4:
                        this.showMainMenu();
                        break;
                    case 5:
                        this.showMenuAdd();
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
            case 3:
                int choiceEdit = this.showMenuEdit();
                StringBuilder optionEdit = new StringBuilder("edit");
                switch (choiceEdit) {
                    case 1:
                        InternController internController = InternController.init();
                        Intern[] interns = (Intern[]) internController.getAll().toArray();
                        this.showInterns(interns, optionEdit.toString());
                        System.out.println("Enter candidate to edit using column Serial");
                        internController.edit(this.editIntern(interns[Integer.valueOf(in.nextLine().trim()) - 1]));
                        break;
                    case 2:
                        FresherController fresherController = FresherController.init();
                        Fresher[] freshers = (Fresher[]) fresherController.getAll().toArray();
                        this.showFreshers(freshers, optionEdit.toString());
                        System.out.println("Enter candidate to edit using column Serial");
                        fresherController.edit(this.editFresher(freshers[Integer.valueOf(in.nextLine().trim())]));
                        break;
                    case 3:
                        ExperienceController experienceController = ExperienceController.init();
                        Experience[] experiences = (Experience[]) experienceController.getAll().toArray();
                        this.showExperiences(experiences, optionEdit.toString());
                        System.out.println("Enter candidate to edit using column Serial");
                        experienceController
                                .edit(this.editExperience(experiences[Integer.valueOf(in.nextLine().trim())]));
                        break;
                    case 4:
                        this.showMainMenu();
                        break;
                    case 5:
                        this.showMenuFind();
                        break;
                    case 6:
                        this.showMenuAdd();
                        break;
                    case 7:
                        this.showMenuDelete();
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
            case 4:
                StringBuilder optionDelete = new StringBuilder("delete");
                int choiceDelete = this.showMenuDelete();
                switch (choiceDelete) {
                    case 1:
                        InternController internController = InternController.init();
                        Intern[] interns = (Intern[]) internController.getAll().toArray();
                        this.showInterns(interns, optionDelete.toString());
                        System.out.println("Enter candidate to delete using column Serial");
                        internController.delete(interns[Integer.valueOf(in.nextLine().trim()) - 1]);
                        break;
                    case 2:
                        FresherController fresherController = FresherController.init();
                        Fresher[] freshers = (Fresher[]) fresherController.getAll().toArray();
                        this.showFreshers(freshers, optionDelete.toString());
                        System.out.println("Enter candidate to delete using column Serial");
                        fresherController.delete(freshers[Integer.valueOf(in.nextLine().trim()) - 1]);
                        break;
                    case 3:
                        ExperienceController experienceController = ExperienceController.init();
                        Experience[] experiences = (Experience[]) experienceController.getAll().toArray();
                        this.showExperiences(experiences, optionDelete.toString());
                        System.out.println("Enter candidate to delete using column Serial");
                        experienceController.delete(experiences[Integer.valueOf(in.nextLine().trim()) - 1]);
                        break;
                    case 4:
                        this.showMainMenu();
                        break;
                    case 5:
                        this.showMenuFind();
                        break;
                    case 6:
                        this.showMenuAdd();
                        break;
                    case 7:
                        this.showMenuEdit();
                        break;
                    case 8:
                        this.showAllCandidate();
                        break;
                    default:
                        System.out.println("Input 1-8");
                        break;
                }
                break;
            case 5:
                int choiceAllCandidate = this.showAllCandidate();
                StringBuilder optionList = new StringBuilder("list");
                switch (choiceAllCandidate) {
                    case 1:
                        InternController internController = InternController.init();
                        List<Intern> interns = internController.getAll();
                        System.out.println("All intern:");
                        this.showInterns((Intern[]) interns.toArray(), optionList.toString());
                        break;
                    case 2:
                        FresherController fresherController = FresherController.init();
                        List<Fresher> freshers = fresherController.getAll();
                        System.out.println("All fresher: ");
                        this.showFreshers((Fresher[]) freshers.toArray(), optionList.toString());
                        break;
                    case 3:
                        ExperienceController experienceController = ExperienceController.init();
                        List<Experience> experiences = experienceController.getAll();
                        System.out.println("All experience: ");
                        this.showExperiences((Experience[]) experiences.toArray(), optionList.toString());
                        break;
                    case 4:
                        this.showMainMenu();
                        break;
                    case 5:
                        this.showMenuFind();
                        break;
                    case 6:
                        this.showMenuAdd();
                        break;
                    case 7:
                        this.showMenuDelete();
                        break;
                    case 8:
                        this.showMenuEdit();
                        break;
                    default:
                        System.out.println("Input 1-8");
                        break;
                }
                break;
            case 6:
                CandidateController candidateController = CandidateController.init();
                Candidate[] candidates = (Candidate[]) candidateController.getAllCandidateAndTheirCertidicate()
                        .toArray();
                this.showCandidateAndTheirCertificate(candidates);
                this.certificateOption(candidates);
                break;
            case 7:
                return;
            default:
                System.out.println("Try again please");
                break;
        }
    }

    private void certificateOption(Candidate[] candidates) throws SQLException {
        CertificateController certificateController = CertificateController.init();
        try {
            System.out.println("1. View, modify and add certificate for candidate");
            System.out.println("2. delete certificate");
            System.out.println("3. Exit");
            int choice = Integer.valueOf(in.nextLine().trim());
            if (choice == 1) {
                System.out.println("Index of Candidate: ");
                int index = Integer.valueOf(in.nextLine().trim());
                Candidate candidate = candidates[index - 1];
                List<Certificate> certificates = certificateController.getCertificatesByCandidate(candidate);
                this.showCertificates((Certificate[]) certificates.toArray());
                System.out.println("Do you want insert or update ?");
                if (in.nextLine().trim().equalsIgnoreCase("insert")) {
                    this.certificateToSaveOrUpdate(candidate, new Certificate(), certificates);
                }
                if (in.nextLine().trim().equalsIgnoreCase("update")) {
                    System.out.println("Enter index of certificate this you want to update: ");
                    int cerIndex = Integer.valueOf(in.nextLine().trim());
                    this.certificateToSaveOrUpdate(candidate, certificates.get(cerIndex - 1), certificates);
                }
                if (!in.nextLine().trim().equalsIgnoreCase("update")
                        || !in.nextLine().trim().equalsIgnoreCase("insert")) {
                    System.out.println("INSERT or UPDATE ?");
                    this.certificateOption(candidates);
                }
            }
            if (choice == 2) {
                System.out.println("Index of Candidate: ");
                int index = Integer.valueOf(in.nextLine().trim());
                Candidate candidate = candidates[index - 1];
                List<Certificate> certificates = certificateController.getCertificatesByCandidate(candidate);
                this.showCertificates((Certificate[]) certificates.toArray());
                System.out.println("Input index of certificate: ");
                int cerIndex = Integer.valueOf(in.nextLine().trim());
                certificateController.delete(certificates.get(cerIndex - 1));
            }
            if (choice == 3) {
                this.showMainMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number");
        }
    }

    private void showCertificates(Certificate[] certificates) {
        System.out.println("| Certificate Name    | Certificate Rank     | Certificate Day    | Indexes    |");
        for (int i = 0; i < certificates.length; i++) {
            System.out.println(
                    "| " + certificates[i].getCertificatedName() + "    | " + certificates[i].getCertificatedRank()
                            + "    | " + certificates[i].getCertificatedDate().toString() + "    | " + (i + 1));
        }
    }

    private void certificateToSaveOrUpdate(Candidate candidate, final Certificate certificate,
            List<Certificate> certificates) throws SQLException {
        int sizeOfCertificates;
        if (certificates == null) {
            certificates = new ArrayList<>();
            sizeOfCertificates = 0;
        } else {
            sizeOfCertificates = certificates.size();
        }
        CertificateController certificateController = CertificateController.init();
        StringBuilder option = new StringBuilder("insert");
        if (certificate.getCandidateID() != null && !certificates.isEmpty()) {
            certificates.forEach(ce -> {
                try {
                    if (ce.getCertificatedID().equals(certificate.getCandidateID())) {
                        option.delete(0, option.length());
                        option.append("update");
                    }
                } catch (Exception e) {
                    throw new SystemInterruptedException("Something is wrong", e);
                }
            });
        }
        while (true) {
            System.out.println("Input certificate's name (Enter to skip): ");
            certificate.setCertificatedName(
                    in.nextLine().trim() == null ? certificate.getCertificatedName() : in.nextLine().trim());
            System.out.println("Input day receive certificate (Enter to skip): ");
            certificate.setCertificatedDate(in.nextLine().trim() == null ? certificate.getCertificatedDate()
                    : Validator.checkInvalidDate(in.nextLine().trim()));
            System.out.println("Input certificate's rank (Enter to skip): ");
            certificate.setCertificatedRank(
                    in.nextLine().trim() == null ? certificate.getCertificatedRank() : in.nextLine().trim());
            certificate.setCandidate(candidate);
            if (option.toString().equals("insert")) {
                certificates.add(certificate);
                System.out.println("Do you want add another certificate for this candidate ? Y/n");
                if (in.nextLine().trim().equalsIgnoreCase("Y")) {
                    continue;
                } else {
                    List<Certificate> listToSave = certificates.subList(sizeOfCertificates,
                            certificates.size());
                    listToSave.forEach(ce -> {
                        try {
                            certificateController.saveOrUpdate(ce);
                        } catch (SQLException e) {
                            throw new SystemInterruptedException("System has problem, please try again", e);
                        }
                    });
                }
            } else {
                System.out.println("Do you want to update ? Y/n");
                if (in.nextLine().trim().equalsIgnoreCase("n")) {
                    this.showMainMenu();
                } else {
                    certificateController.saveOrUpdate(certificate);
                }
            }
        }
    }

    private void showCandidateAndTheirCertificate(Candidate[] candidates) {
        System.out.println("| Full Name    | Candidate_Type    | Total Certificate    | Indexes    |");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(" " + candidates[i].getFullName() + "    | " + candidates[i].getCandidateType()
                    + "    | " + candidates[i].getTotalCertificate() + "    |" + (i + 1) + "    |");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("If you want to modify certificate of candidate, choose the option and use Indexes column");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private void showExperiences(Experience[] experiences, String option) {
        System.out.println(
                "Full Name    | Birth Day    | Phone    | Email    | Exp in year   | Advance skill    | Serial    ");
        for (int i = 0; i < experiences.length; i++) {
            System.out.println(experiences[i].getFullName() + "    | " + experiences[i].getBirthDate() + "    | "
                    + experiences[i].getPhone() + "    | " + experiences[i].getEmail() + "    | "
                    + experiences[i].getExpInYear()
                    + "    | " + experiences[i].getProSkill() + "    | " + (i + 1));
        }
        System.out.println();
        System.out.println();
        System.out.println("Press any key and enter to exit to find center....");
        if (in.nextLine() != null && option.equals("find")) {
            this.showMenuFind();
        }
        if (in.nextLine() != null && option.equals("edit")) {
            this.showMenuEdit();
        }
        if (in.nextLine() != null && option.equals("delete")) {
            this.showMenuDelete();
        }
        if (in.nextLine() != null && option.equals("list")) {
            this.showAllCandidate();
        }
    }

    private void showFreshers(Fresher[] freshers, String option) {
        System.out.println(
                "Full Name    | Birth Day    | Phone    | Email    | Graduation Day    | Graduation Rank    | University    | Serial    ");
        for (int i = 0; i < freshers.length; i++) {
            System.out.println(freshers[i].getFullName() + "    | " + freshers[i].getBirthDate() + "    | "
                    + freshers[i].getPhone() + "    | " + freshers[i].getEmail() + "    | "
                    + freshers[i].getGraduationDate().toString()
                    + "    | " + freshers[i].getGraduationRank() + "    | " + freshers[i].getEducation() + "    | "
                    + (i + 1));
        }
        System.out.println();
        System.out.println();
        System.out.println("Press any key and enter to exit to find center....");
        if (in.nextLine() != null && option.equals("find")) {
            this.showMenuFind();
        }
        if (in.nextLine() != null && option.equals("edit")) {
            this.showMenuEdit();
        }
        if (in.nextLine() != null && option.equals("delete")) {
            this.showMenuDelete();
        }
        if (in.nextLine() != null && option.equals("list")) {
            this.showAllCandidate();
        }
    }

    private void showInterns(Intern[] interns, String option) {
        System.out.println(
                "Full Name    | Birth Day    | Phone    | Email    | Major    | Semester    | University    | Serial    ");
        for (int i = 0; i < interns.length; i++) {
            System.out.println(interns[i].getFullName() + "    | " + interns[i].getBirthDate() + "    | "
                    + interns[i].getPhone() + "    | " + interns[i].getEmail() + "    | " + interns[i].getMajor()
                    + "    | " + interns[i].getSemester() + "    | " + interns[i].getUniversityName() + "    | "
                    + (i + 1));
        }
        System.out.println();
        System.out.println();
        System.out.println("Press any key and enter to exit to find center....");
        if (in.nextLine() != null && option.equals("find")) {
            this.showMenuFind();
        }
        if (in.nextLine() != null && option.equals("edit")) {
            this.showMenuEdit();
        }
        if (in.nextLine() != null && option.equals("delete")) {
            this.showMenuDelete();
        }
        if (in.nextLine() != null && option.equals("list")) {
            this.showAllCandidate();
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
        System.out.println("6. Print all candidate and their certificate");
        System.out.println("7. Exit");
        System.out.println("------------------------------------------------");
        System.out.println("Enter your choice: ");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 7) {
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
        while (true) {
            System.out
                    .println(
                            "You can add intern with two ways (add list intern or only one intern). What do you choice ?");
            System.out.println(" 1. Add intern with list. ");
            System.out.println(" 2. Add only one intern. ");
            System.out.println(" 3. Exit");
            int choice = Validator.checkInputInt(in.nextLine().trim());
            if (choice < 1 && choice > 3) {
                System.out.println("Please try again");
            } else {
                return choice;
            }

        }
    }

    private int addFresherMenu() {
        while (true) {
            System.out
                    .println(
                            "You can add fresher with two ways (add list fresher or only one fresher). What do you choice ?");
            System.out.println(" 1. Add fresher with list. ");
            System.out.println(" 2. Add only one fresher. ");
            System.out.println(" 3. Exit");
            int choice = Validator.checkInputInt(in.nextLine().trim());
            if (choice < 1 && choice > 3) {
                System.out.println("Please try again");
            } else {
                return choice;
            }
        }
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

    private int findInternMenu() {
        System.out.println("   FIND INTERN   ");
        System.out.println("-----------------");
        System.out.println("1. Find by name");
        System.out.println("2. Find by major");
        System.out.println("3. Find by university");
        System.out.println("4. Exit");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 4) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int findFresherMenu() {
        System.out.println("   FIND FRESHER   ");
        System.out.println("-----------------");
        System.out.println("1. Find by name");
        System.out.println("2. Find by rank");
        System.out.println("3. Find by university");
        System.out.println("4. Exit");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 4) {
            System.out.println("Please try again");
        }
        return choice;
    }

    private int findExperienceMenu() {
        System.out.println("   FIND EXPERIENCE   ");
        System.out.println("-----------------");
        System.out.println("1. Find by name");
        System.out.println("2. Find by experience in year");
        System.out.println("3. Find by advance skill");
        System.out.println("4. Exit");
        int choice = Validator.checkInputInt(in.nextLine().trim());
        if (choice < 1 && choice > 4) {
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

    private Experience editExperience(Experience experience) {
        String[] now = LocalDate.now().toString().split("-");
        System.out.println("If you want to skip field please press space and enter");
        while (true) {
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

    private Fresher editFresher(Fresher fresher) {
        String[] now = LocalDate.now().toString().split("-");
        System.out.println("If you want to skip field please press space and enter");
        while (true) {
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

    private Intern editIntern(Intern intern) {
        String[] now = LocalDate.now().toString().split("-");
        System.out.println("If you want to skip field please press space and enter");
        while (true) {
            System.out.println("Input intern's full name (press space and enter to skip): ");
            intern.setFullName(in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getFullName());
            System.out.println(
                    "Input intern's birth day (year must be > 1900 and <" + " " + now[0] + "). Pattern: yyyy-MM-dd");
            intern.setBirthDate(Validator.checkInvalidDate(
                    in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getBirthDate().toString()));
            System.out.println("Input intern's phone (10 digits): ");
            intern.setPhone(
                    Validator.checkPhone(in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getPhone()));
            System.out.println("Input intern's email (must be has @fsoft.com.vn)");
            intern.setEmail(Validator
                    .checkInvalidEmail(in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getEmail()));
            System.out.println("Input intern's major (Do not input white space): ");
            intern.setMajor(in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getMajor());
            System.out.println("Input semester (Do not input white space): ");
            intern.setSemester(Integer.valueOf(
                    in.nextLine().trim().isEmpty() ? in.nextLine().trim() : String.valueOf(intern.getSemester())));
            System.out.println("Input intern's university name: ");
            intern.setUniversityName(
                    in.nextLine().trim().isEmpty() ? in.nextLine().trim() : intern.getUniversityName());
            return intern;
        }
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