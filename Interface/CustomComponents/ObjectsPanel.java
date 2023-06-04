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
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.metal.MetalComboBoxUI;

import Interface.Utility.FontLoader;
import Interface.Windows.MainWindow;

public class ObjectsPanel extends JPanel {

    public static final String[] OBJECT_TYPES = {"Sphere", "Rectangular Prism", "Triangular Prism"};

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
        this.objectsArea.setLayout(new FlowLayout(0, 0, 0));
        this.objectsArea.setBorder(new MatteBorder(1, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.objectsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.addObjectsArea.add(this.objectsTitle);
        this.addObjectsArea.add(this.addObjectButton);
        this.add(this.addObjectsArea);
        this.add(this.objectsArea);
    }

    public void addObject() {

        JPanel objectContainer = new JPanel();
        JComboBox<String> objectTypeSelector = new JComboBox<String>(ObjectsPanel.OBJECT_TYPES);

        objectTypeSelector.setUI(new MetalComboBoxUI() {

            @Override 
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {

                Insets buttonInsets;

                g.setColor(MainWindow.BACKGROUND_COLOR);
                g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height - 1);

                if (hasFocus && !isPopupVisible(comboBox) && arrowButton != null) {

                    g.setColor(MainWindow.BACKGROUND_COLOR);
                    buttonInsets = arrowButton.getInsets();

                    if (buttonInsets.top > 2) {
                        g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 3, buttonInsets.top - 2);
                    }

                    if (buttonInsets.bottom > 2) {
                        g.fillRect(bounds.x + 2, bounds.y + bounds.height - buttonInsets.bottom, bounds.width - 3, buttonInsets.bottom - 2);
                    }
                }
            }
        });

        for (int i = 0; i < objectTypeSelector.getComponentCount(); i++) {
            if (objectTypeSelector.getComponent(i) instanceof JComponent) {
                ((JComponent) objectTypeSelector.getComponent(i)).setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, MainWindow.BACKGROUND_COLOR), new EmptyBorder(0, 0, 0, 10)));
                ((JComponent) objectTypeSelector.getComponent(i)).setForeground(Color.WHITE);
            } else if (objectTypeSelector.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) objectTypeSelector.getComponent(i)).setFocusable(false);
            }
        }

        objectTypeSelector.setMaximumSize( objectTypeSelector.getPreferredSize() );
        objectTypeSelector.setFont(FontLoader.loadFont("montserrat_medium", 15));
        objectTypeSelector.setBackground(MainWindow.BACKGROUND_COLOR);
        objectTypeSelector.setForeground(Color.WHITE);
        objectTypeSelector.setBorder(new MatteBorder(1, 1, 1, 1, MainWindow.BACKGROUND_COLOR));
        objectTypeSelector.setFocusable(false);
        objectTypeSelector.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        objectContainer.setBorder(new CompoundBorder(new MatteBorder(new Insets(0, 0, 1,1), MainWindow.BORDER_COLOR), new EmptyBorder(0, 0, 0, 0)));
        objectContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        objectContainer.setBackground(MainWindow.BACKGROUND_COLOR);
        objectContainer.setPreferredSize(new Dimension(this.getWidth(), 35));


        objectContainer.add(objectTypeSelector);
        this.objectsArea.add(objectContainer);
    }
}
