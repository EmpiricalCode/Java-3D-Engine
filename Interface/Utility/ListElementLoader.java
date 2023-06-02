package Interface.Utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;

import Core.Utility.Enum.PropertyType;
import Interface.Windows.MainWindow;

public class ListElementLoader {

    // Creates a list element and returns the field component (textfield, etc)
    public static JComponent loadListElement(JPanel listArea, PropertyType property) {

        JLabel fieldNameComponent;
        JComponent fieldValueComponent = null;
        JPanel fieldContainerComponent;

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

        if (property == PropertyType.COLOR || property == PropertyType.POSITION) {
            
            fieldValueComponent = new JTextField(10);
            fieldValueComponent.setVisible(true);
            fieldValueComponent.setBackground(MainWindow.BACKGROUND_COLOR);
            fieldValueComponent.setForeground(Color.WHITE);
            fieldValueComponent.setFont(MainWindow.PROPERTIES_FONT);
            fieldValueComponent.setBorder(new CompoundBorder(new MatteBorder(0, 1, 0, 0, MainWindow.BORDER_COLOR), new EmptyBorder(0, 10, 0, 0)));
            // Must be cast to JTextField to use setCaretColor
            ((JTextField) fieldValueComponent).setCaretColor(Color.WHITE);
            fieldContainerComponent.add(fieldValueComponent);
        }

        listArea.add(fieldContainerComponent);

        return null;
    }
}
