///////////////////////
//
// Filename: ObjectsPanel.java
// Author: Daniel Long
// Course: ICS4U1.
// Description: A class that handles the creation and user interactions of the panel where the environment's objects are displayed/added/removed.
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

import javax.swing.*;
import javax.swing.border.*;

import Core.Environment;
import Core.Entities.RectangularPrism;
import Core.Entities.Sphere;
import Core.Entities.TriangularPrism;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.ReflectionType;
import Interface.Utility.FontLoader;
import Interface.Utility.ComboBox.ComboBoxHelper;
import Interface.Windows.MainWindow;

public class ObjectsPanel extends JPanel {

    // Ignoring triangle since it is a sub-entity
    public static final String[] SUPPORTED_ENTITY_NAMES = {EntityType.SPHERE.getName(), EntityType.RECTANGULAR_PRISM.getName(), EntityType.TRIANGULAR_PRISM.getName()};
    public static final Border UNSELECTED_OBJECT_BORDER = new CompoundBorder(new MatteBorder(new Insets(0, 0, 1,0), MainWindow.BORDER_COLOR), new EmptyBorder(-6, 0, 0, 0));
    public static final Border SELECTED_OBJECT_BORDER = new CompoundBorder(new MatteBorder(new Insets(1, 1, 1, 1), Color.WHITE), new EmptyBorder(-5, 0, 0, 0));
    public static final int OBJECT_CONTAINER_HEIGHT = 35;
    public static final Dimension OBJECT_ICON_DIMENSIONS = new Dimension(26, 18);
    public static final int ENTITY_CAP = 15;

    public static final Sphere DEFAULT_SPHERE = new Sphere(new Vector3D(0, 0, 0), new ColorRGB(255, 255, 255), 0, ReflectionType.DIFFUSE, 2);
    public static final RectangularPrism DEFAULT_RECTANGULAR_PRISM = new RectangularPrism(new Vector3D(0, 0, 0), new ColorRGB(255, 255, 255), 0, ReflectionType.DIFFUSE, 4, 4, 4);
    public static final TriangularPrism DEFAULT_TRIANGULAR_PRISM = new TriangularPrism(new Vector3D(0, 0, 0), new ColorRGB(255, 255, 255), 0, ReflectionType.DIFFUSE, 4, 4, 4);

    private JPanel addObjectsArea;
    private JLabel objectsTitle;
    private JPanel objectsArea;
    private RoundedButton addObjectButton;
    private Environment environment;
    private MainWindow mainWindow;
    
    // Creates a new ObjectPanel object
    public ObjectsPanel(MainWindow mainWindow, int width, int height, Environment environment) {
        super();

        this.environment = environment;
        this.mainWindow = mainWindow;

        // Add various widths and height to static final variables
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        this.addObjectsArea = new JPanel();
        this.addObjectsArea.setPreferredSize(new Dimension(width, 95));
        this.addObjectsArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.addObjectsArea.setLayout(new GridBagLayout());

        this.objectsTitle = new JLabel("Objects");
        this.objectsTitle.setFont(MainWindow.TITLE_FONT);
        this.objectsTitle.setBorder(new EmptyBorder(3, 10, 0, 100));
        this.objectsTitle.setForeground(Color.WHITE);

        this.addObjectButton = new RoundedButton(15, "+ Add Object", new Color(200, 200, 200), new Color(255, 255, 255), true);
        this.addObjectButton.setFont(FontLoader.loadFont("montserrat_medium", 17));
        this.addObjectButton.setBorder(new EmptyBorder(8, 10, 8, 10));

        // Creating a new object if the add object button is pressed
        this.addObjectButton.addMouseListener(new MouseAdapter() {
            
            // In java, buttons only register a mouseClicked event if the mouse does not move between pressing and releasing
            // To remedy this, the mouseReleased event is listened for while the mouse is within the button
            @Override
            public void mouseReleased(MouseEvent event) {

                if (event.getButton() == MouseEvent.BUTTON1 && !mainWindow.isRendering() && addObjectButton.mouseIn()) {
                    addObject();
                }
            }
        });

        this.objectsArea = new JPanel();
        this.objectsArea.setPreferredSize(new Dimension(width, height - addObjectsArea.getHeight()));
        this.objectsArea.setLayout(new FlowLayout(0, 0, 0));
        this.objectsArea.setBorder(new MatteBorder(1, 0, 0, 0, MainWindow.BORDER_COLOR));
        this.objectsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.addObjectsArea.add(this.objectsTitle);
        this.addObjectsArea.add(this.addObjectButton);
        this.add(this.addObjectsArea);
        this.add(this.objectsArea);
    }

