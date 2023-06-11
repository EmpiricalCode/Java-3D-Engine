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
import Core.Structures.Entity;
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

    private RenderWindow renderWindow = null;

    private ObjectsPanel objectsPanel;
    private JPanel objectInfoContainer;
    private PropertiesPanel propertiesPanel;
    private MaterialsPanel materialsPanel;
    private RenderSettingsPanel renderSettingsPanel;

    private Environment environment;
    
    // Main constructor
    public MainWindow(String title)  {

        // Setting up the window
        super(title, new BorderLayout(), MainWindow.WIDTH, MainWindow.HEIGHT);

        // Adding elements
        // TODO: Make all of these final variables
        this.materialsPanel = new MaterialsPanel(this, MainWindow.SECOND_SECTION_WIDTH);
        this.propertiesPanel = new PropertiesPanel(this, MainWindow.SECOND_SECTION_WIDTH);

        this.environment = new Environment(null);
        this.objectsPanel = new ObjectsPanel(this, MainWindow.FIRST_SECTION_WIDTH, MainWindow.HEIGHT, environment);
        this.renderSettingsPanel = new RenderSettingsPanel(MainWindow.THIRD_SECTION_WIDTH, MainWindow.HEIGHT);

        this.objectInfoContainer = new JPanel(new FlowLayout(0, 0, 0));
        this.objectInfoContainer.setBackground(MainWindow.BACKGROUND_COLOR);

        // Clicking anywhere that isn't a button, text field, etc, will focus on an arbitrary panel (this is to defocus text fields, essentially "clicking off" them)
        this.getContentPane().addMouseListener(new MouseAdapter() {          
            
            @Override
            public void mousePressed(MouseEvent e) {
               objectsPanel.requestFocusInWindow();
            }
        });

        // Loading in all JComponents
        this.objectInfoContainer.add(this.propertiesPanel);
        this.objectInfoContainer.add(this.materialsPanel);
        this.add(this.renderSettingsPanel, BorderLayout.EAST);
        this.add(this.objectInfoContainer, BorderLayout.CENTER);
        this.add(this.objectsPanel, BorderLayout.WEST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        // Creating environment and render window
        // environment = new Environment(new Camera(new Vector3D(-5, -20, 10), new Vector3D(15, 15, 0)));

        // environment.addEntity(new TriangularPrism(new Vector3D(15, 15, 0), new ColorRGB(255, 255, 255), 0.2, ReflectionType.SPECULAR, 20, 10, 20));
        // environment.addEntity(new TriangularPrism(new Vector3D(15, -10, 0), new ColorRGB(255, 100, 255), 0, ReflectionType.SPECULAR, 20, 10, 15));
        // environment.addEntity(new Sphere(new Vector3D(13, 1, 0), new ColorRGB(255, 255, 255), 0.5, ReflectionType.SPECULAR, 4));
        // environment.addEntity(new RectangularPrism(new Vector3D(15, 15, -55), new ColorRGB(255, 255, 100), 1, ReflectionType.DIFFUSE, 100, 100, 80));
        // // environment.addEntity(new Sphere(new Vector3D(10, 0, 0), new ColorRGB(200, 100, 255), 1, ReflectionType.DIFFUSE, 4));
        // this.environment.setCamera(new Camera(new Vector3D(0, 0, 0), new Vector3D(5, 0, 0)));
        // this.environment.addEntity(new RectangularPrism(new Vector3D(5, 0, 0), new ColorRGB(25, 25, 255), 0, ReflectionType.DIFFUSE, 3, 3, 3));
        // this.startRender();
        
        // renderWindow.render();
    }

    // Starts the render
    public void startRender() {
        this.renderWindow = new RenderWindow(this.environment, 8, true, true, 100, 10);
        this.renderWindow.getRenderPanel().render();
        this.renderWindow = null;
    }

    // Checks if a render is still ongoing 
    public boolean isRendering() {

        if (this.renderWindow != null && this.renderWindow.getRenderPanel().isRendering()) {
            return true;
        }

        return false;
    }

    // Loads material and regular properties given an Entity
    public void loadProperties(Entity entity) {
        this.removeProperties();

        this.propertiesPanel.loadProperties(entity);
        this.materialsPanel.loadProperties(entity);
    }

    // Removes material and regular properties
    public void removeProperties() {
        this.propertiesPanel.removeProperties();
        this.materialsPanel.removeProperties();
    }
}
