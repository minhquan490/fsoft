package com.system.fsoft.controller;

import java.sql.SQLException;
import java.util.Set;

import com.system.fsoft.entity.Intern;
import com.system.fsoft.service.InternService;
import com.system.fsoft.service.impl.InternServiceImpl;

public class InternController {

    private String patternID = "ITN-";;
    private Set<Intern> interns;
    private InternService service = new InternServiceImpl();
    private Intern intern;

    private InternController(Set<Intern> interns) {
        this.interns = interns;
    }

    private InternController(Intern intern) {
        this.intern = intern;
    }

    public static InternController init(Set<Intern> interns) {
        return new InternController(interns);
    }

    public static InternController init(Intern intern) {
        return new InternController(intern);
    }

    public void saveAll() throws SQLException {
        int totalIntern = service.countInDatabase();
        if (totalIntern == 0) {
            throw new SQLException("Why it equal 0 ?");
        }
        if (this.interns == null) {
            System.out.println("Why it null ?");
        }
        for (Intern intern : interns) {
            intern.setCandidateID(this.patternID + String.valueOf(totalIntern));
            service.save(intern);
            totalIntern++;
        }
    }

    public void save() throws SQLException {
        int totalIntern = service.countInDatabase();
        if (totalIntern == 0) {
            throw new SQLException("Why it equal 0 ?");
        }
        if (this.intern == null) {
            System.out.println("Why it null ?");
        }
        intern.setCandidateID(this.patternID + String.valueOf(totalIntern));
        service.save(intern);
    }
}