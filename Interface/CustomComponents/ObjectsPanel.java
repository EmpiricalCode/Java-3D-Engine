///////////////////////
//
// Filename: ObjectsPanel.java
// Author: Daniel Long
// Course: ICS4U1.
// Description: A class that handles the creation and user interactions of the panel where the environment's objects are displayed/added/removed
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.*;

import Interface.Utility.FontLoader;
import Interface.Windows.MainWindow;

public class ObjectsPanel extends JPanel {

    private JPanel addObjectsArea;
    private JLabel objectsTitle;
    private JPanel objectsArea;
    private JButton addObjectButton;
    
    public ObjectsPanel(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        this.addObjectsArea = new JPanel();
        this.addObjectsArea.setPreferredSize(new Dimension(width, 95));
        this.addObjectsArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.addObjectsArea.setBorder(new MatteBorder(0, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.addObjectsArea.setLayout(new GridBagLayout());

        this.objectsTitle = new JLabel("Objects");
        this.objectsTitle.setFont(MainWindow.TITLE_FONT);
        this.objectsTitle.setBorder(new EmptyBorder(3, 10, 0, 100));
        this.objectsTitle.setForeground(Color.WHITE);

        this.addObjectButton = new RoundedButton(15, "+ Add Object", new Color(200, 200, 200), new Color(255, 255, 255), true);
        this.addObjectButton.setFont(FontLoader.loadFont("montserrat_medium", 17));
        this.addObjectButton.setBorder(new EmptyBorder(8, 10, 8, 10));

        this.objectsArea = new JPanel();
        this.objectsArea.setPreferredSize(new Dimension(width, height - addObjectsArea.getHeight()));
        this. objectsArea.setBorder(new MatteBorder(1, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.objectsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.addObjectsArea.add(this.objectsTitle);
        this.addObjectsArea.add(this.addObjectButton);
        this.add(this.addObjectsArea);
        this.add(this.objectsArea);
    }
}
