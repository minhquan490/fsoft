package com.system.fsoft;

import java.sql.SQLException;

import com.system.fsoft.exception.SystemInterruptedException;
import com.system.fsoft.gui.CandidateManagementApp;

public class App {

	public static void main(String[] args) {

		try {
			CandidateManagementApp app = CandidateManagementApp.create();
			app.run();
		} catch (SystemInterruptedException e) {
			System.out.println(e.getMessage());
			e.getCause().printStackTrace();
		} catch (SQLException e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
		}

	}
}
