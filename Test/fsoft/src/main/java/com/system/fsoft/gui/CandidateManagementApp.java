package com.system.fsoft.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import com.system.fsoft.utils.Validator;

public class CandidateManagementApp {

	private final Logger log = LogManager.getLogger(CandidateManagementApp.class.getName());

	private Scanner in = new Scanner(System.in);
	private TableList tableCandidate = this.createTableCandidate();
	private TableList tableExperience = this.creaTableExperience();
	private TableList tableIntern = this.creaTableIntern();
	private TableList tableFresher = this.creaTableFresher();
	private TableList tableCandidateWithCertificate = this.createTableCandidateWithCer();
	private TableList tableCertificate = this.createTableCertificate();
	private CertificateController certificateController = CertificateController.init();

	private CandidateManagementApp() {
	}

	private void mainMenu() {
		System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION  ");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. Create candidate");
		System.out.println("2. Show all candidate (update, delete, add certificate here)");
		System.out.println("3. Insert candidate via csv file");
		System.out.println("4. Exit");
		System.out.println("------------------------------------------------------------");
		log.info("It work");
	}

	private void subMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("1. Edit candidate using serial");
		System.out.println("2. Delete candidate using serial");
		System.out.println("3. View certificate of candidate using serial");
		System.out.println("4. All experience");
		System.out.println("5. All fresher");
		System.out.println("6. All intern");
		System.out.println("7. Exit");
		System.out.println("----------------------------------------------------");
	}

	private void certificateMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("1. Add certificate for candidate");
		System.out.println("2. Delete certificate using serial");
		System.out.println("3. Exit");
		System.out.println("----------------------------------------------------");
	}

	private Candidate getInfomation() {
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
				return experience;
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
				return fresher;
			}
			if (type.equals("2")) {
				Intern intern = Intern.of(candidate);
				System.out.println("Enter major: ");
				intern.setMajor(in.nextLine().trim());
				System.out.println("Enter semester: ");
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
			return candidate;
		}
		return candidate;
	}

	private Candidate edit(Candidate candidate) throws SQLException {
		System.out.println("Enter candidate's name (enter to skip): ");
		String name = in.nextLine().trim();
		candidate.setFullName(name.isEmpty() ? candidate.getFullName() : name);
		System.out.println("Enter candidate's birth date (enter to skip): ");
		try {
			String birthDate = in.nextLine().trim();
			candidate.setBirthDate(
					birthDate.isEmpty() ? candidate.getBirthDate() : Validator.checkInvalidDate(birthDate));
		} catch (BirthDateException e) {
			System.out.println(e.getMessage());
			candidate.setBirthDate(Date.valueOf(LocalDate.now()));
		}
		System.out.println("Enter canidate's phone (enter to skip): ");
		try {
			String phone = in.nextLine().trim();
			candidate.setPhone(phone.isEmpty() ? candidate.getPhone() : Validator.checkPhone(phone));
		} catch (PhoneException e) {
			System.out.println(e.getMessage());
			candidate.setPhone("0000000000");
		}
		System.out.println("Enter candidate's email: ");
		try {
			String email = in.nextLine().trim();
			candidate.setEmail(email.isEmpty() ? candidate.getEmail() : Validator.checkInvalidEmail(email));
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
			candidate.setEmail(builder.replace(4, builder.length(), "@fsoft.com.vn").toString());
		}
		if (candidate.getCandidateType() == 0) {
			ExperienceController experienceController = ExperienceController.init();
			Experience experience = experienceController.getByID(candidate.getCandidateID());
			experience.setFullName(candidate.getFullName());
			experience.setBirthDate(candidate.getBirthDate());
			experience.setEmail(candidate.getEmail());
			experience.setPhone(candidate.getPhone());
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
			fresher.setFullName(candidate.getFullName());
			fresher.setBirthDate(candidate.getBirthDate());
			fresher.setEmail(candidate.getEmail());
			fresher.setPhone(candidate.getPhone());
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
			intern.setFullName(candidate.getFullName());
			intern.setBirthDate(candidate.getBirthDate());
			intern.setEmail(candidate.getEmail());
			intern.setPhone(candidate.getPhone());
			System.out.println("Enter major (enter to skip): ");
			String major = in.nextLine().trim();
			intern.setMajor(major.isEmpty() ? intern.getMajor() : major);
			System.out.println("Enter semester (enter to skip): ");
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

	private Certificate receiveCertificateInfo(Candidate candidate) {
		Certificate certificate = new Certificate(candidate);
		System.out.println("Enter your certficate name: ");
		certificate.setCertificatedName(in.nextLine().trim());
		System.out.println("Enter your certificate rank: ");
		certificate.setCertificatedRank(Validator.checkGraduationRank(in.nextLine().trim()));
		System.out.println("Enter date you receive certificate: ");
		try {
			certificate.setCertificatedDate(Validator.checkInvalidDate(in.nextLine().trim()));
		} catch (BirthDateException e) {
			certificate.setCertificatedDate(Date.valueOf(LocalDate.now()));
		}
		return certificate;
	}

	private List<Candidate> readCsvFile(String link) throws IOException {
		List<Candidate> candidates = new ArrayList<>();
		StringBuilder contentBuilder = new StringBuilder();
		StringBuilder typeOfCandidate = new StringBuilder();
		String line = "";
		String[] candidateElementStrings;
		BufferedReader reader = Files.newBufferedReader(Paths.get(link));

		while ((line = reader.readLine()) != null) {
			contentBuilder.append(line);
			candidateElementStrings = contentBuilder.toString().split(",");
			int length = candidateElementStrings.length;
			for (int i = 0, j = length - 1; (i < length / 2 && j > length / 2); i++, j--) {
				if (candidateElementStrings[i].equalsIgnoreCase("experience")
						|| candidateElementStrings[j].equalsIgnoreCase("experience")
						|| candidateElementStrings[(length / 2)].equalsIgnoreCase("experience")) {
					typeOfCandidate.append("experience");
					break;
				}
				if (candidateElementStrings[i].equalsIgnoreCase("fresher")
						|| candidateElementStrings[j].equalsIgnoreCase("fresher")
						|| candidateElementStrings[(length / 2)].equalsIgnoreCase("fresher")) {
					typeOfCandidate.append("fresher");
					break;
				}
				if (candidateElementStrings[i].equalsIgnoreCase("intern")
						|| candidateElementStrings[j].equalsIgnoreCase("intern")
						|| candidateElementStrings[(length / 2)].equalsIgnoreCase("intern")) {
					typeOfCandidate.append("intern");
					break;
				}
			}
			if (typeOfCandidate.toString().equals("experience")) {
				Experience experience = new Experience();
				experience.setCandidateType(0);
				experience.setFullName(candidateElementStrings[0]);
				for (int i = 0; i < length; i++) {
					try {
						experience.setBirthDate(Validator.checkInvalidDate(candidateElementStrings[i]));
					} catch (BirthDateException e) {
						experience.setBirthDate(experience.getBirthDate() == null ? null : experience.getBirthDate());
					}
					try {
						experience.setPhone(Validator.checkPhone(candidateElementStrings[i]));
					} catch (PhoneException e) {
						experience.setPhone(experience.getPhone() == null ? null : experience.getPhone());
					}
					try {
						experience.setEmail(Validator.checkInvalidEmail(candidateElementStrings[i]));
					} catch (EmailException e) {
						experience.setEmail(experience.getEmail() == null ? null : experience.getEmail());
					}
					if (candidateElementStrings[i].matches("^[0-9]{1,2}[.][0-9]{1,2}$")) {
						experience.setExpInYear(Float.parseFloat(candidateElementStrings[i]));
					}
					if (candidateElementStrings[i].matches("^[0-9]{1,2}$")) {
						experience.setProSkill(Integer.parseInt(candidateElementStrings[i]));
					}
				}
				candidates.add(experience);
				typeOfCandidate.replace(0, typeOfCandidate.length(), "");
				contentBuilder.replace(0, contentBuilder.length(), "");
				candidateElementStrings = new String[0];
			}
			if (typeOfCandidate.toString().equals("fresher")) {
				Fresher fresher = new Fresher();
				fresher.setFullName(candidateElementStrings[0]);
				fresher.setCandidateType(1);
				fresher.setEducation(candidateElementStrings[length - 1]);
				for (int i = 0; i < length; i++) {
					try {
						fresher.setBirthDate(
								fresher.getBirthDate() == null ? Validator.checkInvalidDate(candidateElementStrings[i])
										: fresher.getBirthDate());
					} catch (BirthDateException e) {
						fresher.setBirthDate(fresher.getBirthDate() == null ? null : fresher.getBirthDate());
					}
					try {
						fresher.setPhone(Validator.checkPhone(candidateElementStrings[i]));
					} catch (PhoneException e) {
						fresher.setPhone(fresher.getPhone() == null ? null : fresher.getPhone());
					}
					try {
						fresher.setEmail(Validator.checkInvalidEmail(candidateElementStrings[i]));
					} catch (EmailException e) {
						fresher.setEmail(fresher.getEmail() == null ? null : fresher.getEmail());
					}
					if (i > 2) {
						try {
							fresher.setGraduationDate(Validator.checkInvalidDate(candidateElementStrings[i]));
						} catch (Exception e) {
							fresher.setGraduationDate(
									fresher.getGraduationDate() == null ? null : fresher.getGraduationDate());
						}
					}
					try {
						fresher.setGraduationRank(Validator.checkGraduationRank(candidateElementStrings[i]));
					} catch (BirthDateException e) {
						fresher.setGraduationRank(
								fresher.getGraduationRank() == null ? null : fresher.getGraduationRank());
					}
				}
				candidates.add(fresher);
				typeOfCandidate.replace(0, typeOfCandidate.length(), "");
				contentBuilder.replace(0, contentBuilder.length(), "");
				candidateElementStrings = new String[0];
			}
			if (typeOfCandidate.toString().equals("intern")) {
				Intern intern = new Intern();
				intern.setCandidateType(2);
				intern.setFullName(candidateElementStrings[0]);
				intern.setUniversityName(candidateElementStrings[candidateElementStrings.length - 1]);
				for (int i = 0; i < length; i++) {
					try {
						intern.setBirthDate(Validator.checkInvalidDate(candidateElementStrings[i]));
					} catch (BirthDateException e) {
						intern.setBirthDate(intern.getBirthDate() == null ? null : intern.getBirthDate());
					}
					try {
						intern.setPhone(Validator.checkPhone(candidateElementStrings[i]));
					} catch (PhoneException e) {
						intern.setPhone(intern.getPhone() == null ? null : intern.getPhone());
					}
					try {
						intern.setEmail(Validator.checkInvalidEmail(candidateElementStrings[i]));
					} catch (EmailException e) {
						intern.setEmail(intern.getEmail() == null ? null : intern.getEmail());
					}
					if (i == 5) {
						intern.setMajor(intern.getMajor() == null ? candidateElementStrings[i] : intern.getMajor());
					}
					if (candidateElementStrings[i].matches("^[0-9]{1,2}$")) {
						intern.setSemester(Integer.parseInt(candidateElementStrings[i]));
					}
				}
				candidates.add(intern);
				typeOfCandidate.replace(0, typeOfCandidate.length(), "");
				contentBuilder.replace(0, contentBuilder.length(), "");
				candidateElementStrings = new String[0];
			}
		}
		reader.close();
		return candidates;
	}

	private void exit() {
		System.out.println("See you later !!!");
		in.close();
	}

	protected TableList createTableCandidate() {
		return this.tableCandidate = new TableList("ID", "Full Name", "Birth Day", "Phone", "Email", "Type", "Serial");
	}

	protected TableList creaTableExperience() {
		return this.tableExperience = new TableList("ID", "Full Name", "Birth Day", "Phone", "Email", "Exp In Year",
				"Pro Skill", "Serial").sortBy(0);
	}

	protected TableList creaTableFresher() {
		return this.tableFresher = new TableList("ID", "Full Name", "Birth Day", "Phone", "Email", "Graduation Day",
				"Graduation Rank", "Univerisy", "Serial").sortBy(0);
	}

	protected TableList creaTableIntern() {
		return this.tableIntern = new TableList("ID", "Full Name", "Birth Day", "Phone", "Email", "Major", "Semester",
				"University", "Serial").sortBy(0);
	}

	protected TableList createTableCandidateWithCer() {
		return this.tableCandidateWithCertificate = new TableList("ID", "Full Name", "Type", "Total Certificate",
				"Serial");
	}

	protected TableList createTableCertificate() {
		return this.tableCertificate = new TableList("Certificate Name", "Certificate Rank", "Certificate_Day",
				"Serial");
	}

	public static CandidateManagementApp create() {
		return new CandidateManagementApp();
	}

	public void run() throws Exception {
		while (true) {
			this.mainMenu();
			int choice = 0;
			try {
				choice = Integer.parseInt(in.nextLine().trim());
			} catch (NumberFormatException e) {
				choice = 3;
			}
			switch (choice) {
			case 1:
				Candidate candidate = this.getInfomation();
				if (candidate.getCandidateType() == 0) {
					ExperienceController controller = ExperienceController.init((Experience) candidate);
					controller.save();
				}
				if (candidate.getCandidateType() == 1) {
					FresherController controller = FresherController.init((Fresher) candidate);
					controller.save();
				}
				if (candidate.getCandidateType() == 2) {
					InternController controller = InternController.init((Intern) candidate);
					controller.save();
				}
				if (candidate.getCandidateType() == -1) {
					System.out.println("Type of candidate must not be null");
				}
				break;
			case 2:
				if (this.tableCandidate.getTableSize() > 0) {
					this.tableCandidate.clearAllData();
				}
				List<Candidate> candidates = CandidateController.init().getAll();
				candidates.sort(Comparator.nullsLast(Comparator.comparingInt(Candidate::getCandidateType).reversed()
						.thenComparing(Candidate::getBirthDate)));
				candidates.forEach(c -> {
					if (c.getCandidateType() == 0) {
						this.tableCandidate.addRow(c.getCandidateID(), c.getFullName(), c.getBirthDate().toString(),
								c.getPhone(), c.getEmail(), "Experience", String.valueOf(candidates.indexOf(c) + 1));
					}
					if (c.getCandidateType() == 1) {
						this.tableCandidate.addRow(c.getCandidateID(), c.getFullName(), c.getBirthDate().toString(),
								c.getPhone(), c.getEmail(), "Fresher", String.valueOf(candidates.indexOf(c) + 1));
					}
					if (c.getCandidateType() == 2) {
						this.tableCandidate.addRow(c.getCandidateID(), c.getFullName(), c.getBirthDate().toString(),
								c.getPhone(), c.getEmail(), "Intern", String.valueOf(candidates.indexOf(c) + 1));
					}
				});
				this.tableCandidate.print();
				this.subMenu();
				int choiceSub = 0;
				try {
					choiceSub = Integer.parseInt(in.nextLine().trim());
				} catch (NumberFormatException e) {
					choiceSub = 6;
				}
				int index = 0;
				switch (choiceSub) {
				case 1:
					this.tableCandidate.print();
					System.out.println("Serial of candidate");
					try {
						index = Integer.parseInt(in.nextLine().trim());
					} catch (NumberFormatException e) {
						System.out.println("Insert number");
					}
					if (candidates.get(index - 1).getCandidateType() == 0) {
						ExperienceController experienceController = ExperienceController.init();
						Experience experience = experienceController
								.getByID(candidates.get(index - 1).getCandidateID());
						experience = (Experience) this.edit(experience);
						experienceController.edit(experience);
					}
					if (candidates.get(index - 1).getCandidateType() == 1) {
						FresherController fresherController = FresherController.init();
						Fresher fresher = fresherController.getByID(candidates.get(index - 1).getCandidateID());
						fresher = (Fresher) this.edit(fresher);
						fresherController.edit(fresher);
					}
					if (candidates.get(index - 1).getCandidateType() == 2) {
						InternController internController = InternController.init();
						Intern intern = internController.getByID(candidates.get(index - 1).getCandidateID());
						intern = (Intern) this.edit(intern);
						internController.edit(intern);
					}
					break;
				case 2:
					this.tableCandidate.print();
					System.out.println("Serial of candidate");
					try {
						index = Integer.parseInt(in.nextLine().trim());
					} catch (NumberFormatException e) {
						System.out.println("Insert number");
					}
					if (candidates.get(index - 1).getCandidateType() == 0) {
						ExperienceController experienceController = ExperienceController.init();
						Experience experience = experienceController
								.getByID(candidates.get(index - 1).getCandidateID());
						experienceController.delete(experience);
					}
					if (candidates.get(index - 1).getCandidateType() == 1) {
						FresherController fresherController = FresherController.init();
						Fresher fresher = fresherController.getByID(candidates.get(index - 1).getCandidateID());
						fresherController.delete(fresher);
					}
					if (candidates.get(index - 1).getCandidateType() == 2) {
						InternController internController = InternController.init();
						Intern intern = internController.getByID(candidates.get(index - 1).getCandidateID());
						internController.delete(intern);
					}
					break;
				case 3:
					if (this.tableCandidateWithCertificate.getTableSize() > 0) {
						this.tableCandidateWithCertificate.clearAllData();
					}
					List<Candidate> candidateWithCer = CandidateController.init().getAllCandidateAndTheirCertidicate();
					candidateWithCer.forEach(c -> {
						if (c.getCandidateType() == 0) {
							this.tableCandidateWithCertificate.addRow(c.getCandidateID(), c.getFullName(), "Experience",
									String.valueOf(c.getTotalCertificate()),
									String.valueOf(candidateWithCer.indexOf(c) + 1));
						}
						if (c.getCandidateType() == 1) {
							this.tableCandidateWithCertificate.addRow(c.getCandidateID(), c.getFullName(), "Fresher",
									String.valueOf(c.getTotalCertificate()),
									String.valueOf(candidateWithCer.indexOf(c) + 1));
						}
						if (c.getCandidateType() == 2) {
							this.tableCandidateWithCertificate.addRow(c.getCandidateID(), c.getFullName(), "Intern",
									String.valueOf(c.getTotalCertificate()),
									String.valueOf(candidateWithCer.indexOf(c) + 1));
						}
					});
					this.tableCandidateWithCertificate.print();
					System.out.println("Serial of candidate who you want to contact: ");
					try {
						int serial = Integer.valueOf(in.nextLine().trim());
						if (candidateWithCer.size() < serial) {
							System.out.println("Out of range");
							return;
						}
						Candidate candidateWithCe = candidateWithCer.get(serial - 1);
						List<Certificate> certificates = CertificateController.init()
								.getCertificatesByCandidate(candidateWithCe);
						if (certificates.isEmpty()) {
							System.out.println("Candidate has no certificate");
							System.out.println("-----------------------------");
							System.out.println("1. Add certificate");
							System.out.println("2. Exit");
							System.out.println("-----------------------------");
							System.out.println("Enter your choice: ");
							int a = 0; // save user choice
							try {
								a = Integer.parseInt(in.nextLine().trim());
							} catch (NumberFormatException e) {
								System.out.println("Enter a number");
							}
							if (a == 1) {
								Certificate certificate = this.receiveCertificateInfo(candidateWithCe);
								certificateController.saveOrUpdate(certificate);
							}
							if (a == 2) {
							}
						} else {
							certificates.forEach(ce -> {
								this.tableCertificate.addRow(ce.getCertificatedName(), ce.getCertificatedRank(),
										ce.getCertificatedDate().toString(),
										String.valueOf(certificates.indexOf(ce) + 1));
							});
							this.tableCertificate.print();
							this.certificateMenu();
							int userChoice = 0;
							try {
								userChoice = Integer.parseInt(in.nextLine().trim());
								if (userChoice > 3) {
									userChoice = 3;
								}
							} catch (NumberFormatException e) {
								userChoice = 3;
							}
							if (userChoice == 1) {
								Certificate certificate = this.receiveCertificateInfo(candidateWithCe);
								certificateController.saveOrUpdate(certificate);
							}
							if (userChoice == 2) {
								System.out.println("Serial of certificate");
								int serialCertificate = 0;
								try {
									serialCertificate = Integer.parseInt(in.nextLine().trim());
								} catch (Exception e) {
									System.out.println("Enter a number");
								}
								if ((serialCertificate - 1) > certificates.size()) {
									System.out.println("Out of range");
									this.tableCertificate.clearAllData();
								}
								Certificate certificate = certificates.get(serial - 1);
								certificateController.delete(certificate);
								this.tableCertificate.clearAllData();
							}
							if (userChoice == 3) {
								this.tableCertificate.clearAllData();
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("Enter number");
					}
					break;
				case 4:
					List<Experience> experiences = ExperienceController.init().getAll();
					experiences.forEach(e -> {
						this.tableExperience.addRow(e.getCandidateID(), e.getFullName(), e.getBirthDate().toString(),
								e.getPhone(), e.getEmail(), String.valueOf(e.getExpInYear()),
								String.valueOf(e.getProSkill()), String.valueOf(experiences.indexOf(e) + 1));
					});
					this.tableExperience.print();
					this.tableExperience.clearAllData();
					break;
				case 5:
					List<Fresher> freshers = FresherController.init().getAll();
					freshers.forEach(f -> {
						this.tableFresher.addRow(f.getCandidateID(), f.getFullName(), f.getBirthDate().toString(),
								f.getPhone(), f.getEmail(), f.getGraduationDate().toString(), f.getGraduationRank(),
								f.getEducation(), String.valueOf(freshers.indexOf(f) + 1));
					});
					this.tableFresher.print();
					this.tableFresher.clearAllData();
					break;
				case 6:
					List<Intern> interns = InternController.init().getAll();
					interns.forEach(i -> {
						this.tableIntern.addRow(i.getCandidateID(), i.getFullName(), i.getBirthDate().toString(),
								i.getPhone(), i.getEmail(), i.getMajor(), String.valueOf(i.getSemester()),
								i.getUniversityName(), String.valueOf(interns.indexOf(i) + 1));
					});
					this.tableIntern.print();
					this.tableIntern.clearAllData();
					break;
				case 7:
					break;
				}
				break;
			case 3:
				System.out.println("Enter csv link: ");
				String csvLink = in.nextLine().trim();
				List<Candidate> candidateOfCSV = this.readCsvFile(csvLink);
				List<Experience> experiences = new ArrayList<>();
				List<Fresher> freshers = new ArrayList<>();
				List<Intern> interns = new ArrayList<>();
				candidateOfCSV.forEach(c -> {
					if (c.getCandidateType() == 0) {
						experiences.add((Experience) c);
					}
					if (c.getCandidateType() == 1) {
						freshers.add((Fresher) c);
					}
					if (c.getCandidateType() == 2) {
						interns.add((Intern) c);
					}
				});
				if (!experiences.isEmpty()) {
					ExperienceController.init(experiences.stream().collect(Collectors.toSet())).saveAll();
				}
				if (!freshers.isEmpty()) {
					FresherController.init(freshers.stream().collect(Collectors.toSet())).saveAll();
				}
				if (!interns.isEmpty()) {
					InternController.init(interns.stream().collect(Collectors.toSet())).saveAll();
				}
				break;
			case 4:
				this.exit();
				if (this.tableCandidate.getTableSize() > 0) {
					this.tableCandidate.clearAllData();
				}
				if (this.tableExperience.getTableSize() > 0) {
					this.tableExperience.clearAllData();
				}
				if (this.tableFresher.getTableSize() > 0) {
					this.tableFresher.clearAllData();
				}
				if (this.tableIntern.getTableSize() > 0) {
					this.tableIntern.clearAllData();
				}
				if (this.tableCandidateWithCertificate.getTableSize() > 0) {
					this.tableCandidateWithCertificate.clearAllData();
				}
				return;
			}
		}
	}
}