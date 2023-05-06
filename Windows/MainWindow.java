package Windows;

import java.awt.*;

import Structures.Window;

public class MainWindow extends Window {
    
    public MainWindow() throws InterruptedException {

        // Setting up the window
        super("Main Window", true, new BorderLayout(), 500, 500);

        RenderWindow renderWindow = new RenderWindow(8);
    }
}
