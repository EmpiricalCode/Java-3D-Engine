package Interface.Structures;

import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Window extends JFrame {
    
    // Main constructor
    public Window(String title, LayoutManager layout, int width, int height) {

        // Setting up window 
        super(title);

        this.setVisible(true);
        this.setLayout(layout);
        this.setSize(width, height);
    }
}