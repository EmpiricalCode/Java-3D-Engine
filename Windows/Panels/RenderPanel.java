package Windows.Panels;

import java.awt.*;
import javax.swing.*;

public class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;

    private int[][] colorMatrix;

    public RenderPanel(int quality) {

        // Calculating dimensions and pixel size
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;

        // Setting size
        setPreferredSize(new Dimension(pixelSize * dimensions + 20, pixelSize * dimensions + 10));
    }

    // Updates the matrix of pixel colors and 
    // calls for the panel to be repainted
    public void draw(int[][] cMatrix) {
        this.colorMatrix = cMatrix;
        repaint();
    }

    // Draws each pixel in the pixel matrix onto the panel
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}