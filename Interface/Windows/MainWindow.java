package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;

import Core.Environment;
import Core.Entities.*;
import Core.Utility.*;
import Interface.Structures.Window;

public class MainWindow extends Window {
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating render window
        Environment environment = new Environment(new Camera(new Vector3D(0, 0, 0), new Vector3D(1, 2, 1)));

        environment.addEntity(new Sphere(new Vector3D(12, 22, 10), new ColorRGB(200, 200, 200), 5));
        environment.addEntity(new Sphere(new Vector3D(12, 22, 5), new ColorRGB(200, 200, 200), 5));
        
        RenderWindow renderWindow = new RenderWindow(environment, 9);

        renderWindow.render();
    }
}
