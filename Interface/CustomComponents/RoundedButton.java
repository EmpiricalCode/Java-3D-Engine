package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import Interface.Utility.FontLoader;

public class RoundedButton extends JButton implements MouseListener {
    
    private int radius;
    private boolean mouseIn;

    public RoundedButton(int radius, String text) {
        super(text);

        this.setFont(FontLoader.loadFont("Montserrat SemiBold", 14));
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.addMouseListener(this);
        this.setFocusPainted(false);
        this.radius = radius;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(Color.red);
        
        if (this.mouseIn) {
            g2D.setColor(Color.green);
        }

        g2D.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), radius, radius);

        super.paintComponent(g2D);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        this.mouseIn = true;
    }

    public void mouseExited(MouseEvent e) {
        this.mouseIn = false;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }
}
