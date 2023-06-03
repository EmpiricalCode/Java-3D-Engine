///////////////////////
//
// Filename: PropertyEventHandler.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles all the necessary events corresponding to when a property is set (ex. unfocusing of a textfield, clicking of a checkbox, etc).
//
///////////////////////

package Interface.Utility;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import Core.Structures.Entity;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;
import Core.Utility.Enum.PropertyType;

public class PropertyEventHandler implements FocusListener {
    
    private Entity entity;
    private PropertyType propertyType;
    private JComponent propertyValueComponent;

    public PropertyEventHandler(Entity entity, PropertyType propertyType, JComponent propertyValueComponent) {
        this.entity = entity;
        this.propertyType = propertyType;
        this.propertyValueComponent = propertyValueComponent;
    }

    @Override
    public void focusGained(FocusEvent event) {}

    @Override
    // Handles property setting for all text-field related properties
    public void focusLost(FocusEvent event) {

        JTextField textField = (JTextField) propertyValueComponent;
        String modifiedFieldText;

        ColorRGB originalColor = this.entity.getColor();
        Vector3D originalPosition = this.entity.getPosition();

        String[] triple;

        // Handling property setting for color
        if (this.propertyType == PropertyType.COLOR) {

            modifiedFieldText = PropertyFormatter.formatTripleInt(textField.getText());

            if (modifiedFieldText != null) {
                textField.setText(modifiedFieldText);
                triple = modifiedFieldText.split(", ");
                entity.setColor(new ColorRGB(Integer.valueOf(triple[0]), Integer.valueOf(triple[1]), Integer.valueOf(triple[2])));
            } else {
                textField.setText(originalColor.getR() + ", " + originalColor.getG() + ", " + originalColor.getB());
            }
            
        // Handling property setting for position
        } else if (this.propertyType == PropertyType.POSITION) {
            
            modifiedFieldText = PropertyFormatter.formatTripleDouble(textField.getText());

            if (modifiedFieldText != null) {
                textField.setText(modifiedFieldText);
                triple = modifiedFieldText.split(", ");
                entity.setPosition(new Vector3D(Double.valueOf(triple[0]), Double.valueOf(triple[1]), Double.valueOf(triple[2])));
            } else {
                textField.setText(originalPosition.getX() + ", " + originalPosition.getY() + ", " + originalPosition.getZ());
            }

        // Handling property setting for fuzziness
        } else if (this.propertyType == PropertyType.FUZZINESS) {

            modifiedFieldText = PropertyFormatter.formatDouble(textField.getText());

            if (modifiedFieldText != null) {
                textField.setText(modifiedFieldText);
                entity.setFuzziness(Double.valueOf(modifiedFieldText));
            } else {
                textField.setText(String.valueOf(entity.getFuzziness()));
            }
        }
    }
}
