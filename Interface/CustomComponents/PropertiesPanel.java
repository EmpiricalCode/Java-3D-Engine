///////////////////////
//
// Filename: PropertiesPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation and user interactions of the panel that allows the changing of regular object properties.
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Dimension;

import javax.swing.*;

import Core.Entities.Sphere;
import Core.Structures.Entity;
import Core.Utility.Enum.PropertyType;
import Interface.Structures.ObjectPropertyPanel;
import Interface.Utility.ListElementLoader;
import Interface.Utility.PropertyEventHandler;
import Interface.Windows.MainWindow;

public class PropertiesPanel extends ObjectPropertyPanel {

    public static final int BASE_HEIGHT = 110;

    private MainWindow mainWindow;
    
    // Creates a new property panel
    public PropertiesPanel(MainWindow mainWindow, int width) {
        super("Properties", width, PropertiesPanel.BASE_HEIGHT, PropertiesPanel.BASE_HEIGHT);

        // the MainWindow object is passed in because the MaterialsPanel object must be accessed
        this.mainWindow = mainWindow;
    }

    // Loads the properties for an entity
    public void loadProperties(Entity entity) {

        PropertyType[] properties = entity.getProperties();
        JComponent fieldValueComponent;
        
        // Setting property field name 
        this.getSubtitle().setText(entity.getEntityType().getName());
        this.setPreferredSize(new Dimension(this.getWidth(), PropertiesPanel.BASE_HEIGHT));

        // For each property, create a relevant propety field
        for (PropertyType property : properties) {

            // Scale the properties panel with the number of property fields
            this.setPreferredSize(new Dimension(this.getWidth(), (int) this.getPreferredSize().getHeight() + MainWindow.FIELD_CONTAINER_HEIGHT));

            fieldValueComponent = ListElementLoader.loadListElement(this.getPropertiesArea(), property);

            // Grouping properties by how they are accessed, and using the relevant listeners to handle setting those properties
            if (property == PropertyType.POSITION) {

                // Setting initial values
                ((JTextField) fieldValueComponent).setText(entity.getPosition().getX() + ", " + entity.getPosition().getY() + ", " + entity.getPosition().getZ());

                // Adding the correct event listener
                fieldValueComponent.addFocusListener(new PropertyEventHandler(entity, property, fieldValueComponent));

            } else if (property == PropertyType.RADIUS) {

                // Setting initial values
                ((JTextField) fieldValueComponent).setText(String.valueOf(((Sphere) entity).getRadius()));

                // Adding the correct event listener
                fieldValueComponent.addFocusListener(new PropertyEventHandler(entity, property, fieldValueComponent));

            }
        }

        this.mainWindow.materialsPanel.setPreferredSize(new Dimension(this.mainWindow.materialsPanel.getWidth(), MainWindow.HEIGHT - this.getHeight()));
        this.revalidate();
        this.repaint();
    }
}
