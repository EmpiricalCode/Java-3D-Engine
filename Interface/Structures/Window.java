///////////////////////
//
// Filename: Window.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A superclass that handles some aspects of window creation.
//
///////////////////////

package Interface.Structures;

import java.awt.LayoutManager;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    // Main constructor
    public Window(String title, LayoutManager layout, int width, int height) {

        // Setting up window 
        super(title);

        // setVisible is not called here because the individual windows must add their JComponents first before setting visible
        // in order to avoid issues where components do not show until the window is resized.
        this.setLayout(layout);
        this.setSize(width, height);

        // Setting the app icon
        try {
            this.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/Icons/Application Icon.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}