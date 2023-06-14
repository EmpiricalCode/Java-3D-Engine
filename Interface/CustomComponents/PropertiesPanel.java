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
import Interface.Structures.PropertyPanel;
import Interface.Utility.PropertyElementLoader;
import Interface.Utility.EntityPropertySetEvents.PropertyTextFieldEventHandler;
import Interface.Windows.MainWindow;

public class PropertiesPanel extends PropertyPanel {

    public static final int BASE_HEIGHT = 110;

    private MainWindow mainWindow;
    private Entity entity;

    // Creates a new properties panel
    public PropertiesPanel(MainWindow mainWindow, int width) {
        super("Properties", width, PropertiesPanel.BASE_HEIGHT, PropertiesPanel.BASE_HEIGHT);

        this.mainWindow = mainWindow;
    }

    // Selects an entity
    public void loadEntity(Entity entity) {
        this.entity = entity;
    }

    // Loads the properties for an entity
    public void loadProperties() {

        PropertyType[] properties = this.entity.getProperties();
        JComponent fieldValueComponent;
        
        // Setting property field name 
        this.getSubtitle().setText(this.entity.getEntityType().getName());
        this.setPreferredSize(new Dimension(this.getWidth(), PropertiesPanel.BASE_HEIGHT));

        // For each property, create a relevant property field
        for (PropertyType property : properties) {

            // Scale the properties panel with the number of property fields
            this.setPreferredSize(new Dimension(this.getWidth(), (int) this.getPreferredSize().getHeight() + MainWindow.FIELD_CONTAINER_HEIGHT));

            fieldValueComponent = PropertyElementLoader.loadListElement(this.getPropertiesArea(), property);

            // Setting the initial property values
            if (property == PropertyType.POSITION) {

                ((JTextField) fieldValueComponent).setText(this.entity.getPosition().getX() + ", " + this.entity.getPosition().getY() + ", " + this.entity.getPosition().getZ());

            } else if (property == PropertyType.RADIUS) {

                ((JTextField) fieldValueComponent).setText(String.valueOf(((Sphere) this.entity).getRadius()));

            } else if (property == PropertyType.WIDTH) {
                    
                ((JTextField) fieldValueComponent).setText(String.valueOf(this.entity.getWidth()));

            } else if (property == PropertyType.DEPTH) {

                ((JTextField) fieldValueComponent).setText(String.valueOf(this.entity.getDepth()));

            } else if (property == PropertyType.HEIGHT) {

                ((JTextField) fieldValueComponent).setText(String.valueOf(this.entity.getHeight()));
            }

            // All current properties rely on a focus listener to be set, so no if statement is required
            // to determine which listener is necessary

            // However, for scalability reasons, a JComponent is still used to store the field value component even though 
            // all properties currently only use JTextLabels
            fieldValueComponent.addFocusListener(new PropertyTextFieldEventHandler(this.mainWindow, this.entity, property, (JTextField) fieldValueComponent));
        }

        this.revalidate();
        this.repaint();
    }

    // Removes properties
    // This must use an override because the properties panel needs to resize based on how many properties it has
    @Override
    public void removeProperties() {
        super.removeProperties();

        this.setPreferredSize(new Dimension(this.getWidth(), PropertiesPanel.BASE_HEIGHT));
    }
}
