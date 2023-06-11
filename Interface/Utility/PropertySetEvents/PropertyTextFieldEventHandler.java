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

    // Handles color property setting
    public void setColor() {

        String modifiedFieldText = PropertyFormatter.formatColor(this.textField.getText());
        ColorRGB originalColor = this.entity.getColor();
        String[] triple;

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

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

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

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

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

            this.textField.setText(modifiedFieldText);
            this.entity.setFuzziness(Double.valueOf(modifiedFieldText));

        } else {
            this.textField.setText(String.valueOf(this.entity.getFuzziness()));
        }
    }

    // Handles radius property setting
    public void setRadius() {

        String modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

            this.textField.setText(modifiedFieldText);
            ((Sphere) this.entity).setRadius(Double.valueOf(modifiedFieldText));
            
        } else {
            this.textField.setText(String.valueOf(((Sphere) this.entity).getRadius()));
        }
    }

    // Handles width property setting
    public void setWidth() {

        // Dimensions uses the same formatting as radius, so the formatRadius method can just be reused
        String modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

            this.textField.setText(modifiedFieldText);
            
            entity.setWidth(Double.valueOf(Double.valueOf(modifiedFieldText)));
            
        } else {
            
            this.textField.setText(String.valueOf(this.entity.getWidth()));
        }
    }

    // Handles depth property setting
    public void setDepth() {

        // Dimensions uses the same formatting as radius, so the formatRadius method can just be reused
        String modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

            this.textField.setText(modifiedFieldText);
            
            entity.setDepth(Double.valueOf(Double.valueOf(modifiedFieldText)));
            
        } else {
            
            this.textField.setText(String.valueOf(this.entity.getDepth()));
        }
    }

    // Handles height property setting
    public void setHeight() {

        // Dimensions uses the same formatting as radius, so the formatRadius method can just be reused
        String modifiedFieldText = PropertyFormatter.formatRadius(this.textField.getText());

        if (modifiedFieldText != null && !this.mainWindow.isRendering()) {

            this.textField.setText(modifiedFieldText);
            
            entity.setHeight(Double.valueOf(Double.valueOf(modifiedFieldText)));
            
        } else {
            
            this.textField.setText(String.valueOf(this.entity.getHeight()));
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

        // Handling property setting for width
        } else if (this.propertyType == PropertyType.WIDTH) {

            this.setWidth();

        // Handling property setting for depth
        } else if (this.propertyType == PropertyType.DEPTH) {

            this.setWidth();

        // Handling property setting for height
        } else if (this.propertyType == PropertyType.HEIGHT) {

            this.setWidth();
        }
    }
}
