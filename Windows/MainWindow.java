package Windows;

import java.awt.*;

import Structures.Window;

public class MainWindow extends Window {
    
    public MainWindow() {
        super("Main Window", true, new BorderLayout(), 500, 500);

        RenderWindow renderWindow = new RenderWindow(8);
    }
}
