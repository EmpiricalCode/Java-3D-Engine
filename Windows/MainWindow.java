package Windows;

import java.awt.*;
import javax.swing.JFrame;

import Windows.Structures.Window;

public class MainWindow extends Window {
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating render window
        new RenderWindow(8);
    }
}
