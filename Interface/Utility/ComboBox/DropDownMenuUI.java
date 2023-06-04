///////////////////////
//
// Filename: DropDownMenuUI.java
// Author: Daniel Long
// Course: ICS4U1.
// Description: This class provides custom UI to the drop down menu JComboBox that removes some unwanted visual artifacts (border around popup, unusual double borders around the button next to the down-arrow)
//
///////////////////////

package Interface.Utility.ComboBox;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;

public class DropDownMenuUI extends MetalComboBoxUI {
    
    // This empty override is necessary to help remove any unwanted borders or other unwanted artifacts
    @Override 
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {}

    // Removing the popup border
    @Override
    public ComboPopup createPopup() {
        BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
        
        basicComboPopup.setBorder(new EmptyBorder(0, 0, 0, 0));
        return basicComboPopup;
    }
}
