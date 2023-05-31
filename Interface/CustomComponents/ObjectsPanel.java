package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

        addObjectsArea = new JPanel();
        addObjectsArea.setPreferredSize(new Dimension(width, 95));
        addObjectsArea.setBackground(MainWindow.BACKGROUND_COLOR);
        addObjectsArea.setBorder(new MatteBorder(0, 0, 0, 1, new Color(60, 60, 60)));
        addObjectsArea.setLayout(new GridBagLayout());

        objectsTitle = new JLabel("Objects");
        objectsTitle.setFont(FontLoader.loadFont("Montserrat SemiBold", 26));
        objectsTitle.setBorder(new EmptyBorder(3, 10, 0, 100));
        objectsTitle.setForeground(Color.WHITE);
        addObjectsArea.add(objectsTitle);

        addObjectButton = new RoundedButton(15, "+ Add Object", new Color(200, 200, 200), new Color(255, 255, 255), true);
        addObjectButton.setFont(FontLoader.loadFont("Montserrat Medium", 17));
        addObjectButton.setBorder(new EmptyBorder(8, 10, 8, 10));
        addObjectsArea.add(addObjectButton);

        objectsArea = new JPanel();
        objectsArea.setPreferredSize(new Dimension(width, height - addObjectsArea.getHeight()));
        objectsArea.setBorder(new MatteBorder(1, 0, 0, 1, new Color(60, 60, 60)));
        objectsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.add(addObjectsArea);
        this.add(objectsArea);
    }
}
