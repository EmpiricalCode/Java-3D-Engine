package Interface.Utility;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.*;

import Interface.CustomComponents.PropertiesPanel;
import Interface.Windows.MainWindow;

public class ListElementLoader {

    public static void loadListElement(JPanel listArea, String fieldName) {

        JLabel fieldNameComponent;
        JPanel fieldContainerComponent;

        fieldContainerComponent = new JPanel();
        fieldContainerComponent.setPreferredSize(new Dimension(listArea.getWidth(), PropertiesPanel.FIELD_CONTAINER_HEIGHT));
        fieldContainerComponent.setBorder(new MatteBorder(0, 0, 1, 0, MainWindow.BORDER_COLOR));
        fieldContainerComponent.setLayout(new BoxLayout(fieldContainerComponent, BoxLayout.X_AXIS));
        fieldContainerComponent.setBackground(MainWindow.PROPERTIES_COLOR);

        fieldNameComponent = new JLabel(fieldName);
        fieldNameComponent.setBorder(new EmptyBorder(0, 20, 0, 0));
        fieldNameComponent.setFont(MainWindow.PROPERTIES_FONT);
        fieldNameComponent.setForeground(Color.WHITE);

        fieldContainerComponent.add(fieldNameComponent);
        listArea.add(fieldContainerComponent);
    }
}
