package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;

import Core.Environment;
import Core.Utility.Camera;
import Core.Utility.Vector3D;
import Interface.Structures.Window;

public class MainWindow extends Window {
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating render window
        new RenderWindow(new Environment(new Camera(new Vector3D(0, 0, 0), new Vector3D(1, 0, 0))), 1);
    }
}
