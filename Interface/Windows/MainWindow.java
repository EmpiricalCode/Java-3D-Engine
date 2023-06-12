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
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Environment;
import Core.Structures.Entity;
import Core.Utility.Camera;
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

    public static final int PREVIEW_QUALITY = 6;
    public static final int PREVIEW_PIXEL_SAMPLES = 5;
    public static final int PREVIEW_RAY_DEPTH = 5;

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
        this.renderSettingsPanel = new RenderSettingsPanel(this, MainWindow.THIRD_SECTION_WIDTH, MainWindow.HEIGHT);

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

        this.renderSettingsPanel.loadProperties();
    }

    // Starts the render
    public void startRender() {

        // Closing the old render window
        if (this.renderWindow != null) {
            this.renderWindow.dispatchEvent(new WindowEvent(renderWindow, WindowEvent.WINDOW_CLOSING));
        }

        this.environment.setCamera(new Camera(this.renderSettingsPanel.getCameraPosition(), this.renderSettingsPanel.getCameraLookAt()));
        this.renderWindow = new RenderWindow(this, this.environment, this.renderSettingsPanel.getQuality(), this.renderSettingsPanel.getAntiAliasing(), this.renderSettingsPanel.getGamma(), this.renderSettingsPanel.getPixelSamples(), this.renderSettingsPanel.getRayDepth());
        (new Thread(this.renderWindow.getRenderPanel())).start();
    }

    // Starts a preview render with some parameters set to a low default
    public void startPreview() {

        // Closing the old render window
        if (this.renderWindow != null) {
            this.renderWindow.dispatchEvent(new WindowEvent(renderWindow, WindowEvent.WINDOW_CLOSING));
        }

        this.environment.setCamera(new Camera(this.renderSettingsPanel.getCameraPosition(), this.renderSettingsPanel.getCameraLookAt()));
        this.renderWindow = new RenderWindow(this, this.environment, MainWindow.PREVIEW_QUALITY, false, this.renderSettingsPanel.getGamma(), MainWindow.PREVIEW_PIXEL_SAMPLES, MainWindow.PREVIEW_RAY_DEPTH);
        (new Thread(this.renderWindow.getRenderPanel())).start();
    }

    // Cancels a render
    public void cancelRender() {
        if (this.renderWindow != null) {
            this.renderWindow.dispatchEvent(new WindowEvent(renderWindow, WindowEvent.WINDOW_CLOSING));
        }
    }

    // Updates the progress for a render
    public void updateRenderProgress(double percent) {
        this.renderSettingsPanel.updateRenderProgress(percent);
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

        this.propertiesPanel.loadEntity(entity);
        this.materialsPanel.loadEntity(entity);

        this.propertiesPanel.loadProperties();
        this.materialsPanel.loadProperties();
    }

    // Removes material and regular properties
    public void removeProperties() {
        this.propertiesPanel.removeProperties();
        this.materialsPanel.removeProperties();
    }
}
