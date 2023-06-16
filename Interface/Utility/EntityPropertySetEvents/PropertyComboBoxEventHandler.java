///////////////////////
//
// Filename: PropertyComboBoxEventHandler.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the setting of entity properties that are related to drop-down menus (JComboBoxes).
//
///////////////////////

package Interface.Utility.EntityPropertySetEvents;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import Core.Structures.Entity;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;
import Interface.Windows.MainWindow;

public class PropertyComboBoxEventHandler implements ItemListener {

    private Entity entity;
    private PropertyType propertyType;
    private JComboBox<?> comboBox;
    private MainWindow mainWindow;
    
    // Creates a new PropertyComboBoxEventHandler
    // a "?" is used to parameterize comboBox because a casted JComponent object is passed into the constructor.
    // Casting the JComponent to JComboBox<String> causes an unchecked cast warning, so "?" is used
    public PropertyComboBoxEventHandler(MainWindow mainWindow, Entity entity, PropertyType propertyType, JComboBox<?> comboBox) {
        this.entity = entity;
        this.propertyType = propertyType;
        this.comboBox = comboBox;
        this.mainWindow = mainWindow;
    }

    // Handling reflection type property setting
    public void setReflectionType(ItemEvent event) {
        
        // Preventing properties from being changed while a render is taking place
        if (this.mainWindow.isRendering()) {
            comboBox.setSelectedItem(entity.getReflectiontype().getName());
            return;
        }

        if (event.getItem().equals(ReflectionType.DIFFUSE.getName())) {

            entity.setReflectiontype(ReflectionType.DIFFUSE);

        // In case more reflection types are added in the future, else if was used instead of else
        } else if (event.getItem().equals(ReflectionType.SPECULAR.getName())) {
            
            entity.setReflectiontype(ReflectionType.SPECULAR);
        }
    }

    // When the JComboBox registers an itemStateChanged event, handle the property change
    @Override
    public void itemStateChanged(ItemEvent event) {

        // This is necessary to prevent a double-register of the event (one event is fired for deselected and for selected)
        if (event.getStateChange() == ItemEvent.SELECTED) {

            // This seemingly unnecessary if statement is for scalability reasons; 
            // in case there are more properties that are set using drop-down menus in the future

            // Handling property setting for reflection type
            if (this.propertyType == PropertyType.REFLECTION_TYPE) {

                this.setReflectionType(event);
            }
        }
    }
}
