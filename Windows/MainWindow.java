package Windows;

import java.awt.*;
import javax.swing.JFrame;

public class MainWindow extends Window {
    
    public MainWindow() throws InterruptedException {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RenderWindow renderWindow = new RenderWindow(8);
    }
}
