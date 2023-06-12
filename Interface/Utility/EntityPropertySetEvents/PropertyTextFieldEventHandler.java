///////////////////////
//
// Filename: PropertyTextFieldEventHandler.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the setting of entity properties that are related to text fields. De-focusing a property text-field will register the user's input as the property.
//
///////////////////////

package Interface.Utility.EntityPropertySetEvents;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import Core.Entities.Sphere;
import Core.Structures.Entity;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;
import Core.Utility.Enum.PropertyType;
import Interface.Utility.PropertyFormatter;
import Interface.Windows.MainWindow;

public class PropertyTextFieldEventHandler implements FocusListener {
    
    private Entity entity;
    private PropertyType propertyType;
    private JTextField textField;
    private MainWindow mainWindow;

    // Creates a new PropertyTextFieldEventHandler
    public PropertyTextFieldEventHandler(MainWindow mainWindow, Entity entity, PropertyType propertyType, JTextField textField) {
        this.entity = entity;
        this.propertyType = propertyType;
        this.textField = textField;
        this.mainWindow = mainWindow;
    }

    // Manditory override
    @Override
    public void focusGained(FocusEvent event) {}

    @Override
    // Handles property setting for all text-field related properties
    public void focusLost(FocusEvent event) {

        String modifiedFieldText;
        String[] triple;

        // Handling property setting for color
        if (this.propertyType == PropertyType.COLOR) {

            modifiedFieldText = PropertyFormatter.formatColor(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(this.entity.getColor().toString(), modifiedFieldText, this.textField, this.mainWindow.isRendering());
            
            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                triple = modifiedFieldText.split(", ");
                this.entity.setColor(new ColorRGB(Integer.valueOf(triple[0]), Integer.valueOf(triple[1]), Integer.valueOf(triple[2])));
            }
            
        // Handling property setting for position
        } else if (this.propertyType == PropertyType.POSITION) {
            
            modifiedFieldText = PropertyFormatter.formatPosition(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(this.entity.getPosition().toString(), modifiedFieldText, this.textField, this.mainWindow.isRendering());
            
            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                triple = modifiedFieldText.split(", ");
                this.entity.setPosition(new Vector3D(Double.valueOf(triple[0]), Double.valueOf(triple[1]), Double.valueOf(triple[2])));
            }

        // Handling property setting for fuzziness
        } else if (this.propertyType == PropertyType.FUZZINESS) {

            modifiedFieldText = PropertyFormatter.formatFuzziness(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(String.valueOf(this.entity.getFuzziness()), modifiedFieldText, this.textField, this.mainWindow.isRendering());

            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                this.entity.setFuzziness(Double.valueOf(modifiedFieldText));
            }

        // Handling property setting for radius
        } else if (this.propertyType == PropertyType.RADIUS) {

            modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(String.valueOf(((Sphere) this.entity).getRadius()), modifiedFieldText, this.textField, this.mainWindow.isRendering());

            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                ((Sphere) this.entity).setRadius(Double.valueOf(modifiedFieldText));
            };

        // Handling property setting for width
        } else if (this.propertyType == PropertyType.WIDTH) {

            // Dimensions use same formatting as radius
            modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(String.valueOf(this.entity.getWidth()), modifiedFieldText, this.textField, this.mainWindow.isRendering());

            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                this.entity.setWidth(Double.valueOf(modifiedFieldText));
            }

        // Handling property setting for depth
        } else if (this.propertyType == PropertyType.DEPTH) {

            // Dimensions use same formatting as radius
            modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(String.valueOf(this.entity.getDepth()), modifiedFieldText, this.textField, this.mainWindow.isRendering());

            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                this.entity.setDepth(Double.valueOf(modifiedFieldText));
            }

        // Handling property setting for height
        } else if (this.propertyType == PropertyType.HEIGHT) {

            // Dimensions use same formatting as radius
            modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());
            PropertyTextFieldEventHandler.setProperty(String.valueOf(this.entity.getHeight()), modifiedFieldText, this.textField, this.mainWindow.isRendering());

            if (modifiedFieldText != null && !this.mainWindow.isRendering()) {
                this.entity.setHeight(Double.valueOf(modifiedFieldText));
            }
        }
    }

    // Handles the front-end of property setting
    // Sets or resets the textfield accoring to whether or not the main window is rendering, and if what the user entered was valid
    public static void setProperty(String originalProperty, String modifiedFieldText, JTextField textField, boolean rendering) {

        if (modifiedFieldText != null && !rendering) {
            textField.setText(modifiedFieldText);
        } else {
            textField.setText(originalProperty);
        }
    }
}
