///////////////////////
//
// Filename: MaterialsPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation and user interactions of the panel that allows the changing of material properties.
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.*;

import Core.Structures.Entity;
import Core.Utility.Enum.PropertyType;
import Interface.Utility.ListElementLoader;
import Interface.Utility.PropertyEventHandler;
import Interface.Windows.MainWindow;

public class MaterialsPanel extends JPanel {

    private JPanel titleArea;
    private JLabel materialsTitle;
    private JLabel materialsSubtitle;
    private JPanel materialsArea;
    
    // Creates a new materials panel
    public MaterialsPanel(int width) {
        super();

        // Initializing panel
        this.setBorder(new MatteBorder(1, 0, 0, 0, MainWindow.BORDER_COLOR));
        this.setBackground(MainWindow.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        this.setLayout(new FlowLayout(0, 0, 0));

        // Creating panel components
        titleArea = new JPanel();
        titleArea.setPreferredSize(new Dimension(width, 110));
        titleArea.setBackground(MainWindow.BACKGROUND_COLOR);
        titleArea.setBorder(new EmptyBorder(29, 40, 0, 0));
        titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));

        materialsTitle = new JLabel("Materials");
        materialsTitle.setFont(MainWindow.TITLE_FONT);
        materialsTitle.setForeground(Color.WHITE);

        materialsSubtitle = new JLabel("Empty");
        materialsSubtitle.setFont(MainWindow.SUBTITLE_FONT);
        materialsSubtitle.setForeground(MainWindow.SUBTITLE_COLOR);

        materialsArea = new JPanel();
        materialsArea.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        materialsArea.setBorder(new MatteBorder(new Insets(1, 0, 0, 0), MainWindow.BORDER_COLOR));
        materialsArea.setLayout(new FlowLayout(0, 0, 0));
        materialsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        // Adding components
        titleArea.add(materialsTitle);
        titleArea.add(materialsSubtitle);
        this.add(titleArea);
        this.add(materialsArea);
    }

    // Loads the materials for an entity
    public void loadMaterialProperties(Entity entity) {

        PropertyType[] materialProperties = entity.getMaterialProperties();
        JComponent fieldValueComponent;
        
        // Setting material property field name 
        this.materialsSubtitle.setText(entity.getEntityType().getName());

        // For each property, create a relevant property field
        for (PropertyType property : materialProperties) {

            fieldValueComponent = ListElementLoader.loadListElement(this.materialsArea, property);

            // Setting initial values
            if (property == PropertyType.COLOR) {
                ((JTextField) fieldValueComponent).setText(entity.getColor().getR() + ", " + entity.getColor().getG() + ", " + entity.getColor().getB());
            } else if (property == PropertyType.FUZZINESS) {
                ((JTextField) fieldValueComponent).setText(String.valueOf(entity.getFuzziness()));
            }

            // Grouping material properties by how they are accessed, and using the relevant listeners to handle setting those properties
            if (property == PropertyType.COLOR || property == PropertyType.FUZZINESS) {
                fieldValueComponent.addFocusListener(new PropertyEventHandler(entity, property, fieldValueComponent));
            }
        }

        this.revalidate();
        this.repaint();
    }
}
