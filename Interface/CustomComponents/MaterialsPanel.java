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
import Interface.Structures.ObjectPropertyPanel;
import Interface.Utility.ListElementLoader;
import Interface.Utility.PropertySetEvents.PropertyTextFieldEventHandler;
import Interface.Windows.MainWindow;

public class MaterialsPanel extends ObjectPropertyPanel {
    
    // Creates a new materials panel
    public MaterialsPanel(int width) {
        super("Materials", width, MainWindow.HEIGHT, 110);
    }

    // Loads the material properties for an entity
    public void loadProperties(Entity entity) {

        PropertyType[] materialProperties = entity.getMaterialProperties();
        JComponent fieldValueComponent;
        
        // Setting material property field name 
        this.getSubtitle().setText(entity.getEntityType().getName());

        // For each property, create a relevant property field
        for (PropertyType property : materialProperties) {

            fieldValueComponent = ListElementLoader.loadListElement(this.getPropertiesArea(), property);

            // Setting initial values
            if (property == PropertyType.COLOR) {
                ((JTextField) fieldValueComponent).setText(entity.getColor().getR() + ", " + entity.getColor().getG() + ", " + entity.getColor().getB());
            } else if (property == PropertyType.FUZZINESS) {
                ((JTextField) fieldValueComponent).setText(String.valueOf(entity.getFuzziness()));
            }

            // Grouping material properties by how they are accessed, and using the relevant listeners to handle setting those properties
            if (property == PropertyType.COLOR || property == PropertyType.FUZZINESS) {
                fieldValueComponent.addFocusListener(new PropertyTextFieldEventHandler(entity, property, fieldValueComponent));
            }
        }

        this.revalidate();
        this.repaint();
    }
}
