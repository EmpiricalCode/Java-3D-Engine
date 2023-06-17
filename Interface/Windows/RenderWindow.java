///////////////////////
//
// Filename: RenderWindow.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation of the render window and all of its relevant components, as well as its behavior.
//
///////////////////////

package Interface.Windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Core.Environment;
import Interface.CustomComponents.RenderPanel;
import Interface.Structures.Window;

public class RenderWindow extends Window {

    public static final String WINDOW_TITLE = "MoonRays Render";

    private RenderPanel renderPanel;
    
    // Creates a new render window
    public RenderWindow(MainWindow mainWindow, Environment environment, int quality, boolean antiAliasing, double gammaCorrection, int pixelSamples, int rayDepth) {

        // Setting up the window
        super(RenderWindow.WINDOW_TITLE, new FlowLayout(FlowLayout.LEFT, 0, 0), 512, 512);

        this.setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(mainWindow, environment, quality, antiAliasing, gammaCorrection, pixelSamples, rayDepth);
        this.add(this.renderPanel);

        // Handling window close
        // Stopping rendering when render window closes
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                renderPanel.stopRendering();
            }
        });
        
        this.pack();
        this.setVisible(true);
    }

    // Returns the render panel
    public RenderPanel getRenderPanel() {
        return this.renderPanel;
    }
}