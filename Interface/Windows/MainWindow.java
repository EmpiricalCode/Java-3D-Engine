package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;

import Core.Environment;
import Interface.Structures.Window;

public class MainWindow extends Window {
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating render window
        new RenderWindow(new Environment(), 1);
    }
}
