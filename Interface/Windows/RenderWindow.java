package Interface.Windows;

import java.awt.*;

import Core.Environment;
import Core.Structures.Entity;
import Interface.Panels.RenderPanel;
import Interface.Structures.Window;

public class RenderWindow extends Window {

    private RenderPanel renderPanel;
    private Insets insets;
    
    // Main constructor
    public RenderWindow(int quality) {

        // Setting up the window
        super("Render Window", new FlowLayout(FlowLayout.LEFT, 0, 0), 512, 512);

        insets = this.getInsets();

        // Resizing the window to account for the insets (the content must be 512px by 512px, not the window itself)
        this.setSize(512 + insets.left + insets.right, 512 + insets.top + insets.bottom);
        this.setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(quality);
        this.add(this.renderPanel);

        // Rendering
        while (true) {
            this.renderPanel.draw(null);
        }
    }

    // Renders an environment of objects
    public void render(Environment environment) {
        
        for (Entity entity : environment.getEntities()) {

            // TODO: Render each entity
        }
    }
}