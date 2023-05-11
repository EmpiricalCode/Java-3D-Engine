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
        Environment environment = new Environment(new Camera(new Vector3D(15, 15, 0), new Vector3D(15, 0, 0)));

        environment.addEntity(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(0, 0, 0), 5));
        environment.addEntity(new Sphere(new Vector3D(25, 0, 0), new ColorRGB(0, 0, 0), 5));
        environment.addEntity(new Sphere(new Vector3D(10, 0, -205), new ColorRGB(0, 0, 0), 200));
        
        RenderWindow renderWindow = new RenderWindow(environment, 8);

        // Vector3D original = new Vector3D(0, 0, 5);
        // Vector3D originalDir = new Vector3D(15, 0, 0);

        // while (true) {

        //     original = Vector3D.add(original, new Vector3D(0, 0.3, 0.1));
        //     // originalDir = Vector3D.add(originalDir, new Vector3D(0, 0.5, 0));
        //     environment.setCamera(new Camera(original, originalDir));
            renderWindow.render();
        // }
    }
}
