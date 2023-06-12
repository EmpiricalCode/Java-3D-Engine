///////////////////////
//
// Filename: RoundedButton.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A custom class that allows for the creation of rounded buttons.
//
///////////////////////

package Interface.CustomComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class RoundedButton extends JButton implements MouseListener {
    
    private int radius;
    private boolean mouseIn;
    private boolean hollow;
    private Color mouseEnteredColor;
    private Color mouseExitedColor;

    public RoundedButton(int radius, String text, Color mouseExitedColor, Color mouseEnteredColor, boolean hollow) {
        super(text);

        this.radius = radius;
        this.hollow = hollow;
        this.mouseEnteredColor = mouseEnteredColor;
        this.mouseExitedColor = mouseExitedColor;

        this.setContentAreaFilled(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.addMouseListener(this);
        this.setFocusPainted(false);
    }

    // Returns if the mouse is in the button
    public boolean mouseIn() {
        return this.mouseIn;
    }

    @Override   
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        // Necessary to make button appear smooth
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setColor(this.mouseExitedColor);
            
        if (this.mouseIn) {
            g2D.setColor(this.mouseEnteredColor);
        }

        if (this.hollow) {

            this.setForeground(mouseExitedColor);

            if (this.mouseIn) {
                this.setForeground(mouseEnteredColor);
            }

            g2D.setStroke(new BasicStroke(1));
            g2D.drawRoundRect(1, 1, this.getWidth()-2, this.getHeight()-2, radius, radius);

        } else {

            g2D.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), radius, radius);
            g2D.setStroke(new BasicStroke(1));
        } 

        super.paintComponent(g2D);
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {
        this.mouseIn = true;
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.mouseIn = false;
    }

    @Override
    public void mouseClicked(MouseEvent event) {}
}
