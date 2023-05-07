package Windows;

import java.awt.*;

import Windows.Panels.RenderPanel;

public class RenderWindow extends Window {

    private RenderPanel renderPanel;
    
    public RenderWindow(int quality) throws InterruptedException {

        // Setting up the window
        super("Render Window", new FlowLayout(FlowLayout.LEFT, 0, 0), 528, 551);

        setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(quality);
        add(this.renderPanel);

        // Rendering
        while (true) {
            this.renderPanel.render(null);
        }
    }
}