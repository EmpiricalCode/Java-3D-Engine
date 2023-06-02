package Interface.Windows;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Environment;
import Core.Entities.*;
import Core.Utility.*;
import Core.Utility.Enum.*;
import Interface.CustomComponents.MaterialsPanel;
import Interface.CustomComponents.ObjectsPanel;
import Interface.CustomComponents.PropertiesPanel;
import Interface.CustomComponents.RenderSettingsPanel;
import Interface.Structures.Window;
import Interface.Utility.FontLoader;

public class MainWindow extends Window {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final Color BACKGROUND_COLOR = new Color(23, 23, 23);
    public static final Color BORDER_COLOR = new Color(65, 65, 65);
    public static final Color SUBTITLE_COLOR = new Color(200, 200, 200);
    public static final Color PROPERTIES_COLOR = new Color(24, 24, 24);

    public static final Font TITLE_FONT = FontLoader.loadFont("montserrat_semibold", 26);
    public static final Font SUBTITLE_FONT = FontLoader.loadFont("montserrat_medium", 16);
    public static final Font PROPERTIES_FONT = FontLoader.loadFont("montserrat_medium", 15);

    // These were made public in case they must access each other using the MainWindow class
    public ObjectsPanel objectsPanel;
    public JPanel objectInfoContainer;
    public PropertiesPanel propertiesPanel;
    public MaterialsPanel materialsPanel;
    public RenderSettingsPanel renderSettingsPanel;
    
    // TODO: Ask if ok that main window is nonstatic
    // Main constructor
    public MainWindow()  {

        // Setting up the window
        super("Main Window", new BorderLayout(), MainWindow.WIDTH, MainWindow.HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding elements
        // TODO: Make all of these final variables
        objectsPanel = new ObjectsPanel(400, MainWindow.HEIGHT);
        renderSettingsPanel = new RenderSettingsPanel(350, MainWindow.HEIGHT);

        objectInfoContainer = new JPanel(new FlowLayout(0, 0, 0));
        objectInfoContainer.setBackground(Color.RED);
        propertiesPanel = new PropertiesPanel(this, 450);
        materialsPanel = new MaterialsPanel(450);

        objectInfoContainer.add(propertiesPanel);
        objectInfoContainer.add(materialsPanel);
        this.add(renderSettingsPanel, BorderLayout.EAST);
        this.add(objectInfoContainer, BorderLayout.CENTER);
        this.add(objectsPanel, BorderLayout.WEST);

        // this.setResizable(true);
        this.setVisible(true);

        propertiesPanel.loadProperties(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), ReflectionType.SPECULAR, 5));
        materialsPanel.loadMaterialProperties(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), ReflectionType.SPECULAR, 5));

        // Creating environment and render window
        Environment environment = new Environment(new Camera(new Vector3D(15, 15, 0), new Vector3D(15, 0, 0)));

        environment.addEntity(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(25, 0, 0), new ColorRGB(200, 200, 200), ReflectionType.SPECULAR, 5));
        environment.addEntity(new Sphere(new Vector3D(22, 0, 10), new ColorRGB(200, 200, 0), ReflectionType.DIFFUSE, 5));
        environment.addEntity(new Sphere(new Vector3D(10, 0, -205), new ColorRGB(200, 200, 200), ReflectionType.DIFFUSE, 200));
        
        RenderWindow renderWindow = new RenderWindow(environment, 9, true, true, 100, 20);
        renderWindow.render();
    }
}
