///////////////////////
//
// Filename: PropertyElementLoader.java
// Author: Daniel Long
// Course: ICS4U1
// Description: This class loads properties for panels such as the MaterialsPanel, PropertiesPanel, etc, that rely on a table layout to set properties.
//
///////////////////////

package Interface.Utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;

import Core.Utility.Enum.PropertySetType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;
import Interface.Utility.ComboBox.ComboBoxHelper;
import Interface.Windows.MainWindow;

public class PropertyElementLoader {

    // Drop-down menu options
    public static final String[] REFLECTION_TYPE_OPTIONS = {ReflectionType.DIFFUSE.getName(), ReflectionType.SPECULAR.getName()};
    public static final String[] ANTI_ALIASING_OPTIONS = {"True", "False"};

    // Creates a list element and returns the property field component (JTextField, JComboBox)
    public static JComponent loadListElement(JPanel listArea, PropertyType property) {

        JLabel fieldNameComponent;
        JPanel fieldContainerComponent;
        JComponent fieldValueComponent = null;

        // Creating the property field
        fieldContainerComponent = new JPanel();
        fieldContainerComponent.setPreferredSize(new Dimension(listArea.getWidth(), MainWindow.FIELD_CONTAINER_HEIGHT));
        fieldContainerComponent.setBorder(new MatteBorder(0, 0, 1, 0, MainWindow.BORDER_COLOR));
        fieldContainerComponent.setLayout(new GridLayout(1, 2));
        fieldContainerComponent.setBackground(MainWindow.PROPERTIES_COLOR);

        fieldNameComponent = new JLabel(property.getName());
        fieldNameComponent.setBorder(new EmptyBorder(0, 10, 0, 0));
        fieldNameComponent.setFont(MainWindow.PROPERTIES_FONT);
        fieldNameComponent.setForeground(Color.WHITE);

        fieldContainerComponent.add(fieldNameComponent);

        // Creating the relevant JComponents based on how the property is to be modified (drop down menu, text field, etc.)
        if (property.getSetType() == PropertySetType.TEXT_FIELD) {
            
            fieldValueComponent = new JTextField(10);
            fieldValueComponent.setVisible(true);
            fieldValueComponent.setBackground(MainWindow.BACKGROUND_COLOR);
            fieldValueComponent.setForeground(Color.WHITE);
            fieldValueComponent.setFont(MainWindow.PROPERTIES_FONT);
            fieldValueComponent.setBorder(new CompoundBorder(new MatteBorder(0, 1, 0, 0, MainWindow.BORDER_COLOR), new EmptyBorder(0, 10, 0, 0)));
            // Must be cast to JTextField to use setCaretColor
            ((JTextField) fieldValueComponent).setCaretColor(Color.WHITE);

        } else if (property.getSetType() == PropertySetType.DROP_DOWN_MENU) {

            if (property == PropertyType.REFLECTION_TYPE) {
                fieldValueComponent = ComboBoxHelper.createComboBox(PropertyElementLoader.REFLECTION_TYPE_OPTIONS);
            } else if (property == PropertyType.ANTI_ALIASING) {
                fieldValueComponent = ComboBoxHelper.createComboBox(PropertyElementLoader.ANTI_ALIASING_OPTIONS);
            }
        }

        fieldContainerComponent.add(fieldValueComponent);
        listArea.add(fieldContainerComponent);

        return fieldValueComponent;
    }
}
