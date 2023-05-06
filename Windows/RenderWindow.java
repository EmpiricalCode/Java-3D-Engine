package Windows;

import java.awt.*;

import javax.swing.JPanel;

import Structures.Window;
import Utility.ColorRGB;

public class RenderWindow extends Window {

    private RenderPanel renderPanel;
    
    public RenderWindow(int quality) {
        super("Render Window", new FlowLayout(FlowLayout.LEFT, 0, 0), 528, 551);

        setResizable(false);

        // Creating render panel
        this.renderPanel = new RenderPanel(quality);
        add(this.renderPanel);
    }
}

class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;

    private ColorRGB[][] colorGrid;

    public RenderPanel(int quality) {
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;

        setPreferredSize(new Dimension(pixelSize * dimensions + 20, pixelSize * dimensions + 10));
    }

    public void render(ColorRGB[][] cGrid) {
        this.colorGrid = cGrid;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("dd");

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}