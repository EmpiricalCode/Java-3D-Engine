package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;

import Core.Environment;
import Core.Entities.*;
import Core.Utility.*;
import Core.Utility.Enum.*;
import Interface.CustomComponents.ObjectsPanel;
import Interface.Structures.Window;

public class MainWindow extends Window {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final Color BACKGROUND_COLOR = new Color(27, 27, 27);

    private ObjectsPanel objectsPanel;
    
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", new BorderLayout(), MainWindow.WIDTH, MainWindow.HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding elements
        objectsPanel = new ObjectsPanel(400, 800);
        objectsPanel.setBackground(Color.black);

        this.add(objectsPanel, BorderLayout.WEST);
        this.setResizable(false);
        this.setVisible(true);

        // Creating environment and render window
        Environment environment = new Environment(new Camera(new Vector3D(15, 15, 0), new Vector3D(15, 0, 0)));

        environment.addEntity(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(25, 0, 0), new ColorRGB(100, 255, 0), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(22, 0, 10), new ColorRGB(200, 200, 0), ReflectionType.DIFFUSE, 5));
        environment.addEntity(new Sphere(new Vector3D(10, 0, -205), new ColorRGB(200, 200, 200), ReflectionType.DIFFUSE, 200));
        
        RenderWindow renderWindow = new RenderWindow(environment, 9, true, true, 100, 20);
        renderWindow.render();
    }
}
