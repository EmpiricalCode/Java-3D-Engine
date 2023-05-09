package Interface.Panels;

import java.awt.*;
import javax.swing.*;

import Core.Environment;
import Core.Structures.Entity;

public class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;
    private int quality;

    private int[][][] colorMatrix;

    private Environment environment;

    // Main constructor
    public RenderPanel(Environment environment, int quality) {

        this.environment = environment;

        // Calculating dimensions and pixel size
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;
        this.quality = quality;

        // Setting up color matrix
        this.colorMatrix = new int[dimensions][dimensions][3];

        // Setting size
        this.setPreferredSize(new Dimension(pixelSize * dimensions, pixelSize * dimensions));
    }

    // Renders the environment
    // Fills the colorMatrix variable and calls repaint() to display it 
    public void render() {

        for (Entity entity : this.environment.getEntities()) {
            // TODO: render each entity
        }

        repaint();
    }

    // Draws each pixel in the pixel matrix onto the panel
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color(colorMatrix[r][c][0], colorMatrix[r][c][1], colorMatrix[r][c][2]));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}