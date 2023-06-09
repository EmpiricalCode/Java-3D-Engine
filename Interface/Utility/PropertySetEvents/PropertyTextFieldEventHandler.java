///////////////////////
//
// Filename: PropertyEventHandler.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the setting of properties that are related to text fields. De-focusing a property text-field will register the user's input as the property.
//
///////////////////////

package Interface.Utility.PropertySetEvents;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import Core.Entities.RectangularPrism;
import Core.Entities.Sphere;
import Core.Entities.TriangularPrism;
import Core.Structures.Entity;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Interface.Utility.PropertyFormatter;

public class PropertyTextFieldEventHandler implements FocusListener {
    
    private Entity entity;
    private PropertyType propertyType;
    private JTextField textField;

    // Creates a new PropertyTextFieldEventHandler
    public PropertyTextFieldEventHandler(Entity entity, PropertyType propertyType, JTextField textField) {
        this.entity = entity;
        this.propertyType = propertyType;
        this.textField = textField;
    }

    // Handles color property setting
    public void setColor() {

        String modifiedFieldText = PropertyFormatter.formatColor(this.textField.getText());
        ColorRGB originalColor = this.entity.getColor();
        String[] triple;

        if (modifiedFieldText != null) {

            this.textField.setText(modifiedFieldText);

            triple = modifiedFieldText.split(", ");
            this.entity.setColor(new ColorRGB(Integer.valueOf(triple[0]), Integer.valueOf(triple[1]), Integer.valueOf(triple[2])));

        } else {
            this.textField.setText(originalColor.getR() + ", " + originalColor.getG() + ", " + originalColor.getB());
        }
    }

    // Handles position property setting
    public void setPosition() {

        String modifiedFieldText = PropertyFormatter.formatPosition(this.textField.getText());
        Vector3D originalPosition = this.entity.getPosition();
        String[] triple;

        if (modifiedFieldText != null) {

            this.textField.setText(modifiedFieldText);

            triple = modifiedFieldText.split(", ");
            this.entity.setPosition(new Vector3D(Double.valueOf(triple[0]), Double.valueOf(triple[1]), Double.valueOf(triple[2])));

        } else {
            this.textField.setText(originalPosition.getX() + ", " + originalPosition.getY() + ", " + originalPosition.getZ());
        }
    }

    // Handles fuzziness property setting
    public void setFuzziness() {

        String modifiedFieldText = PropertyFormatter.formatFuzziness(this.textField.getText());

        if (modifiedFieldText != null) {

            this.textField.setText(modifiedFieldText);
            this.entity.setFuzziness(Double.valueOf(modifiedFieldText));

        } else {
            this.textField.setText(String.valueOf(this.entity.getFuzziness()));
        }
    }

    // Handles radius property setting
    public void setRadius() {

        String modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());

        if (modifiedFieldText != null) {

            this.textField.setText(modifiedFieldText);
            ((Sphere) this.entity).setRadius(Double.valueOf(modifiedFieldText));
            
        } else {
            this.textField.setText(String.valueOf(((Sphere) this.entity).getRadius()));
        }
    }

    // Handles dimension property setting
    public void setDimensions() {

        // Dimensions uses the same formatting as position, so the formatPosition method can just be reused
        String modifiedFieldText = PropertyFormatter.formatPosition(this.textField.getText());
        String[] modifiedFieldTextSplit;

        if (modifiedFieldText != null) {

            modifiedFieldTextSplit = modifiedFieldText.split(", ");

            this.textField.setText(modifiedFieldText);
            
            entity.setWidth(Double.valueOf(Double.valueOf(modifiedFieldTextSplit[0])));
            entity.setWidth(Double.valueOf(Double.valueOf(modifiedFieldTextSplit[1])));
            entity.setWidth(Double.valueOf(Double.valueOf(modifiedFieldTextSplit[2])));
            
        } else {
            
            this.textField.setText(entity.getWidth() + ", " + entity.getHeight() + ", " + entity.getDepth());
        }
    }

    // Manditory override
    @Override
    public void focusGained(FocusEvent event) {}

    @Override
    // Handles property setting for all text-field related properties
    public void focusLost(FocusEvent event) {

        // Handling property setting for color
        if (this.propertyType == PropertyType.COLOR) {

            this.setColor();
            
        // Handling property setting for position
        } else if (this.propertyType == PropertyType.POSITION) {
            
            this.setPosition();

        // Handling property setting for fuzziness
        } else if (this.propertyType == PropertyType.FUZZINESS) {

            this.setFuzziness();

        // Handling property setting for radius
        } else if (this.propertyType == PropertyType.RADIUS) {

            this.setRadius();

        // Handling property setting for dimensions
        } else if (this.propertyType == PropertyType.DIMENSIONS) {

            this.setDimensions();
        }
    }
}
