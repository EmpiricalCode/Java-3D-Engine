///////////////////////
//
// Filename: RenderWindow.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation of the render window and all of its relevant components.
//
///////////////////////

package Interface.Windows;

import java.awt.*;

import Core.Environment;
import Interface.CustomComponents.RenderPanel;
import Interface.Structures.Window;

public class RenderWindow extends Window {

    private RenderPanel renderPanel;
    
    // Main constructor
    public RenderWindow(Environment environment, int quality, boolean antiAliasing, double gammaCorrection, int pixelSamples, int rayDepth) {

        // Setting up the window
        super("Render Window", new FlowLayout(FlowLayout.LEFT, 0, 0), 512, 512);

        // Resizing the window to account for the insets (the content must be 512px by 512px, not the window itself)
        this.setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(environment, quality, antiAliasing, gammaCorrection, pixelSamples, rayDepth);
        this.add(this.renderPanel);
        
        this.pack();
        this.setVisible(true);
    }

    // Returns the render panel
    public RenderPanel getRenderPanel() {
        return this.renderPanel;
    }
}