    // Adds a sphere entity object to the object list
    public void addObject() {

        Sphere defaultSphere = ObjectsPanel.DEFAULT_SPHERE;
        Sphere newSphere = new Sphere(defaultSphere.getPosition(), defaultSphere.getColor(), defaultSphere.getFuzziness(), defaultSphere.getReflectiontype(), defaultSphere.getRadius());
        JPanel objectPanel = new JPanel();
        IconPanel objectIcon = new IconPanel(EntityType.SPHERE);
        JComboBox<String> objectTypeSelector = ComboBoxHelper.createComboBox(ObjectsPanel.SUPPORTED_ENTITY_NAMES);

        // Preventing new objects from being created if there are already the maximum number of objects in the environment
        if (this.environment.getEntities().size() < ObjectsPanel.ENTITY_CAP) {
            
            // By default, a sphere is added
            this.environment.addEntity(newSphere);

            // Object components are JPanels that act as the interface between an actual environment Entity and the user
            // Object components can be selected to change various properties in the Materials and Properties panels
            objectPanel.setBorder(ObjectsPanel.UNSELECTED_OBJECT_BORDER);
            objectPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            objectPanel.setBackground(MainWindow.BACKGROUND_COLOR);
            objectPanel.setPreferredSize(new Dimension(this.getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT));
            objectPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // When the object component is selected, select that object
            objectPanel.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mousePressed(MouseEvent event) {

                    int index;
                    Component[] objectPanels;
                    JPanel currentObjectJPanel;

                    if (event.getButton() == MouseEvent.BUTTON1) {

                        index = ObjectsPanel.findComponentIndex(objectPanel);
                        objectPanels = objectPanel.getParent().getComponents();

                        // Unselecting other objectPanels
                        for (Component component : objectPanels) {

                            // There shouldn't be any other components that aren't JPanels within 
                            // the objectArea, but this is just in case
                            if (component instanceof JPanel) {

                                // Deselecting object panel
                                currentObjectJPanel = (JPanel) component;

                                currentObjectJPanel.setBorder(ObjectsPanel.UNSELECTED_OBJECT_BORDER);
                                currentObjectJPanel.setPreferredSize(new Dimension((int) objectPanel.getPreferredSize().getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT));
                                
                                if (currentObjectJPanel.getComponentCount() > 2) {
                                    currentObjectJPanel.remove(2);
                                }
                            }
                        }

                        // Selecting the JPanel
                        objectPanel.setBorder(ObjectsPanel.SELECTED_OBJECT_BORDER);
                        objectPanel.setPreferredSize(new Dimension((int) objectPanel.getPreferredSize().getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT + 2));

                        // Creating a delete button
                        ObjectsPanel.createDeleteButton(objectPanel, objectsArea, environment, mainWindow);

                        // Loading the properties for that object
                        mainWindow.loadProperties(environment.getEntities().get(index)); 
                    }
                }
            });

