///////////////////////
//
// Filename: ObjectPropertyPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A superclass that handles the creation of the PropertiesPanel and MaterialsPanel.
//
///////////////////////

package Interface.Structures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.*;

import Core.Structures.Entity;
import Interface.Windows.MainWindow;

public abstract class ObjectPropertyPanel extends JPanel {

    private JPanel titleArea;
    private JLabel title;
    private JLabel subtitle;
    private JPanel propertiesArea;
    
    // Creates a new property panel
    public ObjectPropertyPanel(String titleText, int width, int height, int titleAreaHeight) {
        super();

        // Initializing panel
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));
        this.setBorder(new MatteBorder(new Insets(1, 0, 0, 0), MainWindow.BORDER_COLOR));

        // Creating panel components
        this.titleArea = new JPanel();
        this.titleArea.setPreferredSize(new Dimension(width, titleAreaHeight));
        this.titleArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.titleArea.setBorder(new EmptyBorder(29, 40, 0, 0));
        this.titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));

        this.title = new JLabel(titleText);
        this.title.setFont(MainWindow.TITLE_FONT);
        this.title.setForeground(Color.WHITE);

        this.subtitle = new JLabel("Empty");
        this.subtitle.setFont(MainWindow.SUBTITLE_FONT);
        this.subtitle.setForeground(MainWindow.SUBTITLE_COLOR);

        this.propertiesArea = new JPanel();
        this.propertiesArea.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        this.propertiesArea.setBorder(new MatteBorder(new Insets(1, 0, 0, 0), MainWindow.BORDER_COLOR));
        this.propertiesArea.setLayout(new FlowLayout(0, 0, 0));
        this.propertiesArea.setBackground(MainWindow.BACKGROUND_COLOR);

        // Adding componentst
        this.titleArea.add(this.title);
        this.titleArea.add(this.subtitle);
        this.add(this.titleArea);
        this.add(this.propertiesArea);
    }

    // Gets the subtitle component
    public JLabel getSubtitle() {
        return this.subtitle;
    }

    // Gets the property area component
    public JPanel getPropertiesArea() {
        return this.propertiesArea;
    }

    // Loads the properties for an entity
    public abstract void loadProperties(Entity entity);
}
