///////////////////////
//
// Filename: Main.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Creates a new main window. This program is a minimal ray tracing playground, allowing users to add objects, modify their properties, and render scenes.
//
///////////////////////

// TODO: Remember to check all header comments
// TODO: Make sure width depth height is all sorted out properly
// TODO: maybe remove protected methods

import Interface.Windows.MainWindow;

public class Main {

    public static final String APPLICATION_NAME = "MoonRays";

    public static void main(String[] args) {

        // Necessary to make application text look smooth
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        // Creating the main window
        new MainWindow(Main.APPLICATION_NAME);
    }
}