            // When a object-type drop-down menu registers an item state change, change its corresponding icon and update the environment's entities accordingly
            objectTypeSelector.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent event) {

                    RectangularPrism defaultRectangularPrism = ObjectsPanel.DEFAULT_RECTANGULAR_PRISM;
                    Sphere defaultSphere = ObjectsPanel.DEFAULT_SPHERE;
                    TriangularPrism defaultTriangularPrism = ObjectsPanel.DEFAULT_TRIANGULAR_PRISM;

                    IconPanel iconPanel;
                    int index = 0;

                    // This is necessary to prevent a double-register of the event (one event is fired for deselected and for selected)
                    if (event.getStateChange() == ItemEvent.SELECTED) {

                        index = ObjectsPanel.findComponentIndex(objectPanel);

                        // If a render is currently undergoing, do not modify the environment's objects
                        if (mainWindow.isRendering()) {
                            objectTypeSelector.setSelectedItem(environment.getEntities().get(index).getEntityType().getName());
                            return;
                        }

                        iconPanel = (IconPanel) objectPanel.getComponent(0);
                        
                        if (event.getItem().equals(EntityType.RECTANGULAR_PRISM.getName())) {

                            iconPanel.changeEntityType(EntityType.RECTANGULAR_PRISM);

                            // Creating a new rectangular prism to replace the old entity
                            environment.getEntities().set(index, new RectangularPrism(defaultRectangularPrism.getPosition(), defaultRectangularPrism.getColor(), defaultRectangularPrism.getFuzziness(), defaultRectangularPrism.getReflectiontype(), defaultRectangularPrism.getWidth(), defaultRectangularPrism.getDepth(), defaultRectangularPrism.getHeight()));

                        } else if (event.getItem().equals(EntityType.TRIANGULAR_PRISM.getName())) {

                            iconPanel.changeEntityType(EntityType.TRIANGULAR_PRISM);

                            // Creating a new triangular prism to replace the old entity
                            environment.getEntities().set(index, new TriangularPrism(defaultTriangularPrism.getPosition(), defaultTriangularPrism.getColor(), defaultTriangularPrism.getFuzziness(), defaultTriangularPrism.getReflectiontype(), defaultTriangularPrism.getWidth(), defaultTriangularPrism.getDepth(), defaultTriangularPrism.getHeight()));

                        } else if (event.getItem().equals(EntityType.SPHERE.getName())) {

                            iconPanel.changeEntityType(EntityType.SPHERE);

                            // Creating a new sphere to replace the old entity
                            environment.getEntities().set(index, new Sphere(defaultSphere.getPosition(), defaultSphere.getColor(), defaultSphere.getFuzziness(), defaultSphere.getReflectiontype(), defaultSphere.getRadius()));
                        }

                        // If the current object is currently selected, update the properties panels to reflect the new object
                        if (objectPanel.getBorder() == ObjectsPanel.SELECTED_OBJECT_BORDER) {
                            mainWindow.loadProperties(environment.getEntities().get(index));
                        }
                    }
                }
            });

            objectIcon.setPreferredSize(ObjectsPanel.OBJECT_ICON_DIMENSIONS);

            objectPanel.add(objectIcon);
            objectPanel.add(objectTypeSelector);
            this.objectsArea.add(objectPanel);

            // If this is the first object added to the environment, select it
            if (environment.getEntities().size() == 1) {

                objectPanel.setBorder(ObjectsPanel.SELECTED_OBJECT_BORDER);
                objectPanel.setPreferredSize(new Dimension((int) objectPanel.getPreferredSize().getWidth(), ObjectsPanel.OBJECT_CONTAINER_HEIGHT + 2));
                ObjectsPanel.createDeleteButton(objectPanel, this.objectsArea, this.environment, this.mainWindow);

                mainWindow.loadProperties(newSphere);
            }

            this.revalidate();
            this.repaint();
        }
    }

    // Finds the index of a component within its parent container by comparing references
    // This method should never return one, since a component should always be able to be found within its parent container
    private static int findComponentIndex(JComponent component) {

        for (int i = 0; i < component.getParent().getComponentCount(); i++) {

            // If a component's reference matches the target component
            if (component == component.getParent().getComponent(i)) {
                return i;
            }
        }

        return -1;
    }

    // Creates a delete button for an object component
    // and handles all its relevant behavior
    private static void createDeleteButton(JPanel objectComponent, JPanel objectsArea, Environment environment, MainWindow mainWindow) {

        RoundedButton removeButton = new RoundedButton(15, "Remove", new Color(200, 100, 100), new Color(220, 120, 120), false);

        removeButton.setFont(FontLoader.loadFont("montserrat_medium", 13));
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorder(new EmptyBorder(2, 10, 2, 10));

        removeButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent event) {

                int index;

                if (event.getButton() == MouseEvent.BUTTON1 && !mainWindow.isRendering() && removeButton.mouseIn()) {

                    // Removing the entity
                    index = ObjectsPanel.findComponentIndex((JComponent) removeButton.getParent());

                    environment.removeEntity(index);

                    // Removing the panel
                    objectComponent.getParent().remove(objectComponent);

                    objectsArea.revalidate();
                    objectsArea.repaint();

                    // Resetting properties panels
                    mainWindow.removeProperties();
                }
            }
        });

        objectComponent.add(removeButton);
    }
}
