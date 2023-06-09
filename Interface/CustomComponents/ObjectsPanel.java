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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;

import Core.Utility.Enum.EntityType;
import Interface.Utility.FontLoader;
import Interface.Utility.ComboBox.ComboBoxHelper;
import Interface.Windows.MainWindow;

public class ObjectsPanel extends JPanel implements MouseListener {

    // Ignoring triangle since it is a sub-entity
    public static final String[] SUPPORTED_ENTITY_NAMES = {EntityType.SPHERE.getName(), EntityType.RECTANGULAR_PRISM.getName(), EntityType.TRIANGULAR_PRISM.getName()};
    public static final Border UNSELECTED_OBJECT_BORDER = new CompoundBorder(new MatteBorder(new Insets(0, 0, 1,1), MainWindow.BORDER_COLOR), new EmptyBorder(-6, 0, 0, 0));
    public static final Border SELECTED_OBJECT_BORDER = new CompoundBorder(new MatteBorder(new Insets(1, 1, 1, 1), Color.WHITE), new EmptyBorder(-5, 0, 0, 0));
    public static final int OBJECT_CONTAINER_HEIGHT = 35;
    public static final Dimension OBJECT_ICON_DIMENSIONS = new Dimension(26, 18);

    private JPanel addObjectsArea;
    private JLabel objectsTitle;
    private JPanel objectsArea;
    private JButton addObjectButton;
    
    // Creates a new ObjectPanel object
    public ObjectsPanel(int width, int height) {
        super();
        // Add various widths and height to static final variables
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
        this.addObjectButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addObjectButton.addMouseListener(this);

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

    // Adds a sphere entity object to the object list
    // TODO: Remember to put a limit on how many objects can be created
    public void addObject() {

        JPanel objectContainer = new JPanel();
        IconPanel objectIcon = new IconPanel(EntityType.SPHERE);
        JComboBox<String> objectTypeSelector = ComboBoxHelper.createComboBox(ObjectsPanel.SUPPORTED_ENTITY_NAMES);

        // When a object type drop-down menu registers an item state change, change its corresponding icon
        objectTypeSelector.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {

                IconPanel iconPanel;

                // This is necessary to prevent a double-register of the event (one event is fired for deselected and for selected)
                if (event.getStateChange() == ItemEvent.SELECTED) {

                    iconPanel = (IconPanel) ((JComponent) event.getSource()).getParent().getComponent(0);
                    
                    if (event.getItem().equals(EntityType.RECTANGULAR_PRISM.getName())) {

                        iconPanel.changeEntityType(EntityType.RECTANGULAR_PRISM);

                    } else if (event.getItem().equals(EntityType.TRIANGULAR_PRISM.getName())) {

                        iconPanel.changeEntityType(EntityType.TRIANGULAR_PRISM);

                    } else if (event.getItem().equals(EntityType.SPHERE.getName())) {

                        iconPanel.changeEntityType(EntityType.SPHERE);
                    }
                }
            }
        });

        objectContainer.setBorder(ObjectsPanel.UNSELECTED_OBJECT_BORDER);
        objectContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        objectContainer.setBackground(MainWindow.BACKGROUND_COLOR);
        objectContainer.setPreferredSize(new Dimension(this.getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT));
        objectContainer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // When the object container is selected, select that object
        objectContainer.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent event) {

                JPanel objectContainer = (JPanel) event.getSource();
                Component[] objectContainers = objectContainer.getParent().getComponents();

                objectContainer.setBorder(ObjectsPanel.SELECTED_OBJECT_BORDER);
                objectContainer.setPreferredSize(new Dimension((int) objectContainer.getPreferredSize().getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT + 2));

                // Unselecting other objectContainers
                for (Component component : objectContainers) {

                    // There shouldn't be any other components that aren't JPanels within 
                    // the objectArea, but this is just in case
                    if (component instanceof JPanel) {

                        // Comparing references to make sure the selected objectContainer isn't unselected
                        if (component != objectContainer) {

                            ((JPanel) component).setBorder(ObjectsPanel.UNSELECTED_OBJECT_BORDER);
                            ((JPanel) component).setPreferredSize(new Dimension((int) objectContainer.getPreferredSize().getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT));
                        }
                    }
                }
            }
        });

        objectIcon.setPreferredSize(ObjectsPanel.OBJECT_ICON_DIMENSIONS);

        objectContainer.add(objectIcon);
        objectContainer.add(objectTypeSelector);
        this.objectsArea.add(objectContainer);

        this.revalidate();
        this.repaint();
    }

    // Manditory override methods
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    // Adding a new object when the addObject button is clicked
    @Override
    public void mouseClicked(MouseEvent event) {
        this.addObject();
    }
}
