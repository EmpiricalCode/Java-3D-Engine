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
import Interface.Utility.PropertyElementLoader;
import Interface.Utility.PropertySetEvents.PropertyTextFieldEventHandler;
import Interface.Windows.MainWindow;

public class PropertiesPanel extends ObjectPropertyPanel {

    public static final int BASE_HEIGHT = 110;

    private MaterialsPanel materialsPanel;
    
    // Creates a new property panel
    public PropertiesPanel(MaterialsPanel materialsPanel, int width) {
        super("Properties", width, PropertiesPanel.BASE_HEIGHT, PropertiesPanel.BASE_HEIGHT);

        // the MaterialsPanel object is passed in because it must be resized based on how many property fields the PropertiesPanel object has
        this.materialsPanel = materialsPanel;
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

            fieldValueComponent = PropertyElementLoader.loadListElement(this.getPropertiesArea(), property);

            // Setting the initial property values
            // Grouping properties by how they are accessed, and using the relevant listeners to handle setting those properties
            if (property == PropertyType.POSITION) {

                // Setting initial values
                ((JTextField) fieldValueComponent).setText(entity.getPosition().getX() + ", " + entity.getPosition().getY() + ", " + entity.getPosition().getZ());

                // Adding the correct event listener
                fieldValueComponent.addFocusListener(new PropertyTextFieldEventHandler(entity, property, (JTextField) fieldValueComponent));

            } else if (property == PropertyType.RADIUS) {

                // Setting initial values
                ((JTextField) fieldValueComponent).setText(String.valueOf(((Sphere) entity).getRadius()));

                // Adding the correct event listener
                fieldValueComponent.addFocusListener(new PropertyTextFieldEventHandler(entity, property, (JTextField) fieldValueComponent));

            }
        }

        materialsPanel.setPreferredSize(new Dimension(materialsPanel.getWidth(), MainWindow.HEIGHT - this.getHeight()));
        this.revalidate();
        this.repaint();
    }
}
