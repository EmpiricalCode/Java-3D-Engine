package Windows;

import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Window extends JFrame {
    
    public Window(String title, boolean initiallyVisible, LayoutManager layout, int width, int height) {
        super(title);

        setVisible(initiallyVisible);
        setLayout(layout);
        setSize(width, height);
    }

    public Window(String title, LayoutManager layout, int width, int height) {
        this(title, true, layout, width, height);
    }
}
