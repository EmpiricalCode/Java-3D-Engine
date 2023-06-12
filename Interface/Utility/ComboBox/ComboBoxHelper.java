///////////////////////
//
// Filename: ComboBoxHelper.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation of custom JComboBoxes.
//
///////////////////////

package Interface.Utility.ComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Interface.Utility.FontLoader;
import Interface.Windows.MainWindow;

public class ComboBoxHelper {
    
    // Creates a new custom JComboBox
    public static JComboBox<String> createComboBox(String[] elements) {
        
        JComboBox<String> comboBox = new JComboBox<String>(elements);
        Component comboBoxComponent;

        comboBox.setUI(new DropDownMenuUI());

        for (int i = 0; i < comboBox.getComponentCount(); i++) {

            comboBoxComponent = comboBox.getComponent(i);

            // Removing combobox borders
            if (comboBoxComponent instanceof JComponent) {
                ((JComponent) comboBoxComponent).setBorder(new EmptyBorder(0, 0, 0, 10)); 
            }
        }

        comboBox.setPreferredSize(new Dimension(175, 35));
        comboBox.setFont(FontLoader.loadFont("montserrat_medium", 15));
        comboBox.setBackground(MainWindow.BACKGROUND_COLOR);
        comboBox.setForeground(Color.WHITE);
        comboBox.setBorder(new MatteBorder(0, 1, 0, 1, new Color(45, 45, 45)));
        comboBox.setRenderer(new DropDownMenuRenderer());
        comboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        return comboBox;
    }
}
