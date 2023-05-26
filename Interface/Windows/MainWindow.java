package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;

import Core.Environment;
import Core.Entities.*;
import Core.Utility.*;
import Core.Utility.Enum.*;
import Interface.Structures.Window;

public class MainWindow extends Window {
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", new BorderLayout(), 500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating render window
        Environment environment = new Environment(new Camera(new Vector3D(15, 15, 0), new Vector3D(15, 0, 0)));

        environment.addEntity(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(0, 0, 0), ReflectionType.DIFFUSE, 5));
        environment.addEntity(new Sphere(new Vector3D(25, 0, 0), new ColorRGB(0, 0, 0), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(22, 0, 10), new ColorRGB(200, 0, 0), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(10, 0, -205), new ColorRGB(0, 0, 0), ReflectionType.SPECULAR, 200));
        
        RenderWindow renderWindow = new RenderWindow(environment, 9, true, true, 100, 20);
        renderWindow.render();
    }
}
