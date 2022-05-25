package com.system.fsoft;

import java.sql.SQLException;

import com.system.fsoft.gui.MainPanel;

public class App {

    public static void main(String[] args) {

        try {
            MainPanel panel = MainPanel.createPanel();
            panel.run();
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }
}
