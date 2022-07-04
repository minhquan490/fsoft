package com.system.fsoft;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.system.fsoft.exception.SystemInterruptedException;
import com.system.fsoft.gui.CandidateManagementApp;
import com.system.fsoft.gui.Planet;

public class App {

	static final Logger log = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {

		// try {
		// 	CandidateManagementApp app = CandidateManagementApp.create();
		// 	app.run();
		// } catch (SystemInterruptedException e) {
		// 	System.out.println(e.getMessage());
		// 	log.error("", e);
		// } catch (SQLException e) {
		// 	System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		// 	log.error("Problem at jbdc", e);
		// } catch (Exception e) {
		// 	System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		// 	log.error("", e);
		// }
	}
}
