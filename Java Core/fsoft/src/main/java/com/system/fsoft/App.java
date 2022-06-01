package com.system.fsoft;

import java.sql.SQLException;

import com.system.fsoft.exception.SystemInterruptedException;
import com.system.fsoft.gui.MainPanel;

public class App {

	public static void main(String[] args) {
		try {
			MainPanel panel = MainPanel.createPanel();
			panel.run();
		} catch (SystemInterruptedException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e1) {
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e1.printStackTrace();
		}
	}
}
