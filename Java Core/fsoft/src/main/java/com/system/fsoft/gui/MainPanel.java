package com.system.fsoft.gui;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
import com.system.fsoft.exception.BirthDateException;
import com.system.fsoft.exception.EmailException;
import com.system.fsoft.exception.PhoneException;
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
		this.mainMenu();
		int choice = Integer.parseInt(in.nextLine().trim());
		if (choice < 1 || choice > 5) {
			throw new SystemInterruptedException("Input value from 1 to 5", null);
		}
		List<Candidate> candidates;
		switch (choice) {
		case 1:
			candidates = this.createCandidate(new ArrayList<>());
			System.out.println("Are you sure ? Y/n");
			if (in.nextLine().trim().equalsIgnoreCase("y")) {
				List<Intern> interns = new ArrayList<>();
				List<Experience> experiences = new ArrayList<>();
				List<Fresher> freshers = new ArrayList<>();
				candidates.forEach((c) -> {
					if (c instanceof Intern) {
						Intern intern = (Intern) c;
						interns.add(intern);
					}
					if (c instanceof Experience) {
						Experience experience = (Experience) c;
						experiences.add(experience);
					}
					if (c instanceof Fresher) {
						Fresher fresher = (Fresher) c;
						freshers.add(fresher);
					}
				});
				if (!interns.isEmpty()) {
					InternController internController = InternController.init(new HashSet<>(interns));
					internController.saveAll();
				}
				if (!experiences.isEmpty()) {
					ExperienceController experienceController = ExperienceController.init(new HashSet<>(experiences));
					experienceController.saveAll();
				}
				if (!freshers.isEmpty()) {
					FresherController fresherController = FresherController.init(new HashSet<>(freshers));
					fresherController.saveAll();
				}
			} else {
				this.createCandidate(candidates);
			}
			break;
		case 2:
			candidates = CandidateController.init().getAll();
			this.showCandidates((Candidate[]) candidates.toArray());
			System.out.println("Do you want update ? Use indexes column");
			System.out.println("Input index: ");
			try {
				int index = Integer.parseInt(in.nextLine().trim());
				Candidate candidate = this.showCandidateForEdit(candidates.get(index - 1));
				if (candidate instanceof Experience) {
					Experience experience = (Experience) candidate;
					ExperienceController experienceController = ExperienceController.init();
					experienceController.edit(experience);
				}
				if (candidate instanceof Fresher) {
					Fresher fresher = (Fresher) candidate;
					FresherController fresherController = FresherController.init();
					fresherController.edit(fresher);
				}
				if (candidate instanceof Intern) {
					Intern intern = (Intern) candidate;
					InternController internController = InternController.init();
					internController.edit(intern);
				}
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Something wrong with your input !");
				this.mainMenu();
			}
			break;
		case 3:
			candidates = CandidateController.init().getAll();
			this.showCandidates((Candidate[]) candidates.toArray());
			System.out.println("Do you want update ? Use indexes column");
			System.out.println("Input index: ");
			try {
				int index = Integer.parseInt(in.nextLine().trim());
				Candidate candidate = candidates.get(index - 1);
				if (candidate.getCandidateType() == 0) {
					ExperienceController experienceController = ExperienceController.init();
					Experience experience = new Experience();
					experience.setCandidateID(candidate.getCandidateID());
					experienceController.delete(experience);
				}
				if (candidate.getCandidateType() == 1) {
					FresherController fresherController = FresherController.init();
					Fresher fresher = new Fresher();
					fresher.setCandidateID(candidate.getCandidateID());
					fresherController.delete(fresher);
				}
				if (candidate.getCandidateType() == 2) {
					InternController internController = InternController.init();
					Intern intern = new Intern();
					intern.setCandidateID(candidate.getCandidateID());
					internController.delete(intern);
				}
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Something wrong with your input !");
				this.mainMenu();
			}
			break;
		case 4:
			candidates = CandidateController.init().getAllCandidateAndTheirCertidicate();
			candidates.sort(Comparator.nullsLast(Comparator.comparingInt(Candidate::getCandidateType).reversed()
					.thenComparing(Candidate::getBirthDate)));
			this.showCandidates((Candidate[]) candidates.toArray());
			try {
				int yourChoice = this.showCandidateForAddCertificate(candidates);
				if (yourChoice < 1 && yourChoice > 3) {
					System.out.println("You should be input value 1 - 3");
					this.mainMenu();
				}
				CertificateController certificateController = CertificateController.init();
				Candidate candidate;
				List<Certificate> certificates;
				switch (yourChoice) {
				case 1:
					System.out.println("Candidate index ?");
					int index = Integer.parseInt(in.nextLine().trim());
					candidate = candidates.get(index - 1);
					certificates = this.getInfoOfCertificate(candidate,
							certificateController.getCertificatesByCandidate(candidate));
					certificates.forEach(ce -> {
						try {
							certificateController.saveOrUpdate(ce);
						} catch (SQLException e) {
							throw new SystemInterruptedException("System has problem, please try later", e);
						}
					});
					break;
				case 2:
					System.out.println("Candidate index ?");
					int idx = Integer.parseInt(in.nextLine().trim());
					candidate = candidates.get(idx - 1);
					certificates = certificateController.getCertificatesByCandidate(candidate);
					this.showCertificateInfo(certificates);
					System.out.println("Input certificate index");
					try {
						int certificateIndex = Integer.parseInt(in.nextLine().trim());
						certificateController.delete(certificates.get(certificateIndex - 1));
					} catch (Exception e) {
						System.out.println("Something wrong");
						this.mainMenu();
					}
					break;
				case 3:
					this.mainMenu();
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You should be input value 1 - 5");
				this.mainMenu();
			}
			break;
		case 5:
			System.out.println("See you later !");
			break;
		}
	}

	private void showCertificateInfo(List<Certificate> certificates) {
		System.out.println("| Certificate name    | Certificate rank    | Certificate date | index    |");
		for (Certificate certificate : certificates) {
			System.out.println("| " + certificate.getCertificatedName() + "    | " + certificate.getCertificatedRank()
					+ "    | " + certificate.getCertificatedDate() + " |" + certificates.indexOf(certificate) + 1
					+ "    |");
		}
	}

	private void mainMenu() {
		System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION  ");
		System.out.println("------------------------------------------------");
		System.out.println("1. Create candidate");
		System.out.println("2. Update candidate");
		System.out.println("3. Delete candidate");
		System.out.println("4. Show all candidate (add certificate for candidate here)");
		System.out.println("5. Exit");
	}

	private List<Certificate> getInfoOfCertificate(Candidate candidate, List<Certificate> certificates) {
		if (certificates == null) {
			certificates = new ArrayList<>();
		}
		Certificate certificate = new Certificate();
		System.out.println("Certificate name ?");
		String name = in.nextLine().trim();
		certificate.setCertificatedName(name);
		System.out.println("Certificate rank ?");
		try {
			String rank = in.nextLine().trim();
			certificate.setCertificatedRank(Validator.checkGraduationRank(rank));
		} catch (BirthDateException e) {
			certificate.setCertificatedRank("F");
		}
		System.out.println("Graduation date ?");
		try {
			String graduationDate = in.nextLine().trim();
			certificate.setCertificatedDate(Validator.checkInvalidDate(graduationDate));
		} catch (BirthDateException | IllegalArgumentException e) {
			certificate.setCertificatedDate(Date.valueOf(LocalDate.now()));
		}
		certificate.setCandidate(candidate);
		certificates.add(certificate);
		System.out.println("Would you like to add another certificate ? Y/n");
		if (in.nextLine().trim().equalsIgnoreCase("n")) {
			return certificates;
		} else {
			return getInfoOfCertificate(candidate, certificates);
		}

	}

	private int showCandidateForAddCertificate(Collection<Candidate> candidates) {
		int noCertificate = 0;
		for (Candidate candidate : candidates) {
			if (candidate.getTotalCertificate() == 0) {
				noCertificate += 1;
			}
		}
		System.out.println("There is " + noCertificate
				+ " candidate not have certificate. Would you like to add certificate for them ?");
		System.out.println("1. Add certificate for candidate (using index)");
		System.out.println("2. Remove certificate of candidate");
		System.out.println("3. Exit to main menu");
		System.out.println("----------------------------------------------");
		System.out.println("Your choice ?");
		return Integer.parseInt(in.nextLine().trim());
	}

	private void showCandidates(Candidate[] candidates) {
		System.out.println("| Full Name    | Birth Day    | Phone    | Email     | Type    | Serial    |");
		for (int i = 0; i < candidates.length; i++) {
			System.out.println(candidates[i].getFullName() + "    | " + candidates[i].getBirthDate() + "    | "
					+ candidates[i].getPhone() + "    | " + candidates[i].getEmail() + "    | "
					+ candidates[i].getCandidateType() + "    | " + (i + 1));
		}
	}

	private Candidate showCandidateForEdit(Candidate candidate) throws SQLException {
		System.out.println("Enter candidate's name (enter to skip): ");
		candidate.setFullName(in.nextLine().trim().isEmpty() ? candidate.getFullName() : in.nextLine().trim());
		System.out.println("Enter candidate's birth date (enter to skip): ");
		try {
			candidate.setBirthDate(in.nextLine().trim().isEmpty() ? candidate.getBirthDate()
					: Validator.checkInvalidDate(in.nextLine().trim()));
		} catch (BirthDateException e) {
			System.out.println(e.getMessage());
			candidate.setBirthDate(Date.valueOf(LocalDate.now()));
		}
		System.out.println("Enter canidate's phone (enter to skip): ");
		try {
			candidate.setPhone(
					in.nextLine().trim().isEmpty() ? candidate.getPhone() : Validator.checkPhone(in.nextLine().trim()));
		} catch (PhoneException e) {
			System.out.println(e.getMessage());
			candidate.setPhone("0000000000");
		}
		System.out.println("Enter candidate's email: ");
		try {
			candidate.setEmail(in.nextLine().trim().isEmpty() ? candidate.getEmail()
					: Validator.checkInvalidEmail(in.nextLine().trim()));
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
			candidate.setEmail(builder.replace(4, builder.length(), "@fsoft.com.vn").toString());
		}
		if (candidate.getCandidateType() == 0) {
			ExperienceController experienceController = ExperienceController.init();
			Experience experience = experienceController.getByID(candidate.getCandidateID());
			System.out.println("Enter exp in year (enter to skip): ");
			try {
				float expInYear = Float.parseFloat(in.nextLine().trim());
				experience.setExpInYear(expInYear > 0 ? expInYear : Float.parseFloat("0.5"));
			} catch (NullPointerException | NumberFormatException e) {
				experience.setExpInYear(experience.getExpInYear());
			}
			System.out.println("Enter advanced skill (pro skill) - enter to skip: ");
			try {
				int proSkill = Integer.parseInt(in.nextLine().trim());
				experience.setProSkill(proSkill > 0 ? proSkill : 1);
			} catch (NumberFormatException e) {
				experience.setProSkill(experience.getProSkill());
			}
			return experience;
		}
		if (candidate.getCandidateType() == 1) {
			FresherController fresherController = FresherController.init();
			Fresher fresher = fresherController.getByID(candidate.getCandidateID());
			System.out.println("Enter new graduation date (enter to skip)");
			try {
				String graduationDate = in.nextLine().trim();
				fresher.setGraduationDate(
						graduationDate.isEmpty() ? fresher.getBirthDate() : Validator.checkInvalidDate(graduationDate));
			} catch (BirthDateException e) {
				System.out.println(e.getMessage());
				fresher.setGraduationDate(Date.valueOf(LocalDate.now()));
			}
			System.out.println("Enter graduation rank (D- -> A+) - enter to skip: ");
			try {
				String graduationRank = in.nextLine().trim();
				fresher.setGraduationRank(graduationRank.isEmpty() ? fresher.getGraduationRank()
						: Validator.checkGraduationRank(graduationRank));
			} catch (BirthDateException e) {
				fresher.setGraduationRank("D-");
			}
			System.out.println("Enter fresher's university (enter to skip): ");
			String university = in.nextLine().trim();
			fresher.setEducation(university.isEmpty() ? fresher.getEducation() : university);
			return fresher;
		}
		if (candidate.getCandidateType() == 2) {
			InternController internController = InternController.init();
			Intern intern = internController.getByID(candidate.getCandidateID());
			System.out.println("Enter major (enter to skip): ");
			String major = in.nextLine().trim();
			intern.setMajor(major.isEmpty() ? intern.getMajor() : major);
			System.err.println("Enter semester (enter to skip): ");
			try {
				int semester = Integer.parseInt(in.nextLine().trim());
				intern.setSemester(semester > 0 ? semester : 1);
			} catch (NumberFormatException e) {
				intern.setSemester(intern.getSemester());
			}
			System.out.println("Enter university name");
			String university = in.nextLine().trim();
			intern.setUniversityName(university.isEmpty() ? intern.getUniversityName() : university);
			return intern;
		}
		System.out.println("Type of this candidate is not yet. Do you change type of candidate ? Y/n");
		String answer = in.nextLine().trim();
		if (answer.equalsIgnoreCase("y")) {
			try {
				int type = Integer.parseInt(in.nextLine().trim());
				candidate.setCandidateType(type > 2 ? -1 : type);
				if (type == 0) {
					Experience experience = Experience.of(candidate);
					System.out.println("Enter exp in year: ");
					try {
						float expInYear = Float.parseFloat(in.nextLine().trim());
						experience.setExpInYear(expInYear > 0 ? expInYear : Float.parseFloat("0.5"));
					} catch (NullPointerException | NumberFormatException e) {
						System.out.println("You enter wrong value ? The default value will be apply");
						experience.setExpInYear(Float.parseFloat("0.5"));
					}
					System.out.println("Enter advanced skill (pro skill): ");
					try {
						int proSkill = Integer.parseInt(in.nextLine().trim());
						experience.setProSkill(proSkill > 0 ? proSkill : 1);
					} catch (NumberFormatException e) {
						System.out.println("Wrong format !");
						experience.setProSkill(1);
					}
					return experience;
				}
				if (type == 1) {
					Fresher fresher = Fresher.of(candidate);
					System.out.println("Enter graduation day: ");
					try {
						fresher.setGraduationDate(Validator.checkInvalidDate(in.nextLine().trim()));
					} catch (BirthDateException e) {
						System.out.println(e.getMessage());
						fresher.setGraduationDate(Date.valueOf(LocalDate.now()));
					}
					System.out.println("Enter graduation rank (D- -> A+): ");
					try {
						fresher.setGraduationRank(Validator.checkGraduationRank(in.nextLine().trim()));
					} catch (BirthDateException e) {
						fresher.setGraduationRank("D-");
					}
					System.out.println("Enter fresher's university: ");
					fresher.setEducation(in.nextLine().trim());
					return fresher;
				}
				if (type == 2) {
					Intern intern = Intern.of(candidate);
					System.out.println("Enter major: ");
					intern.setMajor(in.nextLine().trim());
					System.err.println("Enter semester: ");
					try {
						int semester = Integer.parseInt(in.nextLine().trim());
						intern.setSemester(semester > 0 ? semester : 1);
					} catch (NumberFormatException e) {
						intern.setSemester(1);
					}
					System.out.println("Enter university name");
					intern.setUniversityName(in.nextLine().trim());
					return intern;
				}
			} catch (NumberFormatException e) {
				System.out.println("Wrong type !");
				candidate.setCandidateType(-1);
			}
		}
		return candidate;
	}

	private List<Candidate> createCandidate(List<Candidate> candidates) {
		Candidate candidate = new Candidate();
		System.out.println("Enter candidate's name: ");
		candidate.setFullName(in.nextLine().trim());
		System.out.println("Enter candidate's birth date: ");
		try {
			candidate.setBirthDate(Validator.checkInvalidDate(in.nextLine().trim()));
		} catch (BirthDateException e) {
			System.out.println(e.getMessage());
			candidate.setBirthDate(Date.valueOf(LocalDate.now()));
		}
		System.out.println("Enter canidate's phone: ");
		try {
			candidate.setPhone(Validator.checkPhone(in.nextLine().trim()));
		} catch (PhoneException e) {
			System.out.println(e.getMessage());
			candidate.setPhone("0000000000");
		}
		System.out.println("Enter candidate's email: ");
		try {
			candidate.setEmail(Validator.checkInvalidEmail(in.nextLine().trim()));
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
			candidate.setEmail(builder.replace(4, builder.length(), "@fsoft.com.vn").toString());
		}
		System.out.println("Type of candidate ? (0: Experience, 1: Fresher , 2: Intern)");
		try {
			String type = in.nextLine().trim();
			if (Integer.parseInt(type) > 2) {
				candidate.setCandidateType(-1);
			} else {
				candidate.setCandidateType(Integer.parseInt(type));
			}
			if (type.equals("0")) {
				Experience experience = Experience.of(candidate);
				System.out.println("Enter exp in year: ");
				try {
					float expInYear = Float.parseFloat(in.nextLine().trim());
					experience.setExpInYear(expInYear > 0 ? expInYear : Float.parseFloat("0.5"));
				} catch (NullPointerException | NumberFormatException e) {
					System.out.println("You enter wrong value ? The default value will be apply");
					experience.setExpInYear(Float.parseFloat("0.5"));
				}
				System.out.println("Enter advanced skill (pro skill): ");
				try {
					int proSkill = Integer.parseInt(in.nextLine().trim());
					experience.setProSkill(proSkill > 0 ? proSkill : 1);
				} catch (NumberFormatException e) {
					System.out.println("Wrong format !");
					experience.setProSkill(1);

				}
			}
			if (type.equals("1")) {
				Fresher fresher = Fresher.of(candidate);
				System.out.println("Enter graduation day: ");
				try {
					fresher.setGraduationDate(Validator.checkInvalidDate(in.nextLine().trim()));
				} catch (BirthDateException e) {
					System.out.println(e.getMessage());
					fresher.setGraduationDate(Date.valueOf(LocalDate.now()));
				}
				System.out.println("Enter graduation rank (D- -> A+): ");
				try {
					fresher.setGraduationRank(Validator.checkGraduationRank(in.nextLine().trim()));
				} catch (BirthDateException e) {
					fresher.setGraduationRank("D-");
				}
				System.out.println("Enter fresher's university: ");
				fresher.setEducation(in.nextLine().trim());
				candidates.add(fresher);
			}
			if (type.equals("2")) {
				Intern intern = Intern.of(candidate);
				System.out.println("Enter major: ");
				intern.setMajor(in.nextLine().trim());
				System.err.println("Enter semester: ");
				try {
					int semester = Integer.parseInt(in.nextLine().trim());
					intern.setSemester(semester > 0 ? semester : 1);
				} catch (NumberFormatException e) {
					intern.setSemester(1);
				}
				System.out.println("Enter university name");
				intern.setUniversityName(in.nextLine().trim());
				candidates.add(intern);
			}
		} catch (NumberFormatException e) {
			System.out.println("Wrong type !");
			candidate.setCandidateType(-1);
		}
		System.out.println("Do you want add another candidate ? Y/n");
		if (in.nextLine().trim().equalsIgnoreCase("Y")) {
			createCandidate(candidates);
		}
		return candidates;
	}
}