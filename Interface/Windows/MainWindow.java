///////////////////////
//
// Filename: MainWindow.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation of the main window and all of its relevant components.
//
///////////////////////

package Interface.Windows;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Environment;
import Core.Entities.*;
import Core.Entities.SubEntities.Triangle;
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
    public static final int FIELD_CONTAINER_HEIGHT = 45;
    public static final int FIRST_SECTION_WIDTH = 400;
    public static final int SECOND_SECTION_WIDTH = 450;
    public static final int THIRD_SECTION_WIDTH = 350;

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
        super("MoonRays", new BorderLayout(), MainWindow.WIDTH, MainWindow.HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding elements
        // TODO: Make all of these final variables
        objectsPanel = new ObjectsPanel(MainWindow.FIRST_SECTION_WIDTH, MainWindow.HEIGHT);
        renderSettingsPanel = new RenderSettingsPanel(MainWindow.THIRD_SECTION_WIDTH, MainWindow.HEIGHT);

        objectInfoContainer = new JPanel(new FlowLayout(0, 0, 0));
        objectInfoContainer.setBackground(Color.RED);

        materialsPanel = new MaterialsPanel(MainWindow.SECOND_SECTION_WIDTH);
        propertiesPanel = new PropertiesPanel(materialsPanel, MainWindow.SECOND_SECTION_WIDTH);

        this.getContentPane().addMouseListener(new MouseAdapter() {          

            // Clicking anywhere that isn't a button, text field, etc, will focus on an arbitrary panel (this is to defocus text fields, essentially "clicking off" them)
            @Override
            public void mousePressed(MouseEvent e) {
               objectsPanel.requestFocusInWindow();
            }
        });

        // Loading in all JComponents
        objectInfoContainer.add(propertiesPanel);
        objectInfoContainer.add(materialsPanel);
        this.add(renderSettingsPanel, BorderLayout.EAST);
        this.add(objectInfoContainer, BorderLayout.CENTER);
        this.add(objectsPanel, BorderLayout.WEST);

        this.setResizable(false);
        this.setVisible(true);

        objectsPanel.addObject();
        objectsPanel.addObject();
        propertiesPanel.loadProperties(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), 0.1, ReflectionType.SPECULAR, 5));
        materialsPanel.loadProperties(new Sphere(new Vector3D(15, 0, 0), new ColorRGB(100, 0, 255), 0.2, ReflectionType.SPECULAR, 5));

        // Creating environment and render window
        Environment environment = new Environment(new Camera(new Vector3D(-5, -20, 10), new Vector3D(15, 15, 0)));

        environment.addEntity(new TriangularPrism(new Vector3D(15, 15, 0), new ColorRGB(255, 255, 255), 0.2, ReflectionType.SPECULAR, 20, 10, 20));
        environment.addEntity(new TriangularPrism(new Vector3D(15, -10, 0), new ColorRGB(255, 100, 255), 0.2, ReflectionType.SPECULAR, 20, 10, 15));
        environment.addEntity(new Sphere(new Vector3D(13, 1, 0), new ColorRGB(255, 255, 255), 0.5, ReflectionType.SPECULAR, 4));
        environment.addEntity(new RectangularPrism(new Vector3D(15, 15, -55), new ColorRGB(255, 255, 100), 1, ReflectionType.DIFFUSE, 100, 100, 80));
        // environment.addEntity(new Sphere(new Vector3D(10, 0, 0), new ColorRGB(200, 100, 255), 1, ReflectionType.DIFFUSE, 4));
        
        RenderWindow renderWindow = new RenderWindow(environment, 8, true, true, 100, 10);
        renderWindow.render();
    }
}
