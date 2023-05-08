package Interface.Structures;

import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Window extends JFrame {
    
    // Main constructor
    public Window(String title, boolean initiallyVisible, LayoutManager layout, int width, int height) {

        // Setting up window 
        super(title);

        this.setVisible(initiallyVisible);
        this.setLayout(layout);
        this.setSize(width, height);
    }

    // Alternative constructor with initiallyVisible defaulted to true
    public Window(String title, LayoutManager layout, int width, int height) {
        this(title, true, layout, width, height);
    }
}