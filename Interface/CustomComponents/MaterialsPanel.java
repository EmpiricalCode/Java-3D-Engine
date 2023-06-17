///////////////////////
//
// Filename: MaterialsPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation and user interactions of the panel that allows the changing of material properties.
//
///////////////////////

package Interface.CustomComponents;

import javax.swing.*;

import Core.Structures.Entity;
import Core.Utility.Enum.PropertyType;
import Interface.Structures.PropertyPanel;
import Interface.Utility.PropertyElementLoader;
import Interface.Utility.EntityPropertySetEvents.PropertyComboBoxEventHandler;
import Interface.Utility.EntityPropertySetEvents.PropertyTextFieldEventHandler;
import Interface.Windows.MainWindow;

public class MaterialsPanel extends PropertyPanel {

    private MainWindow mainWindow;
    private Entity entity;
    
    // Creates a new materials property panel
    public MaterialsPanel(MainWindow mainWindow, int width) {
        super("Materials", width, MainWindow.HEIGHT, 110);

        this.mainWindow = mainWindow;
    }

    // Selects an entity
    public void loadEntity(Entity entity) {
        this.entity = entity;
    }

    // Loads the material properties for an entity
    public void loadProperties() {

        PropertyType[] materialProperties = this.entity.getMaterialProperties();
        JComponent fieldValueComponent;
        
        // Setting material property field name 
        this.getSubtitle().setText(this.entity.getEntityType().getName());

        // For each property, create a relevant property field
        for (PropertyType property : materialProperties) {

            // Creating the relevant fieldValueComponent based on propery type
            fieldValueComponent = PropertyElementLoader.loadListElement(this.getPropertiesArea(), property);

            // Setting initial values
            if (property == PropertyType.COLOR) {
                ((JTextField) fieldValueComponent).setText(this.entity.getColor().getR() + ", " + this.entity.getColor().getG() + ", " + this.entity.getColor().getB());
            } else if (property == PropertyType.FUZZINESS) {
                ((JTextField) fieldValueComponent).setText(String.valueOf(this.entity.getFuzziness()));
            } else if (property == PropertyType.REFLECTION_TYPE) {
                ((JComboBox<?>) fieldValueComponent).setSelectedItem(this.entity.getReflectiontype().getName());
            }

            // One general variable (fieldValueComponent) is used to store the all various JComponents related to setting properties (JComboBox, JTextField),
            // so it must be downcast manually to those subclasses

            // Grouping material properties by how they are accessed, and using the relevant listeners to handle setting those properties
            // Text field properties
            if (property == PropertyType.COLOR || property == PropertyType.FUZZINESS) {

                fieldValueComponent.addFocusListener(new PropertyTextFieldEventHandler(mainWindow, this.entity, property, (JTextField) fieldValueComponent));

            // Drop-down menu properties
            } else if (property == PropertyType.REFLECTION_TYPE) {

                // Must cast to JComboBox to use addItemListener
                ((JComboBox<?>) fieldValueComponent).addItemListener(new PropertyComboBoxEventHandler(this.mainWindow, this.entity, property, (JComboBox<?>) fieldValueComponent));
            }
        }

        this.revalidate();
        this.repaint();
    }
}
