package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.*;

import Core.Structures.Entity;
import Core.Utility.Enum.PropertyType;
import Interface.Windows.MainWindow;

public class PropertiesPanel extends JPanel {

    public static final int FIELD_CONTAINER_HEIGHT = 40;
    public static final int BASE_HEIGHT = 110;

    private JPanel titleArea;
    private JLabel propertiesTitle;
    private JLabel propertiesSubtitle;
    private JPanel propertiesArea;
    private MainWindow mainWindow;
    
    public PropertiesPanel(MainWindow mainWindow, int width) {
        super();

        // the MainWindow object is passed in because the MaterialsPanel object must be accessed
        this.mainWindow = mainWindow;

        this.setPreferredSize(new Dimension(width, PropertiesPanel.BASE_HEIGHT));
        this.setLayout(new FlowLayout(0, 0, 0));

        titleArea = new JPanel();
        titleArea.setPreferredSize(new Dimension(width, PropertiesPanel.BASE_HEIGHT));
        titleArea.setBackground(MainWindow.BACKGROUND_COLOR);
        titleArea.setBorder(new EmptyBorder(27, 40, 0, 0));
        titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));

        propertiesTitle = new JLabel("Properties");
        propertiesTitle.setFont(MainWindow.TITLE_FONT);
        propertiesTitle.setForeground(Color.WHITE);

        propertiesSubtitle = new JLabel("Rectangular Prism");
        propertiesSubtitle.setFont(MainWindow.SUBTITLE_FONT);
        propertiesSubtitle.setForeground(MainWindow.SUBTITLE_COLOR);

        propertiesArea = new JPanel();
        propertiesArea.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        propertiesArea.setBorder(new MatteBorder(new Insets(1, 0, 0, 0), MainWindow.BORDER_COLOR));
        propertiesArea.setLayout(new FlowLayout(0, 0, 0));
        propertiesArea.setBackground(MainWindow.BORDER_COLOR);

        titleArea.add(propertiesTitle);
        titleArea.add(propertiesSubtitle);
        this.add(titleArea);
        this.add(propertiesArea);
    }

    // Loads the properties for an entity
    public void loadProperties(Entity entity) {
        
        JPanel fieldContainer; 
        JLabel fieldName;
        JTextField fieldArea;
        PropertyType[] properties = entity.getProperties();
        
        this.setPreferredSize(new Dimension(this.getWidth(), PropertiesPanel.BASE_HEIGHT));

        for (PropertyType property : properties) {

            this.setPreferredSize(new Dimension(this.getWidth(), (int) this.getPreferredSize().getHeight() + PropertiesPanel.FIELD_CONTAINER_HEIGHT));

            fieldContainer = new JPanel();
            fieldContainer.setPreferredSize(new Dimension(this.getWidth(), PropertiesPanel.FIELD_CONTAINER_HEIGHT));
            fieldContainer.setBorder(new MatteBorder(0, 0, 1, 0, MainWindow.BORDER_COLOR));
            fieldContainer.setLayout(new BoxLayout(fieldContainer, BoxLayout.X_AXIS));
            fieldContainer.setBackground(MainWindow.PROPERTIES_COLOR);

            fieldName = new JLabel(property.getName());
            fieldName.setBorder(new EmptyBorder(5, 15, 5, 5));
            fieldName.setFont(MainWindow.PROPERTIES_FONT);
            fieldName.setForeground(Color.WHITE);

            fieldContainer.add(fieldName);
            this.propertiesArea.add(fieldContainer);

            if (property == PropertyType.POSITION) {

            }
        }

        this.mainWindow.materialsPanel.setPreferredSize(new Dimension(this.mainWindow.materialsPanel.getWidth(), MainWindow.HEIGHT - this.getHeight()));

        repaint();
    }
}
